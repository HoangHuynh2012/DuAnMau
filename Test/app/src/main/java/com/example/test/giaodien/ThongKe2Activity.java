package com.example.test.giaodien;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.example.test.R;
import com.example.test.dao.ThongKeDAO;
import com.example.test.mode.HoaDonChiTiet;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

public class ThongKe2Activity extends AppCompatActivity {
    Toolbar toolbartk;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_ke2);

        toolbartk = findViewById(R.id.toolbarthongke);
        setSupportActionBar(toolbartk);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        rc_thongke = findViewById(R.id.rVthongke);
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ThongKeActivity.this); // add sc vao layout
//        rc_thongke.setLayoutManager(layoutManager);


//        listHoadon = ThongKeDAO.tongTien(ThongKeActivity.this); // goi ham getall
//        thongKeAdapter = new ThongKeAdapter(ThongKeActivity.this, listHoadon);
//        rc_thongke.setAdapter(thongKeAdapter);

//        NumberFormat format = new NumberFormat.Field()

        PieChart pieChart = findViewById(R.id.pieChart1);
        ArrayList<HoaDonChiTiet> listHoadon = new ArrayList<HoaDonChiTiet>();
        listHoadon = ThongKeDAO.tongTien(ThongKe2Activity.this); // goi ham getall
//        thongKeAdapter = new ThongKeAdapter(ThongKeActivity.this, listHoadon);
        ArrayList<PieEntry> list = new ArrayList<>();
        for (int j = 0; j < listHoadon.size(); j++) {
            Integer soluong = (int) (listHoadon.get(j).getSoluong());
            DecimalFormat decimalFormat = new DecimalFormat("0");
            Integer result = Integer.valueOf(decimalFormat.format(Double.valueOf(soluong)));
            list.add(new PieEntry(Integer.valueOf(result), String.valueOf(listHoadon.get(j).getTensach())));
        }
//        list.add(new PieEntry(100, "2016"));
//        list.add(new PieEntry(200, "2017"));
//        list.add(new PieEntry(300, "2018"));
//        list.add(new PieEntry(400, "2019"));
//        list.add(new PieEntry(500, "2020"));

        PieDataSet pieDataSet = new PieDataSet(list, "Thống Kê");
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieDataSet.setValueTextColor(Color.BLACK);
        pieDataSet.setValueTextSize(16f);

        PieData pieData = new PieData(pieDataSet);

        pieChart.setData(pieData);
        pieChart.getDescription().setEnabled(false);
        pieChart.setCenterText("Thống Kê Theo Số Lượng");
        pieChart.animate();

    }
}