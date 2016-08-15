package com.example.enerjizeit.p_1011_contentprovider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

public class MyContProvider extends ContentProvider{
    final String LOG_TAG = "myLogs";

    static final String DB_NAME = "myBD";
    static final int VERSION = 1;

    static final String TABLE_NAME = "TABLE_CONTACTS";

    static final String CONTACT_ID = "_id";
    static final String CONTACT_NAME = "name";
    static final String CONTACT_EMAIL = "email";

    static final String DB_CREATE = "create table " + TABLE_NAME + "("
            + CONTACT_ID + " integer primary key autoincrement, "
            + CONTACT_NAME + " text, " + CONTACT_EMAIL + " text);";


    /* константы AUTHORITY и CONTACT_PATH – это составные части Uri.
     Их этих двух констант и префикса content:// мы формируем общий Uri - CONTACT_CONTENT_URI.
      Т.к. здесь не указан никакой ID, этот Uri дает доступ ко всем контактам. */
    /* URI *//* autority */
    static final String AUTHORITY = "com.enerjizeit.providers.AdressBook";
    /* path */
    static final String CONTACT_PATH = "contacts";
    /* общий uri */
    public static final Uri CONTACT_CONTENT_URI = Uri.parse("content://"
        + AUTHORITY + "/" + CONTACT_PATH);

    /* описываем MIME-типы данных, предоставляемых провайдером. Один для набора данных, другой для конкретной записи.
  Все пользовательские типы данных должны начинаться с vnd.android.cursor.dir/vnd для директорий,
   и с vnd.android.cursor.item/vnd для отдельных записей. */
    /* Типы Данных */ /* набор строк */
    static final String CONTACT_CONTENT_TYPE = "vnd.android.curcor.dir/vnd."
            +AUTHORITY +"."+ CONTACT_PATH;
    static final String CONTACT_CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd."
            +AUTHORITY+"."+CONTACT_PATH;

    /* UriMatcher *//* Общий uri */
    /* В Android SDK есть класс UriMatcher, который хранит соответствие между Uri и неким заданным значение
integer. Это значение можно использовать в операторе switch, чтобы описать поведение для каждого Content URI. */
    static final int URI_CONTACTS = 1;
    /* Uri с указанным ID */
    static final int URI_CONTACTS_ID = 2;
    /* Описание и создание UriMatcher */
    private static final UriMatcher uriMatcher;
    static {
        /* В методе addURI мы даем ему комбинацию: authority, path и константа. Причем, мы можем использовать
         спецсимволы: * - строка любых символов любой длины, # - строка цифр любой длины. На вход провайдеру
          будут поступать Uri, и мы будем сдавать их в UriMatcher на проверку. Если Uri будет подходить под
           комбинацию authority и path, ранее добавленных в addURI, то UriMatcher вернет константу из того же
            набора: authority, path, константа. */
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, CONTACT_PATH, URI_CONTACTS);
        /* строка:
        uriMatcher.addURI(AUTHORITY, CONTACT_PATH, URI_CONTACTS);
        означает, что мы добавили в uriMatcher комбинацию значений AUTHORITY, CONTACT_PATH и URI_CONTACTS.  */
        uriMatcher.addURI(AUTHORITY, CONTACT_PATH + "/#", URI_CONTACTS_ID);
    }

    DBHelper dbHelper;
    SQLiteDatabase db;

    @Override
    public boolean onCreate() {
        Log.i(LOG_TAG, "On Create");
        dbHelper = new DBHelper(getContext());
        return true;
    }

    @Nullable
    @Override /* Чтение */
    /* мы получаем на вход Uri и набор параметров для выборки из БД: projection - столбцы,
     selection - условие, selectionArgs – аргументы для условия, sortOrder – сортировка. */
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        Log.i(LOG_TAG, "query, " + uri.toString());

        /* Проверяем Uri */
        /* Если мы попросим uriMatcher проверить Uri, состоящий из AUTHORITY и CONTACT_PATH,
        то он вернет нам значение URI_CONTACTS. А если дадим ему Uri, состоящий из AUTHORITY,
        CONTACT_PATH и числа (ID), то он вернет нам URI_CONTACTS_ID. А мы по этим константам определим
        – работать со всеми записями или какой-то конкретной. */
        switch (uriMatcher.match(uri)){
            case URI_CONTACTS: /* Общий Uri */
                Log.i(LOG_TAG, "URI_CONTACTS");
                /* Если сортировка не указана, ставим свою по имени */
                if(TextUtils.isEmpty(sortOrder)){
                    sortOrder = CONTACT_NAME + " ASC";
                }
                break;
            case URI_CONTACTS_ID: /* Uri c ID */
    /* Если мы получили URI_CONTACTS_ID, то провайдер должен вернуть запись по конкретному ID.
    Для этого мы извлекаем ID из Uri методом getLastPathSegment и добавляем его в условие selection. */
                String id = uri.getLastPathSegment();
                Log.i(LOG_TAG, "URI_CONTACTS_ID" + id);
                /* добавим Id к условию выборки */
                if(TextUtils.isEmpty(selection)){
                    selection = CONTACT_ID + " = " + id;
                } else {
                    selection = selection + " AND " + CONTACT_ID + " = " + id;
                }
                break;
            default:
                throw new IllegalArgumentException("Wrong URI: " + uri);
        }
        db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query(TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
        /* просим ContentResolver уведомлять этот курсор об изменениях данных в CONTACT_CONTENT_URI */
        cursor.setNotificationUri(getContext().getContentResolver(), CONTACT_CONTENT_URI);

        return cursor;
    }

    @Nullable
    @Override
