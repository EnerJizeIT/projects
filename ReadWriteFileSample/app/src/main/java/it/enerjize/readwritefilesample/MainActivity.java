package it.enerjize.readwritefilesample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    private final static String FILENAME = "file.txt";
    private EditText edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edit = (EditText)findViewById(R.id.edit);
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
            InputStream inStream = openFileInput(FILENAME);

            if(inStream != null){
                InputStreamReader tmp = new InputStreamReader(inStream);
                BufferedReader reader = new BufferedReader(tmp);
                String str;
                StringBuffer buffer = new StringBuffer();

                while ((str = reader.readLine()) != null ){
                    buffer.append(str + "\n");
                }

                inStream.close();
                edit.setText(buffer.toString());
            }
        } catch (Throwable t){
            Toast.makeText(MainActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    private void saveFile(String filename) {
        try{
            OutputStreamWriter outStream = new OutputStreamWriter(openFileOutput(FILENAME, 0));
            outStream.write(edit.getText().toString());
            outStream.close();
        } catch (Throwable t){
            Toast.makeText(MainActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
        }
    }
}
