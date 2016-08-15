package com.example.enerjizeit.crimeintentex;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.enerjizeit.crimeintentex.DataBase.CrimeCursorWrapper;
import com.example.enerjizeit.crimeintentex.DataBase.DBHelper;
import com.example.enerjizeit.crimeintentex.DataBase.DBTable;
import com.example.enerjizeit.crimeintentex.DataBase.DBTable.Table.Rows;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.example.enerjizeit.crimeintentex.DataBase.DBTable.Table.TABLE_NAME;

public class CrimeLab {
    private static CrimeLab crimeLab;
    private static SQLiteDatabase database;
    private Context context;
    public static CrimeLab getCrimeLab(Context context) {
        if(crimeLab == null){
            crimeLab = new CrimeLab(context);
        }
        return crimeLab;
    }
    private CrimeLab (Context context){
        this.context = context.getApplicationContext();
        database = new DBHelper(context).getWritableDatabase();
    }
    public void addCrime(Crime crime){
        ContentValues contentValues = getContentValues(crime);
        database.insert(TABLE_NAME, null, contentValues);
    }
    public static void delCrime(Crime crime){
        String uuidString = crime.getId().toString();
        database.delete(TABLE_NAME, Rows.UUID + " = ?", new String[]{ uuidString});
    }
    public List<Crime> getCrimes() {
        List<Crime> crimes = new ArrayList<>();

        CrimeCursorWrapper cursorWrapper = queryCrimes(null, null);
        try{
            cursorWrapper.moveToFirst();
            while (!cursorWrapper.isAfterLast()){
                crimes.add(cursorWrapper.getCrime());
                cursorWrapper.moveToNext();
            }
        } finally {
            cursorWrapper.close();
        }

        return crimes;
    }
    public Crime getCrime(UUID id){
        CrimeCursorWrapper cursorWrapper = queryCrimes(Rows.UUID + " = ?", new String[] { id.toString()});

        try{
            if(cursorWrapper.getCount() == 0){
                return null;
            }

            cursorWrapper.moveToFirst();
            return cursorWrapper.getCrime();
        } finally {
            cursorWrapper.close();
        }
    }

    public void updateCrime(Crime crime){
        String uuidString = crime.getId().toString();
        ContentValues contentValues = getContentValues(crime);

        database.update(TABLE_NAME, contentValues, Rows.UUID + " = ?", new String[]{ uuidString});
    }

    private static ContentValues getContentValues(Crime crime){
        ContentValues contentValues = new ContentValues();
        contentValues.put(Rows.UUID, crime.getId().toString());
        contentValues.put(Rows.TITLE, crime.getTitle());
        contentValues.put(Rows.DATE, crime.getDate().getTime());
        contentValues.put(Rows.SOLVED, crime.isSolved() ? 1 : 0);
        contentValues.put(Rows.SUSPECT, crime.getSuspect());
        return contentValues;
    }

    private CrimeCursorWrapper queryCrimes(String whereClause, String [] whereArgs){
        Cursor cursor = database.query(TABLE_NAME, null, whereClause, whereArgs, null, null, null);
        CrimeCursorWrapper wrapper = new CrimeCursorWrapper(cursor);
        return wrapper;
    }
}
