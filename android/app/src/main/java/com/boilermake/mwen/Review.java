package com.boilermake.mwen;

import androidx.annotation.NonNull;

public class Review {

    public String username;
    public String review;

    public Review(String username, String review) {
        this.username = username;
        this.review = review;
    }

    @NonNull
    @Override
    public String toString() {
        return String.format("{\"username\": \"%s\", \"review\": \"%s\"}", username, review);
    }
}
