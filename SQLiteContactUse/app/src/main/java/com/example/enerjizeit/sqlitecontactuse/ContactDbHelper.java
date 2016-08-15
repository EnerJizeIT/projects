package com.example.enerjizeit.sqlitecontactuse;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.provider.BaseColumns;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public class ContactDbHelper extends SQLiteOpenHelper implements BaseColumns{
    static final String DB_CONTACTS = "contacts.db";
    static final String TABLE_NAME = "people";
    static final String NAME = "name";
    static final String PHONE = "phone";
    static final String PHOTO = "photo";
    static final String ABOUT = "about";

    static final String PHOTOS_DIR = "ContactPhotos";

    private Context context;

    public ContactDbHelper(Context context) {
        super(context, DB_CONTACTS, null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME
                + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME + " TEXT, "
                + PHONE + " TEXT, "
                + PHOTO + " TEXT, "
                + ABOUT + " TEXT);");

        String dir = String.format("%s/%s", Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath(), PHOTOS_DIR);
        copyMedia(dir);

        ContentValues cv = new ContentValues();
        cv.put(NAME, "Arseny");
        cv.put(PHONE, "79615879966");
        cv.put(PHOTO, dir + "/a1.png");
        cv.put(ABOUT, "Old friend");
        db.insert(TABLE_NAME, null, cv);

        cv.put(NAME, "Alexey");
        cv.put(PHONE, "79648211253");
        cv.put(PHOTO, dir + "/a2.png");
        cv.put(ABOUT, "We meet on Conference");
        db.insert(TABLE_NAME, null, cv);

        cv.put(NAME, "Zina");
        cv.put(PHONE, "79623155877");
        cv.put(PHOTO, dir + "/a3.png");
        cv.put(ABOUT, "Sweet girl from university");
        db.insert(TABLE_NAME, null, cv);

        cv.put(NAME, "BRO");
        cv.put(PHONE, "79681458254");
        cv.put(PHOTO, dir + "/a4.png");
        cv.put(ABOUT, "BRo is Bro");
        db.insert(TABLE_NAME, null, cv);

        cv.put(NAME, "Gynadiy");
        cv.put(PHONE, "79681453886");
        cv.put(PHOTO, dir + "/a5.png");
        cv.put(ABOUT, "Gynadiy is always Gynadiy");
        db.insert(TABLE_NAME, null, cv);

        cv.put(NAME, "Jora");
        cv.put(PHONE, "79147856245");
        cv.put(PHOTO, dir + "/a6.png");
        cv.put(ABOUT, "It seems one Jora you know");
        db.insert(TABLE_NAME, null, cv);

        cv.put(NAME, "Lida");
        cv.put(PHONE, "79153698756");
        cv.put(PHOTO, dir + "/a7.png");
        cv.put(ABOUT, "Meet on street");
        db.insert(TABLE_NAME, null, cv);

        cv.put(NAME, "Tanya");
        cv.put(PHONE, "71265473254");
        cv.put(PHOTO, dir + "/a8.png");
        cv.put(ABOUT, "Bro's girlfriend");
        db.insert(TABLE_NAME, null, cv);

        cv.put(NAME, "Gynza");
        cv.put(PHONE, "79816442514");
        cv.put(PHOTO, dir + "/a9.png");
        cv.put(ABOUT, "The place when you always drunk");
        db.insert(TABLE_NAME, null, cv);

        cv.put(NAME, "Father");
        cv.put(PHONE, "79123456795");
        cv.put(PHOTO, dir + "/a10.png");
        cv.put(ABOUT, "Its our Father men..");
        db.insert(TABLE_NAME, null, cv);

        cv.put(NAME, "Anna");
        cv.put(PHONE, "79216543851");
        cv.put(PHOTO, dir + "/a11.png");
        cv.put(ABOUT, "Your dear girlfrind");
        db.insert(TABLE_NAME, null, cv);

        cv.put(NAME, "someone");
        cv.put(PHONE, "---");
        cv.put(PHOTO, dir + "/a12.png");
        cv.put(ABOUT, "We meet on Conference");
        db.insert(TABLE_NAME, null, cv);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME);
        onCreate(db);
    }

    private void copyMedia(String dir) {
        try{
            File folder = new File(dir);
            if(!folder.exists()){
                folder.mkdir();
            }
            AssetManager mgr = context.getAssets();
            final String assetSubDir = "photos";
            String [] files = mgr.list(assetSubDir);

            for (String filename: files){
                InputStream inStream = mgr.open(assetSubDir + "/" + filename);
                byte [] buffer = new byte[inStream.available()];
                inStream.read(buffer);
                inStream.close();

                File destFile = new File(dir, filename);
                FileOutputStream outPutStream = new FileOutputStream(destFile);
                outPutStream.write(buffer);
                outPutStream.close();
            }
        } catch (Exception e){
            Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show();
        }
    }
}
