package com.example.enerjizeit.sqlitecontactuse;

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
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends Activity implements View.OnClickListener,AdapterView.OnItemClickListener,
        AdapterView.OnItemLongClickListener {
    private ArrayList<ContactItem> list;
    private CustomAdapter adapter;
    private ListView listView;
    private Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView)findViewById(R.id.listView);
        btnAdd = (Button) findViewById(R.id.bAdd);
        btnAdd.setOnClickListener(this);

        list = ContactDbManager.getInstance(this).getContacts();
        adapter = new CustomAdapter(MainActivity.this, list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(this);
        listView.setOnItemLongClickListener(this);


    }

    @Override
    public void onClick(View v) {
        openAddDialog();

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ContactItem item = adapter.getItem(position);
        ContactItem.selectedItem = item;
        Intent intent = new Intent(MainActivity.this, DelailsActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        ContactItem item = adapter.getItem(position);
        openDeleteDialog(item);
        return false;
    }

    private void openAddDialog() {
        LayoutInflater dialogInflate = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = dialogInflate.inflate(R.layout.edit, null);

        final EditText name = (EditText)view.findViewById(R.id.detailsName);
        final EditText phone = (EditText)view.findViewById(R.id.detailsPhone);
        final EditText about = (EditText)view.findViewById(R.id.detailsAbout);

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setView(view);
        builder.setMessage("Add contact");

        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ContactItem item = new ContactItem();
                item.setName(name.getText().toString());
                item.setPhone(phone.getText().toString());
                item.setAbout(about.getText().toString());

                ContactDbManager.getInstance(getApplicationContext()).addContact(item);
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

    private void openDeleteDialog(final ContactItem item) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage(String.format("Delete contact %s?", item.getName()));
        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ContactDbManager.getInstance(getApplicationContext()).
                        deleteContact(item.getID());
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
        list = ContactDbManager.getInstance(MainActivity.this).getContacts();
        adapter = new CustomAdapter(this, list);
        listView.setAdapter(adapter);
    }
}
