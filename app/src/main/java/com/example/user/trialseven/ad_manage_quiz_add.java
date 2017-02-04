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

public class ad_manage_quiz_add extends AppCompatActivity {

    private EditText et_question;
    private EditText et_optiona;
    private EditText et_optionb;
    private EditText et_nxa;
    private EditText et_nxb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_manage_quiz_add);
        et_question = (EditText)findViewById(R.id.et_question);
        et_optiona = (EditText)findViewById(R.id.et_optiona);
        et_optionb = (EditText)findViewById(R.id.et_optionb);
        et_nxa = (EditText)findViewById(R.id.et_nxa);
        et_nxb = (EditText)findViewById(R.id.et_nxb);
    }

    public void onAdd (View view) {
        final String question = et_question.getText().toString().trim();
        final String optiona = et_optiona.getText().toString().trim();
        final String optionb = et_optionb.getText().toString().trim();
        final String nxa = et_nxa.getText().toString().trim();
        final String nxb = et_nxb.getText().toString().trim();


        if (TextUtils.isEmpty(question)) {
            Toast.makeText(this, "Certain Fields Are Empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(optiona)) {
            Toast.makeText(this, "Certain Fields Are Empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(optionb)) {
            Toast.makeText(this, "Certain Fields Are Empty", Toast.LENGTH_SHORT).show();
            return;

        }
        if (TextUtils.isEmpty(nxa)) {
            Toast.makeText(this, "Certain Fields Are Empty", Toast.LENGTH_SHORT).show();
            return;

        }
        if (TextUtils.isEmpty(nxb)) {
            Toast.makeText(this, "Certain Fields Are Empty", Toast.LENGTH_SHORT).show();
            return;

        }
        class AddQn extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(ad_manage_quiz_add.this, "Loading...","Please Wait...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(ad_manage_quiz_add.this, s, Toast.LENGTH_SHORT).show();
            }

            @Override
            protected String doInBackground(Void... voids) {
                HashMap<String, String> params = new HashMap<>();
                params.put(Config.KEY_ASS_QUESTION,question);
                params.put(Config.KEY_ASS_OPA,optiona);
                params.put(Config.KEY_ASS_OPB,optionb);
                params.put(Config.KEY_ASS_NXA,nxa);
                params.put(Config.KEY_ASS_NXB,nxb);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Config.URL_ADDQN, params);
                return res;
            }
        }
        AddQn aq = new AddQn();
        aq.execute();
    }

    public void onBack (View view){
        startActivity(new Intent(this,ad_manage_quiz.class));
    }
}
