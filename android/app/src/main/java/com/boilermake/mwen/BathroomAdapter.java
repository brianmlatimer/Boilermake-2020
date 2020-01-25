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

public class BathroomAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        Context context;
        ArrayList<Bathroom> items;
        public BathroomAdapter(Context context, ArrayList<Bathroom> items) {
                this.context = context;
                this.items   = items;
        }
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                LayoutInflater inflater = LayoutInflater.from(context);
                View row = inflater.inflate(R.layout.bathroom_layout, parent, false);
                Item item = new Item(row);

                return item;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
            Item i = (Item) holder;
            i.textView.setText(items.get(position).name);
            i.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, BathroomActivity.class);
                    intent.putExtra("obj", items.get(position).toString());
                    context.startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
                return items.size();
        }

        public class Item extends RecyclerView.ViewHolder {
            View itemView;
            TextView textView;
            public Item(@NonNull View itemView) {
                super(itemView);
                this.itemView = itemView;
                textView = itemView.findViewById(R.id.name);
            }
        }
}
