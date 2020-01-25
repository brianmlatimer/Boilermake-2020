package com.boilermake.mwen;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;

public class AddBathroomActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bathroom);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final com.google.android.material.textfield.TextInputEditText name   = findViewById(R.id.add_bathroom_name_textview);
        final com.google.android.material.textfield.TextInputEditText review = findViewById(R.id.add_bathroom_review_textview);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddBathroomActivity.this.onBackPressed();
                Log.v("ADD", "name: " + name.getText() + ", review: " + review.getText());
            }
        });
    }

}
