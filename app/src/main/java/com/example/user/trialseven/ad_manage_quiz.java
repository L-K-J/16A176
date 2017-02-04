package com.example.user.trialseven;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class ad_manage_quiz extends AppCompatActivity implements ListView.OnItemClickListener {

    private ListView listView;

    private String JSON_STRING;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_manage_quiz);
        listView = (ListView) findViewById(R.id.listView4);
        listView.setOnItemClickListener(this);
        getJSON();
    }

    private void showQns() {
        JSONObject jsonObject = null;
        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);

            for (int i = 0; i < result.length(); i++) {
                JSONObject jo = result.getJSONObject(i);
                String qnid = jo.getString(Config.TAG_ASS_QNID);
                String question = jo.getString(Config.TAG_ASS_QN);

                HashMap<String, String> qns = new HashMap<>();
                qns.put(Config.TAG_ASS_QNID, qnid);
                qns.put(Config.TAG_ASS_QN, question);
                list.add(qns);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListAdapter adapter = new SimpleAdapter(
                ad_manage_quiz.this, list, R.layout.list_question,
                new String[]{Config.TAG_ASS_QNID, Config.TAG_ASS_QN},
                new int[]{R.id.vt_qnid, R.id.vt_question});

        listView.setAdapter(adapter);
    }

    private void getJSON() {
        class GetJSON extends AsyncTask<Void, Void, String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(ad_manage_quiz.this, "Loading...", "Please Wait...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                showQns();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequest(Config.URL_VIEWQNS);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, ad_manage_quiz_detail.class);
        HashMap<String,String> map =(HashMap)parent.getItemAtPosition(position);
        String qnID = map.get(Config.TAG_ASS_QNID).toString();
        intent.putExtra(Config.ASS_ID,qnID);
        startActivity(intent);
    }

    public void onHome (View view){
        startActivity(new Intent(this,home_admin.class));
    }

    public void onAdd (View view){
        startActivity(new Intent(this,ad_manage_quiz_add.class));
    }
}
