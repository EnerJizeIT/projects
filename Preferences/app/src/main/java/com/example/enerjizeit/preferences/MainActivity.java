package com.example.enerjizeit.preferences;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private final static String DIRECTORY_DOCUMENTS = "/Mydocs";
    private final static String FILE_EXT = ".txt";

    private EditText edit;
    private String curFileName = "";
    private String dir;
    private int pos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit = (EditText)findViewById(R.id.edit);
        /* Читаем каталог во внешней памяти */
        dir = Environment.getExternalStorageDirectory().toString() + DIRECTORY_DOCUMENTS;
        File folder = new File(dir);
        if(!folder.exists()){
            folder.mkdir();
        }
    }
    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences prefs =
                PreferenceManager.getDefaultSharedPreferences(this);

        // Читаем режим открытия файла из CheckBoxPreference
        if (prefs.getBoolean(getString(R.string.pr_openmode), false)) {
            openFile(curFileName);
        }
        // Читаем изменения цвета
        int color = Color.BLACK;
        if (prefs.getBoolean(getString(R.string.pr_color_red), false)) {
            color += Color.RED;
        }
        if (prefs.getBoolean(getString(R.string.pr_color_green), false)) {
            color += Color.GREEN;
        }
        if (prefs.getBoolean(getString(R.string.pr_color_blue), false)) {
            color += Color.BLUE;
        }

        // Читаем размер текста из EditTextPreference
        float fSize = Float.parseFloat(
                prefs.getString(getString(R.string.pr_size), "20"));
        // Читаем стили текста из ListPreference
        String regular = prefs.getString(getString(R.string.pr_style), "");
        int typeface = Typeface.NORMAL;
        if (regular.contains("Bold")) {
            typeface += Typeface.BOLD;
        }
        if (regular.contains("Italic")) {
            typeface += Typeface.ITALIC;
        }
        // Меняем настройки в EditText
        edit.setTextSize(fSize);
        edit.setTextSize(fSize);
        edit.setTypeface(null, typeface);
        }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
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
            case R.id.menuItemSettings:
                Intent intent = new Intent(this, PreferencesActivity.class);
                startActivity(intent);
                break;
            case R.id.menuItemExit:
                finish();
                break;
            default:
                return  false;
        }
        return true;
    }

    private void callSaveDialog() {
        LayoutInflater inflater = this.getLayoutInflater();
        View view = inflater.inflate(R.layout.savedialog, null);
        final EditText editFileName = (EditText)view.findViewById(R.id.edit_filename);
        editFileName.setText(curFileName);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
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
            if(files.length>0){
                pos=0;
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
        } catch (Exception e){
            Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_LONG).show();
        }
    }

    private void saveFile(String s) {
        try{
            if(!s.endsWith(FILE_EXT)){
                s += FILE_EXT;
                File file = new File(dir, s);
                FileOutputStream output = new FileOutputStream(file);
                output.write(edit.getText().toString().getBytes());
                output.close();
            }
        } catch (Exception e){
            Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_LONG).show();
        }
    }

    private void openFile(String fileName) {
        try {
            File file = new File(dir, fileName);
            FileInputStream inStream = new FileInputStream(file);

            if (inStream != null) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(inStream));
                String str;
                StringBuffer buffer = new StringBuffer();

                while ((str = reader.readLine()) != null)
                    buffer.append(str + "\n");

                inStream.close();
                edit.setText(buffer.toString());
                curFileName = fileName;
                this.setTitle(getResources().getString(R.string.app_name) +
                        ": " + curFileName);
            }
        }
        catch (Exception e) {
            Toast.makeText(this,"openFile() " + e.toString(), Toast.LENGTH_LONG).show();
        }
    }

    private String[] findFiles(String dir) {
        ArrayList<String> items = new ArrayList<String>();
        try{
            File f = new File(dir);
            File[] files = f.listFiles();

            for(int i = 0; i<files.length; i++){
                File file = files[i];
                if(!file.isDirectory()){
                    items.add(file.getName());
                }
            }
        }catch (Exception e){
            Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_LONG).show();
        }
        return items.toArray(new String[items.size()]);
    }
}
