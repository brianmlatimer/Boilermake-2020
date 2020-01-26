package com.boilermake.mwen;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import java.util.Objects;

public class AddBathroomActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bathroom);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final com.google.android.material.textfield.TextInputEditText name   = findViewById(R.id.add_bathroom_name_textview);
        final com.google.android.material.textfield.TextInputEditText review = findViewById(R.id.add_bathroom_review_textview);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String path = String.format("/bathroom/%s", Objects.requireNonNull(name.getText()).toString());
                DatabaseReference myRef = database.getReference(path);
                myRef.setValue("");

                @SuppressLint("DefaultLocale")
                DatabaseReference ratingRef = database.getReference(String.format("/rating/\"%s\"/%d",name.getText().toString(),0));
                ratingRef.setValue(new Review("unknown", Objects.requireNonNull(review.getText()).toString()));

                AddBathroomActivity.this.onBackPressed();
            }
        });
    }

}
