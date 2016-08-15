package it.enerjize.stopwatch;

import android.app.Activity;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainStopwatch extends Activity
{
    private int second = 0;
    private boolean running;
    private boolean wasRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_stopwatch);
        // при изменении ориентации мы применяем метод сохранения и передачи параметнов в новую активность
        if (savedInstanceState != null)
        {
            second = savedInstanceState.getInt("second");
            running = savedInstanceState.getBoolean("running");
            wasRunning = savedInstanceState.getBoolean("wasRunning");
        }
        runTimer();
    }
    @Override // сохранили состояние переменной
    public void onSaveInstanceState(Bundle savedInstanceState)
    {
        savedInstanceState.putInt("second", second);
        savedInstanceState.putBoolean("running", running);
        savedInstanceState.putBoolean("wasRunning", wasRunning);
    }
    @Override // переопределили метод с инструкцией что делать при остановке.
    protected void onStop()
    {
        super.onStop();
        wasRunning = running;
        running = false;
    }
    protected void OnStart()
    {
        super.onStart();
        if(wasRunning)
        {
            running = true;
        }
    }
    // Запустить секундомер при щелчке на кнопку Start
    public void onClickStart(View view)
    {
        running = true;
    }
    // Остановить секундомер при щелчке на кнопку Stop
    public void onClickStop(View view)
    {
        running = false;
    }
    // Обнулить секундомер при щелчке на кнопку Reset
    public void onClickReset(View view)
    {
        running = false;
        second = 0;
    }
    // создание класса который будет увеличивать время при значении переменной running = true
    private void runTimer()
    {
        final TextView timeView = (TextView) findViewById (R.id.time_view);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = second/3600;
                int minutes = (second%3600)/60;
                int secs = second%60;
                String time = String.format("%d:%02d:%02d", hours, minutes, secs);
                timeView.setText(time);
                if(running)
                {
                    second++;
                }
                handler.postDelayed(this, 1000);

            }
        } ) ;
    }
}
