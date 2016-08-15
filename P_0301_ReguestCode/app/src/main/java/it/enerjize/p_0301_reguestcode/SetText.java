package it.enerjize.p_0301_reguestcode;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SetText extends AppCompatActivity implements View.OnClickListener{

    Button bt_tol, bt_venya, bt_gora;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_text);

        bt_gora = (Button)findViewById(R.id.bt_gora);
        bt_venya = (Button)findViewById(R.id.bt_venya);
        bt_tol = (Button)findViewById(R.id.bt_tol);

        bt_gora.setOnClickListener(this);
        bt_venya.setOnClickListener(this);
        bt_tol.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        if(v.getId() == R.id.bt_gora){
            intent.putExtra("name", "Гораций");
        } else if(v.getId() == R.id.bt_venya)
        {
            intent.putExtra("name", "Вениамин");
        } else if (v.getId() == R.id.bt_tol)
        {
            intent.putExtra("name", "Анатолий");
        }
        setResult(RESULT_OK, intent);
        finish();
    }
}
