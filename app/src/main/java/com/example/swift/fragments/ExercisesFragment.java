package com.example.swift.fragments;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swift.ExercisesAdapter;
import com.example.swift.R;
import com.example.swift.fragments.CreateExercisesFragment;
import com.example.swift.models.Exercise;
import com.example.swift.viewModels.ExercisesViewModel;

public class ExercisesFragment extends Fragment {
    public ExercisesFragment(){super (R.layout.fragment_exercises);}

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ExercisesViewModel viewModel = new ViewModelProvider(getActivity()).get(ExercisesViewModel.class); // view model store owner
        ObservableArrayList<Exercise> exercises = viewModel.getEntries();
        ExercisesAdapter adapter = new ExercisesAdapter(
                exercises, entry -> {
                    viewModel.setCurrentEntry(entry);
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, ExercisesFragment.class, null)
                            .setReorderingAllowed(true)
                            .addToBackStack(null)
                            .commit();
                }
        );

        // notify recycler it has changed
        exercises.addOnListChangedCallback(new ObservableList.OnListChangedCallback<ObservableList<Exercise>>() {
            @Override
            public void onChanged(ObservableList<Exercise> sender) {
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onItemRangeChanged(ObservableList<Exercise> sender, int positionStart, int itemCount) {
                adapter.notifyItemRangeChanged(positionStart, itemCount);
            }

            @Override
            public void onItemRangeInserted(ObservableList<Exercise> sender, int positionStart, int itemCount) {
                adapter.notifyItemRangeInserted(positionStart, itemCount);
            }

            @Override
            public void onItemRangeMoved(ObservableList<Exercise> sender, int fromPosition, int toPosition, int itemCount) {
                adapter.notifyItemMoved(fromPosition, toPosition);
            }

            @Override
            public void onItemRangeRemoved(ObservableList<Exercise> sender, int positionStart, int itemCount) {
                adapter.notifyItemRangeRemoved(positionStart, itemCount);
            }
        });

        RecyclerView recyclerView = view.findViewById(R.id.recycler_exercises);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        // Put floating action button down for user to input a new exercise
        view.findViewById(R.id.floating_action_button).setOnClickListener(floating_action_button -> {
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, CreateExercisesFragment.class, null)
                            .setReorderingAllowed(true)
                            .addToBackStack(null)
                            .commit();

                }
        );

    }
}
