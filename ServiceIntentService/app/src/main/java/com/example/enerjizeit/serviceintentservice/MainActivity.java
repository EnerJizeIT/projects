package com.example.enerjizeit.serviceintentservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView infoTextView;
    private ProgressBar progressBar;

    private MyBroadcastReceiver myBroadcast;
    private UpdateBroadcastReceiver upBroadcast;

    private Intent myServiceIntent;
    private int numberOfIntentService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.e("TAG", "Activity onCreate");
        Button btnStart = (Button) findViewById(R.id.buttonStart);
        Button btnStop = (Button) findViewById(R.id.buttonStop);
        progressBar = (ProgressBar)findViewById(R.id.progressbar);
        infoTextView = (TextView)findViewById(R.id.textView);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyIntentService.startActionFoo(MainActivity.this, "All complete", 10);
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sIntent = new Intent();
                sIntent.setAction(MyIntentService.ACTION_STOP);
                sendBroadcast(sIntent);
            }
        });

        numberOfIntentService = 0;

        myBroadcast = new MyBroadcastReceiver();
        upBroadcast = new UpdateBroadcastReceiver();

        IntentFilter intentFilter = new IntentFilter(MyIntentService.ACTION_MYINTENTSERVICE);
        intentFilter.addCategory(Intent.CATEGORY_DEFAULT);
        registerReceiver(myBroadcast, intentFilter);

        IntentFilter upDateIntentFilter = new IntentFilter(MyIntentService.ACTION_UPDATE);
        intentFilter.addCategory(Intent.CATEGORY_DEFAULT);
        registerReceiver(upBroadcast, upDateIntentFilter);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myBroadcast);
        unregisterReceiver(upBroadcast);
    }

    public class MyBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String result = intent.getStringExtra(MyIntentService.EXTRA_KEY_OUT);
            infoTextView.setText(result);
        }
    }

    public class UpdateBroadcastReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            int update = intent.getIntExtra(MyIntentService.EXTRA_KEY_UPDATE, 0);
            progressBar.setProgress(update);
        }
    }
}
