package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UpdateActivity extends AppCompatActivity
{

    EditText title;
    EditText description;
    Button save;
    Button cancel;
    int noteid;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Edit Note");
        setContentView(R.layout.activity_update);
        title=findViewById(R.id.updateeditTextTexttitle);
        description=findViewById(R.id.updateeditTextText2description);
        save=findViewById(R.id.updatebuttonsave);
        cancel=findViewById(R.id.updatebuttoncancel);
        getData();
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
                updateNote();


            }
        });

    }
    private void updateNote()
    {
        String titleedited=title.getText().toString();
        String descEdited=description.getText().toString();
        Intent intent=new Intent();
        intent.putExtra("titleEdited",titleedited);
        intent.putExtra("desEdited",descEdited);
        if(noteid!=-1)
        {
            intent.putExtra("noteId",noteid);
            setResult(RESULT_OK,intent);
            finish();
        }


    }
    public  void getData()
    {
        Intent i=getIntent();
         noteid=i.getIntExtra("id",-1);
        String title1 =i.getStringExtra("title");
        String description1=i.getStringExtra("description");
        title.setText(title1);
        description.setText(description1);

    }


}