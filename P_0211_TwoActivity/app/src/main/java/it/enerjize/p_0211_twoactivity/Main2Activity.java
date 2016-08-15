package it.enerjize.p_0211_twoactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener{
    final String TAG = "lifecicle";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Log.d(TAG, "Second Создано");
        Button bnt2 = (Button) findViewById(R.id.btn2);
        bnt2.setOnClickListener(this);
        Button bnt3 = (Button) findViewById(R.id.btn3);
        bnt3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn2)
        {
           Intent intent1 = new Intent(this, Main3Activity.class);
            startActivity(intent1);
        } else if (v.getId() == R.id.btn3)
        {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "Second Становится видимым");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "Second Получает фокус");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "Second Приостановлено");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"Second Остановлено");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "Second Уничтоженно");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "Second Рестартует");
    }
}
