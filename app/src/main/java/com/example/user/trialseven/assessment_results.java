package com.example.user.trialseven;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;

public class assessment_results extends AppCompatActivity {

    private String c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19, c20, c21, c22, c23, c24, c25, c26, c27, c28, c29, c30, c31, c32, c33, c34, c35, c36;
    private String c37, c38, c39, c40, c41, c42, c43, c44, c45, c46, c47;

    TextView vt_c1, vt_c2, vt_c3, vt_c4, vt_c5, vt_c6, vt_c7, vt_c8, vt_c9, vt_c10, vt_c11,vt_c12, vt_c13, vt_c14, vt_c15, vt_c16, vt_c17, vt_c18, vt_c19, vt_c20, vt_c21, vt_c22, vt_c23;
    TextView vt_c24, vt_c25, vt_c26, vt_c27, vt_c28, vt_c29, vt_c30, vt_c31, vt_c32, vt_c33, vt_c34, vt_c35, vt_c36, vt_c37, vt_c38, vt_c39, vt_c40, vt_c41, vt_c42, vt_c43, vt_c44, vt_c45;
    TextView vt_c46, vt_c47;

    float perc1, perc2, perc3, perc4, perc5, perc6, perc7, perc8, perc9, perc10, perc11, perc12, perc13, perc14, perc15, perc16, perc17, perc18, perc19, perc20, perc21, perc22, perc23, perc24;
    float perc25, perc26, perc27, perc28, perc29, perc30, perc31, perc32, perc33, perc34, perc35, perc36, perc37, perc38, perc39, perc40, perc41, perc42, perc43, perc44, perc45, perc46, perc47;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_results);

        vt_c1 = (TextView)findViewById(R.id.vt_c1);
        vt_c2 = (TextView)findViewById(R.id.vt_c2);
        vt_c3 = (TextView)findViewById(R.id.vt_c3);
        vt_c4 = (TextView)findViewById(R.id.vt_c4);
        vt_c5 = (TextView)findViewById(R.id.vt_c5);
        vt_c6 = (TextView)findViewById(R.id.vt_c6);
        vt_c7 = (TextView)findViewById(R.id.vt_c7);
        vt_c8 = (TextView)findViewById(R.id.vt_c8);
        vt_c9 = (TextView)findViewById(R.id.vt_c9);
        vt_c10 = (TextView)findViewById(R.id.vt_c10);
        vt_c11 = (TextView)findViewById(R.id.vt_c11);
        vt_c12 = (TextView)findViewById(R.id.vt_c12);
        vt_c13 = (TextView)findViewById(R.id.vt_c13);
        vt_c14 = (TextView)findViewById(R.id.vt_c14);
        vt_c15 = (TextView)findViewById(R.id.vt_c15);
        vt_c16 = (TextView)findViewById(R.id.vt_c16);
        vt_c17 = (TextView)findViewById(R.id.vt_c17);
        vt_c18 = (TextView)findViewById(R.id.vt_c18);
        vt_c19 = (TextView)findViewById(R.id.vt_c19);
        vt_c20 = (TextView)findViewById(R.id.vt_c20);
        vt_c21 = (TextView)findViewById(R.id.vt_c21);
        vt_c22 = (TextView)findViewById(R.id.vt_c22);
        vt_c23 = (TextView)findViewById(R.id.vt_c23);
        vt_c24 = (TextView)findViewById(R.id.vt_c24);
        vt_c25 = (TextView)findViewById(R.id.vt_c25);
        vt_c26 = (TextView)findViewById(R.id.vt_c26);
        vt_c27 = (TextView)findViewById(R.id.vt_c27);
        vt_c28 = (TextView)findViewById(R.id.vt_c28);
        vt_c29 = (TextView)findViewById(R.id.vt_c29);
        vt_c30 = (TextView)findViewById(R.id.vt_c30);
        vt_c31 = (TextView)findViewById(R.id.vt_c31);
        vt_c32 = (TextView)findViewById(R.id.vt_c32);
        vt_c33 = (TextView)findViewById(R.id.vt_c33);
        vt_c34 = (TextView)findViewById(R.id.vt_c34);
        vt_c35 = (TextView)findViewById(R.id.vt_c35);
        vt_c36 = (TextView)findViewById(R.id.vt_c36);
        vt_c37 = (TextView)findViewById(R.id.vt_c37);
        vt_c38 = (TextView)findViewById(R.id.vt_c38);
        vt_c39 = (TextView)findViewById(R.id.vt_c39);
        vt_c40 = (TextView)findViewById(R.id.vt_c40);
        vt_c41 = (TextView)findViewById(R.id.vt_c41);
        vt_c42 = (TextView)findViewById(R.id.vt_c42);
        vt_c43 = (TextView)findViewById(R.id.vt_c43);
        vt_c44 = (TextView)findViewById(R.id.vt_c44);
        vt_c45 = (TextView)findViewById(R.id.vt_c45);
        vt_c46 = (TextView)findViewById(R.id.vt_c46);
        vt_c47 = (TextView)findViewById(R.id.vt_c47);

        c1 = getIntent().getStringExtra("c1");  float vc1 = Float.parseFloat(c1);   perc1 = (vc1/7)*100;
        c2 = getIntent().getStringExtra("c2");  float vc2 = Float.parseFloat(c2);   perc2 = (vc2/9)*100;
        c3 = getIntent().getStringExtra("c3");  float vc3 = Float.parseFloat(c3);   perc3 = (vc3/12)*100;
        c4 = getIntent().getStringExtra("c4");  float vc4 = Float.parseFloat(c4);   perc4 = (vc4/6)*100;
        c5 = getIntent().getStringExtra("c5");  float vc5 = Float.parseFloat(c5);   perc5 = (vc5/9)*100;
        c6 = getIntent().getStringExtra("c6");  float vc6 = Float.parseFloat(c6);   perc6 = (vc6/7)*100;
        c7 = getIntent().getStringExtra("c7");  float vc7 = Float.parseFloat(c7);   perc7 = (vc7/8)*100;
        c8 = getIntent().getStringExtra("c8");  float vc8 = Float.parseFloat(c8);   perc8 = (vc8/9)*100;
        c9 = getIntent().getStringExtra("c9");  float vc9 = Float.parseFloat(c9);   perc9 = (vc9/9)*100;
        c10 = getIntent().getStringExtra("c10");  float vc10 = Float.parseFloat(c10);   perc10 = (vc10/11)*100;
        c11 = getIntent().getStringExtra("c11");  float vc11 = Float.parseFloat(c11);   perc11 = (vc11/8)*100;
        c12 = getIntent().getStringExtra("c12");  float vc12 = Float.parseFloat(c12);   perc12 = (vc12/13)*100;
        c13 = getIntent().getStringExtra("c13");  float vc13 = Float.parseFloat(c13);   perc13 = (vc13/13)*100;
        c14 = getIntent().getStringExtra("c14");  float vc14 = Float.parseFloat(c14);   perc14 = (vc14/4)*100;
        c15 = getIntent().getStringExtra("c15");  float vc15 = Float.parseFloat(c15);   perc15 = (vc15/9)*100;
        c16 = getIntent().getStringExtra("c16");  float vc16 = Float.parseFloat(c16);   perc16 = (vc16/7)*100;
        c17 = getIntent().getStringExtra("c17");  float vc17 = Float.parseFloat(c17);   perc17 = (vc17/8)*100;
        c18 = getIntent().getStringExtra("c18");  float vc18 = Float.parseFloat(c18);   perc18 = (vc18/13)*100;
        c19 = getIntent().getStringExtra("c19");  float vc19 = Float.parseFloat(c19);   perc19 = (vc19/14)*100;
        c20 = getIntent().getStringExtra("c20");  float vc20 = Float.parseFloat(c20);   perc20 = (vc20/9)*100;
        c21 = getIntent().getStringExtra("c21");  float vc21 = Float.parseFloat(c21);   perc21 = (vc21/10)*100;
        c22 = getIntent().getStringExtra("c22");  float vc22 = Float.parseFloat(c22);   perc22 = (vc22/10)*100;
        c23 = getIntent().getStringExtra("c23");  float vc23 = Float.parseFloat(c23);   perc23 = (vc23/8)*100;
        c24 = getIntent().getStringExtra("c24");  float vc24 = Float.parseFloat(c24);   perc24 = (vc24/22)*100;
        c25 = getIntent().getStringExtra("c25");  float vc25 = Float.parseFloat(c25);   perc25 = (vc25/11)*100;
        c26 = getIntent().getStringExtra("c26");  float vc26 = Float.parseFloat(c26);   perc26 = (vc26/12)*100;
        c27 = getIntent().getStringExtra("c27");  float vc27 = Float.parseFloat(c27);   perc27 = (vc27/18)*100;
        c28 = getIntent().getStringExtra("c28");  float vc28 = Float.parseFloat(c28);   perc28 = (vc28/17)*100;
        c29 = getIntent().getStringExtra("c29");  float vc29 = Float.parseFloat(c29);   perc29 = (vc29/4)*100;
        c30 = getIntent().getStringExtra("c30");  float vc30 = Float.parseFloat(c30);   perc30 = (vc30/10)*100;
        c31 = getIntent().getStringExtra("c31");  float vc31 = Float.parseFloat(c31);   perc31 = (vc31/10)*100;
        c32 = getIntent().getStringExtra("c32");  float vc32 = Float.parseFloat(c32);   perc32 = (vc32/12)*100;
        c33 = getIntent().getStringExtra("c33");  float vc33 = Float.parseFloat(c33);   perc33 = (vc33/13)*100;
        c34 = getIntent().getStringExtra("c34");  float vc34 = Float.parseFloat(c34);   perc34 = (vc34/12)*100;
        c35 = getIntent().getStringExtra("c35");  float vc35 = Float.parseFloat(c35);   perc35 = (vc35/8)*100;
        c36 = getIntent().getStringExtra("c36");  float vc36 = Float.parseFloat(c36);   perc36 = (vc36/7)*100;
        c37 = getIntent().getStringExtra("c37");  float vc37 = Float.parseFloat(c37);   perc37 = (vc37/9)*100;
        c38 = getIntent().getStringExtra("c38");  float vc38 = Float.parseFloat(c38);   perc38 = (vc38/14)*100;
        c39 = getIntent().getStringExtra("c39");  float vc39 = Float.parseFloat(c39);   perc39 = (vc39/7)*100;
        c40 = getIntent().getStringExtra("c40");  float vc40 = Float.parseFloat(c40);   perc40 = (vc40/13)*100;
        c41 = getIntent().getStringExtra("c41");  float vc41 = Float.parseFloat(c41);   perc41 = (vc41/3)*100;
        c42 = getIntent().getStringExtra("c42");  float vc42 = Float.parseFloat(c42);   perc42 = (vc42/5)*100;
        c43 = getIntent().getStringExtra("c43");  float vc43 = Float.parseFloat(c43);   perc43 = (vc43/4)*100;
        c44 = getIntent().getStringExtra("c44");  float vc44 = Float.parseFloat(c44);   perc44 = (vc44/5)*100;
        c45 = getIntent().getStringExtra("c45");  float vc45 = Float.parseFloat(c45);   perc45 = (vc45/14)*100;
        c46 = getIntent().getStringExtra("c46");  float vc46 = Float.parseFloat(c46);   perc46 = (vc46/13)*100;
        c47 = getIntent().getStringExtra("c47");  float vc47 = Float.parseFloat(c47);   perc47 = (vc47/11)*100;

        vt_c1.setText(Float.toString(perc1)+"%");
        vt_c2.setText(Float.toString(perc2)+"%");
        vt_c3.setText(Float.toString(perc3)+"%");
        vt_c4.setText(Float.toString(perc4)+"%");
        vt_c5.setText(Float.toString(perc5)+"%");
        vt_c6.setText(Float.toString(perc6)+"%");
        vt_c7.setText(Float.toString(perc7)+"%");
        vt_c8.setText(Float.toString(perc8)+"%");
        vt_c9.setText(Float.toString(perc9)+"%");
        vt_c10.setText(Float.toString(perc10)+"%");
        vt_c11.setText(Float.toString(perc11)+"%");
        vt_c12.setText(Float.toString(perc12)+"%");
        vt_c13.setText(Float.toString(perc13)+"%");
        vt_c14.setText(Float.toString(perc14)+"%");
        vt_c15.setText(Float.toString(perc15)+"%");
        vt_c16.setText(Float.toString(perc16)+"%");
        vt_c17.setText(Float.toString(perc17)+"%");
        vt_c18.setText(Float.toString(perc18)+"%");
        vt_c19.setText(Float.toString(perc19)+"%");
        vt_c20.setText(Float.toString(perc20)+"%");
        vt_c21.setText(Float.toString(perc21)+"%");
        vt_c22.setText(Float.toString(perc22)+"%");
        vt_c23.setText(Float.toString(perc23)+"%");
        vt_c24.setText(Float.toString(perc24)+"%");
        vt_c25.setText(Float.toString(perc25)+"%");
        vt_c26.setText(Float.toString(perc26)+"%");
        vt_c27.setText(Float.toString(perc27)+"%");
        vt_c28.setText(Float.toString(perc28)+"%");
        vt_c29.setText(Float.toString(perc29)+"%");
        vt_c30.setText(Float.toString(perc30)+"%");
        vt_c31.setText(Float.toString(perc31)+"%");
        vt_c32.setText(Float.toString(perc32)+"%");
        vt_c33.setText(Float.toString(perc33)+"%");
        vt_c34.setText(Float.toString(perc34)+"%");
        vt_c35.setText(Float.toString(perc35)+"%");
        vt_c36.setText(Float.toString(perc36)+"%");
        vt_c37.setText(Float.toString(perc37)+"%");
        vt_c38.setText(Float.toString(perc38)+"%");
        vt_c39.setText(Float.toString(perc39)+"%");
        vt_c40.setText(Float.toString(perc40)+"%");
        vt_c41.setText(Float.toString(perc41)+"%");
        vt_c42.setText(Float.toString(perc42)+"%");
        vt_c43.setText(Float.toString(perc43)+"%");
        vt_c44.setText(Float.toString(perc44)+"%");
        vt_c45.setText(Float.toString(perc45)+"%");
        vt_c46.setText(Float.toString(perc46)+"%");
        vt_c47.setText(Float.toString(perc47)+"%");

        if (perc1 >= 75){
            vt_c1.setBackgroundColor(Color.GREEN);
        }if (perc2 >= 75){
            vt_c2.setBackgroundColor(Color.GREEN);
        }if (perc3 >= 75){
            vt_c3.setBackgroundColor(Color.GREEN);
        }if (perc4 >= 75){
            vt_c4.setBackgroundColor(Color.GREEN);
        }if (perc5 >= 75){
            vt_c5.setBackgroundColor(Color.GREEN);
        }if (perc6 >= 75){
            vt_c6.setBackgroundColor(Color.GREEN);
        }if (perc7 >= 75){
            vt_c7.setBackgroundColor(Color.GREEN);
        }if (perc8 >= 75){
            vt_c8.setBackgroundColor(Color.GREEN);
        }if (perc9 >= 75){
            vt_c9.setBackgroundColor(Color.GREEN);
        }if (perc10 >= 75){
            vt_c10.setBackgroundColor(Color.GREEN);
        }if (perc11 >= 75){
            vt_c11.setBackgroundColor(Color.GREEN);
        }if (perc12 >= 75){
            vt_c12.setBackgroundColor(Color.GREEN);
        }if (perc13 >= 75){
            vt_c13.setBackgroundColor(Color.GREEN);
        }if (perc14 >= 75){
            vt_c14.setBackgroundColor(Color.GREEN);
        }if (perc15 >= 75){
            vt_c15.setBackgroundColor(Color.GREEN);
        }if (perc16 >= 75){
            vt_c16.setBackgroundColor(Color.GREEN);
        }if (perc17 >= 75){
            vt_c17.setBackgroundColor(Color.GREEN);
        }if (perc18 >= 75){
            vt_c18.setBackgroundColor(Color.GREEN);
        }if (perc19 >= 75){
            vt_c19.setBackgroundColor(Color.GREEN);
        }if (perc20 >= 75){
            vt_c20.setBackgroundColor(Color.GREEN);
        }if (perc21 >= 75){
            vt_c21.setBackgroundColor(Color.GREEN);
        }if (perc22 >= 75){
            vt_c22.setBackgroundColor(Color.GREEN);
        }if (perc23 >= 75){
            vt_c23.setBackgroundColor(Color.GREEN);
        }if (perc24 >= 75){
            vt_c24.setBackgroundColor(Color.GREEN);
        }if (perc25 >= 75){
            vt_c25.setBackgroundColor(Color.GREEN);
        }if (perc26 >= 75){
            vt_c26.setBackgroundColor(Color.GREEN);
        }if (perc27 >= 75){
            vt_c27.setBackgroundColor(Color.GREEN);
        }if (perc28 >= 75){
            vt_c28.setBackgroundColor(Color.GREEN);
        }if (perc29 >= 75){
            vt_c29.setBackgroundColor(Color.GREEN);
        }if (perc30 >= 75){
            vt_c30.setBackgroundColor(Color.GREEN);
        }if (perc31 >= 75){
            vt_c31.setBackgroundColor(Color.GREEN);
        }if (perc32 >= 75){
            vt_c32.setBackgroundColor(Color.GREEN);
        }if (perc33 >= 75){
            vt_c33.setBackgroundColor(Color.GREEN);
        }if (perc34 >= 75){
            vt_c34.setBackgroundColor(Color.GREEN);
        }if (perc35 >= 75){
            vt_c35.setBackgroundColor(Color.GREEN);
        }if (perc36 >= 75){
            vt_c36.setBackgroundColor(Color.GREEN);
        }if (perc37 >= 75){
            vt_c37.setBackgroundColor(Color.GREEN);
        }if (perc38 >= 75){
            vt_c38.setBackgroundColor(Color.GREEN);
        }if (perc39 >= 75){
            vt_c39.setBackgroundColor(Color.GREEN);
        }if (perc40 >= 75){
            vt_c40.setBackgroundColor(Color.GREEN);
        }if (perc41 >= 75){
            vt_c41.setBackgroundColor(Color.GREEN);
        }if (perc42 >= 75){
            vt_c42.setBackgroundColor(Color.GREEN);
        }if (perc43 >= 75){
            vt_c43.setBackgroundColor(Color.GREEN);
        }if (perc44 >= 75){
            vt_c44.setBackgroundColor(Color.GREEN);
        }if (perc45 >= 75){
            vt_c45.setBackgroundColor(Color.GREEN);
        }if (perc46 >= 75){
            vt_c46.setBackgroundColor(Color.GREEN);
        }if (perc47 >= 75){
            vt_c47.setBackgroundColor(Color.GREEN);
        }
    }

    public void onNext (View view){
        startActivity(new Intent(this,assessment_feedback.class));
    }
}
