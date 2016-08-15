package com.example.enerjizeit.asynctaskexample;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView listview;
    private String [] texts = {"Qwe","Qwe","Qwe","Qwe","Qwe","Qwe","Qwe","Qwe","Qwe","Qwe","Qwe","Qwe",
            "Qwe","Qwe","Qwe","Qwe","Qwe","Qwe","Qwe","Qwe","Qwe","Qwe","Qwe","Qwe","Qwe","Qwe","Qwe",
            "Qwe","Qwe","Qwe","Qwe","Qwe","Qwe","Qwe","Qwe","Qwe","Qwe","Qwe","Qwe","Qwe","Qwe","Qwe",
            "Qwe","Qwe","Qwe","Qwe","Qwe","Qwe","Qwe","Qwe","Qwe","Qwe","Qwe","Qwe","Qwe","Qwe","Qwe",
            "Qwe","Qwe","Qwe","Qwe","Qwe","Qwe","Qwe","Qwe","Qwe","Qwe","Qwe","Qwe","Qwe","Qwe","Qwe",
            "Qwe","Qwe","Qwe","Qwe","Qwe","Qwe","Qwe","Qwe","Qwe","Qwe","Qwe","Qwe","Qwe","Qwe","Qwe",
            "Qwe","Qwe","Qwe","Qwe","Qwe","Qwe","Qwe","Qwe","Qwe","Qwe","Qwe","Qwe","Qwe","Qwe","Qwe",
            "Qwe",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /* Перед закреплением макета мы уст прогресс бар */
        requestWindowFeature(Window.FEATURE_PROGRESS);
        setContentView(R.layout.activity_main);
        listview = (ListView)findViewById(R.id.listview);
        listview.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                new ArrayList<String>()));
        new MyTask().execute();
    }

    private class MyTask extends AsyncTask<Void, String, Void>{
        private ArrayAdapter<String> adapter;
        private int count=0;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            adapter = (ArrayAdapter<String>) listview.getAdapter();
            setProgressBarIndeterminate(false);
            setProgressBarVisibility(true);
        }

        @Override
        protected Void doInBackground(Void... params) {
            for(String items : texts){
                publishProgress(items);
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            adapter.add(values[0]);
            count++;
            setProgress((int)(((double)count/texts.length)*10000));
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            setProgressBarVisibility(false);
            Toast.makeText(MainActivity.this, "All items were added successfully", Toast.LENGTH_SHORT).show();
        }
    }
}
