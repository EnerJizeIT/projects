package it.enerjize.ast_13_tooglebutton;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    ToggleButton togBut;
    TextView text;
    Switch swi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        togBut = (ToggleButton)findViewById(R.id.toggle_button);
        text = (TextView)findViewById(R.id.text);
        text.setVisibility(View.INVISIBLE);
        togBut.setChecked(false);
        togBut.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    text.setVisibility(View.VISIBLE);
                    text.setText("On");
                } else {
                    text.setVisibility(View.INVISIBLE);

                }
            }
        });
        swi = (Switch)findViewById(R.id.swi);
        swi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    text.setTextColor(Color.RED);
                } else{
                 text.setTextColor(Color.BLUE);
                }
            }
        });

    }


}
