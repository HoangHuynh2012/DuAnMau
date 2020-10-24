package com.example.test.giaodien;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Toast;

import com.example.test.R;
import com.example.test.adapter.SachAdapter;
import com.example.test.adapter.ThongKeAdapter;
import com.example.test.dao.SachDAO;
import com.example.test.dao.ThongKeDAO;
import com.example.test.mode.HoaDon;
import com.example.test.mode.Sach;

import java.util.ArrayList;

public class ThongKeActivity extends AppCompatActivity {
    public static RecyclerView rc_thongke;
    ThongKeAdapter thongKeAdapter;
    ArrayList<HoaDon> listHoadon;
    Toolbar toolbartk;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_ke);

        toolbartk = findViewById(R.id.toolbarthongke);
        setSupportActionBar(toolbartk);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        rc_thongke = findViewById(R.id.rVthongke);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ThongKeActivity.this); // add sc vao layout
        rc_thongke.setLayoutManager(layoutManager);


        listHoadon = ThongKeDAO.tongTien(ThongKeActivity.this); // goi ham getall
        thongKeAdapter = new ThongKeAdapter(ThongKeActivity.this, listHoadon);
        rc_thongke.setAdapter(thongKeAdapter);



    }
}