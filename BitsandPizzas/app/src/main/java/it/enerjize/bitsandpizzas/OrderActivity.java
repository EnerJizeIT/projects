package it.enerjize.bitsandpizzas;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;

public class OrderActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        ActionBar actionBar = getActionBar(); /* Кнопка вверх добавляется в коде активности. Сначала
         вы получаете ссылку на панель действий, используя метод getActionBar() активности, а затем
         вызываете метод setDisplayHomeAsUpEnabled() панели действий и передаёте при вызове значение true */
        actionBar.setDisplayHomeAsUpEnabled(true);
    }
}
