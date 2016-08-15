package it.enerjize.expandablelistview;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailsActivity extends Activity {

    private ImageView photo;
    private TextView name;
    private TextView phone;
    private TextView email;
    private TextView about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        photo = (ImageView)findViewById(R.id.detailsPhoto);
        name = (TextView)findViewById(R.id.detailsName);
        phone = (TextView)findViewById(R.id.detailsPhone);
        email = (TextView)findViewById(R.id.detailsEmail);
        about = (TextView)findViewById(R.id.detailsAbout);

        InfoLab item = InfoLab.selectedItem;

        photo.setImageResource(item.getPhotoID());
        name.setText(item.getName());
        phone.setText(item.getPhone());
        email.setText(item.getEmail());
        about.setText(item.getAbout());
    }
}
