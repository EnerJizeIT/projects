package it.enerjize.asynctask_completelistener;


import android.content.Context;
import android.os.AsyncTask;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class CustomAsyncTask extends AsyncTask<Void, Integer, Boolean> {

    private static final int MAX_COUNT = 100;
    private Context context;
    private ProgressBar progress;
    private TextView text;
    private OnDownloadCompletedListener interfaceListener;

    public interface OnDownloadCompletedListener {
        void loadTaskCompleted(boolean success);
    }
    public void setOnDownloadCompletedListener(OnDownloadCompletedListener listener) {
        interfaceListener = listener;
    }
    public CustomAsyncTask(Context con,ProgressBar progress,TextView text) {
        this.context = con;
        this.progress = progress;
        this.text = text;
    }

    @Override
    protected void onPreExecute() {
        progress.setProgress(0);
    }
    @Override
    protected Boolean doInBackground(Void... params) {

        for(int i = 0; i<MAX_COUNT; i++){
            try {
                Thread.sleep(100);

            }
            catch (InterruptedException e) {
                Toast.makeText(context, "ERROR! Thread Interrupted", Toast.LENGTH_SHORT).show();
                return false;
            }
            publishProgress(i);
        }
        return true;
    }
    @Override
    protected void onProgressUpdate(Integer... values) {
        progress.setProgress(values[0]);
        text.setText("Run task #" + values[0]);
    }
    @Override
    protected void onPostExecute(Boolean result) {
        // Данный метод запустится по завершению задачи получает на вход true если успешно завершенно
        if (interfaceListener != null)
            interfaceListener.loadTaskCompleted(result);
    }
    @Override
    protected void onCancelled() {
        Toast.makeText(context, "Cancel task", Toast.LENGTH_LONG).show();
    }
}