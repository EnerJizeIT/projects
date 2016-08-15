package com.example.enerjizeit.contentproviderstudy;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;

public class ContactProvider extends ContentProvider{

    private static final Uri CONTENT_URI = Uri.parse("content://com.example.enerjizeit.contentproviderstudy/people");
    private SQLiteDatabase db;

    @Override /* инициализация базы данных */
    public boolean onCreate() {
        db = new ContactDbHelper(getContext()).getWritableDatabase();
        return (db == null) ? false : true;
    }

    @Nullable
    @Override /* для возвращения данных вызывающей программе */
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        String orderBy;
        if(TextUtils.isEmpty(sortOrder)){
            orderBy = ContactDbHelper.NAME;
        } else {
            orderBy = sortOrder;
        }
        Cursor cursor = db.query(ContactDbHelper.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Nullable
    @Override /* для вставки новых данных в Content Provider */
    public Uri insert(Uri url, ContentValues invalues) {
        ContentValues values = new ContentValues(invalues);

        long rowId = db.insert(ContactDbHelper.TABLE_NAME, ContactDbHelper.NAME, values);

        if(rowId > 0){
            Uri uri = ContentUris.withAppendedId(CONTENT_URI, rowId);
            getContext().getContentResolver().notifyChange(uri, null);
            return uri;
        } else {
            throw new SQLiteException("Failed to insert row into" + url);
        }
    }

    @Override /* для удаления данных в Content Provider */
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int retVal = db.delete(ContactDbHelper.TABLE_NAME, selection, selectionArgs);
        getContext().getContentResolver().notifyChange(uri, null);
        return retVal;
    }

    @Override /* для обновления существующих данных в Content Provider */
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int retVal = db.update(ContactDbHelper.TABLE_NAME, values, selection, selectionArgs);
        getContext().getContentResolver().notifyChange(uri, null);
        return retVal;
    }

    @Nullable
    @Override /* для возвращения типа MIME данных в Content Provider. Этот метод
реализовывать необязательно, если в нем нет необходимости. */
    public String getType(Uri uri) {
        return null;
    }
}
