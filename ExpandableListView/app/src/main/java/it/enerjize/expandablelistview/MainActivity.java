package it.enerjize.expandablelistview;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends Activity implements ExpandableListView.OnGroupClickListener, ExpandableListView.OnChildClickListener{

    private TextView textSelect;
    private ArrayList<ContactGroup> list;
    private CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ExpandableListView expListView = (ExpandableListView)findViewById(R.id.expListView);
        expListView.setSelected(true);
        textSelect = (TextView)findViewById(R.id.textSelect);

        initContacts();
        /* Создаём адаптер данных */
        adapter = new CustomAdapter(this, list);
        /* Загружаем данные в список */
        expListView.setAdapter(adapter);
        expListView.setOnGroupClickListener(this);
        expListView.setOnChildClickListener(this);

    }

    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

        InfoLab item = adapter.getChild(groupPosition,childPosition);
        textSelect.setText(String.format("Last opened contact: \n%s", item.getName()));

        InfoLab.selectedItem = item;
        Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
        startActivity(intent);
        return false;
    }
    @Override
    public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {

        /* Просто раскрываем и скрываем список */

        return false;
    }
    private void initContacts() {
        list = new ArrayList<ContactGroup>();
    // Создаём группу My Friends
        ContactGroup group = new ContactGroup("My Friends");
        group.addContact(new InfoLab(
                "Jacob Anderson", "412412411", "mail", R.drawable.a1, about));
        group.addContact(new InfoLab(
                "Emily Duncan", "161863187", "mail", R.drawable.a2, about));
        group.addContact(new InfoLab(
                "Michael Fuller", "896443658", "mail", R.drawable.a3, about));
        group.addContact(new InfoLab(
                "Emma Greenman", "964990543", "mail", R.drawable.a4, about));
        group.addContact(new InfoLab(
                "Joshua Harrison", "759285086", "mail", R.drawable.a5, about));
        list.add(group);

        group = new ContactGroup("My Colleagues");
        group.addContact(new InfoLab(
                "Madison Johnson", "950285777", "mail", R.drawable.a6, about));
        group.addContact(new InfoLab(
                "Matthew Cotman", "687699999", "mail", R.drawable.a7, about));
        group.addContact(new InfoLab(
                "Olivia Lawson", "161863187", "mail", R.drawable.a8, about));
        group.addContact(new InfoLab(
                "Andrew Chapman", "546599645", "mail", R.drawable.a9, about));
        group.addContact(new InfoLab(
                "Daniel Honeyman", "876545644", "mail", R.drawable.a10, about));
        list.add(group);

        group = new ContactGroup("Other");
        group.addContact(new InfoLab(
                "Isabella Jackson", "907868756", "mail", R.drawable.a11, about));
        group.addContact(new InfoLab(
                "William Patterson", "687699693", "mail", R.drawable.a12, about));
        group.addContact(new InfoLab(
                "Joseph Godwin", "965467575", "mail", R.drawable.a13, about));
        group.addContact(new InfoLab(
                "Samantha Bush", "907865645", "mail", R.drawable.a14, about));
        group.addContact(new InfoLab(
                "Christopher Gateman", "896874556", "mail", R.drawable.a15, about));
        list.add(group);
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
