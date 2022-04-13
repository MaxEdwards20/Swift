package com.example.swift;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.swift.models.Exercise;

public class ExercisesFragment extends Fragment {
    public ExercisesFragment(){super (R.layout.fragment_exercises);}

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Put floating action button down for user to input a new exercise
        view.findViewById(R.id.floating_action_button).setOnClickListener(floating_action_button -> {
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, CreateExercisesFragment.class, null)
                            .setReorderingAllowed(true)
                            .addToBackStack(null)
                            .commit();

                }
        );


//        MainActivity activity = (MainActivity) getActivity();
//        RecyclerView recyclerView = view.findViewById(R.id.recycler_exercises);
//        ExercisesAdapter exercisesAdapter = new ExercisesAdapter(testExercises);
//
//        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext())); // Organizes views as a linear layout
//        recyclerView.setAdapter(exercisesAdapter);
    }
}
