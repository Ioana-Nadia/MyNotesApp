package com.example.newnotesapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recycler;
    private List<Note> notes;
    private Adapter adapter;
    private Adapter.RecyclerViewClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Notes");
        Dao dao = new Dao();
        this.notes = dao.getAllNotes(getApplicationContext());
        recycler = findViewById(R.id.notedList);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        setOnClickListener();
        adapter = new Adapter(this, this.notes, this.listener);
        recycler.setAdapter(adapter);
    }

    private void setOnClickListener() {
        this.listener = new Adapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(getApplicationContext(),NoteProfileActivity.class);
                intent.putExtra("noteTitleInfo", notes.get(position).getTitle());
                intent.putExtra("noteContentInfo", notes.get(position).getContent());
                startActivity(intent);
            }
        };
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_addition, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.addButton) {
            Intent intent = new Intent(this, UIAdding.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}