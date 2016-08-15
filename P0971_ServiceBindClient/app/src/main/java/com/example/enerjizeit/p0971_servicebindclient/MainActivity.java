package com.example.enerjizeit.p0971_servicebindclient;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    boolean bound = false;
    ServiceConnection serviceConnection;
    Intent intent;

    Button btnStart,btnStop, btnBindService, btnUnBindService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intent = new Intent("com.example.enerjizeit.p0972_servicebindserver.myservice");

        serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Log.e("TAG", "MainActivity onServiceConnected");
                bound = true;
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.e("TAG", "MainActivity onServiceDisconnected");
                bound = false;
            }
        };

        btnStart = (Button)findViewById(R.id.btnStart);
        btnStop = (Button)findViewById(R.id.btnStop);
        btnBindService = (Button)findViewById(R.id.btnBind);
        btnUnBindService = (Button)findViewById(R.id.btnUnbind);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(intent);
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(intent);
            }
        });

        btnBindService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bindService(intent, serviceConnection, BIND_AUTO_CREATE);
            }
        });

        btnUnBindService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!bound)return;
                unbindService(serviceConnection);
                bound = false;
            }
        });
    }

    /*  Не рекомендуется оставлять незавершенные подключения к сервисам по окончании
работы приложения. Подключение к сервису производится в методе onStart, а отключение - в
onStop */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        serviceConnection = null;
    }
}
