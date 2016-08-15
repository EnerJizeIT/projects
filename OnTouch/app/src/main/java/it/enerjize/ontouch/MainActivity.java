package it.enerjize.ontouch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity  implements View.OnTouchListener{

    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RelativeLayout rev = (RelativeLayout)findViewById(R.id.rev);
        text = (TextView)findViewById(R.id.text);

        rev.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        String action = "";
        switch (event.getActionMasked()){
            case MotionEvent.ACTION_DOWN:
                action = "Action Down";
                break;
            case MotionEvent.ACTION_MOVE:
                action = "Action Move";
                break;
            case MotionEvent.ACTION_UP:
                action = "Action Up";
                break;
            case MotionEvent.ACTION_CANCEL:
                action = "Action Cancel";
                break;
            
        }
        text.setText(String.format("%s: X=%.1f, Y=%.1f", action, event.getX(), event.getY()));
        return true;
    }
}
