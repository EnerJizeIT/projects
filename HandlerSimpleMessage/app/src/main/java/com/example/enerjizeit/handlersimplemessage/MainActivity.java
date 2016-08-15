package com.example.enerjizeit.handlersimplemessage;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    LooperThread mLooperThread;
    private static class LooperThread extends Thread{
        /* Определение рабочего потока, работающего как потребитель сообщений из очереди. */
        public Handler mHandler;

        @Override
        public void run() {
            Looper.prepare(); /* Связывание объекта тина Looper
             - и в неявном виде очереди сообщений MessageQueue - с основным нотоком. */
            mHandler = new Handler(){
/* Создание обработчика Handler, используемого производителем для вставки сообщений в очередь. Здесь используется конструктор
 по умолчанию, поэтому он будет связан с объектом Looper из текущего потока. Следовательно, этот обработчик Handler может
быть создан только после выполнения метода Looper. prepare (), в противном случае устанавливать связь будет не с чем. */
                @Override
                public void handleMessage(Message msg) { /* Функция, которая будет вызываться диспетчером при передаче
сообщения в рабочий воток. В ней проверяется параметр what и затем выполняется продолжительная операния. */
                    if(msg.what == 0){
                        doLongRunningOperation();
                    }
                }
            };
            Looper.loop();/* Запускается регулирование (передача) сообщений из очереди
в ноток-потребитель. Это блокирующий вызовб поэтому рабочий поток не будет завершён. */
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLooperThread = new LooperThread(); /*  Запускается рабочий поток. теперь он готов к обработке сообщений. */
        mLooperThread.start();

        Button btnStartLongOperation = (Button) findViewById (R.id.btnStart);
        btnStartLongOperation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
/* Здесь возможно состояние гонки, кота обработчик mHandler еще не создан u фоновом потоке, но Ul-поток уже пытается
использовать его. Поэтому необходимо проверить доступность обработчика mHandler. */
                if(mLooperThread.mHandler != null){
                    Message msg = mLooperThread.mHandler.obtainMessage(0); /* Инициализация объекта Message с
                    установкой нейтрального значения О для аргумента what. */

                    /* msg.sendToTarget();  Сообщению известен принимающий его обработчик, то есть Handler,
 поэтому сообщение может само поместить себя в очередь при помощи метода Message.sendToTarget(): */
                    mLooperThread.mHandler.sendMessage(msg); /* Вставка сообщения в очередь. */
                }
            }
        });
    }

    private static void doLongRunningOperation() {
        /* В данном методе проиходит длительная операция */
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLooperThread.mHandler.getLooper().quit(); /* Завершение фонового потока. Вызов метода Looper.quit()
 останавливает передачу сообщений и освобождает Looper.loop() от блокировки, поэтому метод run () получает возможность закончить,
  свою работу, что в свою очередь приводит к завершению соответствующего потока. */
    }
}
