package it.enerjize.asynctast_example;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.Toast;

public class AsynkTaskParams extends AsyncTask<Integer, Integer, Void> {

    private Context context;
    private ProgressDialog progressDialog;
    private int end, start;
    public AsynkTaskParams(Context context) {
        this.context = context;
    }

    @Override
    protected Void doInBackground(Integer... params) {
        start = params[0];
        end = params[1];
        // Запускаем цикл со стартового значения,
        // указанного в параметре start
        int count = start;
        progressDialog.setMax(end);
        // Отрабатываем цикл до шага, указанного в параметре end
        while (count<end){
            try{
                Thread.sleep(200);
                count++;
                publishProgress(count);

            } catch (Exception e){
                Toast.makeText(context, "Thread Exception", Toast.LENGTH_LONG).show();
            }
        }

        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        progressDialog.setProgress(values[0]);
        progressDialog.setMessage(String.format("Downloaded: %d", values));
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Загрузка");
        progressDialog.setTitle("Загрузка");
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(false);
        progressDialog.setProgress(0);
        progressDialog.setMax(0);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.show();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        this.progressDialog.dismiss();
    }
}
