package it.enerjize.p_0381_sqlitetransaction;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {

    final String LOG_TAG = "myLogs";

    DBHelper dbHelper;
    SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(LOG_TAG, "--- On Create Activity ---");
        dbHelper = new DBHelper(this);
        myActions();
    }

    void myActions(){
        database = dbHelper.getWritableDatabase();
        delete(database, "mytable");
        /* Мы подключаемся к базе через метод getWritableDatabase, чистим таблицу, открываем транзакцию  */
        database.beginTransaction();
        insert(database, "mytable", "vall");
        /* Методом setTransactionSuccessful мы явно указываем что транзакция должна быть успешно закрыта,
         * если этого не сделать, то при закрытии транзакции все операции ОТМЕНЯТСЯ(val1 и val2 не будут записаны) */
        database.setTransactionSuccessful();
        insert(database, "mytable", "val3");
        /* Данным методом мы отключаем транзакцию. И несмотря на то, что val3 мы вставляли после подтверждения
        * успешности транзакции, и val2 мы вставляли после закрытия транзакции, все три записи в таблице.*/
        database.endTransaction();
        insert(database, "mytable", "val2");
        read(database, "mytable");
        dbHelper.close();
        /* Так же важно понимать ТРАНЗАКЦИЯ ПРИ ОТКРЫТИИ СТАВИТ БЛОКИРОВКУ НА БАЗУ. А это значит что создав
         * DBHelper dbh2 = new DBHelper(this); SQLiteDatabase db2 = dbh2.getWritableDatabase(); в рамках открытой
         * транзакции выбросит исключение SQLiteException database is locked */
    }
    void insert(SQLiteDatabase database, String table, String value){
        Log.d(LOG_TAG,"Insert in table " + table + " value" + value);
        ContentValues contentValues = new ContentValues();
        contentValues.put("val", value);
        database.insert(table, null, contentValues);
    }
    void read(SQLiteDatabase databases, String table){
        Log.d(LOG_TAG, "Read table" + table);
        Cursor cursor = database.query(table, null, null, null, null, null, null);
        if(cursor != null){
            Log.d(LOG_TAG, "Records count = " + cursor.getCount());
            if(cursor.moveToFirst()){
                do{
                    Log.d(LOG_TAG, cursor.getString(cursor.getColumnIndex("val")));
                } while (cursor.moveToNext());
            }
        }
    }
    void delete(SQLiteDatabase database, String table){
        Log.d(LOG_TAG, "Delete all from table" + table);
        database.delete(table, null, null);
    }

    class DBHelper extends SQLiteOpenHelper{

        public DBHelper(Context context) {
            super(context, "myDb", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.d(LOG_TAG, "--- onCreate database ---");

            database.execSQL("create table mytable ("
            + "id integer primary key autoinzrement,"
            + "val text"
            + ");");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}
