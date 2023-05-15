package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class DayTwo3Activity extends AppCompatActivity {
    // Declare  views
    FirebaseAuth auth;
    ImageButton button;
    TextView textView;
    FirebaseUser user;
    String email;
    int atIndex;
    String emailWithoutDomain;

    ListView listView;
    String [] itemnames = {"Superman","Dolphin kick", "Plank arm lifts","Biceps push-ups","Door frame body weight Curls"};

    String[] itemdesc = {"2 minutes ","3 minutes", "1 minutes","15 reps - 3 sets or 2 min","20 reps - 3 sets or 3 min"};

    String[] description = {"Lie on the floor in a prone (facedown) position, with your legs straight and your arms extended in front of you. Keeping your head in a neutral position (avoid looking up), slowly lift your arms and legs around 6 inches (15.3 cm) off the floor, or until you feel your lower back muscles contracting.",
            "How to: Position yourself face down on a bench so that the crease of your hip is at the end of the bench. Your feet should be resting on the ground with your hands firmly engaged on the underside of the bench for support. Straighten out your legs while raising them up while engaging your abdominals, glutes," +
                         " hips and spinal erectors in your lower back. Your toes should be pointed away from your body and above your head at the top of the movement. Hold this static position for 5 seconds by firmly engaging nearly every muscle in your body. Then drop the feet slightly below the bench and contracting again for additional reps.",
            "Begin in the plank position, face down with your forearms and toes on the floor. Your elbows are directly under your shoulders and your forearms are facing forward. Your head is relaxed and you should be looking at the floor." +
                    "Whilst maintaining this strong plank position, shift your weight slightly to your right and then lift up your left arm up to shoulder height so that it is reaching out in front of you. Aim to keep your hips and shoulders level whilst balancing on one arm. Then return your left hand to the floor below your shoulder. Shift your weight over to the left and then raise your right arm up and out in front of you. ",
            "Get in a standard push-up position with your hands slightly wider than shoulder-width apart.Turn your hands by rotating your wrists to the outside so that your fingertips are pointing as directly as possible toward your feet. Depending on your wrist mobility, there might be some amount of angle remaining." +
                    "Perform a push-up as you normally would, bending your elbows to lower your chest towards the floor, hovering just above the ground before pressing through the palms of your hands to return to the starting position. ",
            "Rather than pulling yourself up per se, you're placing your feet against the base of a door frame, or pillar, or tree, and pulling your body into it. This is an excellent low-impact, low-intensity exercise to begin developing muscle in your back and biceps, and is excellent for first-timers.",
            };
    int [] itemimages = {R.drawable.superman,R.drawable.dolphinkick,R.drawable.plankarmlift,R.drawable.bicepspushups,R.drawable.doorcurls};

    int [] itemgif = {R.drawable.supermangif,R.drawable.dolphinkickgif,R.drawable.plankarmliftgif,R.drawable.bicepspushupsgif,R.drawable.doorcurlsgif};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_two3);

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

        listView = findViewById(R.id.activity_day_two_3);

        CustomAdapter customAdapter = new CustomAdapter();
        listView.setAdapter(customAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                Intent intent = new Intent(getApplicationContext(),listitems.class);
                intent.putExtra("name", itemnames[position]);
                intent.putExtra("image", itemimages[position]);
                intent.putExtra("desc", itemdesc[position]);
                intent.putExtra("description",description[position]);
                intent.putExtra("gif",itemgif[position]);
                startActivity(intent);
            }
        });





    }

    private class CustomAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return itemnames.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view1 = getLayoutInflater().inflate(R.layout.item_list,null);

            TextView name =view1.findViewById(R.id.list_title);
            TextView description =view1.findViewById(R.id.list_desc);
            ImageView image = view1.findViewById(R.id.images);


            name.setText(itemnames[position]);
            image.setImageResource(itemimages[position]);
            description.setText(itemdesc[position]);


            return view1;
        }
    }
}

