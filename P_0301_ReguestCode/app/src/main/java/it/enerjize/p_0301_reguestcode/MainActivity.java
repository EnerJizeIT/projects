package it.enerjize.p_0301_reguestcode;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.webkit.WebSettings;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    final int REQUEST_CODE_COLOR = 1;
    final int REQUEST_CODE_ALIGN = 2;

    TextView textView, bt5, TEXT;
    Button bt1;
    Button bt2;
    Button bt3, bt4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
        TEXT = (TextView)findViewById(R.id.TEXT);
        bt1 = (Button)findViewById(R.id.bt1);
        bt2 = (Button)findViewById(R.id.bt2);
        bt3 = (Button)findViewById(R.id.bt3);
        bt4 = (Button)findViewById(R.id.bt4);
        bt5 = (TextView)findViewById(R.id.text_push);

        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
        bt3.setOnClickListener(this);
        bt4.setOnClickListener(this);
        bt5.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
    Intent intent;
        switch(v.getId())
        {
            case R.id.bt1:
                intent= new Intent(this, Color.class);
                startActivityForResult(intent,REQUEST_CODE_COLOR);
                break;
            case R.id.bt2:
                intent= new Intent(this, Align.class);
                startActivityForResult(intent,REQUEST_CODE_ALIGN);
                break;
            case R.id.bt3:
                intent = new Intent(this, SetText.class);
                startActivityForResult(intent, 3);
                break;
            case R.id.bt4:
                intent = new Intent(this, Size.class);
                startActivityForResult(intent, 4);
                break;
            case R.id.text_push:
                intent = new Intent(this, TextEdit.class);
                startActivityForResult(intent, 5);
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.d("myLog","requestCode = " + requestCode + ", resultCode = " + resultCode);
        if(resultCode == RESULT_OK)
        {
            switch (requestCode)
            {
                case REQUEST_CODE_COLOR:
                    int color = data.getIntExtra("Color", android.graphics.Color.WHITE);
                    textView.setTextColor(color);
                    break;
                case REQUEST_CODE_ALIGN:
                    int align = data.getIntExtra("Align", Gravity.CENTER);
                    textView.setGravity(align);
                    break;
                case 3:
                    String name = data.getStringExtra("name");
                    textView.setText(name);
                    break;
                case 4:
                    int size = data.getIntExtra("size", 20);
                    textView.setTextSize(size);
                    break;
                case 5:
                    String arr =getIntent().getStringExtra("TEXT");
                    TEXT.setText(arr);

                    break;
            }
        }else {
            Toast.makeText(this, "Wrong result", Toast.LENGTH_SHORT).show();
        }
    }
}
