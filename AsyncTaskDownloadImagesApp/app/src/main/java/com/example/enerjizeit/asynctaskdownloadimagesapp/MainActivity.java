package com.example.enerjizeit.asynctaskdownloadimagesapp;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private EditText editText;
    private ListView listView;
    private String [] listOfImages;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText)findViewById(R.id.textView);
        listView = (ListView)findViewById(R.id.listView);
        progressBar = (ProgressBar)findViewById(R.id.loadProgress);
        listView.setOnItemClickListener(this);
        listOfImages = getResources().getStringArray(R.array.imageUrls);

    }

    public void downloadingButton(View view){
        if(editText.getText().toString().length() > 0){
            MyTask myTask = new MyTask();
            myTask.execute(editText.getText().toString());
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        editText.setText(listOfImages[position]);
    }
    private class MyTask extends AsyncTask<String, Integer, Boolean>{
        private int connectLength;
        private int counter;

        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Boolean doInBackground(String... params) {
            boolean successful = false;
            HttpURLConnection connection = null;
            InputStream inputStream = null;
            FileOutputStream fileOutputStream = null;
            try {
                URL downloadURL = new URL(params[0]);
                connection = (HttpURLConnection)  downloadURL.openConnection();
                connectLength = connection.getContentLength();

                inputStream = connection.getInputStream();

                File file = new File(Environment.getExternalStoragePublicDirectory
                        (Environment.DIRECTORY_PICTURES).getAbsolutePath()
                        + "/" + Uri.parse(params[0]).getLastPathSegment()); /* Мы создаём файл с именем загр файла */
                L.l("downloadImageUsingThreads " + file.getAbsolutePath());

                fileOutputStream = new FileOutputStream(file);
                L.l("output" + fileOutputStream.toString());

                int read = -1;
                byte[] bytes = new byte[1024];
                while((read = inputStream.read(bytes)) != -1){
                    L.l("downloadImageUsingThreads.read " + read);
                    fileOutputStream.write(bytes, 0 , read);
                    counter = counter+read;
                    publishProgress(counter);
                }
                successful = true;
            } catch (MalformedURLException e) {
                L.l(e.toString());
            } catch (IOException e) {
                L.l(e.toString());
            } finally {

                if(connection != null){
                    connection.disconnect();
                }
                if(inputStream != null){
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        L.l(e.toString());
                    }
                }
                if(fileOutputStream != null){
                    try {
                        fileOutputStream.close();
                    } catch (IOException e) {
                        L.l(e.toString());
                    }
                }
            }
            return successful;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            progressBar.setProgress((int)(((double)values[0]/connectLength)*100));
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            progressBar.setVisibility(View.INVISIBLE);
        }
    }
}
