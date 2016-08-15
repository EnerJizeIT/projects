package it.enerjize.optionsmenu;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class OptionsMenuActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options_menu);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_options_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();

        if(id==android.R.id.home)
        {
            OptionsMenuActivity.this.finish();
            return  true;
        } else if(id == R.id.action_sitting)
        {
            Toast.makeText(getApplicationContext(),"Sitting option hit!", Toast.LENGTH_SHORT).show();
            return true;
        } else if(id == R.id.item1)
        {
            Toast.makeText(getApplicationContext(),"Option 1 hit!", Toast.LENGTH_SHORT).show();
            return true;
        } else if(id == R.id.item2)
        {
            Toast.makeText(getApplicationContext(),"Option 2 hit!", Toast.LENGTH_SHORT).show();
            return true;
        } else if(id == R.id.item3)
        {
            Toast.makeText(getApplicationContext(),"Option 3 hit!", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
