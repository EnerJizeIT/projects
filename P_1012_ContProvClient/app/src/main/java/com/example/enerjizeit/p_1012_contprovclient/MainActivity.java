package com.example.enerjizeit.p_1012_contprovclient;

import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    final String LOG_TAG = "myLogs";
    /* В CONTACT_URI мы храним общий Uri. В CONTACT_NAME и CONTACT_EMAIL – имена полей. */
    final Uri CONTACT_URI = Uri.parse("content://com.enerjizeit.providers.AdressBook/contacts");
    final String CONTACT_NAME = "name";
    final String CONTACT_EMAIL = "email";

    private Button btnInsert, btnUpdate, btnDelete, btnError;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnInsert = (Button)findViewById(R.id.btnInsert);
        btnUpdate = (Button)findViewById(R.id.btnUpdate);
        btnDelete = (Button)findViewById(R.id.btnDelete);
        btnError = (Button)findViewById(R.id.btnError);
        btnInsert.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnError.setOnClickListener(this);
        /* В onCreate мы используем метод getContentResolver, чтобы получить ContentResolver.
  Этот объект – посредник между нами и провайдером. Мы вызываем его метод query и передаем туда Uri.
  Остальные параметры оставляем пустыми – т.е. нам вернутся все записи, все поля и сортировку мы не задаем.
Полученный курсор мы передаем в Activity на управление – метод startManagingCursor. Далее создаем адаптер и присваиваем его списку. */
        Cursor cursor = getContentResolver().query(CONTACT_URI, null, null, null, null);
        startManagingCursor(cursor);

        String from[] = {"name", "email"};
        int to[] = {android.R.id.text1,android.R.id.text2};
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2, cursor, from, to);

        ListView listView = (ListView)findViewById(R.id.lvContact);
        listView.setAdapter(adapter);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnInsert:
  /* В onClickInsert мы используем метод insert для добавления записей в провайдер.
            Этот метод возвращает нам Uri, соответствующий новой записи. */
                ContentValues cv = new ContentValues();
                cv.put(CONTACT_NAME, "name 4");
                cv.put(CONTACT_EMAIL, "email 4");
                Uri uriIns = getContentResolver().insert(CONTACT_URI, cv);
                Log.i(LOG_TAG, "insert, result Uri: " + uriIns.toString());
                break;
            case R.id.btnUpdate:
                /* В onClickUpdate мы создаем Uri, соответствующий записи с ID = 2, и апдейтим эту запись в провайдере. */
                ContentValues cvUpd = new ContentValues();
                cvUpd.put(CONTACT_NAME, "nail 5");
                cvUpd.put(CONTACT_EMAIL, "email 5");
                Uri uriUpd = ContentUris.withAppendedId(CONTACT_URI, 2);
                int cntUpd = getContentResolver().update(uriUpd, cvUpd, null, null);
                Log.i(LOG_TAG, "update, count = " + cntUpd);
                break;
            case R.id.btnDelete:
/* В onClickDelete мы создаем Uri, соответствующий записи с ID = 3, и удаляем эту запись в провайдере. */
                Uri uriDel = ContentUris.withAppendedId(CONTACT_URI, 3);
                int cntDel = getContentResolver().delete(uriDel, null, null);
                Log.i(LOG_TAG, "delete, count = " + cntDel);
                break;
            case R.id.btnError:
/* В onClickError мы пытаемся получить записи по Uri, который не знает провайдер.
В его uriMatcher не добавляли информации об этом Uri. В этом случае мы генерировали в провайдере ошибку. Здесь попробуем поймать ее. */
                Uri uriErr = Uri.parse("content://com.enerjizeit.providers.AdressBook/people");
                try{
                    Cursor cursor = getContentResolver().query(uriErr, null, null, null, null);
                } catch (Exception e){
                    Log.i(LOG_TAG, "Error: " + e.getClass() + ", " + e.getMessage());
                }
                break;
            default:
                Toast.makeText(MainActivity.this, "Unread command", Toast.LENGTH_LONG).show();
                break;
        }

    }
}
