package it.enerjize.fragmentactivity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Мы создали переменную кода фрагмента, и вызвали начало транзакции.
         * Далее указав в какой макет, мы добавили код. И приказали выполнить. */
        Fragment frag2 = new Fragment2();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.add(R.id.fragment2, frag2);
        ft.commit();
    }
    /* Жмём на кнопки и смотрим Log */

    public void onClick(View v){ /* Нажав на кнопку мы меняем тексты во фрагментах */

        /* Изначально мы находим фрагменты, и с помощью метода getView, мы получаем доступ к их
            TextView и подставляем текст */
        Fragment frag1 = getFragmentManager().findFragmentById(R.id.fragment1);
        ((TextView)frag1.getView().findViewById(R.id.textView)).setText("Access to Fragment 1 from Activity");

        Fragment frag2 = getFragmentManager().findFragmentById(R.id.fragment2);
        ((TextView)frag2.getView().findViewById(R.id.textView)).setText("Access to Fragment 2 from Activity");
    }
}
