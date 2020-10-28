package com.example.test.giaodien;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.test.R;
import com.example.test.adapter.SachAdapter;
import com.example.test.adapter.ThongKeAdapter;
import com.example.test.dao.HoaDonChiTietDAO;
import com.example.test.dao.SachDAO;
import com.example.test.dao.ThongKeDAO;
import com.example.test.mode.HoaDon;
import com.example.test.mode.HoaDonChiTiet;
import com.example.test.mode.Sach;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class ThongKeActivity extends AppCompatActivity {
    public static RecyclerView rc_thongke;
    ThongKeAdapter thongKeAdapter;
    ArrayList<HoaDonChiTiet> listHoadon;
    Toolbar toolbartk;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_ke);

        toolbartk = findViewById(R.id.toolbarthongke);
        TextView tongsotienban = findViewById(R.id.tongsotienban);
        setSupportActionBar(toolbartk);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Button btn_chuyen = findViewById(R.id.btn_chuyen);

        btn_chuyen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent x = new Intent(ThongKeActivity.this, ThongKe2Activity.class);
                startActivity(x);
            }
        });

//        rc_thongke = findViewById(R.id.rVthongke);
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ThongKeActivity.this); // add sc vao layout
//        rc_thongke.setLayoutManager(layoutManager);


//        listHoadon = ThongKeDAO.tongTien(ThongKeActivity.this); // goi ham getall
//        thongKeAdapter = new ThongKeAdapter(ThongKeActivity.this, listHoadon);
//        rc_thongke.setAdapter(thongKeAdapter);


        PieChart pieChart = findViewById(R.id.pieChart);
        ArrayList<HoaDonChiTiet> listHoadon = new ArrayList<HoaDonChiTiet>();
        listHoadon = ThongKeDAO.tongTien(ThongKeActivity.this); // goi ham getall
//        thongKeAdapter = new ThongKeAdapter(ThongKeActivity.this, listHoadon);
        ArrayList<PieEntry> list = new ArrayList<>();
        for (int j = 0; j < listHoadon.size(); j++) {
            int tongtien = listHoadon.get(j).getTongtien();
            list.add(new PieEntry(tongtien, listHoadon.get(j).getTensach()));
        }
//        list.add(new PieEntry(100, "2016"));
//        list.add(new PieEntry(200, "2017"));
//        list.add(new PieEntry(300, "2018"));
//        list.add(new PieEntry(400, "2019"));
//        list.add(new PieEntry(500, "2020"));

        PieDataSet pieDataSet = new PieDataSet(list, "Thống Kê");
        pieDataSet.setColors(ColorTemplate.PASTEL_COLORS);
        pieDataSet.setValueTextColor(Color.BLACK);
        pieDataSet.setValueLineColor(Color.BLUE);
        pieDataSet.setValueTextSize(14f);

        PieData pieData = new PieData(pieDataSet);

        pieChart.setData(pieData);
        pieChart.getDescription().setEnabled(false);
        pieChart.setCenterText("Thống Kê Theo Tổng Tiền (VNĐ)");
        pieChart.animate();

        ArrayList<HoaDonChiTiet> listthongke = new ArrayList<>();
        listthongke = HoaDonChiTietDAO.getThongKe(ThongKeActivity.this);
        for (int i =0 ;i<listthongke.size();i++){
            Integer sotien = listthongke.get(i).getTongsotien();
            tongsotienban.setText("Tổng Số Tiền Nhận Được : " + String.valueOf(sotien) + " VND");
        }

    }


}