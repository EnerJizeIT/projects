package it.enerjize.p_0261_intentfilter;

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

        Button btTime = (Button) findViewById (R.id.bttime);
        Button btDate = (Button) findViewById (R.id.btdate);

        btDate.setOnClickListener(this);
        btTime.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        if(v.getId() == R.id.bttime)
        {
            intent = new Intent("action.time");
            startActivity(intent);
        } else if (v.getId()== R.id.btdate)
        {
            intent = new Intent("action.date");
            startActivity(intent);
        }
    }
}
