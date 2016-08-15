package com.example.enerjizeit.networkhandlerthread;

import android.app.Dialog;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.Process;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private static final int DIALOG_LOADING = 0;
    private static final int SHOW_LOADING = 1;
    private static final int DISMISS_LOADING = 2;

    Handler dialogHandler = new Handler() {
/* Обработчик DialogHandler, который обрабатывает сообщения в UIпотоке. Он используется для управления диалогами на экране. */
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case SHOW_LOADING:
                    showDialog(DIALOG_LOADING);
                    break;
                case DISMISS_LOADING:
                    dismissDialog(DIALOG_LOADING);

            }
        }
    };

    private class NetworkHandlerThread extends HandlerThread {
        private static final int STATE_A = 1;
        private static final int STATE_B = 2;
        private Handler mHandler;

        public NetworkHandlerThread() {
            super("NetworkHandlerThread", Process.THREAD_PRIORITY_BACKGROUND);
        }

        @Override
        protected void onLooperPrepared() {
            super.onLooperPrepared();
            mHandler = new Handler(getLooper()) {
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    switch (msg.what) {
                        case STATE_A:
/* Первый сетевой вызов, который 1ш1щ1шруется в методе onCreate ().
Он передаёт сообщение в UI-поток, где создается диалог, отображающий ход выполнения задачи. После успешного завершения
сетевой операции её результат либо передаётся во вторую задачу (вариант SТАТЕ_В), либо диалог удаляется. */
                            dialogHandler.sendEmptyMessage(SHOW_LOADING);
                            String result = networkOperation1();
                            if (result != null) {
                                sendMessage(obtainMessage(STATE_B, result));
                            } else {
                                dialogHandler.sendEmptyMessage(DISMISS_LOADING);
                            }
                            break;
                        case STATE_B:
                            /* Выполнение второй сетевой операции. */
                            networkOperation2((String) msg.obj);
                            dialogHandler.sendEmptyMessage(DISMISS_LOADING);
                            break;
                    }
                }
            };
          /* Инициация сетевого вызова при запуске фонового потока. */
            fetchDataFromNetwork();
        }

        private String networkOperation1() {
            SystemClock.sleep(2000) /* Имитация сетевой операции */;
            return "A string";
        }

        private void networkOperation2(String data) {
            /* Передача данных в сеть, например с помощью HttpPost */
            SystemClock.sleep(2000);
        }

        /* Открытая (видимая извне) сетевая операция */
        public void fetchDataFromNetwork() {
            mHandler.sendEmptyMessage(STATE_A);
        }
    }

    private NetworkHandlerThread mThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mThread = new NetworkHandlerThread();
        mThread.start();
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        Dialog dialog = null;
        switch (id) {
            case DIALOG_LOADING:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Loading...");
                dialog = builder.create();
                break;
        }
        return dialog;
    }

    /* Обеспечиваем завершение фонового потока одновременно с завершением объекта Activity */

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mThread.quit();
    }
}
