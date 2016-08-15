package it.enerjize.workout;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class WorkoutDetailFragment extends Fragment {

    private long workoutId; /* Идентификатор комплекса упражнений, выбранного пользователем. Позднее, при выводе подробной информации, он будет использован для заполнений представлений фрагмента */

    public WorkoutDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putLong("workoutId", workoutId);
    }

    @Override
    // метод View onCreateView() вызывается тогда, когда Android потребуется макет фрагмента.
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        /*Этот метод является аналогом setContextView() в мире фрагментов.
         * Первый аргумент указывает макет фрагмента, container передаётся активностью, использующей фрагмент.
          * В нем содержится объект ViewGroup активности, в который должен быть вставлен макет фрагмента. */
        if(savedInstanceState != null){
            workoutId = savedInstanceState.getLong("workoutId");
        }

        return inflater.inflate(R.layout.fragment_workout_detail, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        /* Данный getView() метод получает корневой объект View Фрагмента. Далее полученный объект
         используется для получения ссылок на надписи, предназначенные для названия и описания комплекса упражнений.
         У фрагментов отсутсвует ряд методом, и для того чтобы получить ссылку на корневое представление фрагмента методом getView()*/

        if(getView() != null){
            /* мы создаём обект массива из класса Workout, идентифицированный по id. ID мы приводим к инт значению. */
            Workout workout = Workout.workouts[(int) workoutId];

            TextView title = (TextView) getView().findViewById(R.id.textTitle);
            title.setText(workout.getName());
            TextView description = (TextView) getView().findViewById(R.id.textDescription);
            description.setText(workout.getDescrition());
        }
    }
    public void setWorkout(long id){ /* Метод для присваивания идентификатора. Метод используется активностью для передачи значения идентификатора фрагменту.
     Активность должна вызывать метод setWorkout() фрагмента и передавать ему идентификатор нужного комплекса. Давайте посмотрим, как это делается. */
        this.workoutId = id;
    }
}
