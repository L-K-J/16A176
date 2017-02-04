package com.example.user.trialseven;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class home_admin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_admin);
    }
    public void onLogout (View view){
        startActivity(new Intent(this,start.class));
    }
    public void onManage (View view){
        startActivity(new Intent(this,ad_manage_quiz.class));
    }
}
