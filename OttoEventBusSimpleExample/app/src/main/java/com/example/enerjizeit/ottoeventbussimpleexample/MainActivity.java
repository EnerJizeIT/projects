package com.example.enerjizeit.ottoeventbussimpleexample;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    FragmentManager manager;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manager = getSupportFragmentManager();
        manager.beginTransaction().add(R.id.fragment_container1, new Fragment_one(), "F1")
                .add(R.id.fragment_container2, new Fragment_two(), "F2").commit();

        editText = (EditText)findViewById(R.id.edit);
        Button btn = (Button)findViewById(R.id.btnSend);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BusStation.getBus().post(new Message(editText.getText().toString()));
            }
        });
    }
}
