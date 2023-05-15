package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Workout3Activity extends AppCompatActivity {
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
        setContentView(R.layout.activity_workout3);

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