package com.example.enerjizeit.loadercustomasynktask;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.util.Arrays;
import java.util.List;

public class StringLoader extends AsyncTaskLoader<List<String>>{

    public StringLoader(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<String> loadInBackground() {
        List<String> data = Arrays.asList(getContext().getResources().getStringArray(R.array.items));
        return data;
    }

    @Override
    public void deliverResult(List<String> data) {
        super.deliverResult(data);
    }
}
