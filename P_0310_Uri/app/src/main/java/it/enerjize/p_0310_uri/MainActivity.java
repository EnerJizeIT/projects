package it.enerjize.p_0310_uri;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button bt_web, bt_map, bt_call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt_web = (Button) findViewById(R.id.bt_web);
        bt_map = (Button) findViewById(R.id.bt_map);
        bt_call = (Button) findViewById(R.id.bt_call);

        bt_web.setOnClickListener(this);
        bt_map.setOnClickListener(this);
        bt_call.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch ((v.getId()))
        {
            case R.id.bt_web:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://vl.com"));
                startActivity(intent);
                break;
            case  R.id.bt_map:
                intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("geo:-0.45609946, -90.26607513"));
                startActivity(intent);
                break;
            case R.id.bt_call:
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("tel:12345"));
                startActivity(intent);
                break;
        }
    }
}
