package com.example.swift.viewModels;

import android.app.Application;
import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;

import com.example.swift.database.AppDatabase;
import com.example.swift.models.Exercise;

import java.util.ArrayList;

public class ExercisesViewModel extends AndroidViewModel {
    private AppDatabase database;
    private MutableLiveData<Boolean> saving = new MutableLiveData<>();
    private ObservableArrayList<Exercise> entries = new ObservableArrayList<>();
    private MutableLiveData<Exercise> currentEntry = new MutableLiveData<>();
    private Handler handler;

    public ExercisesViewModel(@NonNull Application application) {
        super(application);
        saving.setValue(false);
        handler = new Handler();
        database = Room.databaseBuilder(application, AppDatabase.class, "exercisesdb").build();

        new Thread(() ->{
            ArrayList<Exercise> exercises = (ArrayList<Exercise>) database.getExercisesDao().getAll();
            handler.post(() -> {
                entries.addAll(exercises);
            });
        }).start();
    }

    public MutableLiveData<Exercise> getCurrentEntry(){return this.currentEntry;}

    public void setCurrentEntry(Exercise exercise){this.currentEntry.setValue(exercise);}

    public MutableLiveData<Boolean> getSaving(){return saving;}

    public ObservableArrayList<Exercise> getEntries(){return entries;}

    public void deleteCurrentEntry(){
        new Thread(() ->{
            database.getExercisesDao().delete(currentEntry.getValue());
            handler.post(() ->{
                entries.remove(currentEntry.getValue());
                currentEntry.postValue(null);
            });

        }).start();
    }


    public void saveExercise(String name, String description){
        saving.setValue(true);
        // insert into database
        new Thread(() ->{
            if (currentEntry.getValue() != null){
                Exercise current = currentEntry.getValue();
                current.name = name;
                current.description = description;
                database.getExercisesDao().update(current);
                currentEntry.postValue(current);
                int index = entries.indexOf(current);
                handler.post(() ->{
                    entries.set(index, current);
                });
            }else{
                Exercise exercise = new Exercise();
                exercise.name = name;
                exercise.description = description;
                exercise.createdAt = System.currentTimeMillis();
                exercise.id = database.getExercisesDao().insert(exercise);
                handler.post(() -> {
                    entries.add(exercise);
                });
            }
            saving.postValue(false); // have to use postValue in a thread
        }).start();
    }
}
