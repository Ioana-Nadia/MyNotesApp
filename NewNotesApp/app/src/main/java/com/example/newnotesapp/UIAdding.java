package com.example.newnotesapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class UIAdding extends AppCompatActivity {
    private AlertDialog.Builder alertBuilder;
    private EditText noteTitle, noteContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_note);
        getSupportActionBar().setTitle("Add a new note");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.noteTitle = (EditText) findViewById(R.id.noteTitle);
        this.noteContent = (EditText) findViewById(R.id.noteContent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.save_note, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.saveNote) {
            String title = this.noteTitle.getText().toString();
            String content = this.noteContent.getText().toString();
            if (title.equals("") || content.equals("")) {
                alert(R.string.adding_alert);
                return super.onOptionsItemSelected(item);
            }
            ManageUILogic logic = new ManageUILogic();
            if(!logic.processNewNote(title, content, getApplicationContext())) {
                alert(R.string.adding_alert_existence);
                return super.onOptionsItemSelected(item);
            }
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            Toast.makeText(this, R.string.added_note_confirmation, Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    private void alert(int messageResource) {
        this.alertBuilder = new AlertDialog.Builder(this);
        alertBuilder.setTitle("Alert!")
                .setMessage(messageResource)
                .setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .show();
    }
}