package com.example.enerjizeit.loaderloadermanagerasynctaskloader;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class TimeLoader extends android.support.v4.content.Loader {
    final String LOG_TAG = "myLogs";
    final int PAUSE = 10;

    public final static String ARGS_TIME_FORMAT = "time_format";
    public final static String TIME_FORMAT_SHORT = "h:mm:ss a";
    public final static String TIME_FORMAT_LONG = "yyyy:MM:dd G 'at' HH:mm:ss a";

    GetTimeTask getTimeTask;
    String format;

    public TimeLoader(Context context, Bundle args) {
        super(context);
        Log.e(LOG_TAG, hashCode() + " create TimeLoader");
        if(args != null){
            format = args.getString(ARGS_TIME_FORMAT);
        }
        if(TextUtils.isEmpty(format)){
            format = TIME_FORMAT_SHORT;
        }
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        Log.e(LOG_TAG, hashCode() + " onStartLoading");

    }

    @Override
    protected void onStopLoading() {
        super.onStopLoading();
        Log.e(LOG_TAG, hashCode() + " onStopLoading");
    }

    @Override
    protected void onForceLoad() {
        super.onForceLoad();
        Log.e(LOG_TAG, hashCode() + " onForceLoad");
        if(getTimeTask != null) {
            getTimeTask.cancel(true);
        }
        getTimeTask = new GetTimeTask();
        getTimeTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, format);
    }

    @Override
    protected void onAbandon() {
        super.onAbandon();
        Log.e(LOG_TAG, hashCode() + " onAbandon");
    }

    @Override
    protected void onReset() {
        super.onReset();
        Log.e(LOG_TAG, hashCode() + " onReset");
    }

    void getResultFromTask(String result){
        deliverResult(result);
    }

    class GetTimeTask extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... params) {
            Log.e(LOG_TAG, TimeLoader.this.hashCode() + " doInBackground");

            try {
                TimeUnit.SECONDS.sleep(PAUSE);
            } catch (InterruptedException e) {
                Log.e(LOG_TAG, e + " InterruptedException");
            }

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(params[0],
                    Locale.getDefault());
            return simpleDateFormat.format(new Date());
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.e(LOG_TAG, TimeLoader.this.hashCode() + " onPostExecute " + s);

            getResultFromTask(s);
        }
    }
}
