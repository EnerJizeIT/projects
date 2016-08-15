package com.example.enerjizeit.downloadmanagerandroidapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private final String PREF_DOWNLOAD_ID =
            "com.samples.service.downloadmanager.DOWNLOAD_ID";
    private SharedPreferences prefManager;
    private DownloadManager downManager;
    private ImageView image;
    private BroadcastReceiver downloadReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context arg0, Intent arg1) {
            getDownloadFile();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prefManager = PreferenceManager.getDefaultSharedPreferences(this);
        downManager = (DownloadManager)getSystemService(DOWNLOAD_SERVICE);
        image = (ImageView)findViewById(R.id.imageView);

        Button btn = (Button)findViewById(R.id.btnStart);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri downloadUri = Uri.parse("http://s00.yaplakal.com/pics/pics_original/4/1/0/6269014.jpg");
                DownloadManager.Request request =
                        new DownloadManager.Request(downloadUri);

                long id = downManager.enqueue(request);

                /* сохраняем id в преференс */
                Editor editor = prefManager.edit();
                editor.putLong(PREF_DOWNLOAD_ID, id);
                editor.commit();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
        registerReceiver(downloadReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(downloadReceiver);
    }

    private void getDownloadFile() {
        DownloadManager.Query query = new DownloadManager.Query();
        query.setFilterById(prefManager.getLong(PREF_DOWNLOAD_ID, 0));
        Cursor cursor = downManager.query(query);

        if(cursor.moveToFirst()){
            int columnIndex = cursor.getColumnIndex(DownloadManager.COLUMN_STATUS);
            int status = cursor.getInt(columnIndex);

            if(status == DownloadManager.STATUS_SUCCESSFUL){
                openFile();
            }
        }
    }

    private void openFile() {
        try{
            long id = prefManager.getLong(PREF_DOWNLOAD_ID, 0);
            ParcelFileDescriptor desc = downManager.openDownloadedFile(id);
            FileInputStream is = new ParcelFileDescriptor.AutoCloseInputStream(desc);
            Bitmap bitmap = BitmapFactory.decodeStream(is);
            image.setImageBitmap(bitmap);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
