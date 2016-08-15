package com.example.enerjizeit.uiwithfragmentsorientation;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements FragmentA.Binding{
    FragmentManager manager;
    FragmentA fragmentA;
    FragmentB fragmentB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager = getSupportFragmentManager();
        fragmentA = (FragmentA)manager.findFragmentById(R.id.fragmentA);
        fragmentA.setCommunicator(this);
    }

    @Override
    public void respond(int i) {
        fragmentB = (FragmentB)manager.findFragmentById(R.id.fragmentB);
        if(fragmentB != null && fragmentB.isVisible()){
            fragmentB.changeText(i);
        } else {
            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            intent.putExtra("pos", i);
            startActivity(intent);
        }

    }
}
