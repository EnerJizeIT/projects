package it.enerjize.asynctast_example;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

public class AsynkTaskClass extends AsyncTask<Void,Void,Void>{
    private Context context;
    private ProgressDialog progressDialog;

    public AsynkTaskClass(Context context){
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
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Загрузка, потерпите!");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.show();
        Toast.makeText(context, "OnPreExecute", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        this.progressDialog.dismiss();
        Toast.makeText(context,"OnPostExecute", Toast.LENGTH_SHORT).show();
    }
}
