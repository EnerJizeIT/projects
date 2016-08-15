package it.enerjize.listwithdetails;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button btnAdd;
    private ListView listView;
    private ArrayList<InfoLab> list;
    private CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView)findViewById(R.id.listView);
        btnAdd = (Button)findViewById(R.id.btn1);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddDialog();
            }
        });

        initContacts();
        /* Создаём адаптер данных */
        adapter = new CustomAdapter(this, list);
        /* Загружаем данные в список */
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                InfoLab item = adapter.getItem(position);

                // Запоминаем выбранный элемент списка
                InfoLab.selectedItem = item;

                // Открываем DetailsActivity
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, DetailsActivity.class);
                startActivity(intent);
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                InfoLab item = adapter.getItem(position);
                openDeleteDialog(item);

                return false;
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

    private void openAddDialog(){
        LayoutInflater dialogInflate = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View root = dialogInflate.inflate(R.layout.edit_row, null);

        final EditText name = (EditText)root.findViewById(R.id.editName);
        final EditText phone = (EditText)root.findViewById(R.id.editPhone);
        final EditText email = (EditText)root.findViewById(R.id.editEmail);
        final EditText about = (EditText)root.findViewById(R.id.editAbout);


        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setView(root);
        builder.setMessage("Add contact");

        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                InfoLab item = new InfoLab(name.getText().toString(), phone.getText().toString(),
                        email.getText().toString(), R.drawable.a1, about.getText().toString());
                list.add(item);
                refreshList();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.setCancelable(true);
        builder.create();
        builder.show();
    }
    private void openDeleteDialog(final InfoLab item){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete Contact");
        builder.setMessage(String.format("Delete contact %s?", item.getName()));
        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                list.remove(item);
                refreshList();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.setCancelable(true);
        builder.create();
        builder.show();
    }

    private void refreshList() {
        adapter = new CustomAdapter(MainActivity.this, list);
        listView.setAdapter(adapter);
    }
}
