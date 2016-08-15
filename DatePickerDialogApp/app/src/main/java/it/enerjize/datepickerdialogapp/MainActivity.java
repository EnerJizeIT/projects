package it.enerjize.datepickerdialogapp;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity{

    private TextView textView;
    private int years, months, days;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.text);
        Button btn1 = (Button) findViewById(R.id.btn1);
        btn1.setOnClickListener(onClicker);

        final Calendar c = Calendar.getInstance();
        years = c.get(Calendar.YEAR);
        months = c.get(Calendar.MONTH);
        days = c.get(Calendar.DAY_OF_MONTH);

        updateDisplay();
    }

    private final View.OnClickListener onClicker = new View.OnClickListener()
    {
        @Override
        public void onClick(View v) {
            DatePickerDialog dialog = new DatePickerDialog(MainActivity.this, dateListener, years,months,days);
            dialog.show();
        }
    };

    private final DatePickerDialog.OnDateSetListener dateListener = new DatePickerDialog.OnDateSetListener(){
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonths){
            years = year;
            months = monthOfYear;
            days = dayOfMonths;
            updateDisplay();
        }
    };

    private void updateDisplay() {
        textView.setText(new StringBuilder().append(days).append("-")
        .append(months).append("-")
        .append(years).append("-"));
    }
}
