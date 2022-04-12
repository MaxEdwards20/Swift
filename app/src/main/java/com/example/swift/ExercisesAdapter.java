package com.example.swift;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class ExercisesAdapter extends RecyclerView.Adapter<ExercisesAdapter.ViewHolder> {
    private Exercise[] exercises;

    public ExercisesAdapter(Exercise[] exercises){
        this.exercises = exercises;
        Log.d("Info", "Exercise adapter triggered");
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_exercises, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d("EXERCISES ADAPATER", "onBindViewHolder: VIEW HOLDER WAS REBOUND");
        Exercise exercise = exercises[position];
        TextView exTitle = holder.itemView.findViewById(R.id.exercise_title);
        TextView exDescription = holder.itemView.findViewById(R.id.exercise_description);
        exTitle.setText(exercise.getName());
        System.out.println("Course name is : " + exercise.getName());
        exDescription.setText(exercise.getInformation());
    }

    @Override
    public int getItemCount(){
        return exercises.length;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        public ViewHolder(@NonNull View itemView){
            super(itemView);
        }
    }
}

