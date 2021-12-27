package com.example.newnotesapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class NoteProfileActivity extends AppCompatActivity {
    private EditText noteTitle, noteContent;
    private String auxTitle, auxContent;
    private AlertDialog.Builder alertBuilder;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.note_profile_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        ManageUILogic logic = new ManageUILogic();
        Intent intent = new Intent(this, MainActivity.class);
        switch(item.getItemId()) {
            case R.id.delete:
                logic.processNoteDeletion(this.auxTitle, this);
                startActivity(intent);
                Toast.makeText(this, R.string.deleted_note_confirmation, Toast.LENGTH_SHORT).show();
                return super.onOptionsItemSelected(item);
            case R.id.update:
                if(logic.processNoteUpdate(this.auxTitle, this.auxContent, noteTitle.getText().toString(), noteContent.getText().toString(), this)) {
                    startActivity(intent);
                    Toast.makeText(this, R.string.updated_note_confirmation, Toast.LENGTH_SHORT).show();
                    return super.onOptionsItemSelected(item);
                }
                alert();
                return super.onOptionsItemSelected(item);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void alert() {
        this.alertBuilder = new AlertDialog.Builder(this);
        alertBuilder.setTitle("Alert!")
                .setMessage(R.string.updating_alert)
                .setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .show();
    }
}
