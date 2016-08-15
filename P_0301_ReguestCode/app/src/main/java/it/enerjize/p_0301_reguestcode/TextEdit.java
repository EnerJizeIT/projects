package it.enerjize.p_0301_reguestcode;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class TextEdit extends AppCompatActivity {

    EditText editText1, editText2, editText3;
    CheckBox box1, box2, box3;
    Button bt_ok;
    String Arr, edit1, edit2, edit3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_edit);

        editText1 = (EditText) findViewById(R.id.editText1);
        editText2 = (EditText) findViewById(R.id.editText2);
        editText3 = (EditText) findViewById(R.id.editText3);
        box1 = (CheckBox) findViewById(R.id.Box1);
        box2 = (CheckBox) findViewById(R.id.Box2);
        box3 = (CheckBox) findViewById(R.id.Box3);
        bt_ok = (Button) findViewById(R.id.bt_ok);

        if (box1.isChecked()) {
            edit1 = " " + editText1.getText().toString() + " ";
        } else {
            edit1 = " ";
        }
        if (box2.isChecked()) {
            edit2 = " " + editText2.getText().toString() + " ";
        } else {
            edit2 = " ";
        }
        if (box3.isChecked()) {
            edit3 = " " + editText3.getText().toString() + " ";
        } else {
            edit3 = " ";
        }

        Arr = edit1 + edit2 + edit3;

        bt_ok.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v) {
                if(v.getId() == R.id.bt_ok)
                {
                    Intent intent = new Intent();
                    intent.putExtra("TEXT", Arr);
                    setResult(RESULT_OK,intent);
                    finish();
                }
            }
        });


    }

}
