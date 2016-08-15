package com.example.enerjizeit.sqliteproject;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {
    private ImageView photo;
    private TextView name,email,phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delails);

        photo = (ImageView)findViewById(R.id.det_photo);
        name = (TextView)findViewById(R.id.det_name);
        phone = (TextView)findViewById(R.id.det_phone);
        email = (TextView)findViewById(R.id.det_email);

        ItemLab item = ItemLab.selectedItem;
        photo.setImageDrawable(Drawable.createFromPath(item.getPhotoPath()));
        name.setText(item.getName());
        phone.setText(item.getPhone());
        email.setText(item.getEmail());
    }
}
