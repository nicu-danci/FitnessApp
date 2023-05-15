package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class LogoScreen extends AppCompatActivity {

    Animation logo_start,logo_finish;
    ImageView imageView;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo_screen);


        ImageView imageView =findViewById(R.id.logo);
        logo_start = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.logo_start);
        imageView.setAnimation(logo_start);

        TextView textView =findViewById(R.id.textLogo);
        logo_finish = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.logo_finish);
        textView.setAnimation(logo_finish);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                finish();
            }
        },3500);
    }
}