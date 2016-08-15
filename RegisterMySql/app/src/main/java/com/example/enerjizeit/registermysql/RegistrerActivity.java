package com.example.enerjizeit.registermysql;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegistrerActivity extends AppCompatActivity {
    private EditText loginName, loginEd, loginPass;
    private String name, login, password;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrer);

        loginName = (EditText)findViewById(R.id.registerName);
        loginEd = (EditText)findViewById(R.id.registerEd);
        loginPass = (EditText)findViewById(R.id.registerEdPass);

        btnRegister = (Button)findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = loginName.getText().toString();
                login = loginEd.getText().toString();
                password = loginPass.getText().toString();
                String method = "register";
                BackgroundTask backgroundTask = new BackgroundTask(RegistrerActivity.this);
                backgroundTask.execute(method, name, login, password);
                finish();
            }
        });
    }
}
