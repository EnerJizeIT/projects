package com.example.enerjizeit.threadconfigurationfragment;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TreadFragment threadFragment;
    private TextView textView;
    private Button btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView)findViewById(R.id.textView);
        btnStart = (Button)findViewById(R.id.btnStartThread);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                threadFragment.execute();
  /* Вьполнение рабочего потока передаётся (делегируется) объекту Fragment. */
            }
        });
        threadFragment = (TreadFragment) getSupportFragmentManager().findFragmentByTag("threadFragment");
        if(threadFragment == null){
            /* Создание объекта Fragment, если компонент Activity начинает выполнение в первый раз. */
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            threadFragment = new TreadFragment();
            transaction.add(threadFragment, "threadFragment");
            transaction.commit();
        }
    }

    public void setTextView(final String text){
 /* Общедоступный метод для объекта Fragment, цель которого - зафиксировать результат выполнения рабочего потока. */
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textView.setText(text);
            }
        });
    }
}
