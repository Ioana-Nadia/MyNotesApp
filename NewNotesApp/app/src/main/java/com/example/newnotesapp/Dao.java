package com.example.newnotesapp;

import android.content.Context;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Dao {
    Connection connection;

    public void addNote(Note note, Context context) {
        System.out.println(note.getTitle() + " " + note.getContent() + " " + note.getDate() + " " + note.getTime());
        SQLConnection con = new SQLConnection();
        connection = con.classConnection(context);
        try {
            Statement stm = connection.createStatement();
            ResultSet result = stm.executeQuery(insertStoredProcedure(note));
        }
        catch(Exception e) {
        }
    }

    private String insertStoredProcedure(Note note) {
        return "exec dbo.addNewNote" + " '" + note.getTitle() + "', '" + note.getContent() + "', '" + note.getDate() + "', '" + note.getTime() + "'";
    }
}
