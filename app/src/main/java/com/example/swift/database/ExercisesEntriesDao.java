package com.example.swift.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.swift.models.Exercise;

import java.util.List;
// CRUD


@Dao
public interface ExercisesEntriesDao {
    @Insert
    public long insert(Exercise entry);

    @Query("SELECT * FROM exercise")
    public List<Exercise> getAll();

    @Query("SELECT * FROM exercise WHERE id = :id")
    public Exercise findByID(long id);

    @Update
    public void update(Exercise entry);

    @Delete
    public void delete(Exercise entry);

}
