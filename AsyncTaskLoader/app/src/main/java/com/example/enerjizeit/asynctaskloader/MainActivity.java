package com.example.enerjizeit.asynctaskloader;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String>{

    private TextView mResultTxt;
    private Bundle mBundle;
    public static final int LOADER_RANDOM_ID = 1;
    private Loader<String> mLoader;
    public static final String TAG = "TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mResultTxt = (TextView) findViewById(R.id.resultTxt);
        mBundle = new Bundle();
        mBundle.putString(RandomLoader.ARG_WORD, "test");

        mLoader = getSupportLoaderManager().initLoader(LOADER_RANDOM_ID, mBundle, this);


    }

    @Override
    public Loader<String> onCreateLoader(int id, Bundle args) {
        Loader<String> mLoader = null;
        // условие можно убрать, если вы используете только один загрузчик
        if (id == LOADER_RANDOM_ID) {
            mLoader = new RandomLoader(this, args);
            Log.e(TAG, "onCreateLoader");
        }
        return mLoader;
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String data) {
        Log.e(TAG, "onLoadFinished");
        mResultTxt.setText(data);
    }

    @Override
    public void onLoaderReset(Loader<String> loader) {
        Log.e(TAG, "onLoaderReset");
    }

    public void startLoad(View v){
        Log.e(TAG, "startLoad");
        mLoader.onContentChanged();
    }
}
