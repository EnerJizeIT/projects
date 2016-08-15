package com.example.enerjizeit.looperexample;

import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private MyThread myThread;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button)findViewById(R.id.btn);
        myThread = new MyThread();
        myThread.start();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myThread.handler.post(new Runnable() {
                    @Override
                    public void run() {
                         Log.e("TAG", Thread.currentThread().getName());
                    }
                });
            }
        });
    }

    class MyThread extends Thread{
        Handler handler;
        public MyThread(){}

        @Override
        public void run() {
            Looper.prepare();
            handler = new Handler();
            Looper.loop();
        }
    }
}
