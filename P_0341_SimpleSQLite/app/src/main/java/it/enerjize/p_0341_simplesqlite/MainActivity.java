package it.enerjize.p_0341_simplesqlite;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements View.OnClickListener {

    final String LOG_TAG = "myLogs";
    Button btnAdd, btnRead, btnClean, btnUpd, btnDel;
    EditText etName,etEmail, etId;

    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd =(Button)findViewById(R.id.btnAdd);
        btnRead =(Button)findViewById(R.id.btnRead);
        btnClean =(Button)findViewById(R.id.btnClean);
        btnDel =(Button)findViewById(R.id.btnDel);
        btnUpd =(Button)findViewById(R.id.btnUpd);
        btnAdd.setOnClickListener(this);
        btnRead.setOnClickListener(this);
        btnClean.setOnClickListener(this);
        btnDel.setOnClickListener(this);
        btnUpd.setOnClickListener(this);

        etEmail = (EditText)findViewById(R.id.etEmail);
        etName = (EditText)findViewById(R.id.etName);
        etId = (EditText)findViewById(R.id.etId);
        /* Создали объект который наследуется от SQL класса */
        dbHelper = new DBHelper(this);

    }

    @Override
    public void onClick(View v) {
        String name = etName.getText().toString();
        String email = etEmail.getText().toString();
        String id = etId.getText().toString();
        /* С помощью метода getWritableDatabase() мы подключаемся к БД и получаем объект SQLiteDatabase для управления ей.
        * Он позволит нам работать с БД. Мы будем использовать его методы insert - вставка записи,
        * query - чтение, delete - удаление.*/
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        /* Класс ContentValues используется для указания полей таблицы и значений, который в эти поля мы будем вставлять. */
        ContentValues contentValues = new ContentValues();

        switch(v.getId()){
            case R.id.btnAdd:
                /* В данном методе мы вкладываем считанные значения по принципу КЛЮЧ-Значение*/
                contentValues.put(DBHelper.KEY_NAME, name);
                contentValues.put(DBHelper.KEY_MAIL, email);
                /* В методе Insert мы передаём имя таблицы и объект contentValues с вставленными значениями.
                * Второй аргумент(null) используется для вставки в таблицу пустой строки.
                * Данный метода также может возвращать id - Long для этого его пожно приравнять к переменной Long*/
                database.insert(DBHelper.TABLE_CONTACTS, null, contentValues);
                etName.setText("");
                etEmail.setText("");
                break;
            case R.id.btnRead:
                /* Для чтения используется метод query на вход ему подаётся имя таблицы + список запрашиваемых полей,
                 *   условия выборки, группировка, сортировка, тк они нам пока не нужны мы выставляем null.
                 *   Метод возвращает нам объект класса Cursor. Его можно рассматривать как таблицу с данными.*/
                Cursor cursor = database.query(DBHelper.TABLE_CONTACTS, null, null, null, null, null, null);
                /* Метод moveToFirst делает первую запись в Cursor активной и заодно проверяет, если вообзе записи в нём.
                 * т.е. выбралось вообще что-либо в методе query. */
                if(cursor.moveToFirst()){
                    /* Далее мы получаем порядковые номера столбцов в Cursor по их именам с помощью метода qetColumnIndex.
                     * Эти номера потом используем для чтения данных в методах getInt и getString и выводим данные в лог. */
                    int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
                    int nameIndex = cursor.getColumnIndex(DBHelper.KEY_NAME);
                    int emailIndex = cursor.getColumnIndex(DBHelper.KEY_MAIL);
                    do{
                        Log.d("myLogs", "ID = " + cursor.getInt(idIndex) + ", name = "
                        + cursor.getString(nameIndex) + ", email = " + cursor.getString(emailIndex));
                    /* с помощью метода moveToNext мы перебираем все строки в Cursor пока не добираемся до последней. */
                    } while (cursor.moveToNext());
                } else Log.d("myLogs", "0 rows");
                /* Данным методом мы закрываем Cursor для освобождения занимаемой памяти. */
                cursor.close();
                break;
            case R.id.btnClean:
                /* Метод delete удаляет записи. На вход ему передаём имя таблицы и null в качестве условий для удаления,
                 * это значит удалится всё. Метод может так же возвращать кол. int удалённых записией если приравнять его к переменной. */
                database.delete(DBHelper.TABLE_CONTACTS, null, null);
                break;
            case R.id.btnUpd:/* данной веткой мы будем по номеру id обновлять содержимое таблицы БД */
                if(id.equalsIgnoreCase("")){ /* если строка ID пуста */
                    break;
                }
                contentValues.put(DBHelper.KEY_MAIL, email); /* вводим новое значение */
                contentValues.put(DBHelper.KEY_NAME, name);
                /* используя метод update мы обновляем содержимое БД. На вход в агументах мы передаём название таблицы
                 * значения, и строку условия DBHelper.KEY_ID + "= ?" - При запросе к БД вместо знака ?
                  * будет поставлено значение из массива аргументов - {id}
                  * Если знаков ? в строке условия несколько, то им будут сопоставлены значения из массива по порядку.*/
                int updCount = database.update(DBHelper.TABLE_CONTACTS, contentValues, DBHelper.KEY_ID + "= ?", new String[]{id});
                Log.d("myLogs", "update rows count = " + updCount);
                etId.setText("");
                etName.setText("");
                etEmail.setText("");
                break;
            case R.id.btnDel:
                if(id.equalsIgnoreCase("")){
                    break;
                }
                /* Для удаления мы используем метод delete на вход передаём ему имя таблицы,строку условия,
                 * и массив аргументов для условия. Условия в данном методе эдентичны методу update, однако мы не используем условия,
                  * а указываем их и от этого необходимость исп массив и символ ? отпадает*/
                int delCount = database.delete(DBHelper.TABLE_CONTACTS, DBHelper.KEY_ID + "=" + id, null);
                Log.d("myLogs","deleted rows count = " + delCount);
                break;
        }
        /* Закрываем подключение к бд */
        database.close();

    }
}
