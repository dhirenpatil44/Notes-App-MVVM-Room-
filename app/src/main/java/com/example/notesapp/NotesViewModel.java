package com.example.notesapp;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class NotesViewModel extends AndroidViewModel {
    public NotesRepository repository;
    public LiveData<List<Entity>> getAllNote;
    public LiveData<List<Entity>> hightolow;
    public LiveData<List<Entity>> lowtohigh;

    public NotesViewModel(Application application) {
        super(application);

        repository = new NotesRepository(application);
        getAllNote = repository.getAllNotes;
        hightolow = repository.hightolow;
        lowtohigh = repository.lowtohigh;
    }

    public void insertNote(Entity entity){
        repository.insertNotes(entity);
    }

    public void deleteNote(int id){
        repository.deleteNotes(id);
    }

    public void updateNote(Entity entity){
        repository.updateNotes(entity);
    }
}
