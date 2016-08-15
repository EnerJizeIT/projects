package com.example.enerjizeit.servicedataconnection;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MyService extends Service {
    public MyService() {
    }

    final String TAG = "TAG";
    ExecutorService executorService;
    Object someRes;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "MyService on Create");
        executorService = Executors.newFixedThreadPool(3);
        someRes = new Object();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "MyService onDestroy");
        someRes = null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "MyService onStartCommand");
        int time = intent.getIntExtra("time", startId);
        MyRun myRun = new MyRun(time, startId);
        executorService.execute(myRun);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    class MyRun implements Runnable {
        int time, startId;

        public MyRun(int time, int startId) {
            this.time = time;
            this.startId = startId;
            Log.e("TAG", "MyRun#" + startId + " create");
        }

        @Override
        public void run() {
            Log.e("TAG", "MyRun#" + startId + " start, time = " + time);
            try {
                TimeUnit.SECONDS.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                Log.e("TAG", "MyRun#" + startId + " someRes = " + someRes.getClass());
            } catch (NullPointerException e) {
                Log.e("TAG", "MyRun#" + startId + " error, null pointer");
            }
            stop();
        }

        private void stop() {
            Log.e("TAG", "MyRun#" + startId + " end, stopSelf(" + startId + ") = " + stopSelfResult(startId));
            stopSelfResult(startId);
        }
    }
}
