package com.example.enerjizeit.sharedpreferencethreadextendshandlerthread;

import android.content.SharedPreferences;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.Process;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView mTextView;

    private Handler mUiHandler = new Handler() {
        /* Обработчик Handler для UI-потока, используется фоновым потоком для взаимодействия с UI-потоком */
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                Integer i = (Integer) msg.obj;
                mTextView.setText(Integer.toString(i));
            }
        }
    };

    private class SharedPreferenceThread extends HandlerThread {
        /*  Фоновый поток, который читает и записывает значения в SharedPreferences. */
        private static final String KEY = "key";
        private static final int READ = 1;
        private static final int WRITE = 2;
        private SharedPreferences mPref;

        private Handler mHandler;

        public SharedPreferenceThread() {
            super("SharedPreferenceThread", Process.THREAD_PRIORITY_BACKGROUND);
            mPref = getSharedPreferences("LocalPrefs", MODE_PRIVATE);
        }

        @Override
        protected void onLooperPrepared() {
            super.onLooperPrepared();
            mHandler = new Handler(getLooper()) {
                @Override
                public void handleMessage(Message msg) {
                    switch (msg.what) {
                        case READ:
                            mUiHandler.sendMessage(mUiHandler.obtainMessage(0,
                                    mPref.getInt(KEY, 0)));
                            break;
                        case WRITE:
                            SharedPreferences.Editor editor = mPref.edit();
                            editor.putInt(KEY, (Integer)msg.obj);
                            editor.apply();
                            break;
                    }
                }
            };
        }

        public void read(){
            mHandler.sendEmptyMessage(READ);
        }
        public void write(int i){
            mHandler.sendMessage(Message.obtain(Message.obtain(mHandler, WRITE, i)));
        }
    }

    private int mCount;
    private Button btnRead, btnWrite;
    private SharedPreferenceThread mThread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnRead = (Button)findViewById(R.id.btnRead);
        btnWrite = (Button)findViewById(R.id.btnWrite);
        mTextView = (TextView)findViewById(R.id.textView);
        mThread = new SharedPreferenceThread();
        mThread.start(); /* Запуск фонового потока при создании экземпляра Activity. */
        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mThread.read();
            }
        });
        btnWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mThread.write(mCount++);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mThread.quit();
    }
}