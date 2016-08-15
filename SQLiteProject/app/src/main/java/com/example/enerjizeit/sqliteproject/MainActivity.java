package com.example.enerjizeit.sqliteproject;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<ItemLab> list;
    private ItemAdapter adapter;
    private ListView listView;
    private Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView)findViewById(R.id.listView);
        btnAdd = (Button)findViewById(R.id.bAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddDialog();
            }
        });
        list = DBManagerClass.getInstance(this).getItems();
        adapter = new ItemAdapter(this, list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                ItemLab item = adapter.getItem(position);
                openDeleteDialog(item);

                return false;
            }
        });
    }

    private void openDeleteDialog(final ItemLab item) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(
                String.format("Delete contact %s?", item.getName()));
        builder.setPositiveButton("Delete",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        DBManagerClass.getInstance(
                                getApplicationContext()).deleteContact(item.getID());
                        refreshList();
                    }
                });

        builder.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        builder.setCancelable(false);
        builder.create();
        builder.show();
    }

    private void openAddDialog() {
        LayoutInflater dlgInflater = (LayoutInflater)getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        View root = dlgInflater.inflate(R.layout.edit, null);

        final EditText name = (EditText)root.findViewById(R.id.edit_name);
        final EditText phone = (EditText)root.findViewById(R.id.edit_phone);
        final EditText email = (EditText)root.findViewById(R.id.edit_email);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(root);
        builder.setMessage("Add contact");

        builder.setPositiveButton("Save",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        ItemLab item = new ItemLab();
                        item.setName(name.getText().toString());
                        item.setPhone(phone.getText().toString());
                        item.setEmail(email.getText().toString());

                        DBManagerClass.getInstance(
                                getApplicationContext()).addContact(item);
                        refreshList();

                    }
                });

        builder.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        builder.setCancelable(false);
        builder.create();
        builder.show();
    }

    private void refreshList() {
        list = DBManagerClass.getInstance(this).getItems();
        adapter = new ItemAdapter(this, list);
        listView.setAdapter(adapter);
    }
}
