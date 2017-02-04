package com.example.user.trialseven;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class assessment extends AppCompatActivity {

    private TextView vt_cid, vt_question, vt_first, vt_nxa, vt_nxb;
    private Button btn_start, btn_home, btn_one, btn_two, btn_results;
    private float vc1 = 0 , vc2 = 0 , vc3 = 0 , vc4 = 0 , vc5 = 0, vc6 = 0, vc7 = 0, vc8 = 0, vc9 = 0, vc10 = 0;
    private float vc11 = 0, vc12 = 0, vc13 = 0, vc14 = 0, vc15 = 0, vc16 = 0, vc17 = 0, vc18 = 0, vc19 = 0, vc20 = 0;
    private float vc21 = 0 , vc22 = 0 , vc23 = 0 , vc24 = 0 , vc25 = 0, vc26 = 0, vc27 = 0, vc28 = 0, vc29 = 0, vc30 = 0;
    private float vc31 = 0 , vc32 = 0 , vc33 = 0 , vc34 = 0 , vc35 = 0, vc36 = 0, vc37 = 0, vc38 = 0, vc39 = 0, vc40 = 0;
    private float vc41 = 0 , vc42 = 0 , vc43 = 0 , vc44 = 0 , vc45 = 0, vc46 = 0, vc47 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment);

        vt_cid = (TextView)findViewById(R.id.vt_cid);
        vt_cid.setVisibility(View.INVISIBLE);

        vt_question = (TextView)findViewById(R.id.vt_question);
        vt_question.setVisibility(View.INVISIBLE);

        btn_one = (Button)findViewById(R.id.btn_one);
        btn_one.setVisibility(View.INVISIBLE);

        btn_two = (Button)findViewById(R.id.btn_two);
        btn_two.setVisibility(View.INVISIBLE);

        btn_start = (Button)findViewById(R.id.btn_start);
        btn_start.setVisibility(View.VISIBLE);

        btn_results = (Button)findViewById(R.id.btn_results);
        btn_results.setVisibility(View.INVISIBLE);

        btn_home = (Button)findViewById(R.id.btn_next);
        btn_home.setVisibility(View.INVISIBLE);

        vt_first = (TextView)findViewById(R.id.vt_first);
        vt_first.setText("1");
        vt_first.setVisibility(View.INVISIBLE);

        vt_nxa = (TextView)findViewById(R.id.vt_nxa);
        vt_nxa.setVisibility(View.INVISIBLE);

        vt_nxb = (TextView)findViewById(R.id.vt_nxb);
        vt_nxb.setVisibility(View.INVISIBLE);

        getQN();
    }
    private void getQN(){

        final String id = vt_first.getText().toString().trim();

        class GetQN extends AsyncTask<String,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(assessment.this,"Loading...","Please Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showQN(s);
                //Toast.makeText(assessment.this, s, Toast.LENGTH_SHORT).show();
            }

            @Override
            protected String doInBackground(String... Strings) {
                HashMap<String, String> params = new HashMap<>();
                params.put(Config.KEY_ASS_ID,id);

                RequestHandler rh = new RequestHandler();
                String s = rh.sendPostRequest(Config.URL_ASSESSMENT,params);
                return s;
            }
        }
        GetQN gq = new GetQN();
        gq.execute();
    }

    public void onOne (View view){

        final String id = vt_nxa.getText().toString().trim();
        final String cid = vt_cid.getText().toString().trim();

        class GetQN extends AsyncTask<String,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(assessment.this,"Loading...","Please Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                if (s.contains ("results")){
                    vt_question.setVisibility(View.INVISIBLE);
                    btn_one.setVisibility(View.INVISIBLE);
                    btn_two.setVisibility(View.INVISIBLE);
                    btn_results.setVisibility(View.VISIBLE);
                }else{
                    showQN(s);
                }
                //showVar(s);
                //Toast.makeText(assessment.this, s, Toast.LENGTH_SHORT).show();
            }

            @Override
            protected String doInBackground(String... Strings) {
                HashMap<String, String> params = new HashMap<>();
                params.put(Config.KEY_ASS_ID,id);

                RequestHandler rh = new RequestHandler();
                String s = rh.sendPostRequest(Config.URL_ASSESSMENT2,params);
                return s;
            }
        }
        GetQN gq = new GetQN();
        gq.execute();

        class GetVar extends AsyncTask<String,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(assessment.this,"Loading...","Please Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                //showQN(s);
                showVar(s);
                //Toast.makeText(assessment.this, s, Toast.LENGTH_SHORT).show();
            }

            @Override
            protected String doInBackground(String... Strings) {
                HashMap<String, String> params = new HashMap<>();
                params.put(Config.KEY_ASS_ID,cid);

                RequestHandler rh = new RequestHandler();
                String s = rh.sendPostRequest(Config.URL_ASSESSMENT2,params);
                return s;
            }
        }
        GetVar gv = new GetVar();
        gv.execute();
    }

    public void onTwo (View view){

        final String id = vt_nxb.getText().toString().trim();

        class GetQN extends AsyncTask<String,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(assessment.this,"Loading...","Please Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                if (s.contains ("results")){
                    vt_question.setVisibility(View.INVISIBLE);
                    btn_one.setVisibility(View.INVISIBLE);
                    btn_two.setVisibility(View.INVISIBLE);
                    btn_results.setVisibility(View.VISIBLE);
                }else{
                    showQN(s);
                }                //Toast.makeText(assessment.this, s, Toast.LENGTH_SHORT).show();
            }

            @Override
            protected String doInBackground(String... Strings) {
                HashMap<String, String> params = new HashMap<>();
                params.put(Config.KEY_ASS_ID,id);

                RequestHandler rh = new RequestHandler();
                String s = rh.sendPostRequest(Config.URL_ASSESSMENT,params);
                return s;
            }
        }
        GetQN gq = new GetQN();
        gq.execute();
    }

    private void showQN(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);
            JSONObject c = result.getJSONObject(0);
            String id = c.getString(Config.TAG_ASS_QNID);
            String question = c.getString(Config.TAG_ASS_QN);
            String opa = c.getString(Config.TAG_ASS_OPA);
            String opb = c.getString(Config.TAG_ASS_OPB);
            String nxa = c.getString(Config.TAG_ASS_NXA);
            String nxb = c.getString(Config.TAG_ASS_NXB);

            vt_cid.setText(id);
            vt_question.setText(question);
            btn_one.setText(opa);
            btn_two.setText(opb);
            vt_nxa.setText(nxa);
            vt_nxb.setText(nxb);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    private void showVar(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);
            JSONObject c = result.getJSONObject(0);
            String c1 = c.getString(Config.TAG_C1);
            String c2 = c.getString(Config.TAG_C2);
            String c3 = c.getString(Config.TAG_C3);
            String c4 = c.getString(Config.TAG_C4);
            String c5 = c.getString(Config.TAG_C5);
            String c6 = c.getString(Config.TAG_C6);
            String c7 = c.getString(Config.TAG_C7);
            String c8 = c.getString(Config.TAG_C8);
            String c9 = c.getString(Config.TAG_C9);
            String c10 = c.getString(Config.TAG_C10);
            String c11 = c.getString(Config.TAG_C11);
            String c12 = c.getString(Config.TAG_C12);
            String c13 = c.getString(Config.TAG_C13);
            String c14 = c.getString(Config.TAG_C14);
            String c15 = c.getString(Config.TAG_C15);
            String c16 = c.getString(Config.TAG_C16);
            String c17 = c.getString(Config.TAG_C17);
            String c18 = c.getString(Config.TAG_C18);
            String c19 = c.getString(Config.TAG_C19);
            String c20 = c.getString(Config.TAG_C20);
            String c21 = c.getString(Config.TAG_C21);
            String c22 = c.getString(Config.TAG_C22);
            String c23 = c.getString(Config.TAG_C23);
            String c24 = c.getString(Config.TAG_C24);
            String c25 = c.getString(Config.TAG_C25);
            String c26 = c.getString(Config.TAG_C26);
            String c27 = c.getString(Config.TAG_C27);
            String c28 = c.getString(Config.TAG_C28);
            String c29 = c.getString(Config.TAG_C29);
            String c30 = c.getString(Config.TAG_C30);
            String c31 = c.getString(Config.TAG_C31);
            String c32 = c.getString(Config.TAG_C32);
            String c33 = c.getString(Config.TAG_C33);
            String c34 = c.getString(Config.TAG_C34);
            String c35 = c.getString(Config.TAG_C35);
            String c36 = c.getString(Config.TAG_C36);
            String c37 = c.getString(Config.TAG_C37);
            String c38 = c.getString(Config.TAG_C38);
            String c39 = c.getString(Config.TAG_C39);
            String c40 = c.getString(Config.TAG_C40);
            String c41 = c.getString(Config.TAG_C41);
            String c42 = c.getString(Config.TAG_C42);
            String c43 = c.getString(Config.TAG_C43);
            String c44 = c.getString(Config.TAG_C44);
            String c45 = c.getString(Config.TAG_C45);
            String c46 = c.getString(Config.TAG_C46);
            String c47 = c.getString(Config.TAG_C47);

            int ic1 = Integer.parseInt(c1);
            int ic2 = Integer.parseInt(c2);
            int ic3 = Integer.parseInt(c3);
            int ic4 = Integer.parseInt(c4);
            int ic5 = Integer.parseInt(c5);
            int ic6 = Integer.parseInt(c6);
            int ic7 = Integer.parseInt(c7);
            int ic8 = Integer.parseInt(c8);
            int ic9 = Integer.parseInt(c9);
            int ic10 = Integer.parseInt(c10);
            int ic11 = Integer.parseInt(c11);
            int ic12 = Integer.parseInt(c12);
            int ic13 = Integer.parseInt(c13);
            int ic14 = Integer.parseInt(c14);
            int ic15 = Integer.parseInt(c15);
            int ic16 = Integer.parseInt(c16);
            int ic17 = Integer.parseInt(c17);
            int ic18 = Integer.parseInt(c18);
            int ic19 = Integer.parseInt(c19);
            int ic20 = Integer.parseInt(c20);
            int ic21 = Integer.parseInt(c21);
            int ic22 = Integer.parseInt(c22);
            int ic23 = Integer.parseInt(c23);
            int ic24 = Integer.parseInt(c24);
            int ic25 = Integer.parseInt(c25);
            int ic26 = Integer.parseInt(c26);
            int ic27 = Integer.parseInt(c27);
            int ic28 = Integer.parseInt(c28);
            int ic29 = Integer.parseInt(c29);
            int ic30 = Integer.parseInt(c30);
            int ic31 = Integer.parseInt(c31);
            int ic32 = Integer.parseInt(c32);
            int ic33 = Integer.parseInt(c33);
            int ic34 = Integer.parseInt(c34);
            int ic35 = Integer.parseInt(c35);
            int ic36 = Integer.parseInt(c36);
            int ic37 = Integer.parseInt(c37);
            int ic38 = Integer.parseInt(c38);
            int ic39 = Integer.parseInt(c39);
            int ic40 = Integer.parseInt(c40);
            int ic41 = Integer.parseInt(c41);
            int ic42 = Integer.parseInt(c42);
            int ic43 = Integer.parseInt(c43);
            int ic44 = Integer.parseInt(c44);
            int ic45 = Integer.parseInt(c45);
            int ic46 = Integer.parseInt(c46);
            int ic47 = Integer.parseInt(c47);

            vc1 = vc1+ic1;
            vc2 = vc2+ic2;
            vc3 = vc3+ic3;
            vc4 = vc4+ic4;
            vc5 = vc5+ic5;
            vc6 = vc6+ic6;
            vc7 = vc7+ic7;
            vc8 = vc8+ic8;
            vc9 = vc9+ic9;
            vc10 = vc10+ic10;
            vc11 = vc11+ic11;
            vc12 = vc12+ic12;
            vc13 = vc13+ic13;
            vc14 = vc14+ic14;
            vc15 = vc15+ic15;
            vc16 = vc16+ic16;
            vc17 = vc17+ic17;
            vc18 = vc18+ic18;
            vc19 = vc19+ic19;
            vc20 = vc20+ic20;
            vc21 = vc21+ic21;
            vc22 = vc22+ic22;
            vc23 = vc23+ic23;
            vc24 = vc24+ic24;
            vc25 = vc25+ic25;
            vc26 = vc26+ic26;
            vc27 = vc27+ic27;
            vc28 = vc28+ic28;
            vc29 = vc29+ic29;
            vc30 = vc30+ic30;
            vc31 = vc31+ic31;
            vc32 = vc32+ic32;
            vc33 = vc33+ic33;
            vc34 = vc34+ic34;
            vc35 = vc35+ic35;
            vc36 = vc36+ic36;
            vc37 = vc37+ic37;
            vc38 = vc38+ic38;
            vc39 = vc39+ic39;
            vc40 = vc40+ic40;
            vc41 = vc41+ic41;
            vc42 = vc42+ic42;
            vc43 = vc43+ic43;
            vc44 = vc44+ic44;
            vc45 = vc45+ic45;
            vc46 = vc46+ic46;
            vc47 = vc47+ic47;

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void onStart (View view) {

        vt_cid.setVisibility(View.VISIBLE);
        vt_question.setVisibility(View.VISIBLE);
        btn_one.setVisibility(View.VISIBLE);
        btn_two.setVisibility(View.VISIBLE);
        btn_start.setVisibility(View.INVISIBLE);
        btn_home.setVisibility(View.VISIBLE);
        vt_first.setVisibility(View.VISIBLE);
        vt_nxa.setVisibility(View.VISIBLE);
        vt_nxb.setVisibility(View.VISIBLE);

    }

    public void onHome (View view){
        startActivity(new Intent(this,home_user.class));
    }

    public void onResults (View view){
        Intent intent = (new Intent(this,assessment_results.class));
        intent.putExtra("c1", Float.toString(vc1));
        intent.putExtra("c2", Float.toString(vc2));
        intent.putExtra("c3", Float.toString(vc3));
        intent.putExtra("c4", Float.toString(vc4));
        intent.putExtra("c5", Float.toString(vc5));
        intent.putExtra("c6", Float.toString(vc6));
        intent.putExtra("c7", Float.toString(vc7));
        intent.putExtra("c8", Float.toString(vc8));
        intent.putExtra("c9", Float.toString(vc9));
        intent.putExtra("c10", Float.toString(vc10));
        intent.putExtra("c11", Float.toString(vc11));
        intent.putExtra("c12", Float.toString(vc12));
        intent.putExtra("c13", Float.toString(vc13));
        intent.putExtra("c14", Float.toString(vc14));
        intent.putExtra("c15", Float.toString(vc15));
        intent.putExtra("c16", Float.toString(vc16));
        intent.putExtra("c17", Float.toString(vc17));
        intent.putExtra("c18", Float.toString(vc18));
        intent.putExtra("c19", Float.toString(vc19));
        intent.putExtra("c20", Float.toString(vc20));
        intent.putExtra("c21", Float.toString(vc21));
        intent.putExtra("c22", Float.toString(vc22));
        intent.putExtra("c23", Float.toString(vc23));
        intent.putExtra("c24", Float.toString(vc24));
        intent.putExtra("c25", Float.toString(vc25));
        intent.putExtra("c26", Float.toString(vc26));
        intent.putExtra("c27", Float.toString(vc27));
        intent.putExtra("c28", Float.toString(vc28));
        intent.putExtra("c29", Float.toString(vc29));
        intent.putExtra("c30", Float.toString(vc30));
        intent.putExtra("c31", Float.toString(vc31));
        intent.putExtra("c32", Float.toString(vc32));
        intent.putExtra("c33", Float.toString(vc33));
        intent.putExtra("c34", Float.toString(vc34));
        intent.putExtra("c35", Float.toString(vc35));
        intent.putExtra("c36", Float.toString(vc36));
        intent.putExtra("c37", Float.toString(vc37));
        intent.putExtra("c38", Float.toString(vc38));
        intent.putExtra("c39", Float.toString(vc39));
        intent.putExtra("c40", Float.toString(vc40));
        intent.putExtra("c41", Float.toString(vc41));
        intent.putExtra("c42", Float.toString(vc42));
        intent.putExtra("c43", Float.toString(vc43));
        intent.putExtra("c44", Float.toString(vc44));
        intent.putExtra("c45", Float.toString(vc45));
        intent.putExtra("c46", Float.toString(vc46));
        intent.putExtra("c47", Float.toString(vc47));
        startActivity(intent);
    }
}
