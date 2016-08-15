package com.example.enerjizeit.readwritefile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    private EditText edit;
    private final static String FILENAME = "file.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edit = (EditText)findViewById(R.id.editText);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuItemOpen:
                openFile(FILENAME);
                break;
            case R.id.menuItemSave:
                saveFile(FILENAME);
                break;
            case R.id.menuItemExit:
                finish();
                break;
            default:
                return false;
        }
        return true;
    }

    private void openFile(String filename) {
        try{
            /* InputStream — абстрактный класс, задающий используемую в Java модель входных потоков.
         Все методы этого класса при возникновении ошибки возбуждают исключение IOException */
            InputStream inStream = openFileInput(FILENAME);
            if(inStream != null){
                /* Класс BufferedReader считывает текст из символьного потока ввода, буферизируя прочитанные символы. */
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(openFileInput(FILENAME)));
                /* InputStreamReader является мостом от потоков байтов
                до символьных потоков:читает байты и декодирует их в символы */

                String stringInfo;

                /* Класс StringBuffer следует тогда, когда вы хотите модифицировать содержимое */
                StringBuffer stringBuffer = new StringBuffer();

                while((stringInfo = bufferedReader.readLine()) != null){
                    /* StringBuffer расширяется по мере надобности */
                    stringBuffer.append(stringInfo +"\n");
                }
                /* close() - закрывает источник ввода. Последующие попытки чтения из этого потока
                 приводят к возбуждению IOException. */
                inStream.close();
                edit.setText(stringBuffer.toString());
            }
        }catch (Throwable t){
            Toast.makeText(MainActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    private void saveFile(String filename) {
        try{
            /* OutputStreamWriter является мостом от символьных потоков до потоков байтов:
             Символы, записанные, кодируются в байты */
            OutputStreamWriter outStreamWriter = new OutputStreamWriter(openFileOutput(FILENAME, 0));
            outStreamWriter.write(edit.getText().toString());
            outStreamWriter.close();
        } catch (Throwable t){
            Toast.makeText(MainActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
        }
    }
}
