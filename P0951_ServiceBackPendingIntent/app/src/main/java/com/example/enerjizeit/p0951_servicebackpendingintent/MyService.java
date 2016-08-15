package com.example.enerjizeit.p0951_servicebackpendingintent;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MyService extends Service {
    public MyService() {
    }

    ExecutorService executorService;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("TAG", "MyService onCreate");
        executorService = Executors.newFixedThreadPool(2);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("TAG", "MyService onDestroy");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("TAG", "MyService onStartCommand");

        int time = intent.getIntExtra(MainActivity.PARAM_TIME, 1);
        PendingIntent pendingIntent = intent.getParcelableExtra(MainActivity.PARAM_PINTENT);

        MyRun myRun = new MyRun(time, startId, pendingIntent);
        executorService.execute(myRun);

        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private class MyRun implements Runnable{
        int time;
        int startId;
        PendingIntent pending;

        public MyRun(int time, int startId, PendingIntent pendingIntent) {
            this.time = time;
            this.startId = startId;
            pending = pendingIntent;
            Log.e("TAG", "MyRun#" + startId + " create");
        }

        @Override
        public void run() {
            Log.e("TAG", "MyRun#" + startId + " start, time = " + time);
            try {
                pending.send(MainActivity.STATUS_START);
                TimeUnit.SECONDS.sleep(time);

                Intent intent = new Intent().putExtra(MainActivity.PARAM_RESULT, time * 100);
                pending.send(MyService.this, MainActivity.STATUS_FINISH, intent);
            } catch (PendingIntent.CanceledException e) {
                e.printStackTrace();
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
