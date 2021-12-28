package com.example.newnotesapp;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Note {
    private String title;
    private String content;
    private String date;
    private String time;

    public Note(String title, String content, String date, String time) {
        this.title = title;
        this.content = content;
        this.date = date;
        this.time = time;
    }

    public String getTitle() {
        return this.title;
    }

    public String getContent() {
        return this.content;
    }

    public String getDate() {
        return this.date;
    }

    public String getTime() {
        return this.time;
    }

    public Date getDateFormat() {
        try {
            String stringDate = this.date;
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            return formatter.parse(stringDate);
        }
        catch(ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Time getTimeFormat() {
        try {
            String stringTime = this.time;
            DateFormat formatter = new SimpleDateFormat("HH:mm");
            Time timeValue = new Time(formatter.parse(stringTime).getTime());
            return timeValue;
        }
        catch(ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
