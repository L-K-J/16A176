package com.example.user.trialseven;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class er_view extends AppCompatActivity implements ListView.OnItemClickListener {

    private EditText et_username;

    private ListView listView;

    private String JSON_STRING;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_er_view);
        et_username = (EditText) findViewById(R.id.et_username);
        listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(this);
    }

    public void onView(View view) {

        final String username = et_username.getText().toString().trim();

        if (TextUtils.isEmpty(username)) {
            Toast.makeText(this, "Certain Fields Are Empty", Toast.LENGTH_SHORT).show();
            return;
        }

        class View extends AsyncTask<String, Void, String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(er_view.this, "Loading...", "Please Wait...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                showJobs();
            }

            @Override
            protected String doInBackground(String... strings) {
                HashMap<String, String> params = new HashMap<>();
                params.put(Config.KEY_USR_USERNAME, username);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Config.URL_VIEWPOST, params);
                return res;
            }
        }
        View vw = new View();
        vw.execute();


    }

    private void showJobs() {
        JSONObject jsonObject = null;
        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);

            for (int i = 0; i < result.length(); i++) {
                JSONObject jo = result.getJSONObject(i);
                String jobid = jo.getString(Config.TAG_COM_ID);
                String companyname = jo.getString(Config.TAG_COM_NAME);
                String companyjobtitle = jo.getString(Config.TAG_COM_TITLE);
                String companyemail = jo.getString(Config.TAG_COM_EMAIL);
                String date = jo.getString(Config.TAG_COM_POSTD);

                HashMap<String, String> jobs = new HashMap<>();
                jobs.put(Config.TAG_COM_ID,jobid);
                jobs.put(Config.TAG_COM_NAME, companyname);
                jobs.put(Config.TAG_COM_TITLE, companyjobtitle);
                jobs.put(Config.TAG_COM_EMAIL, companyemail);
                jobs.put(Config.TAG_COM_POSTD, date);
                list.add(jobs);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListAdapter adapter = new SimpleAdapter(
                er_view.this, list, R.layout.list_job,
                new String[]{Config.TAG_COM_ID,Config.TAG_COM_NAME, Config.TAG_COM_TITLE, Config.TAG_COM_EMAIL, Config.TAG_COM_POSTD},
                new int[]{R.id.vt_jobid,R.id.vt_companyname, R.id.vt_jobtitle, R.id.vt_companyemail, R.id.vt_postdate});

        listView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, er_view_detail.class);
        HashMap<String,String> map =(HashMap)parent.getItemAtPosition(position);
        String jobID = map.get(Config.TAG_COM_ID).toString();
        intent.putExtra(Config.COM_ID,jobID);
        startActivity(intent);
    }

    public void onHome (View view){
        startActivity(new Intent(this,home_user.class));
    }
}