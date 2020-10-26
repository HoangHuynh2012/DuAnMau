package com.example.test.giaodien;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.test.DiaLog.DiaLogHoaDon;
import com.example.test.DiaLog.DiaLogHoaDonChiTiet;
import com.example.test.R;
import com.example.test.adapter.HoaDonAdapter;
import com.example.test.adapter.HoaDonChiTietAdapter;
import com.example.test.dao.HoaDonChiTietDAO;
import com.example.test.dao.HoaDonDAO;
import com.example.test.mode.HoaDon;
import com.example.test.mode.HoaDonChiTiet;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class HoaDonChiTietActivity extends AppCompatActivity {
    public static RecyclerView rc_hoadonchiitet;
    FloatingActionButton fl_hoadon;
    HoaDonChiTietAdapter hoaDonAdapter;
    ArrayList<HoaDonChiTiet> list_hoadonchitiet;
    Toolbar toolbarhd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoa_don_chi_tiet);
        rc_hoadonchiitet = findViewById(R.id.rVhoadonchitiet); // khai bao rc
        fl_hoadon = findViewById(R.id.fl_themhoadonchitiet);
        toolbarhd = findViewById(R.id.toolbarhoadon);
        setSupportActionBar(toolbarhd);
        TextView tennguoimua = findViewById(R.id.tennguoimua);

        Intent intent = ((Activity) HoaDonChiTietActivity.this).getIntent();
        String a = intent.getStringExtra("name");
        tennguoimua.setText(a);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(HoaDonChiTietActivity.this); // add sc vao layout
        rc_hoadonchiitet.setLayoutManager(layoutManager);

        Integer b = Integer.valueOf(intent.getStringExtra("mahoadon"));
        list_hoadonchitiet = HoaDonChiTietDAO.getAll(HoaDonChiTietActivity.this,b); // goi ham getall
        hoaDonAdapter = new HoaDonChiTietAdapter(HoaDonChiTietActivity.this, list_hoadonchitiet);
        rc_hoadonchiitet.setAdapter(hoaDonAdapter);
        fl_hoadon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             openDialog();
            }
        });
    }
    private void openDialog() {
        DiaLogHoaDonChiTiet exampleDialog = new DiaLogHoaDonChiTiet();
        exampleDialog.setCancelable(false);
        exampleDialog.show(getSupportFragmentManager(), "example dialog");
    }
}