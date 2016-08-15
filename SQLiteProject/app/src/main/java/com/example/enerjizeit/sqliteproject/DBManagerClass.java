package com.example.enerjizeit.sqliteproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class DBManagerClass {
    private SQLiteDatabase db;
    private final Context context;
    private DBHelperClass dbHelper;

    public DBManagerClass(Context context) {
        this.context = context;
        dbHelper = new DBHelperClass(context);
    }
    private static DBManagerClass instance;

    public static DBManagerClass getInstance(Context context){
        if(instance == null){
            instance = new DBManagerClass(context);
        }
        return instance;
    }

    private String [] columns = new String[]{
            DBHelperClass.NAME,
            DBHelperClass.PHONE,
            DBHelperClass.PHOTO,
            DBHelperClass.EMAIL
    };

    public void open(){
        db = dbHelper.getWritableDatabase();
    }

    public void close(){
        db.close();
    }

    public ArrayList<ItemLab> getItems(){
        open();
        Cursor cursor = db.query(DBHelperClass.TABLE_NAME, null, null, null, null, null, null);
        ArrayList<ItemLab> list = new ArrayList<>();
        if (cursor != null && cursor.getCount()>0){
            cursor.moveToFirst();
            do{
                ItemLab item = new ItemLab();
                item.setID(cursor.getInt(0));
                item.setName(cursor.getString(1));
                item.setPhone(cursor.getString(2));
                item.setPhotoPath(cursor.getString(3));
                item.setEmail(cursor.getString(4));

                list.add(item);
            } while (cursor.moveToNext());
        }
        close();
        return list;
    }

    public int addContact(ItemLab itemLab){
        open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelperClass.NAME, itemLab.getName());
        contentValues.put(DBHelperClass.PHONE, itemLab.getPhone());
        contentValues.put(DBHelperClass.PHOTO, itemLab.getPhotoPath());
        contentValues.put(DBHelperClass.EMAIL, itemLab.getEmail());

        // int res = (int)db.insertOrThrow(ContactDbHelper.TABLE_NAME, null, values);
        int result = (int) db.insert(DBHelperClass.TABLE_NAME, null, contentValues);
        close();
        return result;
    }

    public int updateContact(ItemLab itemLab){
        open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelperClass.NAME, itemLab.getName());
        contentValues.put(DBHelperClass.PHONE, itemLab.getPhone());
        contentValues.put(DBHelperClass.PHOTO, itemLab.getPhone());
        contentValues.put(DBHelperClass.EMAIL, itemLab.getEmail());
        String where = String.format("%s=%d", DBHelperClass._ID, itemLab.getID());

        int result = db.update(DBHelperClass.TABLE_NAME, contentValues, where, null);
        close();
        return result;
    }
    public int deleteContact(int itemId){
        open();
        String where = String.format("%s=%d", DBHelperClass._ID, itemId);
        int result = db.delete(DBHelperClass.TABLE_NAME, where, null);
        close();
        return result;
    }
}
