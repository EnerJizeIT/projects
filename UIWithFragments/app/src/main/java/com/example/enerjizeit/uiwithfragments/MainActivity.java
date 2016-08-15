package com.example.enerjizeit.uiwithfragments;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends FragmentActivity implements FragmentB.Communicator {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void respond(int i) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentB fragB = (FragmentB) fm.findFragmentById(R.id.fragment2);
        fragB.changeData(i);


    }
}
