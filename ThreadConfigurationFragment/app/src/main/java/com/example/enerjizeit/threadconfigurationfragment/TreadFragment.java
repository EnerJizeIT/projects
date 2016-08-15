package com.example.enerjizeit.threadconfigurationfragment;


import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

public class TreadFragment extends Fragment {
    public TreadFragment() {}
    /* Ссылка на родительский объект Activity. */
    private MyThread myThread;
    private MainActivity activity;

    private class MyThread extends Thread{
        @Override
        public void run() {
            final String text = getTextFromNetWork();
            activity.setTextView(text);
        }
        private String getTextFromNetWork() {
            /* Имитация сетевого соеднинения */
            SystemClock.sleep(5000);
            return "Text from network";
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true); /* Возложит, на систему обязанность, сохранять, текущий объект при
изменении конфигурации. */
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = (MainActivity) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        activity = null;
    }

    public void execute() {
        /* Интерфейс для объекта Activity, позволяющий запустить, рабочий поток. */
        myThread = new MyThread();
        myThread.start();
    }
}
