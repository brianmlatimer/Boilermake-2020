package com.boilermake.mwen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // api call make array list
        ArrayList<Bathroom> bathrooms = new ArrayList<>();
        bathrooms.add(new Bathroom("Bathroom 0"));
        bathrooms.add(new Bathroom("Bathroom 1"));
        bathrooms.add(new Bathroom("Bathroom 2"));
        bathrooms.add(new Bathroom("Bathroom 3"));
        bathrooms.add(new Bathroom("Bathroom 4"));
        bathrooms.add(new Bathroom("Bathroom 5"));
        bathrooms.add(new Bathroom("Bathroom 6"));
        bathrooms.add(new Bathroom("Bathroom 7"));
        bathrooms.add(new Bathroom("Bathroom 8"));
        bathrooms.add(new Bathroom("Bathroom 9"));
        bathrooms.add(new Bathroom("Bathroom 10"));
        bathrooms.add(new Bathroom("Bathroom 11"));
        bathrooms.add(new Bathroom("Bathroom 12"));
        bathrooms.add(new Bathroom("Bathroom 13"));
        bathrooms.add(new Bathroom("Bathroom 14"));

        RecyclerView rv = findViewById(R.id.recyclerview);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new BathroomAdapter(this, bathrooms));

        final com.google.android.material.floatingactionbutton.FloatingActionButton fb = findViewById(R.id.floating_action_button);
        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    fb.hide();
                }
                else {
                    fb.show();
                }
            }
        });

        final Intent add_bathroom = new Intent(this, AddBathroomActivity.class);
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(add_bathroom);
            }
        });
    }
}
