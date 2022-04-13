package com.example.swift.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/*
===========
id | title | body | createdAt|


 */
@Entity
public class Exercise {
    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo
    public String name;

    @ColumnInfo
    public String description;

    @ColumnInfo (name = "created_at")
    public long createdAt;
}
