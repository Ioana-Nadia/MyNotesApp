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

import java.util.Calendar;

public class AddNewNote extends AppCompatActivity {
    AlertDialog.Builder alertBuilder;
    EditText noteTitle, noteContent;
    Calendar calendar;
    String currentDate;
    String currentTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_note);
        getSupportActionBar().setTitle("Add a new note");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.noteTitle = (EditText)findViewById(R.id.noteTitle);
        this.noteContent =(EditText)findViewById(R.id.noteContent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.save_note, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.saveNote) {
            calendar = calendar.getInstance();
            currentDate = calendar.get(Calendar.DAY_OF_MONTH) + "/" + (calendar.get(Calendar.MONTH) + 1) + "/" + calendar.get(Calendar.YEAR);
            currentTime = modelateTime(calendar.get(Calendar.HOUR_OF_DAY)) + ":" + calendar.get(Calendar.MINUTE);
            String title = this.noteTitle.getText().toString();
            String content = this.noteContent.getText().toString();
            if(title.equals("") || content.equals(""))
                alert();
            else {
                Dao dao = new Dao();
                Note note = new Note(title, content, currentDate, currentTime);
                dao.addNote(note, getApplicationContext());
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                Toast.makeText(this, R.string.added_note_confirmation, Toast.LENGTH_SHORT).show();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private String modelateTime(int time) {
        if(time >= 10)
            return String.valueOf(time);
        return "0" + time;
    }

    private void alert() {
        this.alertBuilder = new AlertDialog.Builder(this);
        alertBuilder.setTitle("Alert!")
                    .setMessage("Title and content can't be empty!")
                    .setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    })
                    .show();
    }

}