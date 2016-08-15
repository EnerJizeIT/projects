package com.example.enerjizeit.simplehandler;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private Thread thread;
    private Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        thread = new Thread(new MyThread());
        
        Button button = (Button)findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thread.start();
                handler = new Handler(){
                    @Override
                    public void handleMessage(Message msg) {
                        progressBar.setProgress(msg.arg1);
                    }
                };
            }
        });


    }

    private class MyThread implements Runnable{

        @Override
        public void run() {
            for(int i=0; i<100; i++){
                Message message = Message.obtain();
                message.arg1 = i;
                handler.sendMessage(message);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
