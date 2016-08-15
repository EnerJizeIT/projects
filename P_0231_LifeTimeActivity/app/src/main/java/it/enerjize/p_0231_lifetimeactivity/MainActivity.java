package it.enerjize.p_0231_lifetimeactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    final String TAG = "lifecicle";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "Созданно");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "Становится видимым");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "Получает фокус");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "Приостановлено");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"Остановлено");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "Уничтоженно");
    }
}
