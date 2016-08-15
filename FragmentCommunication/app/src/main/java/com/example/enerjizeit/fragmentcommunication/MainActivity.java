package com.example.enerjizeit.fragmentcommunication;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;

public class MainActivity extends FragmentActivity implements FragmentB.Communicator, FragmentC.SecondText {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void respond(String data) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentB fb = (FragmentB) manager.findFragmentById(R.id.fragment2);
        fb.changeText(data);
    }

    @Override
    public void secondTextChange(String data) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentC fragmentC = (FragmentC)manager.findFragmentById(R.id.fragment3);
        fragmentC.changeSecondText(data);
    }
}
