package it.enerjize.customdataadapter;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends Activity {
    private ArrayList<ContactItem> list;
    private CustomAdapter adapter;
    private TextView textSelect;
    private final String about = "Здесь содержится подробная информация о контакте.";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        ListView listView = (ListView)findViewById(R.id.listView);
        textSelect = (TextView)findViewById(R.id.textSelect);

        initContacts();
        adapter = new CustomAdapter(this, list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ContactItem item = adapter.getItem(position);
                textSelect.setText(String.format("Last opened contact: \n%s", item.getName()));

                /* Запоминаем выбраный элемент списка */
                ContactItem.selectedItem = item;

                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initContacts() {
        list = new ArrayList<ContactItem>();

        // Заполняем список контактов
        list.add(new ContactItem(
                "Jacob Anderson", "412412411", R.drawable.a1, about));
        list.add(new ContactItem(
                "Emily Duncan", "161863187", R.drawable.a2, about));
        list.add(new ContactItem(
                "Michael Fuller", "896443658", R.drawable.a3, about));
        list.add(new ContactItem(
                "Emma Greenman", "964990543", R.drawable.a4, about));
        list.add(new ContactItem(
                "Joshua Harrison", "759285086", R.drawable.a5, about));
        list.add(new ContactItem(
                "Madison Johnson", "950285777", R.drawable.a6, about));
        list.add(new ContactItem(
                "Matthew Cotman", "687699999", R.drawable.a7, about));
        list.add(new ContactItem(
                "Olivia Lawson", "161863187", R.drawable.a8, about));
        list.add(new ContactItem(
                "Andrew Chapman",  "546599645", R.drawable.a9, about));
        list.add(new ContactItem(
                "Daniel Honeyman", "876545644", R.drawable.a10, about));
        list.add(new ContactItem(
                "Isabella Jackson", "907868756", R.drawable.a11, about));
        list.add(new ContactItem(
                "William Patterson", "687699693", R.drawable.a12, about));
        list.add(new ContactItem(
                "Joseph Godwin", "965467575", R.drawable.a13, about));
        list.add(new ContactItem(
                "Samantha Bush", "907865645", R.drawable.a14, about));
        list.add(new ContactItem(
                "Christopher Gateman", "896874556", R.drawable.a15, about));
    }
}
