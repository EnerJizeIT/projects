package com.example.enerjizeit.recyclerviewmy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<ItemLab> items;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        initItems();
        initAdapter();
    }

    private void initAdapter() {
        CustomAdapter adapter = new CustomAdapter(items);
        recyclerView.setAdapter(adapter);
    }

    private void initItems() {
        items = new ArrayList<>();
        items.add(new ItemLab("Гоша", "1234", android.R.drawable.sym_action_call));
        items.add(new ItemLab("Гоша", "1234", android.R.drawable.sym_action_call));
        items.add(new ItemLab("Гоша", "1234", android.R.drawable.sym_action_call));
        items.add(new ItemLab("Гоша", "1234", android.R.drawable.sym_action_call));
        items.add(new ItemLab("Гоша", "1234", android.R.drawable.sym_action_call));
        items.add(new ItemLab("Гоша", "1234", android.R.drawable.sym_action_call));
        items.add(new ItemLab("Гоша", "1234", android.R.drawable.sym_action_call));
        items.add(new ItemLab("Гоша", "1234", android.R.drawable.sym_action_call));
        items.add(new ItemLab("Гоша", "1234", android.R.drawable.sym_action_call));
        items.add(new ItemLab("Гоша", "1234", android.R.drawable.sym_action_call));
        items.add(new ItemLab("Гоша", "1234", android.R.drawable.sym_action_call));
        items.add(new ItemLab("Гоша", "1234", android.R.drawable.sym_action_call));
        items.add(new ItemLab("Гоша", "1234", android.R.drawable.sym_action_call));
        items.add(new ItemLab("Гоша", "1234", android.R.drawable.sym_action_call));
        items.add(new ItemLab("Гоша", "1234", android.R.drawable.sym_action_call));
        items.add(new ItemLab("Гоша", "1234", android.R.drawable.sym_action_call));
        items.add(new ItemLab("Гоша", "1234", android.R.drawable.sym_action_call));
        items.add(new ItemLab("Гоша", "1234", android.R.drawable.sym_action_call));
        items.add(new ItemLab("Гоша", "1234", android.R.drawable.sym_action_call));
        items.add(new ItemLab("Гоша", "1234", android.R.drawable.sym_action_call));
        items.add(new ItemLab("Гоша", "1234", android.R.drawable.sym_action_call));
        items.add(new ItemLab("Гоша", "1234", android.R.drawable.sym_action_call));
        items.add(new ItemLab("Гоша", "1234", android.R.drawable.sym_action_call));
        items.add(new ItemLab("Гоша", "1234", android.R.drawable.sym_action_call));
        items.add(new ItemLab("Гоша", "1234", android.R.drawable.sym_action_call));
        items.add(new ItemLab("Гоша", "1234", android.R.drawable.sym_action_call));
        items.add(new ItemLab("Гоша", "1234", android.R.drawable.sym_action_call));
        items.add(new ItemLab("Гоша", "1234", android.R.drawable.sym_action_call));
        items.add(new ItemLab("Гоша", "1234", android.R.drawable.sym_action_call));
        items.add(new ItemLab("Гоша", "1234", android.R.drawable.sym_action_call));
        items.add(new ItemLab("Гоша", "1234", android.R.drawable.sym_action_call));
        items.add(new ItemLab("Гоша", "1234", android.R.drawable.sym_action_call));
        items.add(new ItemLab("Гоша", "1234", android.R.drawable.sym_action_call));
        items.add(new ItemLab("Гоша", "1234", android.R.drawable.sym_action_call));
        items.add(new ItemLab("Гоша", "1234", android.R.drawable.sym_action_call));
        items.add(new ItemLab("Гоша", "1234", android.R.drawable.sym_action_call));
        items.add(new ItemLab("Гоша", "1234", android.R.drawable.sym_action_call));
        items.add(new ItemLab("Гоша", "1234", android.R.drawable.sym_action_call));
        items.add(new ItemLab("Гоша", "1234", android.R.drawable.sym_action_call));
        items.add(new ItemLab("Гоша", "1234", android.R.drawable.sym_action_call));
        items.add(new ItemLab("Гоша", "1234", android.R.drawable.sym_action_call));
        items.add(new ItemLab("Гоша", "1234", android.R.drawable.sym_action_call));
        items.add(new ItemLab("Гоша", "1234", android.R.drawable.sym_action_call));
    }
}
