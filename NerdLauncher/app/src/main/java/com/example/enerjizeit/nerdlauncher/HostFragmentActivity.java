package com.example.enerjizeit.nerdlauncher;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

public abstract class HostFragmentActivity extends AppCompatActivity {

    protected abstract Fragment createFragment();
/* Cубкласс может предоставлять свой идентификатор ресурса макета. */
    @LayoutRes
    protected int getLayoutResId(){
        return R.layout.activity_fragment;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getLayoutResId());
        /* Мы включаем в актвиность метод управляющий фрагментами */
        FragmentManager fm = getSupportFragmentManager();
        /* Когда вам потеруется получить экземпляр */
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        if(fragment == null){
            fragment = createFragment();
            fm.beginTransaction().add(R.id.fragment_container, fragment).commit();
            /* Этот код создаёт и закрепляет транзакцию фрагмента.
             * Транзакции фрагментов используются для добавления, удаления, присоединения, отсоединения
              * и замены фрагментов в списке фрагментов. */
        }
    }
}
