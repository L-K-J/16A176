package com.example.user.trialseven;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class ad_manage_quiz_detail extends AppCompatActivity implements View.OnClickListener {

    private EditText et_question;
    private EditText et_optiona;
    private EditText et_optionb;
    private EditText et_nxa;
    private EditText et_nxb;
    private TextView vt_id;

    private Button btn_Update;
    private Button btn_Delete;

    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_manage_quiz_detail);
        et_question = (EditText)findViewById(R.id.et_question);
        et_optiona = (EditText)findViewById(R.id.et_optiona);
        et_optionb = (EditText)findViewById(R.id.et_optionb);
        et_nxa = (EditText)findViewById(R.id.et_nxa);
        et_nxb = (EditText)findViewById(R.id.et_nxb);
        vt_id = (TextView)findViewById(R.id.vt_qnid);

        btn_Update = (Button) findViewById(R.id.btn_update);
        btn_Delete = (Button) findViewById(R.id.btn_delete);
        btn_Update.setOnClickListener(this);
        btn_Delete.setOnClickListener(this);

        Intent intent = getIntent();
        id = intent.getStringExtra(Config.ASS_ID);

        vt_id.setText(id);

        getQnInfo();
    }
    private void getQnInfo(){

        final String id = vt_id.getText().toString().trim();

        class GetQnInfo extends AsyncTask<String,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(ad_manage_quiz_detail.this,"Loading...","Please Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showQn(s);
                Toast.makeText(ad_manage_quiz_detail.this, s, Toast.LENGTH_SHORT).show();
            }

            @Override
            protected String doInBackground(String... Strings) {
                HashMap<String, String> params = new HashMap<>();
                params.put(Config.KEY_ASS_ID,id);

                RequestHandler rh = new RequestHandler();
                String s = rh.sendPostRequest(Config.URL_GETQN,params);
                return s;
            }
        }
        GetQnInfo gq = new GetQnInfo();
        gq.execute();
    }

    private void showQn(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);
            JSONObject c = result.getJSONObject(0);
            String qnid = c.getString(Config.TAG_ASS_QNID);
            String question = c.getString(Config.TAG_ASS_QN);
            String optiona = c.getString(Config.TAG_ASS_OPA);
            String optionb = c.getString(Config.TAG_ASS_OPB);
            String nexta = c.getString(Config.TAG_ASS_NXA);
            String nextb = c.getString(Config.TAG_ASS_NXB);

            vt_id.setText(qnid);
            et_question.setText(question);
            et_optiona.setText(optiona);
            et_optionb.setText(optionb);
            et_nxa.setText(nexta);
            et_nxb.setText(nextb);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private void updateQn(){
        final String question = et_question.getText().toString().trim();
        final String optiona = et_optiona.getText().toString().trim();
        final String optionb = et_optionb.getText().toString().trim();
        final String nexta = et_nxa.getText().toString().trim();
        final String nextb = et_nxb.getText().toString().trim();

        class UpdateQn extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(ad_manage_quiz_detail.this,"Updating...","Please Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(ad_manage_quiz_detail.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put(Config.KEY_ASS_ID,id);
                hashMap.put(Config.KEY_ASS_QUESTION,question);
                hashMap.put(Config.KEY_ASS_OPA,optiona);
                hashMap.put(Config.KEY_ASS_OPB,optionb);
                hashMap.put(Config.KEY_ASS_NXA,nexta);
                hashMap.put(Config.KEY_ASS_NXB,nextb);

                RequestHandler rh = new RequestHandler();

                String s = rh.sendPostRequest(Config.URL_UPDATEQN,hashMap);

                return s;
            }
        }

        UpdateQn uq = new UpdateQn();
        uq.execute();
    }

    private void deleteQn(){

        final String id = vt_id.getText().toString().trim();

        class DeleteQn extends AsyncTask<String,Void,String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(ad_manage_quiz_detail.this, "Deleting...", "Please Wait...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(ad_manage_quiz_detail.this, s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(String... Strings) {
                HashMap<String, String> params = new HashMap<>();
                params.put(Config.KEY_ASS_ID,id);

                RequestHandler rh = new RequestHandler();
                String s = rh.sendPostRequest(Config.URL_DELETEQN, params);
                return s;
            }
        }

        DeleteQn dq = new DeleteQn();
        dq.execute();
    }

    private void confirmDeleteQn(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are You Sure?");

        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        deleteQn();
                        startActivity(new Intent(ad_manage_quiz_detail.this,ad_manage_quiz.class));
                    }
                });

        alertDialogBuilder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }


    @Override
    public void onClick(View view) {
        if(view == btn_Update){
            updateQn();
        }

        if(view == btn_Delete){
            confirmDeleteQn();
        }
    }
    public void onBack (View view){
        startActivity(new Intent(this,ad_manage_quiz.class));
    }
}