package com.example.enerjizeit.fragmentcommunication;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentA extends Fragment {
    Button btn;
    int counter;
    FragmentB.Communicator comm;
    FragmentC.SecondText sec;
    public FragmentA() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if(savedInstanceState == null){
            counter = 0;
        } else {
            counter = savedInstanceState.getInt("counter", 0);
        }
        return inflater.inflate(R.layout.fragment_a, container, false);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("counter", counter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        comm = (FragmentB.Communicator) getActivity();
        sec = (FragmentC.SecondText) getActivity();

        btn = (Button)getActivity().findViewById(R.id.btn1);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter ++;
                comm.respond("The button was clicked " + counter + " times");
                sec.secondTextChange("Counter -- " + String.valueOf(counter-2));
            }
        });

    }
}
