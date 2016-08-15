package com.example.enerjizeit.p0951_servicebackpendingintent;

import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    final int TASK1_CODE = 1;
    final int TASK2_CODE = 2;
    final int TASK3_CODE = 3;

    public final static int STATUS_START = 100;
    public final static int STATUS_FINISH = 200;

    public final static String PARAM_TIME = "time";
    public final static String PARAM_PINTENT = "pendingIntent";
    public final static String PARAM_RESULT = "result";

    TextView textView1;
    TextView textView2;
    TextView textView3;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView1 = (TextView)findViewById(R.id.tvTask1);
        textView1.setText("Task1");
        textView2 = (TextView)findViewById(R.id.tvTask2);
        textView2.setText("Task2");
        textView3 = (TextView)findViewById(R.id.tvTask3);
        textView3.setText("Task3");
        btn = (Button)findViewById(R.id.idq);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PendingIntent pendingIntent;
                Intent intent = new Intent(MainActivity.this, MyService.class);

                pendingIntent = createPendingResult(TASK1_CODE, intent, 0);
                intent.putExtra(PARAM_TIME, 7).putExtra(PARAM_PINTENT, pendingIntent);
                startService(intent);

                pendingIntent = createPendingResult(TASK2_CODE, intent, 0);
                intent = new Intent(MainActivity.this, MyService.class).putExtra(PARAM_TIME, 4)
                        .putExtra(PARAM_PINTENT, pendingIntent);
                startService(intent);

                pendingIntent = createPendingResult(TASK3_CODE, intent, 0);
                intent = new Intent(MainActivity.this, MyService.class).putExtra(PARAM_TIME, 6)
                        .putExtra(PARAM_PINTENT, pendingIntent);
                startService(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("TAG", "requestCode = " + requestCode + ", resultCode = " + resultCode);

        if(resultCode == STATUS_START){
            switch (requestCode){
                case TASK1_CODE:
                    textView1.setText("TASK1 start");
                    break;
                case TASK2_CODE:
                    textView2.setText("TASK2 start");
                    break;
                case TASK3_CODE:
                    textView3.setText("TASK3 start");
                    break;
            }
        }

        if(resultCode == STATUS_FINISH){
            int result = data.getIntExtra(PARAM_RESULT, 0);
            switch (requestCode){
                case TASK1_CODE:
                    textView1.setText("TASK1 finish, result = " + result);
                    break;
                case TASK2_CODE:
                    textView2.setText("TASK2 finish, result = " + result);
                    break;
                case TASK3_CODE:
                    textView3.setText("TASK3 finish, result = " + result);
                    break;
            }
        }
    }
}
