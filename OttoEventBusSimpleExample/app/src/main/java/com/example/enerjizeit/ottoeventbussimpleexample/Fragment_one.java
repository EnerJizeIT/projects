package com.example.enerjizeit.ottoeventbussimpleexample;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.otto.Subscribe;

public class Fragment_one extends Fragment {
    public Fragment_one() {}
    TextView textView1;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_one, container, false);
        textView1 = (TextView)v.findViewById(R.id.text1);
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        BusStation.getBus().register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        BusStation.getBus().unregister(this);
    }

    @Subscribe
    public void recieveMessage(Message message){
        textView1.setText(message.getMsg());
    }
}
