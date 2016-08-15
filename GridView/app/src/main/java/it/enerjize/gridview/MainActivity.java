package it.enerjize.gridview;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends Activity {
    private ArrayList<InfoLab> list;
    private CustomAdapter adapter;
    private TextView textSelect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridView gridView = (GridView)findViewById(R.id.grid);
        textSelect = (TextView)findViewById(R.id.textSelect);

        initContacts();
        adapter = new CustomAdapter(this, list);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                InfoLab item = adapter.getItem(position);
                textSelect.setText(String.format("Last opened contact: %s", item.getName()));

                InfoLab.selectedItem = item;
                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                startActivity(intent);
            }
        });

    }
    private void initContacts() {
        list = new ArrayList<InfoLab>();
        // Заполняем список контактов
        list.add(new InfoLab(
                "Jacob Anderson", "412412411", "mail", R.drawable.a1, about));
        list.add(new InfoLab(
                "Emily Duncan", "161863187", "mail", R.drawable.a2, about));
        list.add(new InfoLab(
                "Michael Fuller", "896443658", "mail", R.drawable.a3, about));
        list.add(new InfoLab(
                "Emma Greenman", "964990543", "mail", R.drawable.a4, about));
        list.add(new InfoLab(
                "Joshua Harrison", "759285086", "mail", R.drawable.a5, about));
        list.add(new InfoLab(
                "Madison Johnson", "950285777", "mail", R.drawable.a6, about));
        list.add(new InfoLab(
                "Matthew Cotman", "687699999", "mail", R.drawable.a7, about));
        list.add(new InfoLab(
                "Olivia Lawson", "161863187", "mail", R.drawable.a8, about));
        list.add(new InfoLab(
                "Andrew Chapman", "546599645", "mail", R.drawable.a9, about));
        list.add(new InfoLab(
                "Daniel Honeyman", "876545644", "mail", R.drawable.a10, about));
        list.add(new InfoLab(
                "Isabella Jackson", "907868756", "mail", R.drawable.a11, about));
        list.add(new InfoLab(
                "William Patterson", "687699693", "mail", R.drawable.a12, about));
        list.add(new InfoLab(
                "Joseph Godwin", "965467575", "mail", R.drawable.a13, about));
        list.add(new InfoLab(
                "Samantha Bush", "907865645", "mail", R.drawable.a14, about));
        list.add(new InfoLab(
                "Christopher Gateman", "896874556", "mail", R.drawable.a15, about));
    }
    // Содержимое для поля about
    private final String about = "Прощай, немытая Россия,\n" +
            "Страна рабов, страна господ,\n" +
            "И вы, мундиры голубые,\n" +
            "И ты, им преданный народ.\n" +
            "\n" +
            "Быть может, за стеной Кавказа\n" +
            "Сокроюсь от твоих пашей,\n" +
            "От их всевидящего глаза,\n" +
            "От их всеслышащих ушей.";
}
