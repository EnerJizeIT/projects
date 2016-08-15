package com.example.enerjizeit.multithreadingdownload;

import android.net.Uri;
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
    private LinearLayout loadingSection = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText)findViewById(R.id.textView);
        listView = (ListView)findViewById(R.id.listView);
        listView.setOnItemClickListener(this);
        loadingSection = (LinearLayout)findViewById(R.id.loadLinear);
        listOfImages = getResources().getStringArray(R.array.imageUrls);

    }

    public void downloadingButton(View view){
        String url = editText.getText().toString();
        Thread myThread = new Thread(new DownloadThread(url));
        myThread.start();

        File file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        L.l("downloadingButton " + file.getAbsolutePath()); /* Получить путь к файлу */
        Uri uri = Uri.parse(url);
        L.l("downloadingButton " + uri.getLastPathSegment()); /* Получаем имя после последего / */

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        editText.setText(listOfImages[position]);
    }

    private boolean downloadImageUsingThreads(String url){
        boolean successful = false;
        HttpURLConnection connection = null;
        InputStream inputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            URL downloadURL = new URL(url);
            connection = (HttpURLConnection)  downloadURL.openConnection();
            inputStream = connection.getInputStream();

            File file = new File(Environment.getExternalStoragePublicDirectory
                    (Environment.DIRECTORY_PICTURES).getAbsolutePath()
                    + "/" + Uri.parse(url).getLastPathSegment()); /* Мы создаём файл с именем загр файла */
            L.l("downloadImageUsingThreads " + file.getAbsolutePath());

            fileOutputStream = new FileOutputStream(file);
            L.l("output" + fileOutputStream.toString());

            int read = -1;
            byte[] bytes = new byte[1024];
            while((read = inputStream.read(bytes)) != -1){
                L.l("downloadImageUsingThreads.read " + read);
                fileOutputStream.write(bytes, 0 , read);
            }
            successful = true;
        } catch (MalformedURLException e) {
            L.l(e.toString());
        } catch (IOException e) {
            L.l(e.toString());
        } finally {
            this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    loadingSection.setVisibility(View.GONE);
                }
            });
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

    private class DownloadThread implements Runnable{
        private String url;

        public DownloadThread(String st) {
            url = st;
        }
        @Override
        public void run() {
            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    loadingSection.setVisibility(View.VISIBLE);
                }
            });
            downloadImageUsingThreads(url);
        }
    }
}
