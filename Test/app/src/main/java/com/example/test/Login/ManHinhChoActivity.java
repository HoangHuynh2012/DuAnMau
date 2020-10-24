package com.example.test.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.test.R;

public class ManHinhChoActivity extends AppCompatActivity {
   ImageView imageView;
   TextView textView;
   Animation top,bottom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_cho);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        imageView = findViewById(R.id.immanhinhcho);
        textView = findViewById(R.id.textViewmhc);

        bottom = AnimationUtils.loadAnimation(this,R.anim.bottom);
        top = AnimationUtils.loadAnimation(this,R.anim.top);
        imageView.setAnimation(top);
        textView.setAnimation(bottom);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(ManHinhChoActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        },3500);

    }
}