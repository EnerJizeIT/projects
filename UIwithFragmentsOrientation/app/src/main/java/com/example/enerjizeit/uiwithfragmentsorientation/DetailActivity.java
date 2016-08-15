package com.example.enerjizeit.uiwithfragmentsorientation;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
    FragmentManager manager;
    FragmentB fragmentB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        int pos = intent.getIntExtra("pos", 0);

        manager = getSupportFragmentManager();
        fragmentB = (FragmentB)manager.findFragmentById(R.id.fragmentB);
        fragmentB.changeText(pos);
    }
}
