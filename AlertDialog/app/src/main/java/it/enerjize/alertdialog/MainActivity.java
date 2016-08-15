package it.enerjize.alertdialog;

import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button btn1, btn2;
    private TextView text;
    private final CharSequence[] colors = {"Red", "Green", "Blue"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = (Button)findViewById(R.id.btn1);
        btn2 = (Button)findViewById(R.id.btn2);
        text = (TextView)findViewById(R.id.textView);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openItemDialog();
            }
        });
    }

    private void openItemDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Выбирай цвет какой нравится");
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setCancelable(false);

        builder.setItems(colors, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(colors[which] == "Red"){
                    text.setTextColor(Color.RED);
                } else if(colors[which] == "Green"){
                    text.setTextColor(Color.GREEN);
                } else if(colors[which] == "Blue"){
                    text.setTextColor(Color.BLUE);
                }
                dialog.cancel();
            }
        });
        builder.create();
        builder.show();
    }

    private void openDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Расскрасить текст?");
        builder.setTitle("Жмяк на кнопку!");
        builder.setIcon(R.mipmap.ic_launcher);

        builder.setPositiveButton("Да, давай!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                text.setTextColor(Color.YELLOW);
            }
        });
        builder.setNegativeButton("Не, не надо!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.setCancelable(false);
        builder.create();
        builder.show();
    }
}
