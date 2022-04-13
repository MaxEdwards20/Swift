package com.example.swift.database;


import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.swift.models.Exercise;

@Database(entities = {Exercise.class}, version=1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ExercisesEntriesDao getExercisesDao();
}
