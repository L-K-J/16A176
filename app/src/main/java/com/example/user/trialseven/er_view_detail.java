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

public class er_view_detail extends AppCompatActivity implements View.OnClickListener {

    private EditText et_companyname;
    private EditText et_jobtitle;
    private EditText et_companyemail;
    private EditText et_description;
    private TextView vt_id;

    private Button btn_Update;
    private Button btn_Delete;

    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_er_view_detail);
        et_companyname = (EditText)findViewById(R.id.et_companyname);
        et_jobtitle = (EditText)findViewById(R.id.et_jobtitle);
        et_companyemail = (EditText)findViewById(R.id.et_companyemail);
        et_description = (EditText)findViewById(R.id.et_description);
        vt_id = (TextView)findViewById(R.id.vt_id);

        btn_Update = (Button) findViewById(R.id.btn_update);
        btn_Delete = (Button) findViewById(R.id.btn_delete);
        btn_Update.setOnClickListener(this);
        btn_Delete.setOnClickListener(this);

        Intent intent = getIntent();
        id = intent.getStringExtra(Config.COM_ID);

        vt_id.setText(id);

        getJobInfo();
    }
    private void getJobInfo(){

        final String id = vt_id.getText().toString().trim();

        class GetJobInfo extends AsyncTask<String,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(er_view_detail.this,"Loading...","Please Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showJob(s);
                //Toast.makeText(er_view_detail.this, s, Toast.LENGTH_SHORT).show();
            }

            @Override
            protected String doInBackground(String... Strings) {
                HashMap<String, String> params = new HashMap<>();
                params.put(Config.KEY_COM_ID,id);

                RequestHandler rh = new RequestHandler();
                String s = rh.sendPostRequest(Config.URL_GETJOB,params);
                return s;
            }
        }
        GetJobInfo gj = new GetJobInfo();
        gj.execute();
    }

    private void showJob(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);
            JSONObject c = result.getJSONObject(0);
            String name = c.getString(Config.TAG_COM_NAME);
            String title = c.getString(Config.TAG_COM_TITLE);
            String email = c.getString(Config.TAG_COM_EMAIL);
            String description = c.getString(Config.TAG_COM_DESCRIPTION);

            et_companyname.setText(name);
            et_jobtitle.setText(title);
            et_companyemail.setText(email);
            et_description.setText(description);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private void updateJob(){
        final String name = et_companyname.getText().toString().trim();
        final String title = et_jobtitle.getText().toString().trim();
        final String email = et_companyemail.getText().toString().trim();
        final String description = et_description.getText().toString().trim();

        class UpdateJob extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(er_view_detail.this,"Updating...","Please Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(er_view_detail.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put(Config.KEY_COM_ID,id);
                hashMap.put(Config.KEY_COM_NAME,name);
                hashMap.put(Config.KEY_COM_TITLE,title);
                hashMap.put(Config.KEY_COM_EMAIL,email);
                hashMap.put(Config.KEY_COM_DESCRIPTION,description);

                RequestHandler rh = new RequestHandler();

                String s = rh.sendPostRequest(Config.URL_UPDATEJOB,hashMap);

                return s;
            }
        }

        UpdateJob uj = new UpdateJob();
        uj.execute();
    }

    private void deleteJob(){

        final String id = vt_id.getText().toString().trim();

        class DeleteJob extends AsyncTask<String,Void,String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(er_view_detail.this, "Deleting...", "Please Wait...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(er_view_detail.this, s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(String... Strings) {
                HashMap<String, String> params = new HashMap<>();
                params.put(Config.KEY_COM_ID,id);

                RequestHandler rh = new RequestHandler();
                String s = rh.sendPostRequest(Config.URL_DELETEJOB, params);
                return s;
            }
        }

        DeleteJob dj = new DeleteJob();
        dj.execute();
    }

    private void confirmDeleteJob(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are You Sure?");

        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        deleteJob();
                        startActivity(new Intent(er_view_detail.this,er_view.class));
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
            updateJob();
        }

        if(view == btn_Delete){
            confirmDeleteJob();
        }
    }
    public void onHome (View view){
        startActivity(new Intent(this,home_user.class));
    }
}
