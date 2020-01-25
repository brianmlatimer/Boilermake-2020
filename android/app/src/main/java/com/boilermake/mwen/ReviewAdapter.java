package com.boilermake.mwen;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ReviewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        Context context;
        ArrayList<Review> items;
        public ReviewAdapter(Context context, ArrayList<Review> items) {
                this.context = context;
                this.items   = items;
        }
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                LayoutInflater inflater = LayoutInflater.from(context);
                View row = inflater.inflate(R.layout.review_layout, parent, false);
                Item item = new Item(row);

                return item;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
            Item i = (Item) holder;
            i.usernameTextView.setText(items.get(position).username);
            i.reviewTextView.setText(items.get(position).review);

        }

        @Override
        public int getItemCount() {
                return items.size();
        }

        public class Item extends RecyclerView.ViewHolder {
            View itemView;
            TextView usernameTextView;
            TextView reviewTextView;
            public Item(@NonNull View itemView) {
                super(itemView);
                this.itemView = itemView;
                usernameTextView = itemView.findViewById(R.id.username);
                reviewTextView   = itemView.findViewById(R.id.review_bathroom);
            }
        }
}
