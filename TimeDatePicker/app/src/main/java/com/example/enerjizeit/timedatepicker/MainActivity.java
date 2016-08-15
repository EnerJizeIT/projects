package com.example.enerjizeit.timedatepicker;

import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends FragmentActivity {
    Button btn1, btn2;
    TextView textView1,textView2;
    private InfoLab infoLab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        infoLab = new InfoLab();
        textView1 = (TextView)findViewById(R.id.text1);
        textView2 = (TextView)findViewById(R.id.text2);
        btn1 = (Button)findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimeFragment().show(getSupportFragmentManager(), "A");
            }
        });
        btn2 = (Button)findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DateFragment().show(getSupportFragmentManager(), "B");
            }
        });
        textView1.setText(infoLab.getTime());
        textView2.setText(infoLab.getDate());
    }
}
