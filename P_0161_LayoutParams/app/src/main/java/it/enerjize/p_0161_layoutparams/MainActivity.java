package it.enerjize.p_0161_layoutparams;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        int wrap_content = ViewGroup.LayoutParams.WRAP_CONTENT;
        int match_parent = ViewGroup.LayoutParams.MATCH_PARENT; // создали переменные для параметров Params которые можно использовать

        LinearLayout linearLayout = new LinearLayout(this); // мы создали макет расположения
        linearLayout.setOrientation(LinearLayout.VERTICAL); // указали ориентацию
        ViewGroup.LayoutParams linearParams= new ViewGroup.LayoutParams(match_parent, match_parent);
        setContentView(linearLayout, linearParams);
        ViewGroup.LayoutParams View1 = new ViewGroup.LayoutParams(wrap_content, wrap_content);

        TextView tv = new TextView(this); // добавили текстовое поле к нашему макету
        tv.setText("TextView");
        tv.setLayoutParams(View1);
        linearLayout.addView(tv);

        Button btn = new Button(this);	// добавили кнопку к нашему макету
        btn.setText("Button");
        linearLayout.addView(btn, View1);

        LinearLayout.LayoutParams marginParams = new LinearLayout.LayoutParams(wrap_content, wrap_content);
        marginParams.leftMargin = 50; // Левый отступ
        marginParams.topMargin = 50; // верхний отступ
        Button btn1 = new Button(this);
        btn1.setText("Button1");
        linearLayout.addView(btn1, marginParams);

        LinearLayout.LayoutParams rightGravityParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        rightGravityParams.gravity = Gravity.RIGHT;
        Button btn2 = new Button(this);
        btn2.setText("Button2");
        linearLayout.addView(btn2, rightGravityParams);
    }
}
