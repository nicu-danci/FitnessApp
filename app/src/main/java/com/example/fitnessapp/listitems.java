package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Locale;

public class listitems extends AppCompatActivity {
    FirebaseAuth auth;
    ImageButton button;
    TextView textView;
    FirebaseUser user;
    String email;
    int atIndex;
    String emailWithoutDomain;

    private Button startTimerButton;
    private CountDownTimer countDownTimer;
    private TextView countdownTextView;
    private long timeLeftInMillis;//= 120000; // 2 minute in milliseconds
    private boolean timerRunning;
    private long userInputInMillis;

    TextView name;
TextView description;
ImageView image;
TextView desc;
ImageView gifImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listitems);

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

        // initialize variables
        startTimerButton = findViewById(R.id.start_timer_button);
        countdownTextView = findViewById(R.id.countdown_text_view);
        name = findViewById(R.id.listitems);
        image = findViewById(R.id.imageView);
        description = findViewById(R.id.description);
        gifImageView = findViewById(R.id.pushupgif);
        desc = findViewById(R.id.list_desc);
        Intent intent = getIntent();

        name.setText(intent.getStringExtra("name"));
        image.setImageResource(intent.getIntExtra("image",0));
        description.setText(intent.getStringExtra("description"));
        gifImageView.setImageResource(intent.getIntExtra("gif",0));
        desc.setText(intent.getStringExtra("desc"));

        // set OnClickListener to startTimerButton
        startTimerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (timerRunning) {
                    // do nothing if the timer is already running
                    return;
                }
                startTimer();
            }
        });
    }

    private void startTimer() {

        EditText timerInputEditText = findViewById(R.id.time_input);
        String timerInputString = timerInputEditText.getText().toString();
        if (timerInputString.isEmpty()) {

            // prompt the user to insert time
            Toast.makeText(getApplicationContext(), "Please insert workout time", Toast.LENGTH_SHORT).show();
            return;

        }

        long userInputInMillisTemp = Long.parseLong(timerInputString) * 1000;
        if (timerRunning) {
            userInputInMillis = timeLeftInMillis;
        } else {
            userInputInMillis = userInputInMillisTemp;
        }

        timeLeftInMillis = userInputInMillis;
        timerInputEditText.setVisibility(View.GONE);

        // create a new CountDownTimer with the desired timeLeftInMillis and interval
        countDownTimer = new CountDownTimer(userInputInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateCountdownText();
            }

            @Override
            public void onFinish() {
                // do something when the timer finishes
                timerRunning = false;
                startTimerButton.setText("Congratulations, you finished the workout!");
            }
        };

        // start the timer and set timerRunning to true
        countDownTimer.start();
        timerRunning = true;
        startTimerButton.setText("Pause workout");

        // set OnClickListener to startTimerButton again to cancel the timer
        startTimerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (timerRunning) {
                    countDownTimer.cancel();
                    timerRunning = false;
                    startTimerButton.setText("Resume workout");
                } else {
                    countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            timeLeftInMillis = millisUntilFinished;
                            updateCountdownText();
                        }

                        @Override
                        public void onFinish() {
                            // do something when the timer finishes
                            timerRunning = false;
                            startTimerButton.setText("Congratulations, you finished the workout!");
                        }
                    };

                    countDownTimer.start();
                    timerRunning = true;
                    startTimerButton.setText("Pause workout");

                }
            }
        });

    }

    private void updateCountdownText() {
        // convert the timeLeftInMillis to a formatted string and update the countdownTextView
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;
        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        countdownTextView.setText(timeLeftFormatted);


    }
}