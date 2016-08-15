package it.enerjize.scrollview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView text = (TextView)findViewById(R.id.text);
        text.setText("Виджет ScrollView, несмотря на свое название, поддерживает только вертикальную" +
                " прокрутку, поэтому для создания вертикальной и горизонтальной прокрутки необходимо" +
                " использовать ScrollView в сочетании с HorizontalScrollView. Обычно ScrollView" +
                " используют в качестве корневого элемента, а HorizontalScrollView — дочернего.");
    }
}
