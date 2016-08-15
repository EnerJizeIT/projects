package com.example.enerjizeit.loaderloadermanagerasynctaskloader;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<String> {
    final String LOG_TAG = "myLogs";
    static  final int LOADER_TIME_ID = 1;

    TextView textView;
    RadioGroup radioGroup;
    static int lastCheckedId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView)findViewById(R.id.textView);
        radioGroup = (RadioGroup)findViewById(R.id.radiogroup);

        Bundle bundle = new Bundle();
        bundle.putString(TimeLoader.ARGS_TIME_FORMAT, getTimeFormat());

        getSupportLoaderManager().initLoader(LOADER_TIME_ID, bundle, this);
        lastCheckedId = radioGroup.getCheckedRadioButtonId();
    }

    @Override
    public Loader<String> onCreateLoader(int id, Bundle args) {
        Loader<String> loader = null;
        if(id == LOADER_TIME_ID){
            loader = new TimeLoader(this, args);
            Log.e(LOG_TAG, "onCreate Loader " + loader.hashCode());
        }
        return loader;
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String result) {
        Log.e(LOG_TAG, "onLoadFinished " + loader.hashCode()
                + ", result = " + result);
        textView.setText(result);
    }

    @Override
    public void onLoaderReset(Loader<String> loader) {
        Log.e(LOG_TAG, "onLoaderReset " + loader.hashCode());
    }

    public void getTimeClick(View view){
        Loader<String> loader;

        int id = radioGroup.getCheckedRadioButtonId();
        if(id == lastCheckedId){
            loader = getSupportLoaderManager().getLoader(LOADER_TIME_ID);
        } else {
            Bundle bundle = new Bundle();
            bundle.putString(TimeLoader.ARGS_TIME_FORMAT, getTimeFormat());
            loader = getSupportLoaderManager().restartLoader(LOADER_TIME_ID, bundle, this);
            lastCheckedId = id;
        }
        loader.forceLoad();
    }

    String getTimeFormat(){
        String result = TimeLoader.TIME_FORMAT_SHORT;
        switch (radioGroup.getCheckedRadioButtonId()){
            case R.id.radioShort:
                result = TimeLoader.TIME_FORMAT_SHORT;
                break;
            case R.id.radioLong:
                result = TimeLoader.TIME_FORMAT_LONG;
                break;
        }
        return result;
    }

    public void observerClick(View v){

    }

}
