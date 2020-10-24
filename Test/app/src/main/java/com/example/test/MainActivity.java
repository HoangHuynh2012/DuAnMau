package com.example.test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.test.Login.LoginActivity;
import com.example.test.Password.MatKhauActivity;
import com.example.test.giaodien.HoaDonActivity;
import com.example.test.giaodien.NguoiDungActivity;
import com.example.test.giaodien.SachActivity;
import com.example.test.giaodien.TheLoaiSachActivity;
import com.example.test.giaodien.ThongKeActivity;
import com.example.test.giaodien.TopSachActivity;


public class MainActivity extends AppCompatActivity {
    String taikhoandadangnhap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ImageView icon_nguoidung = (ImageView) findViewById(R.id.icon_nguoi);
        Intent intent = getIntent();
        taikhoandadangnhap = intent.getStringExtra("taikhoandadangnhap");

        icon_nguoidung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent x = new Intent(MainActivity.this, NguoiDungActivity.class);
                startActivity(x);
            }
        });

        ImageView icon_sach = (ImageView) findViewById(R.id.icon_sach);

        icon_sach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent y = new Intent(MainActivity.this, SachActivity.class);
                startActivity(y);
            }
        });
        ImageView icon_theloaisach = (ImageView) findViewById(R.id.icon_theloaisach);

        icon_theloaisach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent z = new Intent(MainActivity.this, TheLoaiSachActivity.class);
                startActivity(z);
            }
        });

        ImageView icon_hoadon = (ImageView) findViewById(R.id.icon_hoadon);
        icon_hoadon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent c = new Intent(MainActivity.this,HoaDonActivity.class);
                startActivity(c);
            }
        });

        ImageView icon_thongke = (ImageView) findViewById(R.id.icon_thongke);
        icon_thongke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent e = new Intent(MainActivity.this, ThongKeActivity.class);
                startActivity(e);
            }
        });

        ImageView icon_topsach = (ImageView) findViewById(R.id.icon_topsach);
        icon_topsach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent d = new Intent(MainActivity.this, TopSachActivity.class);
                startActivity(d);
            }
        });
    }

    private void setSupportActionBar(Toolbar toolbar) {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menumain, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.doimk) {
            Intent i = new Intent(MainActivity.this, MatKhauActivity.class);
            i.putExtra("tentaikhoan",taikhoandadangnhap);
            startActivity(i);
        }
        else  if(id == R.id.thongtin) {
            Toast.makeText(this, "ĐANG XÂY DỰNG", Toast.LENGTH_SHORT).show();
            Intent l = new Intent(MainActivity.this, ThongTinActivity.class);
            startActivity(l);

        }
        else if (id == R.id.exit) {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(MainActivity.this, "Bye Bye (¬_¬ )", Toast.LENGTH_LONG).show();
                    finish();
                }
            },1500);

        } else if (id == R.id.dangxuat) {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(MainActivity.this, "Đang Đăng Xuất (¬_¬ )", Toast.LENGTH_LONG).show();
                    Intent x = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(x);
                    finish();
                }
            },1000);

        }
        return true;
    }
}