package com.example.enerjizeit.p0972_servicebindserver;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("TAG", "MyService onCreate");
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e("TAG","MyService onBind");
        return new Binder();
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Log.e("TAG","MyService onRebind");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e("TAG","MyService onUnbind");
        return super.onUnbind(intent);
        /* Если в return указать true, то при переподключении сервиса будут срабатывать методы onRebind и onUnbind
        * Таким образом, у нас есть возможность обработать в сервисе каждое повторное подключение/отключение.
        * Если вам надо, чтобы подключение оставалось при временном уходе Activity в background,
          то используйте onCreate и onDestroy. Также не использовуйте для этих целей
            onResume и onPause.*/
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("TAG","MyService onDestroy");
    }
}
