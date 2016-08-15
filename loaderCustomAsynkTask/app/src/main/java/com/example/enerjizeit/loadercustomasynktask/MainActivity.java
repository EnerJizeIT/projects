package com.example.enerjizeit.loadercustomasynktask;

import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private LoaderAdapter loaderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loaderAdapter = new LoaderAdapter(this);

        listView = (ListView)findViewById(R.id.listView);
        listView.setAdapter(loaderAdapter);

        getSupportLoaderManager().initLoader(R.id.string_loader_id, null, loaderCallbacks);
    }

    private LoaderManager.LoaderCallbacks<List<String>> loaderCallbacks = new LoaderManager.LoaderCallbacks<List<String>>() {
        @Override
        public Loader<List<String>> onCreateLoader(int id, Bundle args) {
            return new StringLoader(getApplicationContext());
        }

        @Override
        public void onLoadFinished(Loader<List<String>> loader, List<String> data) {
            loaderAdapter.swapData(data);
        }

        @Override
        public void onLoaderReset(Loader<List<String>> loader) {
            loaderAdapter.swapData(Collections.<String>emptyList());
        }
    };
}
