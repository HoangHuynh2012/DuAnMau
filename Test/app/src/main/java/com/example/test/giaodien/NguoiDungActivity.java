package com.example.test.giaodien;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;


import com.example.test.DiaLog.DiaLogNguoiDung;
import com.example.test.MainActivity;
import com.example.test.R;
import com.example.test.adapter.NguoiDungAdapter;
import com.example.test.dao.NguoiDungDAO;
import com.example.test.mode.NguoiDung;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class NguoiDungActivity extends AppCompatActivity {
    FloatingActionButton fl_nguoidung;
    public static RecyclerView rc;
    NguoiDungAdapter adapter;
    ArrayList<NguoiDung> list;
    Toolbar toolbarnd;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nguoi_dung);
        toolbarnd = findViewById(R.id.toolbarnguoidung);
        fl_nguoidung = findViewById(R.id.fl_themnguoidung);
        rc = findViewById(R.id.recyclerView); // khai bao rc
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(NguoiDungActivity.this); // add sc vao layout
        rc.setLayoutManager(layoutManager);
        setSupportActionBar(toolbarnd);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        list = NguoiDungDAO.getAll(NguoiDungActivity.this); // goi ham getall
        adapter = new NguoiDungAdapter(NguoiDungActivity.this, list);
        rc.setAdapter(adapter);
        fl_nguoidung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });

//        View logoView = getToolbarLogoIcon(toolbarnd);
//        logoView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(NguoiDungActivity.this, MainActivity.class);
//                startActivity(intent);
//            }
//        });


    }

    private void openDialog() { // goi dialog vao
        DiaLogNguoiDung exampleDialog = new DiaLogNguoiDung(); // dialog dat ten la gi thi goi vao
        exampleDialog.setCancelable(false);
        exampleDialog.show(getSupportFragmentManager(), "example dialog");
    }

    //    public void openDiaLogEdit(){
//        DiaLogEditNguoiDung diaLogEditNguoiDung = new DiaLogEditNguoiDung();
//        diaLogEditNguoiDung.setCancelable(false);
//        diaLogEditNguoiDung.show(getSupportFragmentManager(),"example diglogedit");
//    }
//    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
//    public static View getToolbarLogoIcon(Toolbar toolbar) {
//        //check if contentDescription previously was set
//        boolean hadContentDescription = android.text.TextUtils.isEmpty(toolbar.getLogoDescription());
//        String contentDescription = String.valueOf(!hadContentDescription ? toolbar.getLogoDescription() : "logoContentDescription");
//        toolbar.setLogoDescription(contentDescription);
//        ArrayList<View> potentialViews = new ArrayList<View>();
//        //find the view based on it's content description, set programatically or with android:contentDescription
//        toolbar.findViewsWithText(potentialViews, contentDescription, View.FIND_VIEWS_WITH_CONTENT_DESCRIPTION);
//        //Nav icon is always instantiated at this point because calling setLogoDescription ensures its existence
//        View logoIcon = null;
//        if (potentialViews.size() > 0) {
//            logoIcon = potentialViews.get(0);
//        }
//        //Clear content description if not previously present
//        if (hadContentDescription)
//            toolbar.setLogoDescription(null);
//        return logoIcon;
//    }

}