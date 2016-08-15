package it.enerjize.p_0151_contextmenu;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    TextView tvColor, tvSize;

    final int MENU_COLOR_RED = 1;
    final int MENU_COLOR_GREEN = 2;
    final int MENU_COLOR_BLUE = 3;

    final int MENU_SIZE_22 = 4;
    final int MENU_SIZE_26 = 5;
    final int MENU_SIZE_30 = 6;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvColor = (TextView) findViewById(R.id.tvColor);
        tvSize = (TextView) findViewById(R.id.tvSize);

        registerForContextMenu(tvColor);
        registerForContextMenu(tvSize);


        // прописал из кода одну кнопку в активити!
        LinearLayout mainLayout = (LinearLayout) findViewById(R.id.mainLayout);
        LinearLayout.LayoutParams marginParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        marginParams.leftMargin = 150; // Левый отступ
        marginParams.topMargin = 150; // верхний отступ

        Button btn1 = new Button(this);
        btn1.setText("Button1");
        mainLayout.addView(btn1,marginParams);
        

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        switch (v.getId())
        {
            case R.id.tvColor:
                menu.add(0, MENU_COLOR_RED, 0 , "Red");
                menu.add(0, MENU_COLOR_GREEN, 0 , "Green");
                menu.add(0, MENU_COLOR_BLUE, 0 , "Blue");
                break;
            case R.id.tvSize:
                menu.add(0, MENU_SIZE_22, 0 , "22");
                menu.add(0, MENU_SIZE_26, 0 , "26");
                menu.add(0, MENU_SIZE_30, 0 , "30");
                break;
        }

    }

    @Override
    public boolean onContextItemSelected(MenuItem item)
    {
        switch(item.getItemId())
        {
            case MENU_COLOR_RED:
                tvColor.setTextColor(Color.RED);
                tvColor.setText(R.string.Color1);
                break;
            case MENU_COLOR_GREEN:
                tvColor.setTextColor(Color.GREEN);
                tvColor.setText(R.string.Color3);
                break;
            case MENU_COLOR_BLUE:
                tvColor.setTextColor(Color.BLUE);
                tvColor.setText(R.string.Color2);
                break;
            case MENU_SIZE_22:
                tvSize.setTextSize(22);
                tvSize.setText(R.string.Size1);
                break;
            case MENU_SIZE_26:
                tvSize.setTextSize(26);
                tvSize.setText(R.string.Size2);
                break;
            case MENU_SIZE_30:
                tvSize.setTextSize(30);
                tvSize.setText(R.string.Size3);
                break;
        }
        return super.onContextItemSelected(item);
    }
}
