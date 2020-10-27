package com.example.test.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.test.Loading;
import com.example.test.MainActivity;
import com.example.test.Password.MatKhauActivity;
import com.example.test.R;
import com.example.test.dao.NguoiDungDAO;
import com.example.test.database.Database;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class LoginActivity extends AppCompatActivity {
    Button btn1;
    CheckBox chkStatus;
    public static User USER = null;
    EditText tk, mk;
    public static String a;
    Database database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toast.makeText(LoginActivity.this, "‍", Toast.LENGTH_SHORT).show();
        btn1 = (Button) findViewById(R.id.btn1);
        final Loading loading = new Loading(LoginActivity.this);
        tk = findViewById(R.id.tkdangnhap);
        mk = findViewById(R.id.mkdangnhap);
        chkStatus = findViewById(R.id.chk);
        loadData();
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loading.startLoading();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loading.dismissDialog();
                        checktk();
                    }
                }, 2000);
            }
        });
    }

    void checktk() {
        a = tk.getText().toString();
        String b = mk.getText().toString();
        boolean check = chkStatus.isChecked();
        User user = new User(tk, mk);
        if (NguoiDungDAO.checkLogin(LoginActivity.this, a, b)) {
            //
            Intent x = new Intent(LoginActivity.this, MainActivity.class);
            x.putExtra("taikhoandadangnhap", a);
            database.user= a;
            luuTT(a, b, check);
            USER = user;
//            Bundle bundle = new Bundle();
            startActivity(x);
            finish();
        } else if (a.equals("") && b.equals("")) {
            Toast.makeText(LoginActivity.this, "Vui Lòng Nhập Tài Khoản Và Mật Khẩu", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(LoginActivity.this, "Sai Tài Khoản Và Mật Khẩu", Toast.LENGTH_LONG).show();
        }
    }

    public void luuTT(String tk, String mk, boolean check) {
        SharedPreferences sh = getSharedPreferences("data.txt", MODE_PRIVATE);
        SharedPreferences.Editor editor = sh.edit();
        boolean chk = chkStatus.isChecked();
        if (!chk) {
            editor.clear();
        } else {
            editor.putString("username", tk);
            editor.putString("password", mk);
            editor.putBoolean("savestatus", check);
        }
        editor.commit();

    }

    private void loadData() {
        SharedPreferences pref = getSharedPreferences("data.txt", MODE_PRIVATE);
        boolean check = pref.getBoolean("savestatus", false);
        if (check) {
            tk.setText(pref.getString("username", ""));
            mk.setText(pref.getString("password", ""));
            chkStatus.setChecked(check);
        }

    }
}