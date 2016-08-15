package it.enerjize.p_0101_listener;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity
{

    TextView textView;
    Button button1;
    Button button2;
    Button button3;

    private static final String TAG ="MyLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "Найдем View-Элементы");

        textView = (TextView) findViewById(R.id.textView);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);

        View.OnClickListener onClickListener = new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                switch (view.getId())
                {
                    case R.id.button1:
                        Log.d(TAG, "обработаем нажатие кнопки 1");
                        textView.setText(R.string.text1);
                        Toast toast1 = Toast.makeText(MainActivity.this, "Нажата кнопка 1", Toast.LENGTH_SHORT);
                        toast1.setGravity(Gravity.TOP, 10, 10);
                        LinearLayout toastImage = (LinearLayout) toast1.getView();
                        ImageView imageView = new ImageView(MainActivity.this);
                        imageView.setImageResource(R.drawable.bird);
                        toastImage.addView(imageView,0);
                        toast1.show();
                        break;
                    case R.id.button2:
                        Log.d(TAG, "обработаем нажатие кнопки 2");
                        textView.setText(R.string.text2);
                        Toast toast2 = Toast.makeText(MainActivity.this, "Нажата кнопка 2", Toast.LENGTH_SHORT);
                        LinearLayout toastImage2 = (LinearLayout) toast2.getView();
                        ImageView imageView2 = new ImageView(MainActivity.this);
                        imageView2.setImageResource(R.drawable.peng);
                        toastImage2.addView(imageView2,0);
                        toast2.setGravity(Gravity.BOTTOM, 10, 10);
                        toast2.show();
                        break;
                    case R.id.button3:
                        Log.d(TAG, "обработаем нажатие кнопки 3");
                        textView.setText(R.string.text3);
                        Toast toast3 = Toast.makeText(MainActivity.this, "Нажата кнопка 3", Toast.LENGTH_SHORT);
                        toast3.setGravity(Gravity.LEFT, 10, 10);
                        LinearLayout toastImage3 = (LinearLayout) toast3.getView();
                        ImageView imageView3 = new ImageView(MainActivity.this);
                        imageView3.setImageResource(R.drawable.peng);
                        toastImage3.addView(imageView3,0);
                        toast3.show();
                        break;
                }
            }
        };

        button1.setOnClickListener(onClickListener);
        button2.setOnClickListener(onClickListener);
        button3.setOnClickListener(onClickListener);

        textView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Log.d(TAG, "обработаем нажатие текста");
                Toast toast4 = Toast.makeText(MainActivity.this, "Жулик нажал на текст =(", Toast.LENGTH_SHORT);
                toast4.setGravity(Gravity.RIGHT, 10, 10);

                LinearLayout toastImage = (LinearLayout) toast4.getView();
                ImageView imageView = new ImageView(MainActivity.this);
                imageView.setImageResource(R.drawable.bird);
                toastImage.addView(imageView,0);

                toast4.show();
                button3.setText(R.string.textButton);
            }
        });

    }
}
