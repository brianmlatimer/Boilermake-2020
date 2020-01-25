package com.boilermake.mwen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    String[] items = {"item 0", "item 1", "item 2", "item 3", "item 4", "item 5", "item 6", "item 7", "item 8", "item 9", "item 10"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        RecyclerView rv = findViewById(R.id.recyclerview);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new adapter(this, items));

//        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.main_tool_bar);
//        setSupportActionBar(toolbar);

    }
}
