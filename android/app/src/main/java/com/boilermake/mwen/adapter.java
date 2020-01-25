package com.boilermake.mwen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

public class adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        Context context;
        String[] items;
        public adapter(Context context, String[]items) {
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
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
                ((Item) holder).textView.setText(items[position]);
        }

        @Override
        public int getItemCount() {
                return items.length;
        }

        public class Item extends RecyclerView.ViewHolder {
                TextView textView;
                public Item(@NonNull View itemView) {
                        super(itemView);
                        textView = itemView.findViewById(R.id.name);
                }
        }
}
