package com.example.enerjizeit.sqlitecontactuse;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class ContactDbManager {
    private static ContactDbManager instance;

    private SQLiteDatabase db;
    private final Context context;
    private ContactDbHelper dbHelper;

    private String[] columns = new String [] {
            ContactDbHelper.NAME,
            ContactDbHelper.PHONE,
            ContactDbHelper.PHOTO,
            ContactDbHelper.ABOUT,
    };

    public static ContactDbManager getInstance(Context context) {
        if(instance == null){
            instance = new ContactDbManager(context);
        }
        return instance;
    }

    public ContactDbManager(Context c) {
        context = c;
        dbHelper = new ContactDbHelper(context);
    }
    public ArrayList<ContactItem> getContacts(){
        openDB();
        Cursor cursor = db.query(ContactDbHelper.TABLE_NAME, null, null, null, null, null, null);
        ArrayList<ContactItem> list = new ArrayList<ContactItem>();
        if (cursor != null && cursor.getCount()>0){
            cursor.moveToFirst();
            do{
                ContactItem item = new ContactItem();
                item.setID(cursor.getInt(0));
                item.setName(cursor.getString(1));
                item.setPhone(cursor.getString(2));
                item.setPhotoPath(cursor.getString(3));
                item.setAbout(cursor.getString(4));

                list.add(item);
            } while (cursor.moveToNext());
        }
        closeDB();
        return list;
    }
    public int addContact(ContactItem entity){
        openDB();
        ContentValues cv = new ContentValues(4);
        cv.put(ContactDbHelper.NAME, entity.getName());
        cv.put(ContactDbHelper.PHONE, entity.getPhone());
        cv.put(ContactDbHelper.PHOTO, entity.getPhotoPath());
        cv.put(ContactDbHelper.ABOUT, entity.getAbout());

        String where = String.format("%s=%d", ContactDbHelper._ID, entity.getID());

        int res = db.update(ContactDbHelper.TABLE_NAME, cv, where, null);
        closeDB();

        return res;
    }
    public int deleteContact(int contactID) {
        openDB();
        String where = String.format("%s=%d",
                ContactDbHelper._ID, contactID);
        int res = db.delete(ContactDbHelper.TABLE_NAME, where, null);
        closeDB();
        return res;
    }

    public void openDB(){
        db = dbHelper.getWritableDatabase();
    }
    public void closeDB(){
        db.close();
    }

}
