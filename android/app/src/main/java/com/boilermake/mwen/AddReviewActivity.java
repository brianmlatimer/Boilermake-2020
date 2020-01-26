package com.boilermake.mwen;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.FileProvider;
import androidx.fragment.app.DialogFragment;

import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class AddReviewActivity extends AppCompatActivity {

    Bathroom bathroom;
    String currentPhotoPath = null;
    private static final int REQUEST_IMAGE_CAPTURE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_review);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();

        String obj_tostring = intent.getStringExtra("obj");
        final long count        = intent.getLongExtra("count", 0);
        try {
            assert obj_tostring != null;
            JSONObject obj = new JSONObject(obj_tostring);
            bathroom = new Bathroom(obj);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        final com.google.android.material.textfield.TextInputEditText input = findViewById(R.id.add_bathroom_name_textview);
        FloatingActionButton fb = findViewById(R.id.fab_add_review);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final long finalCount = count;
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                @SuppressLint("DefaultLocale")
                DatabaseReference ref = database.getReference(String.format("/rating/\"%s\"/%d",bathroom.name , (int) finalCount));
                ref.setValue(new Review((int) finalCount, "anonymous", Objects.requireNonNull(input.getText()).toString()));
                assert getFragmentManager() != null;
                AddReviewActivity.this.onBackPressed();
            }
        });

        MaterialButton button = findViewById(R.id.material_button_take_pic);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    File photo = null;
                    try {
                        // add the name here
                        photo = createImageFile();
                    } catch (IOException ex) {
                        // catch error
                    }

                    if (photo != null) {
                        Uri photoUri = FileProvider.getUriForFile(AddReviewActivity.this, "com.boilermake.mwen", photo);
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
                    }
                }

            }
        });


        @SuppressLint("CutPasteId")
        FloatingActionButton fab = findViewById(R.id.fab_add_review);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                @SuppressLint("DefaultLocale")
                String root = String.format("/rating/\"%s\"/%d",bathroom.name,(int) count);
                DatabaseReference ratingRef = database.getReference(root);
                ratingRef.setValue(new Review((int) count, "Anonymous", Objects.requireNonNull(input.getText()).toString()));

                // upload picture
//                StorageReference storageRef = storage.getReference();
//                StorageReference mountainsRef = storageRef.child("mountains.jpg");
//                StorageReference mountainImagesRef = storageRef.child("images/mountains.jpg");
//                mountainsRef.getName().equals(mountainImagesRef.getName());    // true
//                mountainsRef.getPath().equals(mountainImagesRef.getPath());    // false

                AddReviewActivity.this.onBackPressed();
            }
        });
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        @SuppressLint("SimpleDateFormat")
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imageFileName, ".jpg", storageDir);

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
        }
    }

}
