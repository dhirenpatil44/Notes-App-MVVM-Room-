package com.example.notesapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ViewHolder> {
    public Context context;
    public List<Entity> entityList;
    public List<Entity> filterItem;


    public RVAdapter(Context context, List<Entity> entityList) {
        this.context = context;
        this.entityList = entityList;
        filterItem = new ArrayList<>(entityList);
    }

    public void searchNote(List<Entity> filterNote) {
        this.entityList = filterNote;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.notes_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RVAdapter.ViewHolder holder, int position) {

        Entity entity1 = entityList.get(position);

        if ("1".equals(entity1.priority)) {
            holder.priView.setBackgroundResource(R.drawable.green_shape);
        } else if ("2".equals(entity1.priority)) {
            holder.priView.setBackgroundResource(R.drawable.yellow_shape);
        } else if ("3".equals(entity1.priority)) {
            holder.priView.setBackgroundResource(R.drawable.red_shape);
        }

        holder.AdaTitle.setText(entityList.get(position).notesTitle);
        holder.AdaSubtitle.setText(entityList.get(position).notesSubtitle);
        holder.AdaDate.setText(entityList.get(position).date);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateNote.class);
                intent.putExtra("id", entity1.id);
                intent.putExtra("title", entity1.notesTitle);
                intent.putExtra("subtitle", entity1.notesSubtitle);
                intent.putExtra("note", entity1.notes);
                intent.putExtra("priority", entity1.priority);

                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return entityList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView AdaTitle, AdaSubtitle, AdaDate;
        private View priView;

        public ViewHolder(View itemView) {
            super(itemView);

            AdaTitle = itemView.findViewById(R.id.AdaTitle);
            AdaSubtitle = itemView.findViewById(R.id.AdaSubtitle);
            AdaDate = itemView.findViewById(R.id.AdaDate);
            priView = itemView.findViewById(R.id.priView);
        }
    }
}
