package com.example.enerjizeit.p0981_servicebindinglocal;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    boolean bound = false;
    ServiceConnection serviceConnection;
    Intent intent;
    MyService myService;
    TextView textView;
    long interval;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textInterval);
        intent = new Intent(this, MyService.class);
        serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Log.e("TAG", "MainActivity onServiceConnected");
                myService = ((MyService.MyBinder)service).getService();
                bound = true;
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.e("TAG", "MainActivity onServiceDisconnected");
                bound = false;
            }
        };

        Button btnStart = (Button)findViewById(R.id.btnStart);
        Button btnUp = (Button)findViewById(R.id.btnUp);
        Button btnDown = (Button)findViewById(R.id.btnDown);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(intent);
            }
        });

        btnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!bound)return;
                interval = myService.upInterval(500);
                textView.setText("interval = " + interval);
            }
        });

        btnDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!bound)return;
                interval = myService.downInterval(500);
                textView.setText("interval = " + interval);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        bindService(intent, serviceConnection, 0);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(!bound) return;
        unbindService(serviceConnection);
        bound = false;
    }
}
