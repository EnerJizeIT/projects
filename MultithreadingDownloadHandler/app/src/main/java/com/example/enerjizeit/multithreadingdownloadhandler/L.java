package com.example.enerjizeit.multithreadingdownloadhandler;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;


public class L {
    public static void l(String s){
        Log.e("TAG", s);
    }
    public static void t(String s, Context c){
        Toast.makeText(c, s, Toast.LENGTH_SHORT).show();
    }
}
