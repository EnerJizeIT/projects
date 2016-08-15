package com.example.enerjizeit.intentsendimage;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Button btnSendImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSendImage = (Button)findViewById(R.id.btnSendImage);
        btnSendImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File pictures = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                String []listOfPictures = pictures.list();
                Uri uri = null;
                ArrayList<Uri> list = new ArrayList<Uri>();
                for (String picture: listOfPictures){
                    /* Если мы хотим получить информацию о файлах в системной папке */
                    String toa = "file://"+pictures.toString()+"/"+picture;
                    Toast.makeText(MainActivity.this, toa, Toast.LENGTH_SHORT).show();
                    /* Все файлы выйдут тостами*/
                    uri = Uri.parse(toa);
                    list.add(uri);
                }
                Intent intent = new Intent(Intent.ACTION_SEND_MULTIPLE);
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_STREAM, list);
                startActivity(Intent.createChooser(intent, "Send multiple Images"));
            }
        });

    }
}
