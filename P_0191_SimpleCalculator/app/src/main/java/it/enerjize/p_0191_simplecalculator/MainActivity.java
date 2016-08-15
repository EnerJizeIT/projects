package it.enerjize.p_0191_simplecalculator;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener{

    final int MENU_RESET_ID = 1;
    final int MENU_QUIT_ID = 2;

    EditText etNum1, etNum2;
    Button btnAdd, btnSub, btnMult, btnDiv;
    TextView tvResult;
    String oper = "";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNum1 = (EditText)findViewById(R.id.etNum1);
        etNum2 = (EditText)findViewById(R.id.etNum2);
        btnAdd = (Button)findViewById(R.id.btnAdd);
        btnSub = (Button)findViewById(R.id.btnSub);
        btnMult = (Button)findViewById(R.id.btnMult);
        btnDiv = (Button)findViewById(R.id.btnDiv);
        tvResult = (TextView)findViewById(R.id.tvResult);

        btnAdd.setOnClickListener(this);
        btnMult.setOnClickListener(this);
        btnSub.setOnClickListener(this);
        btnDiv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        float num1=0;
        float num2=0;
        float result=0;
        // проверяем поля на пустоту
        if(TextUtils.isEmpty(etNum1.getText().toString()) || TextUtils.isEmpty(etNum2.getText().toString()))
        {
            return;
        }
        // читаем EditText и заполняем переменные числами
        num1 =Float.parseFloat(etNum1.getText().toString());
        num2 =Float.parseFloat(etNum2.getText().toString());

        switch (v.getId())
        {
            case R.id.btnAdd:
                oper = "+";
                result = num1+ num2;
                break;
            case R.id.btnSub:
                oper = "-";
                result = num1 - num2;
                break;
            case  R.id.btnMult:
                oper = "*";
                if(num1 == 0)
                {
                    Toast.makeText(MainActivity.this, "Не стоит умножать на ноль", Toast.LENGTH_SHORT).show();
                    break;
                } else if (num2 == 0)
                {
                    Toast.makeText(MainActivity.this, "Не стоит умножать на ноль", Toast.LENGTH_SHORT).show();
                    break;
                }
                result = num1*num2;
                break;
            case R.id.btnDiv:
                oper = "/";
                if(num1 == 0)
                {
                    Toast.makeText(MainActivity.this, "Не стоит ноль делить", Toast.LENGTH_SHORT).show();
                    break;
                } else if (num2 == 0)
                {
                    Toast.makeText(MainActivity.this, "Не стоит делить на ноль", Toast.LENGTH_SHORT).show();
                    break;
                }
                result = num1/num2;
                break;
            default:
                break;
        }
        // формируем строку вывода
        tvResult.setText(num1+" "+oper+" "+ num2+" = "+result);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        menu.add(0, MENU_RESET_ID, 0 , "Reset");
        menu.add(0, MENU_QUIT_ID, 0 , "Quit");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case MENU_QUIT_ID:
                finish();
                break;
            case MENU_RESET_ID:
                etNum1.setText("");
                etNum2.setText("");
                tvResult.setText("");
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
