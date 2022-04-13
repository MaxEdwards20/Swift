package com.example.swift;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.swift.viewModels.ExercisesViewModel;
import com.google.android.material.textfield.TextInputEditText;

public class CreateExercisesFragment extends Fragment {
    public CreateExercisesFragment(){super(R.layout.fragment_create_exercises);}

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ExercisesViewModel viewModel = new ViewModelProvider(getActivity()).get(ExercisesViewModel.class); // view model store owner
        view.findViewById(R.id.save).setOnClickListener(save ->{
            TextInputEditText nameEditText = view.findViewById(R.id.exerciseNameEditText);
            TextInputEditText descriptionEditText = view.findViewById(R.id.exerciseDescriptionEditText);
            viewModel.saveExercise(nameEditText.getText().toString(), descriptionEditText.getText().toString());
        });

    }
}
