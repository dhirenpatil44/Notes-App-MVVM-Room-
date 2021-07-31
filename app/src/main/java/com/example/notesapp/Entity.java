package com.example.notesapp;

import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

@androidx.room.Entity(tableName = "Notes_data")
public class Entity {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "notes")
    public String notes;

    @ColumnInfo(name = "notes_title")
    public String notesTitle;

    @ColumnInfo(name = "notes_subtitle")
    public String notesSubtitle;

    @ColumnInfo(name = "notes_date")
    public String date;

    @ColumnInfo(name = "notes_priority")
    public String priority;

}
