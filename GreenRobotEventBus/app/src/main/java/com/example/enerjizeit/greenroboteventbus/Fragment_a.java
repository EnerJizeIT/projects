package com.example.enerjizeit.greenroboteventbus;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class Fragment_a extends Fragment {
    public Fragment_a() {}
    EditText editText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_a, container, false);
        editText = (EditText)v.findViewById(R.id.edit);


        Button btnSend = (Button)v.findViewById(R.id.btnSendMessage);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new CustomMessageEvent(editText.getText().toString()));
            }
        });
        return v;
    }


}
