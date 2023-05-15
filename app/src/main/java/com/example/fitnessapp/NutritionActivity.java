package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
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


public class NutritionActivity extends AppCompatActivity {
    FirebaseAuth auth;
    ImageButton button;
    TextView textView;
    FirebaseUser user;
    String email;
    int atIndex;
    String emailWithoutDomain;
    ListView listView;
    String [] itemnames = {"Watter","Chicken protein","Fish Oil"};

    String[] itemdesc = {"Importance of Watter","Chicken protein - the best","Fish oil is high in the omega-3 fats EPA and DHA"};

    String[] description = {"Keeping a prone position, with the hands palms down under the shoulders, the balls of the feet on the ground, and the back straight, pushes the body up and lets it down by an alternate straightening and bending of the arms.",
            "An incline pushup is an elevated form of a traditional pushup. Your upper body is elevated with an exercise box or other piece of equipment.",
            "Fish oil is high in the omega-3 fats EPA and DHA"};

    String[] itemLinks = {"https://www.bda.uk.com/resource/the-importance-of-hydration.html", "https://www.healthline.com/nutrition/protein-in-chicken", "https://www.healthline.com/nutrition/fish-oil-bodybuilding"};
    int [] itemimages = {R.drawable.watter,R.drawable.chicken,R.drawable.fish};




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_one3);
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
        listView = findViewById(R.id.activity_day_one_3);

        CustomAdapter customAdapter = new CustomAdapter();
        listView.setAdapter(customAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedLink = itemLinks[position];
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(selectedLink));
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

