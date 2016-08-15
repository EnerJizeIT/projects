package it.enerjize.p_0341_simplesqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
/* Мы наследуемся от абстрактного класса SQLiteOpenHelper с помощью которого можно создавать, открывать и обновлять БД */
public class DBHelper extends SQLiteOpenHelper {
/* Данный класс служит для управления базой данных. Для работы с БД принято использовать открытые константы,
* для того чтобы их можно было использовать в др классах для работы с БД*/
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "constactDb";
    public static final String TABLE_CONTACTS = "contacts";

    /* Добавим константы для заголовков столбцов таблицы. */
    public static final String KEY_ID= "_id"; /* идентификатор должен использовать _ это особенность работы с cursor */
    public static final String KEY_NAME = "name";
    public static final String KEY_MAIL = "mail";

    public DBHelper(Context context) {
        /* В данном конструкторе суперкласса мы передаём в аргументах - Контекст, Название БД,
         * Объект расширяющий стандартный курсор SQLiteDatabase.CursorFactory (нам он пока не нужен поэтому null) и версию бд(1) */
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override  /* метод вызывается если бд не существовала и её необходимо создавать. */
    public void onCreate(SQLiteDatabase db) {
        /* Мы используем метод execSQL объекта SQLiteDatabase для выполнения SQL-запроса, который создаёт таблицу.
        В аргуметах мы передаём название(TABLE_CONTACTS) и поля(id, name, mail) */
        db.execSQL("create table " + TABLE_CONTACTS + "(" + KEY_ID + " integer primary key,"
                + KEY_NAME + " text," + KEY_MAIL + " text" + ")");

    }

    @Override /* Вызывается при изменении версии БД, если предыдущая бд устарела */
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        /* В данном методе мы можем обновить БД */
        db.execSQL("drop table if exists " + TABLE_CONTACTS);

        onCreate(db);
    }
    /* SQLiteOpenHelper  имеет так же необязательные методы которые мы можем заимствовать
     * onDowngrade() - вызывается в ситуации обратной onUpgrade().
     * onOpen() - вызывается при открытии БД.
     * getReadableDatabase() - возвращает БД для чтения.
     * getWritableDatabase() - возвращает БД для чтения и записи*/
}