/* мы проверяем, что нам пришел наш общий Uri. Если все ок, то вставляем данные в таблицу, получаем ID.
 Этот ID мы добавляем к общему Uri и получаем Uri с ID. По идее, это можно сделать и обычным сложением строк,
 но рекомендуется использовать метод withAppendedId объекта. Далее мы уведомляем систему, что поменяли данные,
  соответствующие resultUri. Система посмотрит, не зарегистрировано ли слушателей на этот Uri. Увидит, что мы
   регистрировали курсор, и даст ему знать, что данные обновились. В конце мы возвращаем resultUri, соответствующий
    новой добавленной записи. */
    public Uri insert(Uri uri, ContentValues values) {
        Log.i(LOG_TAG, "insert, " + uri.toString());
        if (uriMatcher.match(uri) != URI_CONTACTS){
            throw new IllegalArgumentException("Wrong URI: " + uri);
        }
        db = dbHelper.getWritableDatabase();
        long rowID = db.insert(TABLE_NAME, null, values);
        Uri resultUri = ContentUris.withAppendedId(CONTACT_CONTENT_URI, rowID);
        /* Уведомляем ContentResolver, что данные по адресу resultUri изменились */
        getContext().getContentResolver().notifyChange(resultUri, null);
        return resultUri;
    }

    @Override
/* В delete мы проверяем, какой Uri нам пришел. Если с ID, то фиксим selection – добавляем туда условие по ID.
 Выполняем удаление в БД, получаем кол-во удаленных записей. Уведомляем, что данные изменились.
 Возвращаем кол-во удаленных записей. */
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        Log.i(LOG_TAG, "delete, " + uri.toString());
        switch (uriMatcher.match(uri)){
            case URI_CONTACTS:
                Log.i(LOG_TAG, "URI_CONTACTS");
                break;
            case URI_CONTACTS_ID:
                String id = uri.getLastPathSegment();
                Log.i(LOG_TAG, "URI_CONTACTS_ID, " + id);
                if (TextUtils.isEmpty(selection)){
                    selection = CONTACT_ID + " = " + id;
                } else {
                    selection = selection + " AND " + CONTACT_ID + " = " + id;
                }
                break;
            default:
                throw new IllegalArgumentException("Wrong URI: " + uri);
        }
        db = dbHelper.getWritableDatabase();
        int cnt = db.delete(TABLE_NAME, selection, selectionArgs);
        getContext().getContentResolver().notifyChange(uri, null);
        return cnt;
    }

    @Override
/* В update мы проверяем, какой Uri нам пришел. Если с ID, то фиксим selection – добавляем туда условие по ID.
    Выполняем обновление в БД, получаем кол-во обновленных записей. Уведомляем, что данные изменились.
    Возвращаем кол-во обновленных записей. */
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        Log.i(LOG_TAG, "update, " + uri.toString());
        switch (uriMatcher.match(uri)){
            case URI_CONTACTS:
                Log.i(LOG_TAG, "URI_CONTACTS");
                break;
            case URI_CONTACTS_ID:
                String id = uri.getLastPathSegment();
                Log.i(LOG_TAG, "URI_CONTACTS_ID" + id);
                if (TextUtils.isEmpty(selection)){
                    selection = CONTACT_ID + " = " + id;
                } else {
                    selection = selection + " AND " + CONTACT_ID + " = " + id;
                }
                break;
            default:
                throw new IllegalArgumentException("Wrong Uri: " + uri);
        }
        db = dbHelper.getWritableDatabase();
        int cnt = db.update(TABLE_NAME, values, selection, selectionArgs);
        getContext().getContentResolver().notifyChange(uri, null);
        return cnt;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        Log.i(LOG_TAG, "getType, " + uri.toString());
        switch (uriMatcher.match(uri)){
            case URI_CONTACTS:
                return CONTACT_CONTENT_TYPE;
            case URI_CONTACTS_ID:
                return CONTACT_CONTENT_ITEM_TYPE;
        }
        return null;
    }
}