package com.example.test.Password;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.test.Login.LoginActivity;
import com.example.test.Login.User;
import com.example.test.MainActivity;
import com.example.test.R;
import com.example.test.dao.NguoiDungDAO;
import com.example.test.mode.NguoiDung;

public class MatKhauActivity extends AppCompatActivity {
    Button btnhuy, btndoimk;
    NguoiDungDAO nguoiDungDAO;
    EditText tk, mk, mkmoi, mkmoi2;
    public static User USER = null;
    String c, d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mat_khau);
        btnhuy = findViewById(R.id.huy);
        btndoimk = findViewById(R.id.luutd);
        tk = (EditText) findViewById(R.id.tk);
        mk = (EditText) findViewById(R.id.mk);
        mkmoi = (EditText) findViewById(R.id.mkmoi);
        mkmoi2 = (EditText) findViewById(R.id.mkmoi2);
        String tentaikhoan = getIntent().getStringExtra("tentaikhoan");

        tk.setText(tentaikhoan);


        btndoimk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkDoimk();
            }
        });

        btnhuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MatKhauActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

    }
//
//    public void checkmkmoi() {
//        if (c.equals(d)) {
//            Intent x = new Intent(MatKhauActivity.this, MainActivity.class);
//            Toast.makeText(MatKhauActivity.this, "Đã Đổi Mật Khẩu", Toast.LENGTH_LONG).show();
//            startActivity(x);
//        } else {
//            Toast.makeText(MatKhauActivity.this, "Hai Mật Khẩu Mới Phải Trùng Nhau", Toast.LENGTH_LONG).show();
//        }
//    }

    public void checkDoimk() {
        User user = new User(tk, mk);
        String taikhoan = tk.getText().toString();
        String matkhau = mk.getText().toString();
        String matkhaumoi2 = mkmoi2.getText().toString();
        String matkhaumoi = mkmoi.getText().toString();
        //code clear nhat cai qua dat nay roi
        if (matkhaumoi.equals("") && matkhaumoi2.equals("")) {
            Toast.makeText(MatKhauActivity.this, "Không Để Trống Mật Khẩu Mới Và Nhập Lại Mật Khẩu Mới", Toast.LENGTH_LONG).show();
            finish();
        }
        try {
            if (!matkhaumoi.equals("") && !matkhaumoi2.equals("")) {
                if (matkhaumoi.equals(matkhaumoi2)) {
                    if (nguoiDungDAO.checkLogin(MatKhauActivity.this, taikhoan, matkhau)) {
                        USER = user;
                        NguoiDung nguoiDung = new NguoiDung(taikhoan, matkhaumoi);
                        nguoiDungDAO = new NguoiDungDAO();
                        nguoiDungDAO.updatematkhau(MatKhauActivity.this, nguoiDung);
                        Toast.makeText(MatKhauActivity.this, "Đã Đổi Mật Khẩu", Toast.LENGTH_SHORT).show();
                        finish();
                    } else if (taikhoan.equals("") && matkhau.equals("")) {
                        Toast.makeText(MatKhauActivity.this, "Vui Lòng Nhập Tài Khoản Và Mật Khẩu", Toast.LENGTH_LONG).show();
                        finish();
                    } else {
                        Toast.makeText(MatKhauActivity.this, "Sai Tài Khoản Và Mật Khẩu", Toast.LENGTH_LONG).show();
                        finish();
                    }
                } else {
                    Toast.makeText(MatKhauActivity.this, "Hai Mật Khẩu Mới Phải Trùng Nhau", Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        } catch (Exception e1) {
            Log.i("abc", matkhaumoi);
            Log.i("abcd", matkhaumoi2);

        }

    }
}



