package com.example.enerjizeit.p0961_servicebackbroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    final int TASK1_CODE = 1;
    final int TASK2_CODE = 2;
    final int TASK3_CODE = 3;

    public final static int STATUS_START = 100;
    public final static int STATUS_FINISH = 200;

    public final static String PARAM_TIME = "time";
    public final static String PARAM_TASK = "task";
    public final static String PARAM_RESULT = "result";
    public final static String PARAM_STATUS = "status";

    public final static String BROADCAST_ACTION = "com.example.enerjizeit.p0961_servicebackbroadcast";

    TextView textView1;
    TextView textView2;
    TextView textView3;
    Button btn;
    BroadcastReceiver broadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView1 = (TextView) findViewById(R.id.tvTask1);
        textView1.setText("Task1");
        textView2 = (TextView) findViewById(R.id.tvTask2);
        textView2.setText("Task2");
        textView3 = (TextView) findViewById(R.id.tvTask3);
        textView3.setText("Task3");
        btn = (Button) findViewById(R.id.idq);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("TAG", "onClick push");
                Intent intent;
                intent = new Intent(MainActivity.this, MyService.class).putExtra(PARAM_TIME, 7)
                        .putExtra(PARAM_TASK, TASK1_CODE);
                startService(intent);

                intent = new Intent(MainActivity.this, MyService.class).putExtra(PARAM_TIME, 4)
                        .putExtra(PARAM_TASK, TASK2_CODE);
                startService(intent);

                intent = new Intent(MainActivity.this, MyService.class).putExtra(PARAM_RESULT, 6)
                        .putExtra(PARAM_TASK, TASK3_CODE);
                startService(intent);
            }
        });

        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                int task = intent.getIntExtra(PARAM_TASK, 0);
                int status = intent.getIntExtra(PARAM_STATUS, 0);
                Log.e("TAG", "onReceive: task = " + task + ", status = " + status);
                /* Ловим сообщения о старте задач */
                if (status == STATUS_START) {
                    switch (task) {
                        case TASK1_CODE:
                            textView1.setText("Task1 start");
                            break;
                        case TASK2_CODE:
                            textView2.setText("Task2 start");
                            break;
                        case TASK3_CODE:
                            textView3.setText("Task3 start");
                            break;
                    }
                }

                /* Ловим сообщения об окончании задач */
                if (status == STATUS_FINISH) {
                    int result = intent.getIntExtra(PARAM_RESULT, 0);
                    switch (task) {
                        case TASK1_CODE:
                            textView1.setText("Task1 finish, result = " + result);
                            break;
                        case TASK2_CODE:
                            textView2.setText("Task2 finish, result = " + result);
                            break;
                        case TASK3_CODE:
                            textView3.setText("Task3 finish, result = " + result);
                            break;
                    }
                }
            }
        };

        /* Создаём фильтр для BroadcastReceiver */
        IntentFilter intentFilter = new IntentFilter(BROADCAST_ACTION);
        registerReceiver(broadcastReceiver, intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadcastReceiver);
    }
}
