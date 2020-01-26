package com.boilermake.mwen;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class ReviewReturn {

    public int id;
    public String username;
    public String review;
    public int starCount = 0;
    public Map<String, Boolean> stars = new HashMap<>();

    public ReviewReturn() {
        // Default constructor required for calls to DataSnapshot.getValue(Post.class)
    }

    public ReviewReturn(int id, String username, String review) {
        this.id = id;
        this.username = username;
        this.review = review;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("id", id);
        result.put("username", username);
        result.put("review", review);
        result.put("starCount", starCount);
        result.put("stars", stars);

        return result;
    }

}