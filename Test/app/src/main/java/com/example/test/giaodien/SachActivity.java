package com.example.test.giaodien;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Spinner;

import com.example.test.DiaLog.DiaLogNguoiDung;
import com.example.test.DiaLog.DiaLogSach;
import com.example.test.R;
import com.example.test.TiengViet;
import com.example.test.adapter.CustomArrayAdapter;
import com.example.test.adapter.NguoiDungAdapter;
import com.example.test.adapter.SachAdapter;
import com.example.test.dao.NguoiDungDAO;
import com.example.test.dao.SachDAO;
import com.example.test.dao.TheLoaiSachDAO;
import com.example.test.mode.NguoiDung;
import com.example.test.mode.Sach;
import com.example.test.mode.TheLoaiSach;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class SachActivity extends AppCompatActivity {
    FloatingActionButton fl_sach;
    public static RecyclerView rc_sach;
    SachAdapter sachadapter;
    ArrayList<Sach> listSach;
    ArrayList<TheLoaiSach> listTheloaisach;
    Spinner spinner;
    Toolbar toolbarsach;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sach);
//        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbarsach);
//        setSupportActionBar(toolbar);
        spinner = findViewById(R.id.spinner_suasach);
        fl_sach = findViewById(R.id.fl_themsach);
        toolbarsach = findViewById(R.id.toolbarsach);

        setSupportActionBar(toolbarsach);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("");
        rc_sach = findViewById(R.id.rVsach); // khai bao rc
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(SachActivity.this); // add sc vao layout
        rc_sach.setLayoutManager(layoutManager);

        listSach = SachDAO.getAll(SachActivity.this); // goi ham getall
        sachadapter = new SachAdapter(SachActivity.this, listSach);
        rc_sach.setAdapter(sachadapter);

        EditText timkiem = (EditText) findViewById(R.id.timkiem);
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
        fl_sach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });
    }
    private void filter(String text) {
        ArrayList<Sach> filteredList = new ArrayList<>();
        for (Sach item : listSach) {
            if (TiengViet.removeAccent(item.getTenSach().toLowerCase()).contains((text.toLowerCase()))) {
                filteredList.add(item);
            }
        }
        sachadapter.filterList(filteredList);
    }
    private void layoutAnimation(RecyclerView rc_sach){
        Context context = rc_sach.getContext();
        LayoutAnimationController layoutAnimationController = AnimationUtils.loadLayoutAnimation(context, R.anim.layout);
        rc_sach.setLayoutAnimation(layoutAnimationController);
        rc_sach.getAdapter().notifyDataSetChanged();
        rc_sach.scheduleLayoutAnimation();

    }
    private void openDialog() {
        DiaLogSach exampleDialog = new DiaLogSach();
        exampleDialog.setCancelable(false);
        exampleDialog.show(getSupportFragmentManager(), "example dialog");
    }

}