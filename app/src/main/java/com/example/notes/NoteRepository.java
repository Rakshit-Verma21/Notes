package com.example.notes;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NoteRepository {
    private NoteDao noteDao;
    private LiveData<List<Note>> note;


    ExecutorService executorService= Executors.newSingleThreadExecutor();

    public NoteRepository(Application application)
    {
        NoteDatebase datebase=NoteDatebase.getInstance(application);
        noteDao= datebase.noteDao();
        note=noteDao.getAllNotes();
    }

    public void insert(Note note)
    {
        executorService.execute(new Runnable() {
            @Override
            public void run()
            {
                noteDao.insert(note);
            }
        });



    }
    public void update(Note note)
    {
        executorService.execute(new Runnable() {
            @Override
            public void run()
            {
                noteDao.update(note);
            }
        });


    }
    public void  delete(Note note)
    {
        executorService.execute(new Runnable() {
            @Override
            public void run()
            {
                noteDao.delete(note);
            }
        });


    }
    public LiveData<List<Note>> getAllNotes()
    {
        return note;
    }





}
