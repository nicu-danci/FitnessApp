package com.example.fitnessapp;

import android.os.AsyncTask;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class ContactActivity extends AppCompatActivity {

    // Declare the views
    private EditText editTextEmail;
    private EditText editTextSubject;
    private EditText editTextMessage;
    private Button buttonSend;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        // Initialize the views
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextSubject = findViewById(R.id.editTextSubject);
        editTextMessage = findViewById(R.id.editTextMessage);
        buttonSend = findViewById(R.id.buttonSend);

        // Set the click listener for the send button
        buttonSend.setOnClickListener(v -> {
            // Start the SendMailTask to send the email
            String email = editTextEmail.getText().toString();
            String subject = editTextSubject.getText().toString();
            String message = editTextMessage.getText().toString();
            new SendMailTask(email, subject, message).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        });
    }

    // Inner class to send the email in the background
    private class SendMailTask extends AsyncTask<String, Void, Boolean> {
        private String mEmail;
        private String mSubject;
        private String mMessage;

        public SendMailTask(String email, String subject, String message) {
            mEmail = email;
            mSubject = subject;
            mMessage = message;
        }

        @Override
        protected Boolean doInBackground(String... params) {
            String email = params[0];
            String subject = params[1];
            String message = params[2];

            // Create a properties object to configure the SMTP connection
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");

            javax.mail.Session session = javax.mail.Session.getInstance(props, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("project_tests@yahoo.com", "projecttests");
                }
            });

            // Create a new message
            Message msg = new MimeMessage(session);

            try {
                // Set the sender and recipient
                msg.setFrom(new InternetAddress(email));
                msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("danci0489@gmail.com", false));

                // Set the subject and message body
                msg.setSubject(subject);
                msg.setText(message);

                // Send the message
                Transport.send(msg);

                return true;
            } catch (MessagingException e) {
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean success) {
            if (success) {
                Toast.makeText(ContactActivity.this, "Email sent", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(ContactActivity.this, "Failed to send email", Toast.LENGTH_SHORT).show();
            }
        }
    }
}