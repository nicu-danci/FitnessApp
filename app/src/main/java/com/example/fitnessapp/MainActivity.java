package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    // Declare  views
    FirebaseAuth auth;
    ImageButton button;
    TextView textView;
    FirebaseUser user;
    String email;
    int atIndex;
    String emailWithoutDomain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();
        button = findViewById(R.id.btnLogout);
        textView = findViewById(R.id.user);
        user = auth.getCurrentUser();

        if (user==null) {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            finish();
        } else {
            email = user.getEmail();
            atIndex = email.indexOf('@'); // find the index of the "@" character
            emailWithoutDomain = email.substring(0, atIndex); // extract the email address part
            textView.setText(emailWithoutDomain); // set the text of the TextView to the email address without the domain
        }

        button.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            finish();
        });

        Toolbar toolbar =findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ConstraintLayout myLayout = findViewById(R.id.workout3days);
        myLayout.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Workout3Activity.class);
            startActivity(intent);
        });

        ConstraintLayout myLayout4 = findViewById(R.id.contactUs);
        myLayout4.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ContactActivity.class);
            startActivity(intent);
        });

        ConstraintLayout myLayout3 = findViewById(R.id.workoutSchedule);
        myLayout3.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ScheduleActivity.class);
            startActivity(intent);
        });

        ConstraintLayout myLayout2 = findViewById(R.id.nutrition);
        myLayout2.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, NutritionActivity.class);
            startActivity(intent);
        });
    }
}