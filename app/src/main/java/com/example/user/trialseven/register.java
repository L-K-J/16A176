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

public class register extends AppCompatActivity {

    private EditText et_name;
    private EditText et_surname;
    private EditText et_dob;
    private EditText et_username;
    private EditText et_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        et_name = (EditText)findViewById(R.id.et_name);
        et_surname = (EditText)findViewById(R.id.et_surname);
        et_dob = (EditText)findViewById(R.id.et_dob);
        et_username = (EditText)findViewById(R.id.et_username);
        et_password = (EditText)findViewById(R.id.et_password);
    }
    public void onRegister(View view){
        final String name = et_name.getText().toString().trim();
        final String surname = et_surname.getText().toString().trim();
        final String dob = et_dob.getText().toString().trim();
        final String username = et_username.getText().toString().trim();
        final String password = et_password.getText().toString().trim();

        if(TextUtils.isEmpty(name)) {
            Toast.makeText(this, "Certain Fields Are Empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(surname)) {
            Toast.makeText(this, "Certain Fields Are Empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(dob)) {
            Toast.makeText(this, "Certain Fields Are Empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(username)) {
            Toast.makeText(this, "Certain Fields Are Empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Certain Fields Are Empty", Toast.LENGTH_SHORT).show();
            return;
        }

        class Register extends AsyncTask<Void, Void, String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(register.this, "Loading...","Please Wait...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(register.this, s, Toast.LENGTH_SHORT).show();
            }

            @Override
            protected String doInBackground(Void... voids) {
                HashMap<String, String> params = new HashMap<>();
                params.put(Config.KEY_USR_NAME,name);
                params.put(Config.KEY_USR_SURNAME,surname);
                params.put(Config.KEY_USR_DOB,dob);
                params.put(Config.KEY_USR_USERNAME,username);
                params.put(Config.KEY_USR_PASSWORD,password);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Config.URL_REGISTER, params);
                return res;
            }
        }

        Register reg = new Register();
        reg.execute();

    }
    public void onBack (View view){
        startActivity(new Intent(this,start.class));
    }
    public void onLogin (View view){
        startActivity(new Intent(this,login.class));
    }
}
