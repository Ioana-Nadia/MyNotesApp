package com.example.newnotesapp;

import android.content.Context;

import java.util.Calendar;

public class ManageUILogic {
    private Calendar calendar;
    private String currentDate;
    private String currentTime;

    public void processNewNote(String title, String content, Context context) {
        calendar = calendar.getInstance();
        currentDate = calendar.get(Calendar.DAY_OF_MONTH) + "/" + (calendar.get(Calendar.MONTH) + 1) + "/" + calendar.get(Calendar.YEAR);
        currentTime = modelateTime(calendar.get(Calendar.HOUR_OF_DAY)) + ":" + modelateTime(calendar.get(Calendar.MINUTE));
        Dao dao = new Dao();
        Note note = new Note(title, content, currentDate, currentTime);
        dao.addNote(note, context);
    }

    private String modelateTime(int time) {
        if (time >= 10)
            return String.valueOf(time);
        return "0" + time;
    }
}
