package it.enerjize.contextmenu;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final LinearLayout edit = (LinearLayout)findViewById(R.id.root);
        registerForContextMenu(edit);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(Menu.NONE, 101, Menu.NONE, "Open");
        menu.add(Menu.NONE, 102, Menu.NONE, "Save");
        menu.add(Menu.NONE, 103, Menu.NONE, "Edit");
        menu.add(Menu.NONE, 104, Menu.NONE, "Help");
        menu.add(Menu.NONE, 105, Menu.NONE, "Exit");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        CharSequence message;
        switch (item.getItemId()) {
            case 101:
                message = "Open item selected";
                break;
            case 102:
                message = "Save item selected";
                break;
            case 103:
                message = "Help item selected";
                break;
            case 104:
                message = "Edit item selected";
                break;
            case 105:
                message = "Exit item selected";
                break;
            default:
                return super.onContextItemSelected(item);
        }
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
        return true;
    }
}
