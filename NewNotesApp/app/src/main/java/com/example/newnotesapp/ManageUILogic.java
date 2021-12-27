package com.example.newnotesapp;

import android.content.Context;

import java.util.Calendar;

public class ManageUILogic {
    private Calendar calendar;
    private String currentDate;
    private String currentTime;

    public boolean processNewNote(String title, String content, Context context) {
        Dao dao = new Dao();
        if(dao.checkTitle(title, context)) {
            calendar = calendar.getInstance();
            currentDate = calendar.get(Calendar.DAY_OF_MONTH) + "/" + (calendar.get(Calendar.MONTH) + 1) + "/" + calendar.get(Calendar.YEAR);
            currentTime = modelateTime(calendar.get(Calendar.HOUR_OF_DAY)) + ":" + modelateTime(calendar.get(Calendar.MINUTE));
            Note note = new Note(title, content, currentDate, currentTime);
            dao.addNote(note, context);
            return true;
        }
        return false;
    }

    public void processNoteDeletion(String noteTitle, Context context) {
        Dao dao = new Dao();
        dao.deleteNote(noteTitle, context);
    }

    public boolean processNoteUpdate(String oldNoteTitle, String oldNoteContent, String newTitle, String newContent, Context context) {
        if(!oldNoteTitle.equals(newTitle) || !oldNoteContent.equals(newContent)) {
            calendar = calendar.getInstance();
            currentDate = calendar.get(Calendar.DAY_OF_MONTH) + "/" + (calendar.get(Calendar.MONTH) + 1) + "/" + calendar.get(Calendar.YEAR);
            currentTime = modelateTime(calendar.get(Calendar.HOUR_OF_DAY)) + ":" + modelateTime(calendar.get(Calendar.MINUTE));
            Note updatedNote = new Note(newTitle, newContent, currentDate, currentTime);
            Dao dao = new Dao();
            dao.updateNote(updatedNote, context, oldNoteTitle);
            return true;
        }
        return false;
    }

    private String modelateTime(int time) {
        if (time >= 10)
            return String.valueOf(time);
        return "0" + time;
    }
}
