package com.example.enerjizeit.volleydemo;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class MySingleton {
    private static MySingleton mySingleton;
    private RequestQueue requestQueue;
    private Context context;

    private MySingleton(Context context) {
        this.context = context;
        requestQueue = getRequestQueue();
    }

    private RequestQueue getRequestQueue() {
        if(requestQueue == null){
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
        return requestQueue;
    }

    public static  synchronized MySingleton getInstance(Context context){
        if(mySingleton == null){
            mySingleton = new MySingleton(context);
        }
        return mySingleton;
    }

    public <T> void addToRequestQue(Request<T> request){
        requestQueue.add(request);
    }


}
