package com.example.enerjizeit.asynctaskloader;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import java.util.Random;

public class RandomLoader extends AsyncTaskLoader<String> {
    public static final String TAG = "TAG";
    public static final String ARG_WORD = "word";
    public static final int RANDOM_STRING_LENGTH = 100;
    private String mWord;

    public RandomLoader(Context context, Bundle args) {
        super(context);
        if(args != null){
            mWord = args.getString(ARG_WORD);
        }
    }

    @Override
    public String loadInBackground() {
        if(mWord == null) return null;
        Log.e(TAG, "loadInBackground");

        return generateString(mWord);
    }

    @Override
    public void forceLoad() {
        super.forceLoad();
        Log.e(TAG, "forceLoad");
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        Log.e(TAG, "onStartLoading");
    }

    @Override
    protected void onStopLoading() {
        super.onStopLoading();
        Log.e(TAG, "onStopLoading");
    }

    @Override
    public void deliverResult(String data) {
        Log.e(TAG, "deliverResult");
        super.deliverResult(data);
    }

    private String generateString(String s) {
        Random rand = new Random();
        char[] text = new char[RANDOM_STRING_LENGTH];
        for(int i = 0; i<RANDOM_STRING_LENGTH; i++){
            text[i] = s.charAt(rand.nextInt(s.length()));
        }
        return new String(text);
    }
}
