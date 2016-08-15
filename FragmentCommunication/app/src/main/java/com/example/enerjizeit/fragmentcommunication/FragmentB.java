package com.example.enerjizeit.fragmentcommunication;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmentB extends Fragment {
    TextView text;
    String data;

    public FragmentB() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_b, container, false);
        if (savedInstanceState != null){
            text = (TextView) view.findViewById(R.id.text);
            data = savedInstanceState.getString("data");
            text.setText(data);
        }
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        text = (TextView)getActivity().findViewById(R.id.text);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("data", data);
    }

    public void changeText(String data){
        this.data = data;
        text.setText(data);
    }
    public interface Communicator {
        void respond(String data);

    }
}
