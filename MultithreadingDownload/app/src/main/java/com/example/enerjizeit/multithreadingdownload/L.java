package com.example.enerjizeit.multithreadingdownload;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class L {
    public static void l(String string){
        Log.i("TAG", string);
    }
    public static void t(Context context, String st){
        Toast.makeText(context, st, Toast.LENGTH_LONG).show();
    }
}
