package com.example.notes;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private NoteViewModwel noteViewModwel;
    RecyclerView recyclerView;
    ActivityResultLauncher<Intent>activityResultLauncherForAddNote;
    ActivityResultLauncher<Intent>activityResultLauncherForUpdateNote;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        registerActivityForAddNote();
        registerActivityForUpdateNote();
        recyclerView=findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        NoteAdapter adapter=new NoteAdapter();
        recyclerView.setAdapter(adapter);
        noteViewModwel=new ViewModelProvider.AndroidViewModelFactory(getApplication()).create(NoteViewModwel.class);
        noteViewModwel.getAllNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes)
            {
                adapter.setNotes(notes);


            }
        });
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT)
        {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction)
            {
                noteViewModwel.delete(adapter.getNotes(viewHolder.getAdapterPosition()));

            }
        }).attachToRecyclerView(recyclerView);

        adapter.setOnItemClickListner(new NoteAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Note note) {
                Intent intent=new Intent(MainActivity.this, UpdateActivity.class);
                intent.putExtra("id",note.getId());
                intent.putExtra("title",note.getTitle());
                intent.putExtra("description",note.getDescription());
                activityResultLauncherForUpdateNote.launch(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {


        getMenuInflater().inflate(R.menu.new_menu,menu);

        return true;

    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {


        if (item.getItemId() == R.id.add_item)
                {
                    Intent intent=new Intent(MainActivity.this , AddNote.class);
                    activityResultLauncherForAddNote.launch(intent);
                    return  true;
                }
        else
        {
            return super.onOptionsItemSelected(item);
        }
    }
    public void registerActivityForUpdateNote()
    {
        activityResultLauncherForUpdateNote=registerForActivityResult(new ActivityResultContracts.
                StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult o)
            {
                int resultcode=o.getResultCode();
                Intent data=o.getData();
                if(resultcode==RESULT_OK&&data!=null)
                {
                    String title=data.getStringExtra("titleEdited");
                    String description=data.getStringExtra("desEdited");
                    int id=data.getIntExtra("noteId",-1);
                    Note note=new Note(title,description);
                    note.setId(id);
                    noteViewModwel.update(note);
                }
            }
        });
    }
    public void registerActivityForAddNote()
    {
        activityResultLauncherForAddNote=registerForActivityResult(new ActivityResultContracts.
                StartActivityForResult(), new ActivityResultCallback<ActivityResult>()
        {
            @Override
            public void onActivityResult(ActivityResult o) {
                int resultcode=o.getResultCode();
                Intent data=o.getData();
                if(resultcode==RESULT_OK&&data!=null)
                {
                    String title=data.getStringExtra("noteTitle");
                    String description=data.getStringExtra("noteDescription");
                    Note note=new Note(title,description);
                    noteViewModwel.insert(note);
                }
            }
        });
    }



}