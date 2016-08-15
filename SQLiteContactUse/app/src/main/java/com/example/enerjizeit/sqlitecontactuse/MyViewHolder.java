package com.example.enerjizeit.sqlitecontactuse;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MyViewHolder {
    ImageView edit;
    ImageView image;
    TextView name;

    MyViewHolder(View v) {
        image = (ImageView)v.findViewById(R.id.image);
        edit = (ImageView)v.findViewById(R.id.edit);
        name = (TextView)v.findViewById(R.id.name);
    }


}
