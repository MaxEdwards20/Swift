package com.example.swift.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/*
===========
id | name | description | createdAt|


 */
@Entity
public class Exercise {
    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo
    public String name;

    @ColumnInfo
    public String weight;

    @ColumnInfo
    public String sets;

    @ColumnInfo (name = "created_at")
    public long createdAt;
}
