package it.enerjize.p_0371_sqliteinnerjoin;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {

    final String LOG_TAG ="myLogs";
    /* Данные для таблицы должностей. Для ДОЛЖНОСТЕЙ мы будем указывать id при заполнении таблиц.
     * Это сделано для того, чтобы мы знали эти номера и могли их использовать в таблицу ЛЮДЕЙ для указания Id должности. */
    int [] position_id = {1,2,3,4};
    String[] position_name = {"Директор","Программер","Бухгалтер","Охранник"};
    int [] possition_salary = {15000, 13000, 10000, 8000};
    /* Данные для таблицы людей */
    String[]people_name = {"Иван","Марья","Пётр","Антон","Даша","Борис","Костя","Игорь"};
    int[] people_posid = {2,3,2,2,3,1,2,4};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Подключаемся к БД */
        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        /* Описание курсора */
        Cursor cursor;

        /* Выводим в лог данные по должностям */
        Log.d(LOG_TAG, "--- Table position --- ");
        cursor = database.query("position", null,null,null,null,null,null);
        logCursor(cursor);
        cursor.close();
        Log.d(LOG_TAG, "--- ----");

        /* Выводим в лог данные по людям */
        Log.d(LOG_TAG, "--- Table people ---");
        cursor = database.query("people", null,null,null,null,null,null);
        logCursor(cursor);
        cursor.close();
        Log.d(LOG_TAG, "--- ---");

        /* Выводим результат объединения */
        /* Используем rowQuery. Это несложный метод, который принимает на вход SQL-запрос
         и список АРГУМЕНТОВ для условия WHERE(если необходимо). */
        Log.d(LOG_TAG, "--- INNER JOIN with rawQuery ---");
        Log.d(LOG_TAG, "Данные из rawQuery. ЗП > 12000");
        /* Мы сформировали запрос на объединения двух таблиц и вывода ИМЕНИ, ДОЛЖНОСТИ и ЗАРПЛАТЫ.
         * Условия выборки, ЗП должна быть больше 12000. И для форм условия мы используем арг WHERE */
        String sqlQuery = "select PL.name as Name, PS. name as Position, salary as Salary"
                + "from people as PL "
                + "inner join position as PS "
                + "on PL.posid = PS.id "
                + "where salary > ?";
        cursor = database.rawQuery(sqlQuery, new String[] {"12000"});
        logCursor(cursor);
        cursor.close();
        Log.d(LOG_TAG, "--- ---");

        /* Вновь выводим результат объединения, но используем обычный query. В table записываем все таблицы,
         * их алиасы и услоавие JOIN. */
        Log.d(LOG_TAG, "--- INNER JOIN with query ---");
        Log.d(LOG_TAG, "Данные из query. ЗП < 12000");
        String table = "people as PL inner join position as PS on PL.posid = PS.id";
        /* В columns все нужные поля с использованием алиасов. */
        String columns[] = {"PL.name as Name", "PS.name as Position", "salary as Salary" };
        /* Указываем условия выборки ЗП меньше 12000 */
        String selection = "selection < ?";
        String [] selectionArgs = {"12000"};
        cursor = database.query(table, columns, selection, selectionArgs, null, null, null);
        logCursor(cursor);
        cursor.close();
        Log.d(LOG_TAG, "--- ---");

        database.close();
        dbHelper.close();

    }

    /* Данный метод на вход получает Cursor и выводит в лог всё содержимое.*/
    void logCursor(Cursor cursor){
        if(cursor != null){
            if(cursor.moveToFirst()){
                String str;
                do{
                    str = "";
                    for(String cn : cursor.getColumnNames()){
                        str = str.concat(cn + " = " +
                                "" + cursor.getString(cursor.getColumnIndex(cn)) + "; ");
                    }
                    Log.d(LOG_TAG, str);
                } while (cursor.moveToNext());
            }
        } else Log.d(LOG_TAG, "Cursor is null");
    }

    /* Класс для работы с БД  */
    class DBHelper extends SQLiteOpenHelper{
        public DBHelper(Context context) {
            super(context, "myDd", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase database) {
            Log.d(LOG_TAG, "--- onCreate Database ---");

            ContentValues contentValues = new ContentValues();
            /* Создаём первую таблицу */
            database.execSQL("create table position ("
            + "id integer primary key,"
            + "name text," + "salary integer"
            + ");");
            /* Заполняем её */
            for(int i = 0; i < position_id.length; i++){
                contentValues.clear();
                contentValues.put("id", position_id[i]);
                contentValues.put("name", position_name[i]);
                contentValues.put("salary", possition_salary[i]);
                database.insert("position", null, contentValues); /* Второй аргумент(null) используется для вставки пустой строки */
            }

            /* Создаём таблицу людей */
            database.execSQL("create table people ("
            + "id integer primary key autoincrement,"
            + "name text," + "posid integer"
            +");");
            /* Заполняем её */
            for(int i = 0; i< people_name.length; i++){
                contentValues.clear();
                contentValues.put("name", people_name[i]);
                contentValues.put("posid", people_posid[i]);
                database.insert("people", null, contentValues);
            }

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}
