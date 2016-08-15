package it.enerjize.p_0301_reguestcode;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Color extends AppCompatActivity implements View.OnClickListener{

    Button btRed;
    Button btGreen;
    Button btBlue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color);

        btRed = (Button)findViewById(R.id.bt_red);
        btGreen = (Button)findViewById(R.id.bt_green);
        btBlue = (Button)findViewById(R.id.bt_blue);

        btRed.setOnClickListener(this);
        btGreen.setOnClickListener(this);
        btBlue.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId())
        {
            case R.id.bt_red:
                intent.putExtra("Color", android.graphics.Color.RED);
                break;
            case R.id.bt_green:
                intent.putExtra("Color", android.graphics.Color.GREEN);
                break;
            case R.id.bt_blue:
                intent.putExtra("Color", android.graphics.Color.BLUE);
                break;
        }
        setResult(RESULT_OK, intent);
        finish();
    }
}
