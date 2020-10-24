package com.example.test.giaodien;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import com.example.test.DiaLog.DiaLogNguoiDung;
import com.example.test.DiaLog.DiaLogTheLoai;
import com.example.test.R;
import com.example.test.adapter.SachAdapter;
import com.example.test.adapter.TheLoaiSachAdapter;
import com.example.test.dao.SachDAO;
import com.example.test.dao.TheLoaiSachDAO;
import com.example.test.mode.Sach;
import com.example.test.mode.TheLoaiSach;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class TheLoaiSachActivity extends AppCompatActivity {
    FloatingActionButton fl_theloaisach;
    public static RecyclerView rc_theloaisach;
    TheLoaiSachAdapter theloaiadapter;
    ArrayList<TheLoaiSach> listTheloai;
    Toolbar toolbartl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_loai_sach);
        fl_theloaisach = findViewById(R.id.fl_themtheloaisach);
        toolbartl = findViewById(R.id.toolbartheloai);
        setSupportActionBar(toolbartl);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        rc_theloaisach = findViewById(R.id.rVtheloaisach); // khai bao rc
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(TheLoaiSachActivity.this); // add sc vao layout
        rc_theloaisach.setLayoutManager(layoutManager);
        listTheloai = TheLoaiSachDAO.getAll(TheLoaiSachActivity.this); // goi ham getall
        theloaiadapter = new TheLoaiSachAdapter(TheLoaiSachActivity.this, listTheloai);
        rc_theloaisach.setAdapter(theloaiadapter);

        fl_theloaisach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });
    }
    private void layoutAnimation(RecyclerView rc_theloaisach){
        Context context = rc_theloaisach.getContext();
        LayoutAnimationController layoutAnimationController = AnimationUtils.loadLayoutAnimation(context, R.anim.layout);
        rc_theloaisach.setLayoutAnimation(layoutAnimationController);
        rc_theloaisach.getAdapter().notifyDataSetChanged();
        rc_theloaisach.scheduleLayoutAnimation();

    }
    private void openDialog() {
        DiaLogTheLoai exampleDialog = new DiaLogTheLoai();
        exampleDialog.setCancelable(false);
        exampleDialog.show(getSupportFragmentManager(), "example dialog");
    }
}