package com.example.enerjizeit.p0942_servicekillserver;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.concurrent.TimeUnit;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("TAG", "MyService created");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("TAG", "MyService onDestroy");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("TAG", "MyService onStartCommand");
        readFlags(flags);
        MyRun myRun = new MyRun(startId);
        new Thread(myRun).start();
        return START_NOT_STICKY;
        /* START_NOT_STICKY – сервис не будет перезапущен после того, как был убит системой
START_STICKY – сервис будет перезапущен после того, как был убит системой
START_REDELIVER_INTENT – сервис будет перезапущен после того, как был убит системой. Кроме этого, сервис снова получит все вызовы
startService, которые не были завершены методом stopSelf(startId). */
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void readFlags(int flags) {

        if((flags&START_FLAG_REDELIVERY) == START_FLAG_REDELIVERY){
            Log.e("TAG", "START_FLAG_REDELIVERY");
        }
        if((flags&START_FLAG_RETRY) == START_FLAG_RETRY){
            Log.e("TAG", "START_FLAG_RETRY");
        }
    }

    private class MyRun implements Runnable{
        int startId;

        public MyRun(int startId) {
            this.startId = startId;
            Log.e("TAG", "MyRun#" + startId + " create");
        }

        @Override
        public void run() {
            Log.e("TAG", "MyRun#" + startId + " start");

            try {
                TimeUnit.SECONDS.sleep(15);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            stop();
        }

        private void stop() {
            Log.d("TAG", "MyRun#" + startId + " end, stopSelfResult("
                    + startId + ") = " + stopSelfResult(startId));
        }
    }
}
