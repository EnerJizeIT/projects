package com.example.enerjizeit.serviceintentservice;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.widget.Toast;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class MyIntentService extends IntentService {
    private static final String ACTION_FOO = "com.example.enerjizeit.serviceintentservice.action.FOO";
    private static final String ACTION_BAZ = "com.example.enerjizeit.serviceintentservice.action.BAZ";

    private static final String EXTRA_PARAM1 = "com.example.enerjizeit.serviceintentservice.extra.PARAM1";
    private static final String EXTRA_PARAM2 = "com.example.enerjizeit.serviceintentservice.extra.PARAM2";

    public static final String ACTION_MYINTENTSERVICE = "ru.alexanderklimov.intentservice.RESPONSE";
    public static final String EXTRA_KEY_OUT = "EXTRA_OUT";
    public static final String ACTION_UPDATE = "ru.alexanderklimov.intentservice.UPDATE";
    public static final String EXTRA_KEY_UPDATE = "EXTRA_UPDATE";
    public static final String ACTION_STOP = "stop";


    private boolean mIsSuccess;
    public static volatile boolean mIsStopped;

    private Handler mHandler;

    NotificationManager nm;

    public MyIntentService() {
        super("MyIntentService");
        mIsSuccess = false;
        mIsStopped = false;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("TAG", "MyIntentService onCreate");
        nm = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mHandler = new Handler();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.e("TAG", "onHandleIntent start");
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_FOO.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final int param2 = intent.getIntExtra(EXTRA_PARAM2, 1);
                handleActionFoo(param1, param2);
            } else if (ACTION_BAZ.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                handleActionBaz(param1, param2);
            }
        }

        mHandler.post(new Runnable() {
            @Override
            public void run() {
                Snackbar.make(null, "Service work done", Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDestroy() {
        String notice;
        mIsStopped = true;
        if(mIsSuccess){
            notice = "onDestroy with success";
        } else {
            notice = "onDestroy WITHOUT success!";
        }

        Toast.makeText(getApplicationContext(), notice, Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }

    public static void startActionFoo(Context context, String param1, int param2) {
        Intent intent = new Intent(context, MyIntentService.class);
        intent.setAction(ACTION_FOO);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }

    public static void startActionBaz(Context context, String param1, String param2) {
        Intent intent = new Intent(context, MyIntentService.class);
        intent.setAction(ACTION_BAZ);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }

    private void handleActionFoo(String param1, int param2) {

        Intent updateIntent = new Intent();
        updateIntent.setAction(ACTION_UPDATE);

        IntentFilter intentFilter = new IntentFilter(ACTION_STOP);
        intentFilter.addCategory(Intent.CATEGORY_DEFAULT);
        StopReceiver stopReceiver = new StopReceiver();
        registerReceiver(stopReceiver, intentFilter);



        Notification.Builder notifiBuilder = new Notification.Builder(getApplicationContext())
                .setContentTitle("Progress")
                .setTicker("Progress Update")
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true)
                .setSmallIcon(R.mipmap.ic_launcher);



        for(int i = 0; i <= param2; i++){
            mIsSuccess = true;
            try{
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            updateIntent.putExtra(EXTRA_KEY_UPDATE, i);
            sendBroadcast(updateIntent);

            String notificationText = String.valueOf((100 * i / 10)) + " %";
            notifiBuilder.setProgress(0,i, false).setContentText(notificationText);
            Notification notifi = notifiBuilder.build();

            nm.notify(100, notifi);

            if(mIsStopped){
                stopSelf();
                return;
            }

        }

        Intent responseIntent = new Intent();
        responseIntent.setAction(ACTION_MYINTENTSERVICE);
        responseIntent.addCategory(Intent.CATEGORY_DEFAULT);
        responseIntent.putExtra(EXTRA_KEY_OUT, param1);
        sendBroadcast(responseIntent);

    }

    private void handleActionBaz(String param1, String param2) {
        throw new UnsupportedOperationException("Not yet implemented");

    }

    public class StopReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            mIsStopped = true;
        }
    }
}
