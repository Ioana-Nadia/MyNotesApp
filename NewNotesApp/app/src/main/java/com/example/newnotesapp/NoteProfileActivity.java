package com.example.newnotesapp;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class NoteProfileActivity extends AppCompatActivity {
    private EditText noteTitle, noteContent;
    private String auxTitle, auxContent;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_profile);
        getSupportActionBar().setTitle("Note profile");
        noteTitle = findViewById(R.id.noteTitleInfo);
        noteContent = findViewById(R.id.noteContentInfo);
        auxTitle = "Not set yet";
        auxContent = "Not set yet";
        Bundle extraInfo = getIntent().getExtras();
        if(extraInfo != null) {
            auxTitle = extraInfo.getString("noteTitleInfo");
            auxContent = extraInfo.getString("noteContentInfo");
        }
        noteTitle.setText(auxTitle);
        noteContent.setText(auxContent);
    }
}
