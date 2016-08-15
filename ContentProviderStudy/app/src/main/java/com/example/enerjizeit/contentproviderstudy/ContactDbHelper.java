package com.example.enerjizeit.contentproviderstudy;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.provider.BaseColumns;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

public class ContactDbHelper extends SQLiteOpenHelper implements BaseColumns{
    public static final String DB_CONTACTS = "contacts.db";
    public static final String TABLE_NAME = "people";
    public static final String NAME = "name";
    public static final String PHONE = "phone";
    public static final String PHOTO = "photo";
    public static final String ABOUT = "about";

    public static final String PHOTOS_DIR = "ContactPhotos";

    private Context context;

        public ContactDbHelper(Context context) {
            super(context, DB_CONTACTS, null, 1);
            this.context = context;
    }

    private void copyMedia(String dir) {
        try{
            File folder = new File(dir);
            if(!folder.exists()){
                folder.mkdir();
            }
            AssetManager assetM = context.getAssets();
            final String assetSubDir = "photos";
            String [] files = assetM.list(assetSubDir);

            for(String fileName : files){
                InputStream inputStream = assetM.open(assetSubDir +"/"+fileName);
                byte [] buffer = new byte[inputStream.available()];
                inputStream.read(buffer);
                inputStream.close();

                File destFile = new File(folder, fileName);
                FileOutputStream output = new FileOutputStream(dir);
                output.write(buffer);
                output.close();
            }
        } catch (Exception e){
            Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME
                + " (_ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME + " TEXT, "
                + PHONE + " TEXT, "
                + PHOTO + " TEXT, "
                + ABOUT + " TEXT);");

        String dir = String.format("%s/%s", Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath(), PHOTOS_DIR);
        copyMedia(dir);

        ContentValues values = new ContentValues();

        values.put(NAME, "Jacob Anderson");
        values.put(PHONE, "412412411");
        values.put(PHOTO, dir + "/a1.png");
        values.put(ABOUT, "Lorem ipsum dolor sit amet, consectetur elit.");
        db.insert(TABLE_NAME, null, values);

        values.put(NAME, "Emily Duncan");
        values.put(PHONE, "161863187");
        values.put(PHOTO, dir + "/a2.png");
        values.put(ABOUT, "Nunc eget ipsum fringilla, dapibus orci nec");
        db.insert(TABLE_NAME, null, values);

        values.put(NAME, "Michael Fuller");
        values.put(PHONE, "896443658");
        values.put(PHOTO, dir + "/a3.png");
        values.put(ABOUT, "Ut consectetur molestie justo, at posere turpis");
        db.insert(TABLE_NAME, null, values);

        values.put(NAME, "Emma Greenman");
        values.put(PHONE, "964990543");
        values.put(PHOTO, dir + "/a4.png");
        values.put(ABOUT, "Donec mattis fermentum libero non eleifend");
        db.insert(TABLE_NAME, null, values);

        values.put(NAME, "Joshua Harrison");
        values.put(PHONE, "759285086");
        values.put(PHOTO, dir + "/a5.png");
        values.put(ABOUT, "Duis tincidunt elit sit amet velit rutrum");
        db.insert(TABLE_NAME, null, values);

        values.put(NAME, "Madison Johnson");
        values.put(PHONE, "950285777");
        values.put(PHOTO, dir + "/a6.png");
        values.put(ABOUT, "Praesent eu metus nec ante facilisis tincidunt");
        db.insert(TABLE_NAME, null, values);

        values.put(NAME, "Matthew Cotman");
        values.put(PHONE, "687699999");
        values.put(PHOTO, dir + "/a7.png");
        values.put(ABOUT, "Proin scelerisque, dolor ornare ulrice portitor");
        db.insert(TABLE_NAME, null, values);

        values.put(NAME, "Olivia Lawson");
        values.put(PHONE, "161863187");
        values.put(PHOTO, dir + "/a8.png");
        values.put(ABOUT, "Vestibulum sed lacus ac nisi pulvinar hendrerit");
        db.insert(TABLE_NAME, null, values);

        values.put(NAME, "Daniel Honeyman");
        values.put(PHONE, "876545644");
        values.put(PHOTO, dir + "/a9.png");
        values.put(ABOUT, "Ut sit amet ultrices velit, non euismod elit");
        db.insert(TABLE_NAME, null, values);

        values.put(NAME, "Isabella Jackson");
        values.put(PHONE, "907868756");
        values.put(PHOTO, dir + "/a10.png");
        values.put(ABOUT, "Proin non orci mollis");
        db.insert(TABLE_NAME, null, values);

        values.put(NAME, "William Patterson");
        values.put(PHONE, "687699693");
        values.put(PHOTO, dir + "/a11.png");
        values.put(ABOUT, "Vivamus facilisis nisl convallis");
        db.insert(TABLE_NAME, null, values);

        values.put(NAME, "Joseph Godwin");
        values.put(PHONE, "965467575");
        values.put(PHOTO, dir + "/a12.png");
        values.put(ABOUT, "Duis odio diam, varius a cursus ut");
        db.insert(TABLE_NAME, null, values);

        values.put(NAME, "Andrew Chapman");
        values.put(PHONE, "896874556");
        values.put(PHOTO, dir + "/a13.png");
        values.put(ABOUT, "Sed interdum nibh ac scelerisque euismod");
        db.insert(TABLE_NAME, null, values);

        values.put(NAME, "Samantha Bush");
        values.put(PHONE, "907865645");
        values.put(PHOTO, dir + "/a14.png");
        values.put(ABOUT, "Maecenas commodo mi a orci ultricies");
        db.insert(TABLE_NAME, null, values);

        values.put(NAME, "Christopher Gateman");
        values.put(PHONE, "896874556");
        values.put(PHOTO, dir + "/a15.png");
        values.put(ABOUT, "Aenean imperdiet porttitor lacinia");
        db.insert(TABLE_NAME, null, values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
