package com.boilermake.mwen;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

import static android.webkit.ConsoleMessage.MessageLevel.LOG;

public class BathroomActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bathroom);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Api Request
        ArrayList<Review> list = new ArrayList<>();
        list.add(new Review("mwen", "good"));
        list.add(new Review("mwen", "bad"));
        list.add(new Review("mwen", "hey nick"));
        list.add(new Review("what", "hey nick"));
        list.add(new Review("zach", "hey nick"));
        list.add(new Review("eww", "okay"));
        list.add(new Review("bahh", "lol"));
        list.add(new Review("bahh", "ad;fjal;s jfadsjf;l as;fj a;lfjd;sa j;"));
        list.add(new Review("bahh", "ad;fjal;s jfadsjf;l as;fj a;lfjd;sa j;"));
        list.add(new Review("bahh", "ad;fjal;s jfadsjf;l as;fj a;lfjd;sa j;"));
        list.add(new Review("bahh", "ad;fjal;s jfadsjf;l as;fj a;lfjd;sa j;"));
        list.add(new Review("bahh", "ad;fjal;s jfadsjf;l as;fj a;lfjd;sa j;"));
        list.add(new Review("bahh", "ad;fjal;s jfadsjf;l as;fj a;lfjd;sa j;"));

        Intent intent = getIntent();
        Bathroom b = null;
        try {
            JSONObject obj = new JSONObject(Objects.requireNonNull(intent.getStringExtra("obj")));
            b   = new Bathroom(obj);
            Log.v("ACTIVITY_BATHROOM", "hi");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        assert b != null;
        TextView bar_text_view = findViewById(R.id.bathroom_tool_bar_textview);
        bar_text_view.setText(b.name);


        RecyclerView rv = findViewById(R.id.review_recycle_view);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new ReviewAdapter(this, list));

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
    }

}
