package it.enerjize.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private NotificationManager nm;
    private final int NOTIFICATION_ID = 127;
    Button btn1, btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = (Button)findViewById(R.id.btn1);
        btn2 = (Button)findViewById(R.id.btnCancel);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNotification(v);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelNotification(v);
            }
        });

        /* Инициализация менеджера отличается от стандартной. Мы обращаемся к конексту и запрашиваем
         * сисмный сервис указывая, NOTIFICATION_SERVISE */
        nm = (NotificationManager)getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);

    }

    public void showNotification(View view){
        Intent intent = new Intent(getApplicationContext(), FinishActivity.class);
        /* Данный интент будет запускатся при нажатии на notification */
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(),
                0, intent, PendingIntent.FLAG_CANCEL_CURRENT);

        /* Для создания notification чаще всего используют билдеры. */
        Notification.Builder builder = new Notification.Builder(getApplicationContext());
        /* Через builder мы можем построить некие уведомления. Через точку, указав им свойства  */
        builder
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getApplicationContext()
                                            .getResources(), R.mipmap.ic_launcher))
                .setTicker("Новое уведомление")
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true)
                .setContentTitle("Уведомление")
                .setContentText("Нажмите чтобы узнать секрет")
                /* 100 это значение завершения, 20 текущее значение, false - ползунок на месте, true ползунок движется */
                .setProgress(100, 20, false);

        Notification notification = builder.build();
        /* Данным методом мы указали что вместе с уведомлением будет вибрация, звук и свет.
         * Чтобы получить все мы можем обратиться к DEFAULT_ALL */
        notification.defaults = Notification.DEFAULT_SOUND|Notification.DEFAULT_LIGHTS;
        /* помимо дефолтных возможностей мы можем использовать также свои собственные звуковые и вибро оповещение */
        long [] vibrate1 = new long[]{1500, 5000, 1500, 1000};/* первое число указывает паузу(ms), второе протяжённость. */
        /* при этом в андройд манефесте нам нужно указать разрешение на исп вибрации */
        notification.vibrate = vibrate1;
        /* Так же мы можем указать важность уведомления, пример музыка, FLAG_ONGOING_EVENT постоянное notification  */
        /* FLAG_INSISTENT уведомление которое будет звучать пока не обратить на него внимение */
        notification.flags = notification.flags|Notification.FLAG_INSISTENT;
        nm.notify(NOTIFICATION_ID, notification);
    }

    public void cancelNotification(View view){
        nm.cancel(NOTIFICATION_ID);
    }
}
