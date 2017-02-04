package com.example.user.trialseven;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class login extends AppCompatActivity {

    private EditText et_username;
    private android.widget.EditText et_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_username = (EditText)findViewById(R.id.et_username);
        et_password = (EditText)findViewById(R.id.et_password);
    }

    public void onLogin (View view){

        final String username = et_username.getText().toString().trim();
        final String password = et_password.getText().toString().trim();

        if(TextUtils.isEmpty(username)) {
            Toast.makeText(this, "Certain Fields Are Empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Certain Fields Are Empty", Toast.LENGTH_SHORT).show();
            return;
        }
        class Login extends AsyncTask<String, Void, String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(login.this, "Loading...","Please Wait...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(login.this, s, Toast.LENGTH_SHORT).show();
                if (s.contains ("admin")){
                    startActivity(new Intent(login.this, home_admin.class));
                }else if (s.contains ("Logged In Successfully")) {
                    startActivity(new Intent(login.this, home_user.class));
                }else{
                    Toast.makeText(login.this, s, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            protected String doInBackground(String... voids) {
                HashMap<String, String> params = new HashMap<>();

                params.put(Config.KEY_USR_USERNAME,username);
                params.put(Config.KEY_USR_PASSWORD,password);
                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Config.URL_LOGIN, params);
                return res;
            }
        }

        Login reg = new Login();
        reg.execute();
    }
    public void onBack (View view){
        startActivity(new Intent(this,start.class));
    }
}
