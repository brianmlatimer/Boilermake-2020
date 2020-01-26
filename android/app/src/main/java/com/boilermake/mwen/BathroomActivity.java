package com.boilermake.mwen;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

public class BathroomActivity extends AppCompatActivity {

    long count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bathroom);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Api Request
        final ArrayList<Review> list = new ArrayList<>();
        final ReviewAdapter adapter = new ReviewAdapter(this, list);

        Intent intent = getIntent();
        Bathroom b = null;
        try {
            JSONObject obj = new JSONObject(Objects.requireNonNull(intent.getStringExtra("obj")));
            b   = new Bathroom(obj);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        assert b != null;


        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        String path = String.format("/rating/\"%s\"/", b.name);
        DatabaseReference myRef = database.getReference(path);

        TextView bar_text_view = findViewById(R.id.bathroom_tool_bar_textview);
        bar_text_view.setText(b.name);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.v("GOOD", "good " + dataSnapshot.getChildrenCount());
                adapter.items.clear();
                count = dataSnapshot.getChildrenCount();
                for (DataSnapshot i: dataSnapshot.getChildren()) {
                    ReviewReturn r = i.getValue(ReviewReturn.class);
                    assert r != null;
                    Log.v("HEY", r.username);
                    list.add(new Review(r));
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        RecyclerView rv = findViewById(R.id.review_recycle_view);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);

        final com.google.android.material.floatingactionbutton.FloatingActionButton fb = findViewById(R.id.add_review_to_toliet_fab);
        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Log.v("MAIN", "dy: " + dy);
                if (dy > 0) {
                    fb.hide();
                }
                else {
                    fb.show();
                }
            }
        });

        final Bathroom finalB = b;
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddReviewFragment dialog = new AddReviewFragment();
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                dialog.show(ft, AddReviewFragment.TAG);
                Bundle bundle = new Bundle();
                bundle.putString("obj", finalB.toString());
                bundle.putLong("count", count);
                dialog.setArguments(bundle);
            }
        });
    }

}
