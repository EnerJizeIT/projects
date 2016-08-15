package com.example.enerjizeit.navigationdrawer;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private ListView listViewPlanet;
    private static CustomAdapter adapter;
    private ArrayList<InfoClass>list;
    private String [] planets;
    private ActionBarDrawerToggle drawerListener;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToolbar();

        drawerLayout=(DrawerLayout)findViewById(R.id.drawerLayout);
        listViewPlanet = (ListView)findViewById(R.id.drawerList);
        initInfo();
        adapter = new CustomAdapter(list, this);
        listViewPlanet.setAdapter(adapter);

        listViewPlanet.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "You ckick on " + list.get(position).getTitle(), Toast.LENGTH_SHORT).show();
                selectItem(position);
                Snackbar.make(view, "You ckick on " + list.get(position).getTitle(), Snackbar.LENGTH_LONG)
                        .setAction("Close Apps", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                finish();
                            }
                        }).show();

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()== R.id.action_settings) {
            return true;
        }
        if (item.getItemId()== R.id.navigate) {
            startActivity(new Intent(MainActivity.this, SecActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }

    private void initToolbar() {
        toolbar = (Toolbar)findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
    }

    private void selectItem(int position) {
        listViewPlanet.setItemChecked(position,true);
        setTitle(planets[position]);
    }
    private void setTitle(String title){
        toolbar.setTitle(title);
    }

    void initInfo(){
        list = new ArrayList<>();
        planets = getResources().getStringArray(R.array.planets);
        for(String planet : planets) {
            list.add(new InfoClass(R.drawable.peng, planet));
        }
    }
}
