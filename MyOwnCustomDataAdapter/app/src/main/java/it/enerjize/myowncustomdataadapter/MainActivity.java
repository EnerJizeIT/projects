package it.enerjize.myowncustomdataadapter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends Activity {

    private TextView textSelect;
    private ListView listView;
    private ArrayList<InfoLab> list;
    private CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView)findViewById(R.id.listView);
        textSelect = (TextView)findViewById(R.id.textSelect);

        initContacts();
        /* Создаём адаптер данных */
        adapter = new CustomAdapter(this, list);
        /* Загружаем данные в список */
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                InfoLab item = adapter.getItem(position);
                String text = String.format("Last opened contact:\n%s", item.getName());
                textSelect.setText(text);
            }
        });

    }

    private void initContacts() {
        list = new ArrayList<InfoLab>();
        // Заполняем список контактов
        list.add(new InfoLab(
                "Jacob Anderson", "412412411", "mail", R.drawable.a1));
        list.add(new InfoLab(
                "Emily Duncan", "161863187", "mail", R.drawable.a2));
        list.add(new InfoLab(
                "Michael Fuller", "896443658", "mail", R.drawable.a3));
        list.add(new InfoLab(
                "Emma Greenman", "964990543", "mail", R.drawable.a4));
        list.add(new InfoLab(
                "Joshua Harrison", "759285086", "mail", R.drawable.a5));
        list.add(new InfoLab(
                "Madison Johnson", "950285777", "mail", R.drawable.a6));
        list.add(new InfoLab(
                "Matthew Cotman", "687699999", "mail", R.drawable.a7));
        list.add(new InfoLab(
                "Olivia Lawson", "161863187", "mail", R.drawable.a8));
        list.add(new InfoLab(
                "Andrew Chapman", "546599645", "mail", R.drawable.a9));
        list.add(new InfoLab(
                "Daniel Honeyman", "876545644", "mail", R.drawable.a10));
        list.add(new InfoLab(
                "Isabella Jackson", "907868756", "mail", R.drawable.a11));
        list.add(new InfoLab(
                "William Patterson", "687699693", "mail", R.drawable.a12));
        list.add(new InfoLab(
                "Joseph Godwin", "965467575", "mail", R.drawable.a13));
        list.add(new InfoLab(
                "Samantha Bush", "907865645", "mail", R.drawable.a14));
        list.add(new InfoLab(
                "Christopher Gateman", "896874556", "mail", R.drawable.a15));
    }

}
