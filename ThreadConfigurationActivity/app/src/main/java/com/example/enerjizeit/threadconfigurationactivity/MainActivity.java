package com.example.enerjizeit.threadconfigurationactivity;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static class MyThread extends Thread{/* Рабочий поток объявлен как статический внутрешшй класс,
чтобы не создавать ссылки на внешний класс. Поток содержит ссылку на экземпляр Activity. Метод attach() используется
 для сохранения ссылки на текущий выполняющийся объект Activity. */
        private MainActivity activity;

        public MyThread (MainActivity mainActivity){
            activity = mainActivity;
        }

        private void onAttach(MainActivity activity){
            this.activity = activity;
        }

        @Override
        public void run() {
            final String text = getTextFromNetwork();
            activity.setTextView(text);
        }

        private String getTextFromNetwork() {
            /* иммитация сетевой задержки */
            SystemClock.sleep(2000);
            return "Text from network";
        }
    }

    private static MyThread myThread;
    private TextView textView;
    private Button start;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView)findViewById(R.id.textView);
        start = (Button)findViewById(R.id.btnStart);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myThread = new MyThread(MainActivity.this);
                myThread.start();
            }
        });

        Object retainedObject = getLastNonConfigurationInstance();/* Если существует объект потока, сохраненный
    в рабочем состоянии, он восстанавливается. Новый экземпляр Activity регистрируется в восстановленном потоке. */
        if(retainedObject != null){
            myThread = (MyThread)retainedObject;
            myThread.onAttach(this);
        }
    }

    @Override /* Сохранение текущего выполняющегося потока перед любым изменением конфигурации. */
    public Object onRetainCustomNonConfigurationInstance() {
        if(myThread != null && myThread.isAlive()){
            return myThread;
        }
        return null;
    }

    private void setTextView(final String text) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textView.setText(text);
            }
        });
    }
}
