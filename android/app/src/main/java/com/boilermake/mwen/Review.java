package com.boilermake.mwen;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;

public class Review {

    public int id;
    public String username;
    public String review;

    public Review(String username, String review) {
        this.id       = 0;
        this.username = username;
        this.review   = review;
    }

    public Review(int id, String username, String review) {
        this.id = id;
        this.username = username;
        this.review = review;
    }

    public Review(ReviewReturn r) {
        this.id = r.id;
        this.username = r.username;
        this.review = r.review;
    }

    @NonNull
    @Override
    public String toString() {
        @SuppressLint("DefaultLocale")
        String var =  String.format("{\"username\": \"%s\", \"review\": \"%s\", \"id\": %d}", username, review, id);
        return var;
    }
}
