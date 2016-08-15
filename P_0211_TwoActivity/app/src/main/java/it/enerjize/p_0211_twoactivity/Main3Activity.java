package it.enerjize.p_0211_twoactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Main3Activity extends AppCompatActivity implements View.OnClickListener{
    final String TAG = "lifecicle";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Log.d(TAG, "Third Создано");
        Button bnt3r = (Button) findViewById(R.id.btn3r);
        bnt3r.setOnClickListener(this);
        Button bnt2r = (Button) findViewById(R.id.btn2r);
        bnt2r.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn2r) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.btn3r) {
            Intent intent1 = new Intent(this, Main2Activity.class);
            startActivity(intent1);
        }
    }
        @Override
        protected void onStart() {
            super.onStart();
            Log.d(TAG, "Third Становится видимым");
        }

        @Override
        protected void onResume() {
            super.onResume();
            Log.d(TAG, "Second Получает фокус");
        }

        @Override
        protected void onPause() {
            super.onPause();
            Log.d(TAG, "Third Приостановлено");
        }

        @Override
        protected void onStop() {
            super.onStop();
            Log.d(TAG,"Third Остановлено");
        }

        @Override
        protected void onDestroy() {
            super.onDestroy();
            Log.d(TAG, "Third Уничтоженно");
        }

        @Override
        protected void onRestart() {
            super.onRestart();
            Log.d(TAG, "Third Рестартует");
        }

}
