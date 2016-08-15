package it.enerjize.progressbar;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;


public class LogickAsynkTask extends AsyncTask<Void,Void,Void> {

    private Context context;
    private ProgressDialog mProgressDialog;

    public LogickAsynkTask(Context context){
        this.context = context;
    }

    @Override
    protected Void doInBackground(Void... params) {
        int count = 0;
        while (count <= 100){
            try{
                Thread.sleep(200);
                count++;
            } catch (Exception e){
                Toast.makeText(context,"Thread Exception", Toast.LENGTH_SHORT).show();
            }
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mProgressDialog = new ProgressDialog(context);
        mProgressDialog.setMessage("Downloading content, please wait...");
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();
        Toast.makeText(context,"OnPreExecute", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        this.mProgressDialog.dismiss();
        Toast.makeText(context,"OnPostExecute", Toast.LENGTH_SHORT).show();
    }
}
