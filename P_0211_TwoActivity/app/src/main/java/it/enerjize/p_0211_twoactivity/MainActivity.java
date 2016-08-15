package it.enerjize.p_0211_twoactivity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener {

    final String TAG = "lifecicle";

    Button btn1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Log.d(TAG, "Main Создано");
        btn1 = (Button)findViewById(R.id.btn1);
        btn1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btn1:
                Intent intent = new Intent(this, Main2Activity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "Main Становится видимым");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "Main Получает фокус");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "Main Приостановлено");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"Main Остановлено");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "Main Уничтоженно");
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "Main Рестартует");
    }
}
