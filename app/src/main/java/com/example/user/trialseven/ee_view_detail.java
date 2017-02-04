package com.example.user.trialseven;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class ee_view_detail extends AppCompatActivity {

    private TextView vt_companyname;
    private TextView vt_jobtitle;
    private TextView vt_companyemail;
    private TextView vt_description;
    private TextView vt_id;

    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ee_view_detail);
        vt_companyname = (TextView)findViewById(R.id.vt_companyname);
        vt_jobtitle = (TextView)findViewById(R.id.vt_jobtitle);
        vt_companyemail = (TextView)findViewById(R.id.vt_companyemail);
        vt_description = (TextView)findViewById(R.id.vt_description);
        vt_id = (TextView)findViewById(R.id.vt_id);

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
                loading = ProgressDialog.show(ee_view_detail.this,"Loading...","Please Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showJob(s);
                //Toast.makeText(ee_view_detail.this, s, Toast.LENGTH_SHORT).show();
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

            vt_companyname.setText(name);
            vt_jobtitle.setText(title);
            vt_companyemail.setText(email);
            vt_description.setText(description);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void onBack (View view){
        startActivity(new Intent(this,ee_view.class));
    }
}
