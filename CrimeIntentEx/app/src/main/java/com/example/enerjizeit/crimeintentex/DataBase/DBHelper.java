package com.example.enerjizeit.crimeintentex.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.enerjizeit.crimeintentex.DataBase.DBTable.Table.Rows;

public class DBHelper extends SQLiteOpenHelper{
    private static final String DB_NAME = "crime.db";
    private static final int VERSION = 1;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + DBTable.Table.TABLE_NAME + "(" +
        " _id integer primary key autoincrement, " +
                Rows.UUID + ", " +
                Rows.TITLE + ", " +
                Rows.DATE + ", " +
                Rows.SOLVED + ", " +
                Rows.SUSPECT + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
