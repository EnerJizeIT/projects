package com.example.enerjizeit.draganddraw;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class DragAndDrawFragment extends Fragment {
    public DragAndDrawFragment() {}
    public static DragAndDrawFragment newInstance(){
        return new DragAndDrawFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_drag_and_draw, container, false);
        return view;
    }

}