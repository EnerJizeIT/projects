package com.example.enerjizeit.cardviewrecyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Person> persons;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=(RecyclerView)findViewById(R.id.recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        initializeData();
        initializeAdapter();
    }

    private void initializeData(){
        persons = new ArrayList<>();
        persons.add(new Person("Emma Wilson", "23 years old", R.drawable.emma));
        persons.add(new Person("Lavery Maiss", "25 years old", R.drawable.skill));
        persons.add(new Person("Lavery Maiss", "25 years old", R.drawable.skill));
        persons.add(new Person("Lavery Maiss", "25 years old", R.drawable.skill));
        persons.add(new Person("Lavery Maiss", "25 years old", R.drawable.skill));
        persons.add(new Person("Lavery Maiss", "25 years old", R.drawable.skill));
        persons.add(new Person("Lavery Maiss", "25 years old", R.drawable.skill));
        persons.add(new Person("Lavery Maiss", "25 years old", R.drawable.skill));
        persons.add(new Person("Lavery Maiss", "25 years old", R.drawable.skill));
        persons.add(new Person("Lavery Maiss", "25 years old", R.drawable.skill));
        persons.add(new Person("Lavery Maiss", "25 years old", R.drawable.skill));
        persons.add(new Person("Lavery Maiss", "25 years old", R.drawable.skill));
        persons.add(new Person("Lavery Maiss", "25 years old", R.drawable.skill));
        persons.add(new Person("Lavery Maiss", "25 years old", R.drawable.skill));
        persons.add(new Person("Lavery Maiss", "25 years old", R.drawable.skill));
        persons.add(new Person("Lavery Maiss", "25 years old", R.drawable.skill));
        persons.add(new Person("Lavery Maiss", "25 years old", R.drawable.skill));
        persons.add(new Person("Lavery Maiss", "25 years old", R.drawable.skill));
        persons.add(new Person("Lavery Maiss", "25 years old", R.drawable.skill));
        persons.add(new Person("Lavery Maiss", "25 years old", R.drawable.skill));
        persons.add(new Person("Lavery Maiss", "25 years old", R.drawable.skill));
        persons.add(new Person("Lavery Maiss", "25 years old", R.drawable.skill));
        persons.add(new Person("Lavery Maiss", "25 years old", R.drawable.skill));
        persons.add(new Person("Lavery Maiss", "25 years old", R.drawable.skill));
        persons.add(new Person("Lavery Maiss", "25 years old", R.drawable.skill));
        persons.add(new Person("Lavery Maiss", "25 years old", R.drawable.skill));
        persons.add(new Person("Lavery Maiss", "25 years old", R.drawable.skill));
        persons.add(new Person("Lavery Maiss", "25 years old", R.drawable.skill));
        persons.add(new Person("Lavery Maiss", "25 years old", R.drawable.skill));
        persons.add(new Person("Lavery Maiss", "25 years old", R.drawable.skill));
        persons.add(new Person("Lavery Maiss", "25 years old", R.drawable.skill));
        persons.add(new Person("Lavery Maiss", "25 years old", R.drawable.skill));
        persons.add(new Person("Lavery Maiss", "25 years old", R.drawable.skill));
        persons.add(new Person("Lillie Watts", "35 years old", R.drawable.lavery));
    }

    private void initializeAdapter(){
        RVAdapter adapter = new RVAdapter(persons);
        recyclerView.setAdapter(adapter);
    }
}
