package com.example.enerjizeit.sqlitecontactuse;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DelailsActivity extends AppCompatActivity {

    private ImageView photo;
    private TextView name;
    private TextView phone;
    private TextView about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delails);

        photo = (ImageView)findViewById(R.id.detailsPhoto);
        name = (TextView)findViewById(R.id.detailsName);
        phone = (TextView)findViewById(R.id.detailsPhone);
        about = (TextView)findViewById(R.id.detailsAbout);

        ContactItem item = ContactItem.selectedItem;
        photo.setImageDrawable(Drawable.createFromPath(item.getPhotoPath()));
        name.setText(item.getName());
        phone.setText(item.getPhone());
        about.setText(item.getAbout());

    }
}
