package it.enerjize.p_0301_reguestcode;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;

public class Align extends AppCompatActivity implements View.OnClickListener{

    Button btCenter, btRight, btLeft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_align);

        btCenter = (Button)findViewById(R.id.bt_center);
        btLeft = (Button)findViewById(R.id.bt_left);
        btRight = (Button)findViewById(R.id.bt_right);

        btCenter.setOnClickListener(this);
        btRight.setOnClickListener(this);
        btLeft.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.bt_center:
                intent.putExtra("Align", Gravity.CENTER);
                break;
            case R.id.bt_right:
                intent.putExtra("Align", Gravity.RIGHT);
                break;
            case R.id.bt_left:
                intent.putExtra("Align", Gravity.LEFT);
                break;
        }
        setResult(RESULT_OK, intent);
        finish();

    }
}
