package com.example.user.trialseven;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class home_user extends AppCompatActivity {

    TextView vt_status;
    Button btn_to_employee, btn_to_employer;
    Button btn_assessment, btn_view_all_jobs, btn_search_for_job;
    Button btn_view_my_posts, btn_post_a_job;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_user);

        vt_status = (TextView)findViewById(R.id.vt_status);
        vt_status.setVisibility(View.VISIBLE);
        vt_status.setText("EMPLOYEE");

        btn_to_employee = (Button)findViewById(R.id.btn_to_employee);
        btn_to_employee.setVisibility(View.INVISIBLE);

        btn_to_employer = (Button)findViewById(R.id.btn_to_employer);
        btn_to_employer.setVisibility(View.VISIBLE);

        btn_assessment = (Button)findViewById(R.id.btn_assessment);
        btn_assessment.setVisibility(View.VISIBLE);

        btn_view_all_jobs = (Button)findViewById(R.id.btn_view_all_jobs);
        btn_view_all_jobs.setVisibility(View.VISIBLE);

        btn_search_for_job = (Button)findViewById(R.id.btn_search_for_job);
        btn_search_for_job.setVisibility(View.VISIBLE);

        btn_view_my_posts = (Button)findViewById(R.id.btn_view_my_posts);
        btn_view_my_posts.setVisibility(View.INVISIBLE);

        btn_post_a_job = (Button)findViewById(R.id.btn_post_a_job);
        btn_post_a_job.setVisibility(View.INVISIBLE);

    }

    public void onAssessment (View view){
        startActivity(new Intent(this,assessment.class));
    }

    public void onPostAJob (View view){
        startActivity(new Intent(this,er_post.class));
    }

    public void onViewMyPosts (View view){
        startActivity(new Intent(this,er_view.class));
    }

    public void onViewAllJobs (View view){
        startActivity(new Intent(this,ee_view.class));
    }

    public void onSearchForJob (View view){
        startActivity(new Intent(this,ee_search.class));
    }

    public void onLogout (View view){
        startActivity(new Intent(this,start.class));
    }

    public void onToEmployer (View view){
        vt_status.setText("EMPLOYER");
        btn_to_employee.setVisibility(View.VISIBLE);
        btn_to_employer.setVisibility(View.INVISIBLE);
        btn_assessment.setVisibility(View.INVISIBLE);
        btn_view_all_jobs.setVisibility(View.INVISIBLE);
        btn_search_for_job.setVisibility(View.INVISIBLE);
        btn_view_my_posts.setVisibility(View.VISIBLE);
        btn_post_a_job.setVisibility(View.VISIBLE);
    }

    public void onToEmployee (View view){
        vt_status.setText("EMPLOYEE");
        btn_to_employee.setVisibility(View.INVISIBLE);
        btn_to_employer.setVisibility(View.VISIBLE);
        btn_assessment.setVisibility(View.VISIBLE);
        btn_view_all_jobs.setVisibility(View.VISIBLE);
        btn_search_for_job.setVisibility(View.VISIBLE);
        btn_view_my_posts.setVisibility(View.INVISIBLE);
        btn_post_a_job.setVisibility(View.INVISIBLE);
    }
}
