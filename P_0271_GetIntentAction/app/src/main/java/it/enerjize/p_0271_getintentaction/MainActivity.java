package it.enerjize.p_0271_getintentaction;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bttime = (Button) findViewById(R.id.button_time);
        Button btdate = (Button) findViewById(R.id.button_date);

        btdate.setOnClickListener(this);
        bttime.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;

        switch (v.getId())
        {
            case R.id.button_date:
                intent = new Intent("action.date");
                startActivity(intent);
                break;
            case R.id.button_time:
                intent = new Intent("action.time");
                startActivity(intent);
                break;
        }
    }
}
