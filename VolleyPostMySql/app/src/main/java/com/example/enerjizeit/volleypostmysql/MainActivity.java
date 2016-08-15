package com.example.enerjizeit.volleypostmysql;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private EditText loginEd, loginPass;
    private Button btnLog;
    private String server_url3 = "http://10.0.3.2/users_table/update_script.php";
    AlertDialog.Builder alertBui;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginEd = (EditText)findViewById(R.id.loginEd);
        loginPass = (EditText)findViewById(R.id.passEd);

        btnLog = (Button)findViewById(R.id.btnLogin);

        btnLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name = loginEd.getText().toString();
                final String pass = loginPass.getText().toString();
                StringRequest stringRequest = new StringRequest(Request.Method.POST, server_url3, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        alertBui.setTitle("ServerResponce").setMessage(response).setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        loginEd.setText("");
                                        loginPass.setText("");
                                    }
                                });
                        AlertDialog dialog = alertBui.create();
                        dialog.show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                        error.printStackTrace();
                        Log.e("TAG", "ERROR" + error.toString());
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> map = new HashMap<String, String>();
                        /* Key идентичен $_POST["name"] */
                        map.put("name", name);
                        map.put("pass", pass);
                        return map;
                    }
                };
                stringRequest.setRetryPolicy(new DefaultRetryPolicy(500, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                MySingleton.getInstance(MainActivity.this).addToRequestQueue(stringRequest);
            }
        });

        alertBui = new AlertDialog.Builder(this);
    }
}