package com.boilermake.mwen;

import androidx.annotation.NonNull;

import org.json.JSONException;
import org.json.JSONObject;

public class Bathroom {

    String name;

    public Bathroom() {
        this.name = "";
    }

    public Bathroom(String name) {
        this.name = name;
    }

    public Bathroom(JSONObject obj) throws JSONException {
        this.name = obj.getString("name");
    }

    @NonNull
    @Override
    public String toString() {
        return String.format("{\"name\": \"%s\"}", name);
    }
}
