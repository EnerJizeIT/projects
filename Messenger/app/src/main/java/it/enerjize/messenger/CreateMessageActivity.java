package it.enerjize.messenger;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

public class CreateMessageActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_message);
    }
    public void onSendMessage()
    {
        EditText messageView = (EditText) findViewById(R.id.message);
        // создали объект из message
        String messageText = messageView.getText().toString();
        // получили текст из объекта
        Intent intent = new Intent(Intent.ACTION_SEND);
        // создали интент с командой ACTION_SEND
        intent.setType("text/plain");
        // установили тип
        intent.putExtra(Intent.EXTRA_TEXT, messageText);
        // вызов метода ???
        startActivity(intent);
        // запуск интента
    }
}
