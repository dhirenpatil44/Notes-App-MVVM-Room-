package com.example.notesapp;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@androidx.room.Dao
public interface NotesDao {

    @Query("SELECT * FROM Notes_data")
    LiveData<List<Entity>> getAllNotes();

    @Query("SELECT * FROM Notes_data ORDER BY notes_priority DESC")
    LiveData<List<Entity>> hightolow();

    @Query("SELECT * FROM Notes_data ORDER BY notes_priority ASC")
    LiveData<List<Entity>> lowtohigh();

    @Insert
    public void insertNotes(Entity... entity);

    @Query("DELETE FROM Notes_data WHERE id=:id")
    public void deleteNotes(int id);

    @Update
    public void updateNotes(Entity entity);

}
