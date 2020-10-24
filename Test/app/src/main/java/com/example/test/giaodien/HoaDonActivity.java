package com.example.test.giaodien;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.EditText;

import com.example.test.DiaLog.DiaLogHoaDon;
import com.example.test.R;
import com.example.test.adapter.HoaDonAdapter;
import com.example.test.dao.HoaDonDAO;
import com.example.test.mode.HoaDon;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class HoaDonActivity extends AppCompatActivity {
    public static RecyclerView rc_hoadon;
    FloatingActionButton fl_hoadon;
    HoaDonAdapter hoaDonAdapter;
    ArrayList<HoaDon> list_hoadon;
    Toolbar toolbarhd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoa_don);
        rc_hoadon = findViewById(R.id.rVhoadon); // khai bao rc
        fl_hoadon = findViewById(R.id.fl_themhoadon);
        toolbarhd = findViewById(R.id.toolbarhoadon);
        setSupportActionBar(toolbarhd);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(HoaDonActivity.this); // add sc vao layout
        rc_hoadon.setLayoutManager(layoutManager);

        list_hoadon = HoaDonDAO.getAll(HoaDonActivity.this); // goi ham getall
        hoaDonAdapter = new HoaDonAdapter(HoaDonActivity.this, list_hoadon);
        rc_hoadon.setAdapter(hoaDonAdapter);
        fl_hoadon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });
        EditText timkiem = (EditText) findViewById(R.id.timkiemhoadon);
        timkiem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());
            }
        });
    }
    private void filter(String text) {
        ArrayList<HoaDon> filteredList = new ArrayList<>();
        for (HoaDon item : list_hoadon) {
            if (String.valueOf(item.getMahoadon()).toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }
        hoaDonAdapter.filterList(filteredList);
    }
    private void openDialog() {
        DiaLogHoaDon exampleDialog = new DiaLogHoaDon();
        exampleDialog.setCancelable(false);
        exampleDialog.show(getSupportFragmentManager(), "example dialog");
    }
    private void layoutAnimation(RecyclerView rc_hoadon){
        Context context = rc_hoadon.getContext();
        LayoutAnimationController layoutAnimationController = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_2);
        rc_hoadon.setLayoutAnimation(layoutAnimationController);
        rc_hoadon.getAdapter().notifyDataSetChanged();
        rc_hoadon.scheduleLayoutAnimation();

    }
}