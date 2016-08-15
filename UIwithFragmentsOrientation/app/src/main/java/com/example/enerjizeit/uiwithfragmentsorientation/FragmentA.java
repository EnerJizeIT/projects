package com.example.enerjizeit.uiwithfragmentsorientation;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class FragmentA extends Fragment implements AdapterView.OnItemClickListener{
    ListView list;
    Binding binding;
    public FragmentA() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_a, container, false);

        list = (ListView)view.findViewById(R.id.list);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(),R.array.list, android.R.layout.simple_list_item_1);
        list.setAdapter(adapter);
        list.setOnItemClickListener(this);

        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        binding.respond(position);
    }

    interface Binding {
        void respond(int i);
    }

    public void setCommunicator(Binding binding) {
        this.binding = binding;
    }
}
