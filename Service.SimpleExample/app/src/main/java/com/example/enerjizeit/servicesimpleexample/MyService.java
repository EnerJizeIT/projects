package com.example.enerjizeit.servicesimpleexample;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.util.TimeUtils;

import java.util.concurrent.TimeUnit;

public class MyService extends Service {
    public MyService() {
    }
    final String TAG = "TAG";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "onCreate service");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "onStartCommand");
        /* Метод onStartCommand – срабатывает, когда сервис запущен методом startService. */
        someTask();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy service");
    }

    private void someTask() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i =0; i<10; i++){
                    Log.e(TAG, "i = " + i);
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                stopSelf();
            }
        }).start();
    }
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
