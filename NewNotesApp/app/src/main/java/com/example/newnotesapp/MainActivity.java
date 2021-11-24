package com.example.newnotesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {
    Connection connection;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button buttonConnect = findViewById(R.id.sqlButton);
        Context context = getApplicationContext();
        buttonConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView display = (TextView) findViewById(R.id.sqlTextView);
                SQLConnection con = new SQLConnection();
                connection = con.classConnection(context);
                try {
                    String sqlTest = context.getString(R.string.selectAll_method);
                    Statement stm = connection.createStatement();
                    ResultSet result = stm.executeQuery(sqlTest);
                    StringBuilder finalResult = new StringBuilder();
                    while(result.next()) {
                        finalResult.append(result.getString(1)).append(" ");
                    }
                    display.setText(finalResult.toString());
                }
                catch(Exception e) {
                    Log.e("The error is: ", e.getMessage());
                }
            }
        });
    }
}