package com.example.enerjizeit.volleypostmysql;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class MySingleton {
    private static MySingleton mySingleton;
    private Context context;
    private RequestQueue requestQueue;

    private MySingleton(Context context) {
        this.context = context;
        requestQueue = getRequestQueue();
    }

    private RequestQueue getRequestQueue() {
        if(requestQueue == null){
            requestQueue = Volley.newRequestQueue(context);
        }
        return requestQueue;
    }

    public static synchronized MySingleton getInstance(Context context){
        if(mySingleton == null){
            mySingleton = new MySingleton(context);
        }
        return mySingleton;
    }

    public <T> void addToRequestQueue(Request<T> request){
        requestQueue.add(request);
    }
}
