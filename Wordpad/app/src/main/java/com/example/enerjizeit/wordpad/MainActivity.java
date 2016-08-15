package com.example.enerjizeit.wordpad;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private final static String FILE_EXT = ".txt";
    private final static String DIRECTORY_DOCUMENTS = "/docs";

    private EditText edit;
    private String  curFileName = "";
    private String dir;
    private int pos = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edit = (EditText)findViewById(R.id.edit);

        /* Указываем каталог с которым будем работать*/
        dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString() + DIRECTORY_DOCUMENTS;
        File folder = new File(dir);
        /* Проверяем, существует ли каталог docs, если нет - создаём */
        if(!folder.exists()){
            folder.mkdir();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuItemNew:
                curFileName = "";
                edit.setText("");
                this.setTitle(R.string.app_name);
                break;
            case R.id.menuItemOpen:
                callOpenDialog();
                break;
            case R.id.menuItemSave:
                callSaveDialog();
                break;
            case R.id.menuItemExit:
                finish();
                break;
            default:
                return false;
        }
        return true;
    }

    private void callSaveDialog() {
        LayoutInflater inflater = MainActivity.this.getLayoutInflater();
        View view = inflater.inflate(R.layout.save_dialog, null);

        final EditText editFileName = (EditText)view.findViewById(R.id.edit_filename);
        editFileName.setText(curFileName);

        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setView(view);
        builder.setTitle(R.string.title_save);
        builder.setPositiveButton(R.string.btn_ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                saveFile(editFileName.getText().toString());
            }
        });

        builder.setNegativeButton(R.string.btn_cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.setCancelable(true);
        builder.create();
        builder.show();
    }

    private void callOpenDialog() {
        try{
            final String[] files = findFiles(dir);
            if(files.length > 0){
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle(R.string.title_open);
                builder.setSingleChoiceItems(files, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        pos = which;
                    }
                });
                builder.setPositiveButton(R.string.btn_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        curFileName = files[pos];
                        openFile(curFileName);
                    }
                });
                builder.setNegativeButton(R.string.btn_cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.setCancelable(true);
                builder.create();
                builder.show();
            }

        }catch (Exception e){
            Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_LONG).show();
        }

    }

    private void saveFile(String filename) {
        try{
            if(!filename.endsWith(FILE_EXT))/*  методе startsWith() определяется, начинается ли
            заданный объект типа String с указанной символьной строки,
            а в методе endsWith() — завершается ли объект типа String заданной подстрокой */
            {
                filename += FILE_EXT;
            }
            File file = new File(dir, filename);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(edit.getText().toString().getBytes());
            fileOutputStream.close();

        }catch (Exception e){
            Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_LONG).show();
        }
    }


    private void openFile(String filename) {
        try{
            File file = new File(dir, filename);
            FileInputStream inStream = new FileInputStream(file);
            if(inStream != null){
                /* Класс BufferedReader считывает текст из символьного потока ввода, буферизируя прочитанные символы. */
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inStream));
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
                curFileName = filename;
                this.setTitle(getResources().getString(R.string.app_name) + ": " + curFileName);
            }
        }catch (Throwable t){
            Toast.makeText(MainActivity.this, t.toString(), Toast.LENGTH_LONG).show();
        }
    }

    private String[] findFiles(String dir) {
        ArrayList<String> items = new ArrayList<>();
        try{
            File f = new File(dir);
            File[] files = f.listFiles();

            for(int i = 0; i < files.length; i++){
                File file = files[i];
                if(!file.isDirectory()){
                    items.add(file.getName());
                }
            }
        } catch (Exception e){
            Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_LONG).show();
        }
        return items.toArray(new String[items.size()]);
    }
}
