package it.enerjize.handler;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button btn1, btn2;
    private TextView text;
    private boolean isRunning;
    private static final int COUNT = 100;
    private static int count = 0;
    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            text.setText(String.format("End Task #%d", msg.what));
            return false;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = (Button)findViewById(R.id.btn1);
        btn2 = (Button)findViewById(R.id.btn2);
        text = (TextView)findViewById(R.id.text);
        btn1.setOnClickListener(mClickListener);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onStopThread();
            }
        });
    }
    private View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            isRunning = true;
            Thread background = new Thread(new Runnable() {
                @Override
                public void run() {
                    while(count<=COUNT&&isRunning){
                        try{
                            Thread.sleep(200);
                            mHandler.sendEmptyMessage(count);
                            count++;
                        } catch (Exception e){
                            Toast.makeText(MainActivity.this, "Exception Thread", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
            background.start();
        }
    };
    public void onStopThread(){
        isRunning = false;
    }
}