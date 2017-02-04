package com.example.user.trialseven;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.Rating;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class assessment_feedback extends AppCompatActivity {

    private EditText name;
    private EditText clss;
    private EditText desc;
    private RatingBar rb;
    private TextView star;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_feedback);

        name = (EditText)findViewById(R.id.et_name);
        clss = (EditText)findViewById(R.id.et_class);
        desc = (EditText)findViewById(R.id.et_desc);
        rb = (RatingBar)findViewById(R.id.ratingBar);
        star = (TextView)findViewById(R.id.vt_star);

        rb.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                star.setText(String.valueOf(v));
            }
        });

    }
    public void onSubmit (View view){
        final String sname = name.getText().toString().trim();
        final String sclss = clss.getText().toString().trim();
        final String sdesc = desc.getText().toString().trim();
        final String sstar = star.getText().toString().trim();

        if (TextUtils.isEmpty(sname)) {
            Toast.makeText(this, "Certain Fields Are Empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(sclss)) {
            Toast.makeText(this, "Certain Fields Are Empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(sstar)) {
            Toast.makeText(this, "Certain Fields Are Empty", Toast.LENGTH_SHORT).show();
            return;
        }

        class AddFb extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(assessment_feedback.this, "Loading...","Please Wait...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(assessment_feedback.this, s, Toast.LENGTH_SHORT).show();
            }

            @Override
            protected String doInBackground(Void... voids) {
                HashMap<String, String> params = new HashMap<>();
                params.put(Config.KEY_ASSF_NAME,sname);
                params.put(Config.KEY_ASSF_CLASS,sclss);
                params.put(Config.KEY_COM_DESCRIPTION,sdesc);
                params.put(Config.KEY_ASSF_STAR,sstar);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Config.URL_FEEDBACK, params);
                return res;
            }
        }
        AddFb fb = new AddFb();
        fb.execute();
        //startActivity(new Intent(this,home_user.class));

    }
}
