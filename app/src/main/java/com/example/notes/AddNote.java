package com.example.notes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Objects;

public class AddNote extends AppCompatActivity
{
    EditText title;
    EditText description;
    Button save;
    Button cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);

        getSupportActionBar().setTitle("Add Note");
        setContentView(R.layout.activity_add_note);
        title=findViewById(R.id.editTextTexttitle);
        description=findViewById(R.id.editTextText2description);
        save=findViewById(R.id.buttonsave);
        cancel=findViewById(R.id.buttoncancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                finish();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                saveNote();

            }
        });


    }
    public void saveNote()
    {
        String titlenew=title.getText().toString();
        String descNew=description.getText().toString();
        Intent i=new Intent();
        i.putExtra("noteTitle",titlenew);
        i.putExtra("noteDescription",descNew);
        setResult(RESULT_OK,i);
        finish();
    }


}