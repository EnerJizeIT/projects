package com.example.enerjizeit.p_1011_contentprovider;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, MyContProvider.DB_NAME, null, MyContProvider.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(MyContProvider.DB_CREATE);
        ContentValues cv = new ContentValues();
        for(int i = 1; i<=3; i++){
            cv.put(MyContProvider.CONTACT_NAME, "name " + i);
            cv.put(MyContProvider.CONTACT_EMAIL, "name " + i);
            db.insert(MyContProvider.TABLE_NAME, null, cv);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
