package it.enerjize.asynctask_progressinfoui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements CostomAsyncTask.ListenerOfSuccess {

    private TextView text;
    private Button btn1,btn2;
    private ProgressBar progressBar;
    private CostomAsyncTask costomAsyncTask;
    private EditText edText1, edText2;
    private int min, max;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (TextView)findViewById(R.id.textView);
        btn1 = (Button)findViewById(R.id.btn1);
        btn2 = (Button)findViewById(R.id.btn2);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        edText1 = (EditText)findViewById(R.id.editText1);
        edText2 = (EditText)findViewById(R.id.editText2);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                min = Integer.parseInt(edText1.getText().toString());
                max = Integer.parseInt(edText2.getText().toString());
                progressBar.setMax(max);
                progressBar.setProgress(0);

                costomAsyncTask = new CostomAsyncTask(MainActivity.this, progressBar, text);
                costomAsyncTask.setListenerOfSuccess(MainActivity.this);
                costomAsyncTask.execute(min,max);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                costomAsyncTask.cancel(false);
            }
        });
    }

    @Override
    public void successMethod(boolean result) {
        if(result){
            text.setText("Task end successfully");
        } else {
            text.setText("Error while running task");
        }
    }
}
