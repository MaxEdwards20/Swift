package com.example.swift.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.swift.R;
import com.example.swift.viewModels.ExercisesViewModel;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class ExerciseFragment extends Fragment {
    public ExerciseFragment(){
        super(R.layout.exercise_fragment);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ExercisesViewModel viewModel = new ViewModelProvider(getActivity()).get(ExercisesViewModel.class);

        viewModel.getCurrentEntry().observe(getViewLifecycleOwner(), (entry) -> {
            if (entry == null) {
                getActivity().getSupportFragmentManager().popBackStack();
            } else {
                TextView titleView = view.findViewById(R.id.titleView);
                TextView bodyView = view.findViewById(R.id.bodyView);
                titleView.setText(entry.name);
                bodyView.setText(entry.description);
            }
        });

        view.findViewById(R.id.delete).setOnClickListener((fab) -> {
            viewModel.deleteCurrentEntry();
        });

        view.findViewById(R.id.edit).setOnClickListener(editFab -> {
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, CreateExercisesFragment.class, null)
                    .setReorderingAllowed(true)
                    .addToBackStack(null)
                    .commit();
        });
    }
}
