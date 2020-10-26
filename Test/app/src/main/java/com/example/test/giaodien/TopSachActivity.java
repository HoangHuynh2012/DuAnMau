package com.example.test.giaodien;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.animation.ObjectAnimator;
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

import java.util.ArrayList;

public class TopSachActivity extends AppCompatActivity {
    public static RecyclerView rc_topsach;
    TopSachAdapter topsachadapter;
    ArrayList<HoaDonChiTiet> listHoadon;
    Toolbar toolbartop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_sach);
        ImageView imageView = findViewById(R.id.ivtopsach);
        toolbartop = findViewById(R.id.toolbartopsach);
        setSupportActionBar(toolbartop);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ObjectAnimator aim = ObjectAnimator.ofFloat(imageView, "translationY", -500f, 0f);
        aim.setDuration(2000);
        aim.start();
        rc_topsach = findViewById(R.id.rctopsach);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(TopSachActivity.this); // add sc vao layout
        rc_topsach.setLayoutManager(layoutManager);

        ObjectAnimator aim2 = ObjectAnimator.ofFloat(rc_topsach, "translationY", 500f, 0f);
        aim2.setDuration(2000);
        aim2.start();

        listHoadon = ThongKeDAO.top(TopSachActivity.this); // goi ham getall
        topsachadapter = new TopSachAdapter(TopSachActivity.this, listHoadon);
        rc_topsach.setAdapter(topsachadapter);
    }
}