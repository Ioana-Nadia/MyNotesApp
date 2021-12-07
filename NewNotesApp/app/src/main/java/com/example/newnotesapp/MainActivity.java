package com.example.newnotesapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {
    Connection connection;
    RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Notes");
        recycler = findViewById(R.id.notedList);
        Button buttonConnect = findViewById(R.id.sqlButton);
        Context context = getApplicationContext();
        buttonConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView display = (TextView) findViewById(R.id.sqlTextView);
                SQLConnection con = new SQLConnection();
                connection = con.classConnection(context);
                try {
                    String sqlTest = context.getString(R.string.selectTest_procedure);
                    Statement stm = connection.createStatement();
                    ResultSet result = stm.executeQuery(sqlTest);
                    StringBuilder finalResult = new StringBuilder();
                    while(result.next()) {
                        finalResult.append(result.getString(2)).append(" ");
                    }
                    display.setText(finalResult.toString());
                }
                catch(Exception e) {
                    Log.e("The error is: ", e.getMessage());
                }
            }
        });
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
            Intent intent = new Intent(this, AddNewNote.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}