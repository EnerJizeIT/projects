package it.enerjize.asynctask_completelistener;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,
        CustomAsyncTask.OnDownloadCompletedListener {

    private TextView text;
    private CustomAsyncTask task;
    private ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (TextView)findViewById(R.id.text);
        progress = (ProgressBar)findViewById(R.id.progress);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_start:
                startTask();
                break;
            case R.id.button_stop:
                stopTask();
                break;
        }
    }
    private void startTask() {
        task = new CustomAsyncTask(this, progress, text);
        task.execute();
        task.setOnDownloadCompletedListener(this);
    }
    private void stopTask() {
        task.cancel(false);
    }
    @Override
    public void loadTaskCompleted(boolean success) {
        if (success)
            text.setText("End task successfully");
        else
            text.setText("Error while running task");
    }
}