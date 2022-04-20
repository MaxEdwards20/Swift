package com.example.swift.fragments;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.swift.R;
import com.example.swift.viewModels.ExercisesViewModel;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class CreateExercisesFragment extends Fragment {
    private boolean previouslySaving = false;
    public CreateExercisesFragment() {
        super(R.layout.fragment_create_exercise);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ExercisesViewModel viewModel = new ViewModelProvider(getActivity()).get(ExercisesViewModel.class);
        viewModel.getCurrentEntry().observe(getViewLifecycleOwner(), (entry) -> {
            if (entry != null) {
                TextInputEditText titleEditText = view.findViewById(R.id.exerciseNameEditText);
                TextInputEditText weightEditText = view.findViewById(R.id.exerciseWeightEditText);
                TextInputEditText setsEditText = view.findViewById(R.id.exerciseSetsEditText);
                titleEditText.setText(entry.name);
                weightEditText.setText(entry.weight);
                setsEditText.setText(entry.sets);
            }
        });

        viewModel.getSaving().observe(getViewLifecycleOwner(), (saving) -> {
            if (saving && !previouslySaving) {
                MaterialButton button = view.findViewById(R.id.save);
                button.setEnabled(false);
                button.setText("Saving...");
                previouslySaving = saving;
            } else if (previouslySaving && !saving) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        view.findViewById(R.id.save).setOnClickListener(saveButton -> {
            TextInputEditText titleEditText = view.findViewById(R.id.exerciseNameEditText);
            TextInputEditText weightEditText = view.findViewById(R.id.exerciseWeightEditText);
            TextInputEditText setsEditText = view.findViewById(R.id.exerciseSetsEditText);
            viewModel.saveExercise(titleEditText.getText().toString(), weightEditText.getText().toString(), setsEditText.toString());
        });
    }
}
