package com.example.enerjizeit.fragmentcommunication;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentC extends Fragment {
    TextView text;
    String data;

    public FragmentC() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        text = (TextView)getActivity().findViewById(R.id.textC);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_c, container, false);
        if(savedInstanceState != null) {
            text = (TextView) view.findViewById(R.id.textC);
            data = savedInstanceState.getString("data");
            text.setText(data);
        }
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("data", data);
    }

    void changeSecondText(String string){
        data = string;
        text.setText(string);
    }
    interface SecondText{
        void secondTextChange(String data);
    }


}
