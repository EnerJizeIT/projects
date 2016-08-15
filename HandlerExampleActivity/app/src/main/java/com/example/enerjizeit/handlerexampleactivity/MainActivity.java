package com.example.enerjizeit.handlerexampleactivity;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements Handler.Callback{
    private final static int SHOW_PROGRESS_BAR = 1;
    private final static int HIDE_PROGRESS_BAR = 0;
    private BackgroundThread mBackgroundThread;

    private TextView mText;
    private Button mButton;
    private ProgressBar mProgressBar;
    private Handler mUiHandler;

    @Override
    public void onCreate(Bundle savedlnstanceState) {
        super.onCreate(savedlnstanceState);
        setContentView(R.layout.activity_main);
        mBackgroundThread = new BackgroundThread();
        mBackgroundThread.start(); /* Фоновый ноток с очередью сообщений начинает работу при создании экземпляра класса
         HandlerExampleActivity. Он обрабатывает(выполняет) задачи, передаваемые из UI-потока. */
        mText = (TextView) findViewById(R.id.textView);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mProgressBar.setVisibility(View.INVISIBLE);
        mButton = (Button) findViewById(R.id.btn);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBackgroundThread.doWork();/* Когда пользователь щелкает на кнопке, в фоновый поток передаётся новая задача.
 Так как задачи выполняются последовательно, несколькими щелчками подряд можно создать очередь задач, ожидающих обработки. */
            }
        });

        mUiHandler = new Handler(this);
/* При иснользовании интерфейса Callback нет необходимости создавать класс, производный от Handler. Вместо этого в конструктор
Handler можно передать реализацию интерфейса Callback, которая в дальнейшем будет принимать сообщения для обработки */
    }
    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case SHOW_PROGRESS_BAR:
      /* Вывод на экран индикатора вынолнения задачи */
                mProgressBar.setVisibility(View.VISIBLE);
                return true;
            case HIDE_PROGRESS_BAR:
 /* Удаление с экрана индикатора выполнения задачи и добавление в область вывода текста TextView результата, полученного из фонового потока. */
                mText.setText(String.valueOf(msg.arg1));
                mProgressBar.setVisibility(View.INVISIBLE);
                return true;
            default:
                /* порядок действий для дальнейшей обработки */
                return false;
        }
/* Метод Callback. handleMessage () должен возвращать значение true, если сообщение обработано и не требует дополнительной обработки.
 Но если возвращается значение false, сообщение будет передано в метод Handler.handleMessage() для дальнейшей обработки. */
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBackgroundThread.exit(); /* Фоновый поток прекращает выполнение при уничтожении объекта HandlerExampleActivity. */
    }

    private class BackgroundThread extends Thread {
/* Для выполнения задач, переданных(выгруженных) из Ul-потока, служит объект типа BackgroundThread. Он действует и принимает
сообщения в течение всего жизненного цикла объекта HandlerExampleActivity. Внутренний обработчик Handler фонового потока сделан недоступным извне,
все операции с ним осуществляются посредством открытых (public) методов doWork () и exit () */
        private Handler mBackgroundHandler;
        public void run() {
            Looper.prepare();/* Связывает объект Looper с текущим потоком. */
            mBackgroundHandler = new Handler(); /* Этот экземпляр Handler обрабатывает только объекты, реализующие интерфейс RunnaЬle (то есть задачи).
    Поэтому для него не требуется реализация метода Handler.handleMessage ().*/
            Looper.loop();
        }

        public void doWork() {
            mBackgroundHandler.post(new Runnable() {/* Передача задачи в фоновый поток для вьполнения. */
                @Override
                public void run() {
 /* Создание объекта Message, содержащего только аргумент what с командой SHOW_PROGRESS_BAR, для передачи в UI-поток, чтобы он мог
вывести на экран индикатор выполнения */
                    mUiHandler.sendMessage(mUiHandler.obtainMessage(SHOW_PROGRESS_BAR, 0, 0, null)); /* Отправка начального сообщения в UI-поток. */
                    Random r = new Random();
                    int randomlnt = r.nextInt(5000);
                    SystemClock.sleep(randomlnt);/* Имнтания задачи случайной продолжительности, генерирующей
некоторые данные randomlnt. */

/* Создание объекта Message с результатом randomlnt, который nередаётся в параметре argl. В параметре what отправляется команда
HIDE_PROGRESS_BAR для удаления с экрана индикатора выполнения. */
                    Message uiMsg = mUiHandler.obtainMessage(HIDE_PROGRESS_BAR, randomlnt, 0, null);
                    mUiHandler.sendMessage(uiMsg);/* Сообщение с-конечным результатом информирует Ul-поток о завершении
                    фоновой задачи и доставляет результат её выполнения. */
                }
            });
        }
        public void exit() {
 /* Завершение работы объекта Looper, после чего может завершиться и текущий ноток. */
            mBackgroundHandler.getLooper().quit();
        }
    }
}
