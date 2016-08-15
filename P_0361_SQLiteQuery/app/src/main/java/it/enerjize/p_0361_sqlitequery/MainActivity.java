package it.enerjize.p_0361_sqlitequery;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

public class MainActivity extends Activity implements View.OnClickListener {

    final String LOG_TAG = "myLogs";
    /* В данных трёх массивах заложенны значения для сортировки */
    String name[] = {"Китай", "США", "Бразилия", "Россия", "Япония", "Германия", "Египет", "Италия", "Франция", "Канада"};
    int people[] = {1400, 311, 195, 145, 128, 82, 80, 60, 66, 35 };
    String region[] = {"Азия", "Америка", "Америка", "Европа", "Азия", "Европа", "Африка", "Европа", "Европа", "Америка"};

    Button btnAll, btnFunc, btnPeople, btnSort, btnGroup, btnHaving;
    EditText etFunc, etPeople, etRegionPeople;
    RadioGroup rgSort;

    DBHelper dbHelper;
    SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAll = (Button)findViewById(R.id.btnAll);
        btnFunc = (Button)findViewById(R.id.btnFunc);
        btnPeople = (Button)findViewById(R.id.btnPeople);
        btnSort = (Button)findViewById(R.id.btnSort);
        btnGroup = (Button)findViewById(R.id.btnGroup);
        btnHaving = (Button)findViewById(R.id.btnHaving);
        btnAll.setOnClickListener(this);
        btnFunc.setOnClickListener(this);
        btnPeople.setOnClickListener(this);
        btnSort.setOnClickListener(this);
        btnGroup.setOnClickListener(this);
        btnHaving.setOnClickListener(this);

        etFunc = (EditText)findViewById(R.id.etFunc);
        etPeople = (EditText)findViewById(R.id.etPeople);
        etRegionPeople = (EditText)findViewById(R.id.etRegionPeople);

        rgSort = (RadioGroup) findViewById(R.id.rgSort);

        dbHelper = new DBHelper(this);
        database = dbHelper.getWritableDatabase();

        // проверка существования записей
        Cursor cursor = database.query("mytable", null, null, null, null, null, null);
        if(cursor.getCount() == 0){ /* если в курсоре содержится 0 строк */
            ContentValues contentValues = new ContentValues(); /* Класс ContentValues используется для указания полей таблицы и значений, который в эти поля мы будем вставлять. */
            // заполняем таблицу значениями из массивов
            for(int i = 0; i<10; i++){
                contentValues.put("name", name[i]);
                contentValues.put("people", people[i]);
                contentValues.put("region", region[i]);
                Log.d(LOG_TAG, "id = " + database.insert("mytable", null, contentValues));
            }
        }
        dbHelper.close();
        // эмулируем нажатие кнопки btnAll
        onClick(btnAll);
    }

    @Override
    public void onClick(View v) {
        // подключаемся к базе
        database = dbHelper.getWritableDatabase();

        //считываем данные с экрана
        String sFunc = etFunc.getText().toString();
        String sPeople = etPeople.getText().toString();
        String sRegionPeople = etRegionPeople.getText().toString();

        // переменные для query
        String[] columns = null;
        String selection = null;
        String[] selectionArgs = null;
        String groupBy = null;
        String having = null;
        String orderBy = null;

        // курсор
        Cursor cursor = null;

        // определяем нажатую кнопку
        switch (v.getId()) {
            case R.id.btnAll:
                Log.d(LOG_TAG, "--- Все записи ---");
                cursor = database.query("mytable", null, null, null, null, null, null);
                break;
            case R.id.btnFunc:
                Log.d(LOG_TAG, "--- Функция " + sFunc + " ---");
                columns = new String[]{sFunc}; /* в данной строке мы подбираем условия*/
                cursor = database.query("mytable", columns, null, null, null, null, null);
                break;
            case R.id.btnPeople:
                Log.d(LOG_TAG, "--- Население больше " + sPeople + " ---");
                selection = "people > ?";
                selectionArgs = new String[]{sPeople};
                cursor = database.query("mytable", null, selection, selectionArgs, null, null, null);
                break;
            case R.id.btnGroup:
                Log.d(LOG_TAG, "--- Население по региону ---");
                columns = new String[]{"region", "sum(people) as people"};
                groupBy = "region";
                cursor = database.query("mytable", columns, null, null, groupBy, null, null);
                break;
            case R.id.btnHaving:
                Log.d(LOG_TAG, "--- Регионы с населением больше " + sRegionPeople
                        + " ---");
                columns = new String[] { "region", "sum(people) as people" };
                groupBy = "region";
                having = "sum(people) > " + sRegionPeople;
                cursor = database.query("mytable", columns, null, null, groupBy, having, null);
                break;
            case R.id.btnSort:
                switch (rgSort.getCheckedRadioButtonId()) {
                    case (R.id.rName):
                        Log.d(LOG_TAG, "--- Сортировка по наименованию ---");
                        orderBy = "name";
                        break;
                    case (R.id.rPeople):
                        Log.d(LOG_TAG, "--- Сортировка по населению ---");
                        orderBy = "people";
                        break;
                    case (R.id.rRegion):
                        Log.d(LOG_TAG, "--- Сортировка по региону ---");
                        orderBy = "region";
                        break;
                }
                cursor = database.query("mytable", null, null, null, null, null, orderBy);
                break;
        }
        if(cursor != null){
            if(cursor.moveToFirst()){
                String str;
                do{
                    str = "";
                    for (String cn: cursor.getColumnNames()){
                        str = str.concat(cn + " = " + cursor.getString(cursor.getColumnIndex(cn)) + "; ");
                    }
                    Log.d(LOG_TAG, str);
                } while (cursor.moveToNext());
            }
        } else Log.d(LOG_TAG, "Cursor  is null");

        dbHelper.close();
    }


    class DBHelper extends SQLiteOpenHelper{

        public DBHelper(Context context) {
            // конструктор суперкласса
            super(context, "myDB", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.d(LOG_TAG,"--- onCreate database ---");
            // создаём аблицу с полями
            db.execSQL("create table mytable ("
            + "id integet primary key autoincrement," + "name text,"
            + "people integer," + "region text" + ");");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}
