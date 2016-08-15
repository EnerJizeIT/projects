package com.example.enerjizeit.p0981_servicebindinglocal;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

public class MyService extends Service {
    public MyService() {
    }
    public MyBinder binder = new MyBinder();

    Timer timer;
    TimerTask timerTask;
    long interval = 1000;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("TAG", "MyService onCreate");
        timer = new Timer();
        schedule();
    }

    private void schedule() {
        /* Мы используем таймер – Timer. Он позволяет повторять какое-либо действие через заданный промежуток
времени. TimerTask – это задача, которую Timer будет периодически выполнять.
В методе run – кодим действия этой задачи. И далее для объекта Timer вызываем метод schedule, в который передаем
задачу TimerTask, время через которое начнется выполнение, и период повтора. Чтобы отменить выполнение задачи,
необходимо вызвать метод cancel для TimerTask. Отмененную задачу нельзя больше запланировать, и если снова
надо ее включить – необходимо создать новый экземпляр TimerTask и скормить его таймеру. */
        if(timerTask != null) timerTask.cancel();
        if(interval > 0){
            timerTask = new TimerTask() {
                @Override
                public void run() {
                    Log.e("TAG", "run TimerTask");
                }
            };
            timer.schedule(timerTask, 1000, interval);
        }
    }

    long upInterval(long gap){
        interval = interval + gap;
        schedule();
        return interval;
    }

    long downInterval(long gap){
        interval = interval - gap;
        if(interval<0) interval = 0;
        schedule();
        return interval;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e("TAG", "MyService OnBind");
        return binder;
    }

    class MyBinder extends Binder {
        MyService getService(){
            return MyService.this;
        }
    }
}
