package com.example.newnotesapp;

import android.content.Context;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Dao {
    Connection connection;

    public boolean checkTitle(String noteTitle, Context context) {
        Connection connection;
        SQLConnection con = new SQLConnection();
        connection = con.classConnection(context);
        Object obj;
        try {
            Statement stm = connection.createStatement();
            ResultSet result = stm.executeQuery(checkTitleStoredProcedure(noteTitle));
            if(result.next()) {
                obj =result.getString(1);
                if(obj != null) {
                    System.out.println("+++" + obj.toString() + "+++");
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }

    public void addNote(Note note, Context context) {
        SQLConnection con = new SQLConnection();
        connection = con.classConnection(context);
        try {
            Statement stm = connection.createStatement();
            stm.executeQuery(insertStoredProcedure(note));
        }
        catch(Exception e) {
        }
    }

    public List<Note> getAllNotes(Context context) {
        Connection connection;
        SQLConnection con = new SQLConnection();
        connection = con.classConnection(context);
        List<Note> notes = new ArrayList<>();
        try {
            String sql = context.getString(R.string.get_all_notes);
            Statement stm = connection.createStatement();
            ResultSet result = stm.executeQuery(sql);
            while(result.next()) {
                Note note = new Note(result.getString(2), result.getString(3), result.getString(4), result.getString(5));
                notes.add(note);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            return notes;
        }
    }

    public void deleteNote(String noteTitle, Context context) {
        SQLConnection con = new SQLConnection();
        connection = con.classConnection(context);
        try {
            Statement stm = connection.createStatement();
            stm.executeQuery(deleteStoredProcedure(noteTitle));
        }
        catch(Exception e) {
        }
    }

    public void updateNote(Note note, Context context, String oldNoteTitle) {
        SQLConnection con = new SQLConnection();
        connection = con.classConnection(context);
        try {
            Statement stm = connection.createStatement();
            stm.executeQuery(updateStoredProcedure(note, oldNoteTitle));
        }
        catch(Exception e) {
        }
    }

    private String insertStoredProcedure(Note note) {
        return "exec dbo.addNewNote" + " '" + note.getTitle() + "', '" + note.getContent() + "', '" + note.getDate() + "', '" + note.getTime() + "'";
    }

    private String deleteStoredProcedure(String noteTitle) {
        return "exec dbo.deleteNote '" + noteTitle + "'";
    }

    private String updateStoredProcedure(Note note, String oldNoteTitle) {
        return "exec dbo.updateNote" + " '" + note.getTitle() + "', '" + note.getContent() + "', '" + note.getDate() + "', '" + note.getTime() + "', '" + oldNoteTitle + "'";
    }

    private String checkTitleStoredProcedure(String noteTitle) {
        return "exec dbo.getTitleID '" + noteTitle + "'";
    }
}
