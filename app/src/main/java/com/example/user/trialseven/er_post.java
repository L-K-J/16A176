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

public class er_post extends AppCompatActivity {

    private EditText et_companyname;
    private EditText et_jobtitle;
    private EditText et_companyemail;
    private EditText et_username;
    private EditText et_description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_er_post);
        et_companyname = (EditText)findViewById(R.id.et_companyname);
        et_jobtitle = (EditText)findViewById(R.id.et_companyjobtitle);
        et_companyemail = (EditText)findViewById(R.id.et_companyemail);
        et_description = (EditText)findViewById(R.id.et_description);
        et_username = (EditText)findViewById(R.id.et_username);
    }

    public void onPost (View view) {
        final String companyname = et_companyname.getText().toString().trim();
        final String jobtitle = et_jobtitle.getText().toString().trim();
        final String companyemail = et_companyemail.getText().toString().trim();
        final String username = et_username.getText().toString().trim();
        final String description = et_description.getText().toString().trim();


        if (TextUtils.isEmpty(companyname)) {
            Toast.makeText(this, "Certain Fields Are Empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(jobtitle)) {
            Toast.makeText(this, "Certain Fields Are Empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(companyemail)) {
            Toast.makeText(this, "Certain Fields Are Empty", Toast.LENGTH_SHORT).show();
            return;

        }
        if (TextUtils.isEmpty(username)) {
            Toast.makeText(this, "Certain Fields Are Empty", Toast.LENGTH_SHORT).show();
            return;

        }
        class PostJob extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(er_post.this, "Loading...","Please Wait...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(er_post.this, s, Toast.LENGTH_SHORT).show();
            }

            @Override
            protected String doInBackground(Void... voids) {
                HashMap<String, String> params = new HashMap<>();
                params.put(Config.KEY_COM_NAME,companyname);
                params.put(Config.KEY_COM_TITLE,jobtitle);
                params.put(Config.KEY_COM_EMAIL,companyemail);
                params.put(Config.KEY_USR_USERNAME,username);
                params.put(Config.KEY_COM_DESCRIPTION,description);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Config.URL_POSTJOB, params);
                return res;
            }
        }
        PostJob pj = new PostJob();
        pj.execute();
    }

    public void onHome (View view){
        startActivity(new Intent(this,home_user.class));
    }
}
