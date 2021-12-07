package com.example.newnotesapp;

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
}
