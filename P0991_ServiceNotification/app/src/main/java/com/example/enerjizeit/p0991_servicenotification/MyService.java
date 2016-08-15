package com.example.enerjizeit.p0991_servicenotification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

import java.util.concurrent.TimeUnit;

public class MyService extends Service {
    public MyService() {
    }

    NotificationManager nm;
    private final int NOTIFICATION_ID = 127;

    @Override
    public void onCreate() {
        super.onCreate();
        nm = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        try{
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        sendNotif();

        return super.onStartCommand(intent, flags, startId);
    }

    private void sendNotif() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(MainActivity.FILE_NAME, "somefile");
        PendingIntent pendInt = PendingIntent.getActivity(this, 0, intent, 0);

        Notification notifi = new NotificationCompat.Builder(this)
                .setTicker("Text in Ticker")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Text title")
                .setContentText("Text in statusBar")
                .setContentIntent(pendInt)
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true)
                .build();

        notifi.number = 3;/* Данная пометка должна помогать с выводом числа на иконке */


        nm.notify(NOTIFICATION_ID, notifi);

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
