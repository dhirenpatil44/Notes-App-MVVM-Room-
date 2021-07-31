package com.example.notesapp;

import android.app.Application;
import androidx.lifecycle.LiveData;
import java.util.List;

public class NotesRepository {

    public NotesDao notesDao;
    public LiveData<List<Entity>> getAllNotes;
    public LiveData<List<Entity>> hightolow;
    public LiveData<List<Entity>> lowtohigh;

    public NotesRepository(Application application){
        NotesDatabase notesDatabase = NotesDatabase.getInstance(application);

        notesDao = notesDatabase.notesDao();
        getAllNotes = notesDao.getAllNotes();
        hightolow = notesDao.hightolow();
        lowtohigh = notesDao.lowtohigh();
    }

    public void insertNotes(Entity entity){
        notesDao.insertNotes(entity);
    }

    public void  deleteNotes(int id){
        notesDao.deleteNotes(id);
    }

    public void updateNotes(Entity entity){
        notesDao.updateNotes(entity);
    }
}
