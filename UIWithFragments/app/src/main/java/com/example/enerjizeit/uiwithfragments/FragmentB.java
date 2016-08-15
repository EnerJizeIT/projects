package com.example.enerjizeit.uiwithfragments;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmentB extends Fragment {
    TextView text;
    int i;

    public FragmentB() {}

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        text = (TextView)getActivity().findViewById(R.id.textView);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_b, container, false);
        if(savedInstanceState != null){
            text = (TextView)view.findViewById(R.id.textView);
            i = savedInstanceState.getInt("pos");
            Resources res = getResources();
            String [] desc = res.getStringArray(R.array.descriptions);
            text.setText(desc[i]);
        }

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("pos", i);
    }

    void changeData(int i){
        this.i = i;
        Resources res = getResources();
        String [] desc = res.getStringArray(R.array.descriptions);
        text.setText(desc[i]);
    }

    interface Communicator{
        void respond(int i);
    }


}
