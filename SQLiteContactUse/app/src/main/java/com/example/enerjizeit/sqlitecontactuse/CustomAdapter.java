package com.example.enerjizeit.sqlitecontactuse;

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
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by EnerJize It on 22.06.2016.
 */
public class CustomAdapter extends BaseAdapter{

    private ArrayList<ContactItem> data;
    private LayoutInflater inflater;
    private Context context;

    public CustomAdapter(Context context, ArrayList<ContactItem> data) {
        this.data = data;
        this.context = context;
        inflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public ContactItem getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        MyViewHolder holder = null;
        if(view == null){
            view = inflater.inflate(R.layout.row, null);
            holder = new MyViewHolder(view);
            view.setTag(holder);
        } else{
            holder = (MyViewHolder) view.getTag();
        }
        final ContactItem item = data.get(position);

        holder.image.setImageDrawable(Drawable.createFromPath(item.getPhotoPath()));
        holder.name.setText(item.getName());
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEditDialog(item);
            }
        });

        return view;
    }

    private void openEditDialog(final ContactItem item) {
        LayoutInflater dialogInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = dialogInflater.inflate(R.layout.edit, null);

        final EditText name = (EditText)view.findViewById(R.id.detailsName);
        final EditText phone = (EditText)view.findViewById(R.id.detailsPhone);
        final EditText about = (EditText)view.findViewById(R.id.detailsAbout);

        name.setText(item.getName());
        phone.setText(item.getPhone());
        about.setText(item.getAbout());

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(view);
        builder.setMessage("Edit contact");

        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                item.setName(name.getText().toString());
                item.setPhone(phone.getText().toString());
                item.setAbout(about.getText().toString());
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
