package it.enerjize.p_0271_getintentaction;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class info extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        Intent intent = getIntent();
        String action = intent.getAction();

        String form = "", textInfo = "";

        if (action.equals("action.time"))
        {
            form = "HH:mm:ss";
            textInfo = "Time: ";
        } else if(action.equals("action.date"))
        {
            form = "dd.MM.yyyy";
            textInfo = "Date: ";
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(form);
        String dateTime = simpleDateFormat.format(new Date(System.currentTimeMillis()));

        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(textInfo+dateTime);
    }
}
