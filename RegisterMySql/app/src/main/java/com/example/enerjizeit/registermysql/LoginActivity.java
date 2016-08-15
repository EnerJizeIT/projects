package com.example.enerjizeit.registermysql;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
    private EditText loginEd, loginPass;
    private Button btnLog, btnReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginEd = (EditText)findViewById(R.id.loginEd);
        loginPass = (EditText)findViewById(R.id.passEd);

        btnLog = (Button)findViewById(R.id.btnLogin);
        btnReg = (Button)findViewById(R.id.btnRegister);

        btnLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String login_name = loginEd.getText().toString();
                String login_pass = loginPass.getText().toString();
                String method = "login";
                BackgroundTask backgroundTask = new BackgroundTask(LoginActivity.this);
                backgroundTask.execute(method,login_name,login_pass);
            }
        });

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegistrerActivity.class));
            }
        });
    }
}
