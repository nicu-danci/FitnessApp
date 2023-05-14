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


public class DayOne3Activity extends AppCompatActivity {

    ListView listView;
    String [] itemnames = {"Push-ups","Incline push-ups", "Decline push-ups","Narrow Push-Ups","Bench Dip", "Pike Push-Up"};

    String[] itemdesc = {"20 reps - 3 sets or 2 minutes ","20 reps - 3 sets or 2 minutes", "20 reps - 3 sets","15 reps - 3 sets or 2 min","15 reps - 3 sets or 2 min", "15 reps - 3 sets or 2 min"};

    String[] description = {"Keeping a prone position, with the hands palms down under the shoulders, the balls of the feet on the ground, and the back straight, pushes the body up and lets it down by an alternate straightening and bending of the arms.",
                            "An incline pushup is an elevated form of a traditional pushup. Your upper body is elevated with an exercise box or other piece of equipment.",
                            "The decline pushup is a variation of the basic pushup. It's done with your feet on an elevated surface, which puts your body at a downward angle.",
                            "The narrow push-up is a variation of the push-up that has a bit more of an emphasis on the triceps than the chest muscles. This makes it one of the best bodyweight exercises for triceps.",
                            "The bench dip is a simple bodyweight tricep exercise that you can perform at home with a bench or sofa or in the park using a park bench. This triceps exercise also engages the front deltoid muscle (the front of the shoulders) as well.",
                            "The pike push-up is an intermediate- to advanced-level bodyweight tricep exercise that targets the tricep and the deltoid muscles."};
    int [] itemimages = {R.drawable.pushups,R.drawable.incline_push_ups,R.drawable.decline_push_ups,R.drawable.narrowpushups,R.drawable.benchdip,R.drawable.pikepushup};

    int [] itemgif = {R.drawable.pushup,R.drawable.pushupincline,R.drawable.pushupdecline,R.drawable.narrowpushupsgif,R.drawable.benchdipgif,R.drawable.pikepushupgif};


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

