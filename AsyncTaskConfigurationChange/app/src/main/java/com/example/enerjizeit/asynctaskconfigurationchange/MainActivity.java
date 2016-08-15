package com.example.enerjizeit.asynctaskconfigurationchange;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    MyTask mt;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("qwe", "create MainActivity: " + this.hashCode());

        textView = (TextView) findViewById(R.id.textView);

        mt = (MyTask)getLastCustomNonConfigurationInstance();
        if(mt == null){
            mt = new MyTask();
            mt.execute();
        }
        mt.link(this);
        Log.e("qwe", "create MyTask: " + mt.hashCode());
    }

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        mt.unLink();
        return mt;
    }

    static class MyTask extends AsyncTask<String, Integer, Void>{

        MainActivity activity;
        void link(MainActivity act){
            activity = act;
        }

        void unLink(){
            activity = null;
        }

        @Override
        protected Void doInBackground(String... params) {
            try{
                for(int i=1; i<=10; i++){
                    TimeUnit.SECONDS.sleep(1);
                    publishProgress(i);

                    Log.e("qwe", "i = " + i + ", MyTask: " + this.hashCode()
                            + ", MainActivity: " + this.hashCode());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            activity.textView.setText("i = " + values[0]);
        }
    }
}
