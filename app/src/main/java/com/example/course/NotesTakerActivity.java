package com.example.course;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.course.Models.Notes;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NotesTakerActivity extends AppCompatActivity {
    EditText edit_title, edit_text;
    ImageView save;
    Notes notes;
    boolean isOldNote=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_taker);

        save=findViewById(R.id.save);
        edit_title=findViewById(R.id.edit_title);
        edit_text=findViewById(R.id.edit_text);

        notes=new Notes();
        try {
            notes= (Notes) getIntent().getSerializableExtra("old_note");
            edit_title.setText(notes.getTitle());
            edit_text.setText(notes.getText());
            isOldNote=true;
        }catch (Exception e){
            e.printStackTrace();
        }

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title= edit_title.getText().toString();
                String text=edit_text.getText().toString();

                if(text.isEmpty()){
                    Toast.makeText(NotesTakerActivity.this, "Note is empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                SimpleDateFormat formatter = new SimpleDateFormat("EEE, d MMM yyyy HH:mm a");
                Date date=new Date();
                if(!isOldNote){
                    notes=new Notes();
                }
                notes.setTitle(title);
                notes.setText(text);
                notes.setDatetime(formatter.format(date));

                Intent intent=new Intent();
                intent.putExtra("note", notes);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
    }
}