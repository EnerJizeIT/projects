package it.enerjize.messenger;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ReceiveMessageActivity extends Activity
{

    public static final String EXTRA_MESSAGE = "message";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        // мы обратились к суперклассу с командой "сохранить состояние экземпляра"
        setContentView(R.layout.activity_receive_message);
        // указали с каким активити ведём работу
        Intent intent = getIntent();
        // получили интент
        String messageText = intent.getStringExtra(EXTRA_MESSAGE);
        // получили объект из интента
        TextView messageView = (TextView) findViewById(R.id.message);
        // создали объект значения message
        messageView.setText(messageText);
        // установили в значение message текст из Интента
    }
}
