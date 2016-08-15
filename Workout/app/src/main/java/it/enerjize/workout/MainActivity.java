package it.enerjize.workout;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/* мы добавили интерфейс слушателя из другого класса */
public class MainActivity extends Activity implements WorkoutListFragment.WorkoutListListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Прежде чем активность сможет взаимодействовать с фрагментом, она должна сначала получить ссылку на него. Для получения ссылки на фрагмент следует сначала получить ссылку
         * на ДЕСПЕЧЕРА ФРАГМЕНТОВ активности при помощи метода getFragmentManager. Затем метод findFragmentById() используется для получиения ссылки на фрагмент.*/
        /* данные строки мы мы удаляем тк мы имлементировали интерфейс
        WorkoutDetailFragment frag = (WorkoutDetailFragment)getFragmentManager().findFragmentById(R.id.detail_frag);
        *//* Приказывает WorkoutDetailFragment вывести подробную информацию о выбранном комплексе(0 элемент соответствует первому значению массива).*//*
        frag.setWorkout(0);
        */
    }
/* этот метод определяется в слушателе и является имплементированным */
    @Override
    public void itemClicked(long id) {
        View fragmentContainer = findViewById(R.id.fragment_container);
        if(fragmentContainer != null){
            WorkoutDetailFragment details = new WorkoutDetailFragment();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            details.setWorkout(id);
            ft.replace(R.id.fragment_container, details);
            ft.addToBackStack(null);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
        } else  {
            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            intent.putExtra(DetailActivity.EXTRA_WORKOUT_ID, (int)id);
            startActivity(intent);
        }

    }
}
