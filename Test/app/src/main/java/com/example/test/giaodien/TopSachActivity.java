package com.example.test.giaodien;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.media.audiofx.AudioEffect;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.test.R;
import com.example.test.adapter.ThongKeAdapter;
import com.example.test.adapter.TopSachAdapter;
import com.example.test.dao.ThongKeDAO;
import com.example.test.mode.HoaDon;
import com.example.test.mode.HoaDonChiTiet;
import com.example.test.mode.TopSach;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class TopSachActivity extends AppCompatActivity {

    ArrayList<HoaDonChiTiet> listHoadon;
    Toolbar toolbartop;
    ArrayList<TopSach> list = new ArrayList<>();
    BarChart barChart;
    ArrayList<BarEntry> listBartChart;
    ArrayList<String> lablesName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_sach);
//        ImageView imageView = findViewById(R.id.ivtopsach);
        toolbartop = findViewById(R.id.toolbartopsach);
        setSupportActionBar(toolbartop);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        BarChart barChart = findViewById(R.id.barchart);
        //
        listBartChart = new ArrayList<>();
        lablesName = new ArrayList<>();
        //
//        listBartChart.clear();
//        lablesName.clear();
        addlist();
        for (int x = 0;x<list.size();x++){
            String tensach = list.get(x).getTensach();
            int soluong = list.get(x).getSoluong();
            listBartChart.add(new BarEntry(x,soluong));
            lablesName.add(tensach);
        }

        BarDataSet barDataSet = new BarDataSet(listBartChart,"Top Sách");
        barDataSet.setColors(ColorTemplate.VORDIPLOM_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);

        barDataSet.setValueTextSize(20f);
        Description description = new Description();
        description.setText("Tên Sách");
        barChart.setDescription(description);

        BarData barData = new BarData(barDataSet);

        barChart.setFitBars(true);
        barChart.setData(barData);
        barChart.getDescription().setText("Top Sách");


        //
        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(lablesName));

        xAxis.setPosition(XAxis.XAxisPosition.TOP);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(false);
        xAxis.setGranularity(1f);
        xAxis.setLabelCount(lablesName.size());
        xAxis.setLabelRotationAngle(270);
        barChart.animateY(2000);
        barChart.invalidate();


    }

    public void addlist(){
        listHoadon = new ArrayList<HoaDonChiTiet>();
        listHoadon = ThongKeDAO.top(TopSachActivity.this); // goi ham getall
        for (int j = 0; j < listHoadon.size(); j++) {
            int soluong = listHoadon.get(j).getSoluong();
            String tensach = listHoadon.get(j).getTensach();
            list.add(new TopSach(tensach,soluong));
        }
    }

}