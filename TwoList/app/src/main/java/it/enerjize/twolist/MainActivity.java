package it.enerjize.twolist;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends ListActivity {
    // Массив-источник данных для списка контактов
    private static final String[] contacts = {
            "Jacob Anderson", "Emily Duncan", "Michael Fuller",
            "Emma Greenman", "Joshua Harrison", "Madison Johnson",
            "Matthew Cotman", "Olivia Lawson", "Andrew Chapman",
            "Daniel Honeyman", "Isabella Jackson", "William Patterson",
            "Joseph Godwin", "Samantha Bush", "Christopher Gateman"};
    // Массив-источник данных для списка c детализацией
    private static final String[] details = {
            "Mobile", "Home", "Address", "EMail", "Back on Contacts"};
    private ArrayAdapter<String> daContacts, daDetails;
    private String strMonth, strDayOfWeek;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        daContacts = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, contacts);
        daDetails = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, details);

        setListAdapter(daContacts);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        if(getListAdapter() == daContacts){
            setListAdapter(daDetails);
            daDetails.notifyDataSetChanged(); /* переходим на список с детализацией */
        } else{
            if((String)getListView().getItemAtPosition(position)=="Back on Contacts"){
                /* Переходим на список контактов */
                setListAdapter(daContacts);
                daContacts.notifyDataSetChanged();
            }
        }

    }
}
