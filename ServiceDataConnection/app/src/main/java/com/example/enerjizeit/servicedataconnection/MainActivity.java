package com.example.enerjizeit.servicedataconnection;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button)findViewById(R.id.idq);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent(MainActivity.this, MyService.class).putExtra("time", 8));
                startService(new Intent(MainActivity.this, MyService.class).putExtra("time", 5));
                startService(new Intent(MainActivity.this, MyService.class).putExtra("time", 2));
            }
        });
    }
}
