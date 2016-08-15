package com.example.enerjizeit.sqliteproject;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;

import java.util.ArrayList;

public class ItemAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<ItemLab> list;
    private LayoutInflater inflater;

    public ItemAdapter(Context context, ArrayList<ItemLab> list) {
        this.context = context;
        this.list = list;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public ItemLab getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(convertView == null){
            view = inflater.inflate(R.layout.row, null);
        }
        final ItemLab item = list.get(position);

        ImageView image = (ImageView)view.findViewById(R.id.row_image);
        TextView name = (TextView)view.findViewById(R.id.row_name);
        name.setText(item.getName());
        image.setImageDrawable(Drawable.createFromPath(item.getPhotoPath()));

        ImageView edit = (ImageView)view.findViewById(R.id.row_edit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEditDialog(item);
            }
        });

        return view;
    }

    private void openEditDialog(final ItemLab item) {
        LayoutInflater dialogInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = dialogInflater.inflate(R.layout.edit, null);

        final EditText name = (EditText)view.findViewById(R.id.edit_name);
        final EditText phone = (EditText)view.findViewById(R.id.edit_phone);
        final EditText email = (EditText)view.findViewById(R.id.edit_email);

        name.setText(item.getName());
        phone.setText(item.getPhone());
        email.setText(item.getEmail());

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(view);
        builder.setMessage("Edit Contact");

        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                item.setName(name.getText().toString());
                item.setEmail(email.getText().toString());
                item.setPhone(phone.getText().toString());
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
}
