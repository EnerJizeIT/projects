package com.example.enerjizeit.crimeintentex.DataBase;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.enerjizeit.crimeintentex.Crime;
import com.example.enerjizeit.crimeintentex.DataBase.DBTable.Table.Rows;

import java.util.Date;
import java.util.UUID;

public class CrimeCursorWrapper extends CursorWrapper {
    public CrimeCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Crime getCrime(){
        String uuid = getString(getColumnIndex(Rows.UUID));
        String title = getString(getColumnIndex(Rows.TITLE));
        long date = getLong(getColumnIndex(Rows.DATE));
        int solved = getInt(getColumnIndex(Rows.SOLVED));
        String suspect = getString(getColumnIndex(Rows.SUSPECT));

        Crime crime = new Crime(UUID.fromString(uuid));
        crime.setTitle(title);
        crime.setDate(new Date(date));
        crime.setSolved(solved != 0);
        crime.setSuspect(suspect);
        return crime;
    }
}
