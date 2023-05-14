package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


public class DayThree3Activity extends AppCompatActivity {

    ListView listView;
    String [] itemnames = {"Goblet squat","Step-ups", "Single leg deadlifts","Plank to downward dog ","Prone X"};

    String[] itemdesc = {"15 reps - 3 sets or 2 minutes ","30 reps - 3 sets or 4 minutes", "20 reps - 3 sets","15 reps - 3 sets or 2 min","2 min"};

    String[] description = {"In a goblet squat, you'll hold the weight in front of your chest with both hands. As you squat down, your elbows will track between your knees while the weight follows. In a back squat, a bar is racked on your upper back. As you squat down, the bar will drop straight down, too.",
            "Perform step-ups by standing in front of an elevated, knee-height surface like a plyometric box or bench. Place your right foot on top of the elevated surface and push through your right leg to lift your body up onto it. Step down slowly with your left leg and perform the next repetition leading with your left foot.",
            "The single-leg deadlift (SLDL) is an exercise characterized by a forward hip-hinge movement while lifting one leg off the ground and extending it backwards. Single-leg deadlifts work muscle groups in your posterior chain, including your calf muscles, lower back muscles, hamstrings, and glutes.",
            "Start in a forward fold and walk your hands out in front of you until you arrive in a plank. From there shift into downward dog. Return to plank and walk your hands back toward your feet to go back into a forward fold. Then unfold into an upright standing position, or walk yourself back to plank.",
            "This exercise is good for the posterior (rear) delts as well as the back muscles. Be sure that your core and glutes are engaged throughout the movement. Focus on using your shoulder blades to pull the arms out to the side. And, like the Plank-To-Down Dog, this is not a race – slower and more controlled is better and safer!",};
    int [] itemimages = {R.drawable.goblinsquats,R.drawable.stepups,R.drawable.singleleg,R.drawable.planktodownarddog,R.drawable.pronex};

    int [] itemgif = {R.drawable.gobletsquatsgif,R.drawable.stepupsgif,R.drawable.singleleggif,R.drawable.planktodownarddoggif,R.drawable.pronexgif};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_one3);

        listView = findViewById(R.id.activity_day_one_3);

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

