package it.enerjize.p_0301_reguestcode;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.widget.Button;

public class Size extends AppCompatActivity implements View.OnClickListener{

    Button bt_big,bt_middle,bt_small;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_size);

        bt_big = (Button)findViewById(R.id.bt_big);
        bt_middle = (Button)findViewById(R.id.bt_middle);
        bt_small = (Button)findViewById(R.id.bt_small);

        bt_big.setOnClickListener(this);
        bt_middle.setOnClickListener(this);
        bt_small.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        if(v.getId() == R.id.bt_big)
        {
            intent.putExtra("size", 40);
        } else if(v.getId() == R.id.bt_middle)
        {
            intent.putExtra("size", 25);
        } else if (v.getId() == R.id.bt_small){
            intent.putExtra("size", 10);
        }
        setResult(RESULT_OK, intent);
        finish();
    }
}
