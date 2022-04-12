package com.example.swift;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Exercises extends Fragment {
    public Exercises(){super (R.layout.fragment_exercises);}

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        // Testing
        Exercise testExercise = new Exercise("Bench Press", "april 12", "Lifted two plates");
        Exercise[] testExercises = new Exercise[1];
        testExercises[0] = testExercise;

        // Real stuff
        super.onViewCreated(view, savedInstanceState);
        MainActivity activity = (MainActivity) getActivity();
        RecyclerView recyclerView = view.findViewById(R.id.recycler_exercises);
        ExercisesAdapter exercisesAdapter = new ExercisesAdapter(testExercises);

        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext())); // Organizes views as a linear layout
        recyclerView.setAdapter(exercisesAdapter);
    }
}
