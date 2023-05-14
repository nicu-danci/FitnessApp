package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;

public class Workout3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout3);

        ConstraintLayout myLayout = findViewById(R.id.dayone3d);
        myLayout.setOnClickListener(v -> {
            Intent intent = new Intent(Workout3Activity.this, DayOne3Activity.class);
            startActivity(intent);
        });


        ConstraintLayout myLayout2 = findViewById(R.id.daytwo3d);
        myLayout2.setOnClickListener(v -> {
            Intent intent = new Intent(Workout3Activity.this, DayTwo3Activity.class);
            startActivity(intent);
        });

        ConstraintLayout myLayout3 = findViewById(R.id.daythree3d);
        myLayout3.setOnClickListener(v -> {
            Intent intent = new Intent(Workout3Activity.this, DayThree3Activity.class);
            startActivity(intent);
        });



    }
}