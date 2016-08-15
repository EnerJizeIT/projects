package it.enerjize.workout;


import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class WorkoutListFragment extends ListFragment {
    public WorkoutListFragment() {
        // Required empty public constructor
    }
    /* мы создаём статический интерфейс слушателя в нашем ListFragment. Это означает что фрагмент
    * сможет взаимодействовать с любой активностью при условии это она реализует его интерфейс.*/
    static interface WorkoutListListener{
        /* в интерфейсе мы укажем метод содержащий на каком представлении был сделан щелчок */
        void itemClicked(long id);
    }
    /* приватная ссылка на нашего слушателя*/
    private WorkoutListListener listener;

    /* Данный метод не является обязательным для ListFragment. Мы включаем его в свой код, тк хотим,
     чтобы списковое представление фрагмента заполнялось данными сразу же после его создания.
     Если ваш код ничего не доалжен делать в этот момент, включать этот метод не обязательн.*/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        /* Мы имеем массив с именами в другом классе. Создаём свой собственный образец.
         * Мы помечаем, что массив будет иметь размер равный размеру workpout, а именно 4 переменые.
          * И используя getter заполняем наш массив.*/
        String [] names = new String[Workout.workouts.length];
        for(int i=0; i<names.length; i++){
            names [i] = Workout.workouts[i].getName();
        }
        /* для создания адаптера массива работающего со списковым представлением используется конструкция
        * похожая на стандартную, однако для получения контекста мы используем метод inflater.getContext()
        * (поскольку делаем это в методе onCreateView() фрагмента) данный метод возврашает текущий контекст */
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(inflater.getContext(), android.R.layout.simple_list_item_1, names);
        /* Когда адаптер будет создан его следует связать с ListView при промощи метода данного метода. */
        setListAdapter(adapter);

        /* Вызов метода суперкласса предоставляет макет по умолчанию для ListFragment */
        return super.onCreateView(inflater, container, savedInstanceState);
    }
    /* Данный метод вызывается при присоедении фрагмента к активности */
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.listener = (WorkoutListListener)activity;
    }

    @Override /* при щелчке мы вызываем интерфейс listener и метод itemClicked в параметрах которого мы передаём id*/
    public void onListItemClick(ListView l, View v, int position, long id) {
        if(listener != null){
            listener.itemClicked(id);
        }
    }
}
