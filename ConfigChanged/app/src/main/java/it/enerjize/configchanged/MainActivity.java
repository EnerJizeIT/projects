package it.enerjize.configchanged;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView text;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (TextView) findViewById(R.id.text);
    }
    @Override
    public void onConfigurationChanged(Configuration config) {
        super.onConfigurationChanged(config);
        String orientation = "";
        switch (config.orientation) {
            case Configuration.ORIENTATION_PORTRAIT:
                orientation = "Portrait";
                break;
            case Configuration.ORIENTATION_LANDSCAPE:
                orientation = "Landscape";
                break;
            case Configuration.ORIENTATION_UNDEFINED:
                orientation = "Undefined";
                break;
        }
        int a = config.screenWidthDp;
        int b = config.screenHeightDp;
        text.setText(orientation + " " + a + " " + b);
    }
}
