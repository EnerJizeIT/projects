package it.enerjize.p_0331_sharedpreferences;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText edText;
    Button btLoad, btSave;
    SharedPreferences sPref;

    final String SAVED_TEXT = "saved_text";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btLoad = (Button) findViewById(R.id.btLoad);
        btSave = (Button) findViewById(R.id.btSave);
        edText = (EditText) findViewById(R.id.edText);

        btLoad.setOnClickListener(this);
        btSave.setOnClickListener(this);
        loadText();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.btSave:
                saveText();
                break;
            case R.id.btLoad:
                loadText();
                break;
            default:
                break;
        }
    }

    private void saveText()
    {
        sPref = getSharedPreferences("MyPref", MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putString(SAVED_TEXT, edText.getText().toString());
        ed.commit();
        Toast.makeText(this, "Text saved", Toast.LENGTH_SHORT).show();

    }
    private  void loadText()
    {
        sPref = getPreferences(MODE_PRIVATE);
        String savedText = sPref.getString(SAVED_TEXT, "");
        edText.setText(savedText);
        Toast.makeText(this,"Text Load", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveText();
    }
}
