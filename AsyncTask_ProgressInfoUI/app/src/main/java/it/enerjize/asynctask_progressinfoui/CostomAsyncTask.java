package it.enerjize.asynctask_progressinfoui;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class CostomAsyncTask extends AsyncTask<Integer,Integer,Boolean>{

    private Context context;
    private ProgressBar progressBar;
    private TextView text;
    private int start, end, count;

    private ListenerOfSuccess listen;

    public interface ListenerOfSuccess{
        void successMethod(boolean result);
    }

    public void setListenerOfSuccess(ListenerOfSuccess listen){
        this.listen = listen;
    }

    public CostomAsyncTask(Context context, ProgressBar progressBar, TextView text){
        this.context = context;
        this.progressBar = progressBar;
        this.text = text;
    }

    @Override
    protected Boolean doInBackground(Integer... params) {
        start = params[0];
        end = params[1];
        count = start;
        while (count < end && !isCancelled()) {
            try{
                Thread.sleep(200);
                count++;
                publishProgress(count);
            }catch (Exception e){
                Toast.makeText(context, "Exception thread", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return true;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressBar.setProgress(0);

    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        progressBar.setProgress(values[0]);
        text.setText("Run task #" + values[0]);
    }

    @Override
    protected void onPostExecute(Boolean result) {
        super.onPostExecute(result);
        if (listen != null){
            listen.successMethod(result);
        }
        Toast.makeText(context, "Finish: "+count, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCancelled(Boolean result) {
        super.onCancelled(result);
        Toast.makeText(context, "Cancel task, count="+count, Toast.LENGTH_SHORT).show();
    }
}
