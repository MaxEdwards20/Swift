package com.example.swift;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swift.models.Exercise;


public class ExercisesAdapter extends RecyclerView.Adapter<ExercisesAdapter.ViewHolder> {
    private ObservableArrayList<Exercise> entries;

    public interface OnExerciseClicked {
        public void onClick(Exercise exercise);
    }

    OnExerciseClicked listener;

    public ExercisesAdapter(ObservableArrayList<Exercise> entries, OnExerciseClicked listener){
        this.entries = entries;
        this.listener = (listener);
        Log.d("Info", "Exercise adapter triggered");
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.exercises_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d("EXERCISES ADAPATER", "onBindViewHolder: VIEW HOLDER WAS REBOUND");
        Exercise exercise = entries.get(position);
        TextView exTitle = holder.itemView.findViewById(R.id.title);
        TextView exDescription = holder.itemView.findViewById(R.id.description);
        // Put the elements into the view
        exTitle.setText(exercise.name);
        exDescription.setText(exercise.description);

        holder.itemView.setOnClickListener(view -> {
            listener.onClick(entries.get(position));
        });

    }

    @Override
    public int getItemCount(){
        return entries.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ViewHolder(@NonNull View itemView){
            super(itemView);
        }
    }
}

