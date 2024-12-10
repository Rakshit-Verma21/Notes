package com.example.notes;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteViewModwel extends AndroidViewModel {
    private NoteRepository repository;
    private LiveData<List<Note>>note;

    public NoteViewModwel(@NonNull Application application)
    {

        super(application);
        repository=new NoteRepository(application);
        note=repository.getAllNotes();

    }
    public void insert(Note note)
    {
        repository.insert(note);

    }
    public void update(Note note)
    {
        repository.update(note);

    }
    public void delete(Note note)
    {
        repository.delete(note);

    }
    public LiveData<List<Note>> getAllNotes()
    {
        return note;
    }


}
