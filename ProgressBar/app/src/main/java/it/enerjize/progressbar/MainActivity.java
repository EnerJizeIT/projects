package it.enerjize.progressbar;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ProgressBar progressBar1,progressBar2,progressBar3,progressBar4;
    private TextView textView;
    private Thread thread;
    private boolean isRunning = false;
    private Button btn1, btn2;
    private ProgressDialog dialog;

    private final static int COUNT = 100;
    private static int count = 0;

    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            dialog.setProgress(msg.what);
            dialog.setMessage(String.format("End Task #%d", msg.what));

            progressBar1.setProgress(msg.what);
            progressBar2.setProgress(msg.what);
            progressBar3.setProgress(msg.what);
            progressBar4.setProgress(msg.what);
            textView.setText(String.format("End Task #%d", msg.what));
            if(msg.what == COUNT){
                setProgressBarIndeterminate(false);
            }
            return false;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.text);
        progressBar1 = (ProgressBar)findViewById(R.id.progressBar1);
        progressBar2 = (ProgressBar)findViewById(R.id.progressBar2);
        progressBar3 = (ProgressBar)findViewById(R.id.progressBar3);
        progressBar4 = (ProgressBar)findViewById(R.id.progressBar4);
        btn1 = (Button)findViewById(R.id.btn1);
        btn2 = (Button)findViewById(R.id.btn2);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onStartThread();

            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onStopThread();
            }
        });

    }

    private void onStartThread() {
        setProgressBarIndeterminate(true);
        isRunning = true;
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (count<=COUNT&&isRunning){
                    try{
                        Thread.sleep(200);
                        mHandler.sendEmptyMessage(count);
                        count++;
                    }catch (Exception e){
                        Toast.makeText(MainActivity.this,"Thread Exception", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        thread.start();
        openDialog();

    }

    private void openDialog() {
        dialog = new ProgressDialog(MainActivity.this);
        dialog.setTitle("wait");
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        //dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        dialog.setCancelable(true);
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                isRunning = false;
                Toast.makeText(MainActivity.this, "User close Dialog", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                Toast.makeText(MainActivity.this, "Finished all task. Dialog dismissed", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.show();
    }

    private void onStopThread() {
        isRunning = false;
        setProgressBarIndeterminate(false);
    }


}
