package com.example.enerjizeit.uiwithfragmentsorientation;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentB extends Fragment {
    TextView text;
    int i;

    public FragmentB() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_b, container, false);
        text = (TextView) view.findViewById(R.id.text);

        return view;
    }
    void changeText(int i){
        String [] det = getResources().getStringArray(R.array.details);
        text.setText(det[i]);
    }

}
