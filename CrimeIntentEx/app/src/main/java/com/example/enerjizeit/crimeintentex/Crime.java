package com.example.enerjizeit.crimeintentex;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.UUID;

import static android.R.attr.y;

public class Crime {
    private UUID id;
    private String title;
    private String suspect;
    private Date date;
    private boolean solved;
    private int year, month, day;
    private Calendar calendar;

    public Crime() {
        this(UUID.randomUUID());

    }

    public Crime(UUID id) {
        this.id = id;
        date = new Date();
    }

    public UUID getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public Date getDate() {
        return date;
    }
    public boolean isSolved() {
        return solved;
    }
    public String getSuspect() {
        return suspect;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public void setSolved(boolean solved) {
        this.solved = solved;
    }
    public void setSuspect(String suspect) {
        this.suspect = suspect;
    }

    public void clearDate(Date d){
        Calendar calendar= Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
    }

}
