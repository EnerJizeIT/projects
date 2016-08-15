package com.example.enerjizeit.sqliteproject;


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

public class DBHelperClass extends SQLiteOpenHelper implements BaseColumns{
    public static final String DB_ITEMS = "items.db";
    public static final String TABLE_NAME = "friends";
    public static final String NAME = "name";
    public static final String PHONE = "phone";
    public static final String EMAIL = "email";
    public static final String PHOTO = "photo";


    public static final String PHOTOS_DIR = "FotoItemsLab";
    public Context context;

    public DBHelperClass(Context context) {
        /* В конструкторе мы вызываем конструктор суперкласса и передаем ему:
        context - контекст
        DB_ITEMS - название базы данных
        null – объект для работы с курсорами, нам пока не нужен, поэтому null
        1 – версия базы данных */
        super(context, DB_ITEMS, null, 1);
        this.context = context;
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    private void createFoto(String dir) {
        try{
            File folder = new File(dir);
            if(!folder.exists()){
                folder.mkdir();
                AssetManager assetManager = context.getAssets();

                final String assetSubDir = "photos";

                String []files = assetManager.list(assetSubDir);
                for (String fileName : files){
                    InputStream inputStream = assetManager.open(assetSubDir+"/"+fileName);
                    byte[] byteBuffer = new byte[inputStream.available()];
                    inputStream.read(byteBuffer);
                    inputStream.close();

                    File destFile = new File (folder, fileName);
                    FileOutputStream output = new FileOutputStream(destFile);
                    output.write(byteBuffer);
                    output.close();
                }
            }
        } catch (Exception e){
            Toast.makeText(context,e.toString(),Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (_ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME + " TEXT, "
                + PHONE + " TEXT, "
                + PHOTO + " TEXT, "
                + EMAIL + " TEXT);");

        String dir = String.format("%s/%s", Environment.getExternalStorageDirectory().getPath(), PHOTOS_DIR);
        createFoto(dir);

        ContentValues values = new ContentValues();

        values.put(NAME, "Jacob Anderson");
        values.put(PHONE, "412412411");
        values.put(PHOTO, dir + "/a1.png");
        values.put(EMAIL, "Lorem ipsum dolor sit amet, consectetur elit.");
        db.insert(TABLE_NAME, null, values);

        values.put(NAME, "Emily Duncan");
        values.put(PHONE, "161863187");
        values.put(PHOTO, dir + "/a2.png");
        values.put(EMAIL, "Nunc eget ipsum fringilla, dapibus orci nec");
        db.insert(TABLE_NAME, null, values);

        values.put(NAME, "Michael Fuller");
        values.put(PHONE, "896443658");
        values.put(PHOTO, dir + "/a3.png");
        values.put(EMAIL, "Ut consectetur molestie justo, at posere turpis");
        db.insert(TABLE_NAME, null, values);

        values.put(NAME, "Emma Greenman");
        values.put(PHONE, "964990543");
        values.put(PHOTO, dir + "/a4.png");
        values.put(EMAIL, "Donec mattis fermentum libero non eleifend");
        db.insert(TABLE_NAME, null, values);

        values.put(NAME, "Joshua Harrison");
        values.put(PHONE, "759285086");
        values.put(PHOTO, dir + "/a5.png");
        values.put(EMAIL, "Duis tincidunt elit sit amet velit rutrum");
        db.insert(TABLE_NAME, null, values);

        values.put(NAME, "Madison Johnson");
        values.put(PHONE, "950285777");
        values.put(PHOTO, dir + "/a6.png");
        values.put(EMAIL, "Praesent eu metus nec ante facilisis tincidunt");
        db.insert(TABLE_NAME, null, values);

        values.put(NAME, "Matthew Cotman");
        values.put(PHONE, "687699999");
        values.put(PHOTO, dir + "/a7.png");
        values.put(EMAIL, "Proin scelerisque, dolor ornare ulrice portitor");
        db.insert(TABLE_NAME, null, values);

        values.put(NAME, "Olivia Lawson");
        values.put(PHONE, "161863187");
        values.put(PHOTO, dir + "/a8.png");
        values.put(EMAIL, "Vestibulum sed lacus ac nisi pulvinar hendrerit");
        db.insert(TABLE_NAME, null, values);

        values.put(NAME, "Daniel Honeyman");
        values.put(PHONE, "876545644");
        values.put(PHOTO, dir + "/a9.png");
        values.put(EMAIL, "Ut sit amet ultrices velit, non euismod elit");
        db.insert(TABLE_NAME, null, values);

        values.put(NAME, "Isabella Jackson");
        values.put(PHONE, "907868756");
        values.put(PHOTO, dir + "/a10.png");
        values.put(EMAIL, "Proin non orci mollis");
        db.insert(TABLE_NAME, null, values);

        values.put(NAME, "William Patterson");
        values.put(PHONE, "687699693");
        values.put(PHOTO, dir + "/a11.png");
        values.put(EMAIL, "Vivamus facilisis nisl convallis");
        db.insert(TABLE_NAME, null, values);

        values.put(NAME, "Joseph Godwin");
        values.put(PHONE, "965467575");
        values.put(PHOTO, dir + "/a12.png");
        values.put(EMAIL, "Duis odio diam, varius a cursus ut");
        db.insert(TABLE_NAME, null, values);

        values.put(NAME, "Andrew Chapman");
        values.put(PHONE, "896874556");
        values.put(PHOTO, dir + "/a13.png");
        values.put(EMAIL, "Sed interdum nibh ac scelerisque euismod");
        db.insert(TABLE_NAME, null, values);

        values.put(NAME, "Samantha Bush");
        values.put(PHONE, "907865645");
        values.put(PHOTO, dir + "/a14.png");
        values.put(EMAIL, "Maecenas commodo mi a orci ultricies");
        db.insert(TABLE_NAME, null, values);

        values.put(NAME, "Christopher Gateman");
        values.put(PHONE, "896874556");
        values.put(PHOTO, dir + "/a15.png");
        values.put(EMAIL, "Aenean imperdiet porttitor lacinia");
        db.insert(TABLE_NAME, null, values);
    }
}
