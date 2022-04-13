package com.example.swift.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.room.Room;

import com.example.swift.database.AppDatabase;
import com.example.swift.models.Exercise;

public class ExercisesViewModel extends AndroidViewModel {
    private AppDatabase database;

    public ExercisesViewModel(@NonNull Application application) {
        super(application);
        database = Room.databaseBuilder(application, AppDatabase.class, "exercisesdb").build();
    }

    public void saveExercise(String name, String description){
        // insert into database
        new Thread(() ->{
            Exercise exercise = new Exercise();
            exercise.name = name;
            exercise.description = description;
            exercise.createdAt = System.currentTimeMillis();
            exercise.id = database.getExercisesDao().insert(exercise);
            }).start();
    }
}
