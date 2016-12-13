package com.wedevol.smartclass.activities.student;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.wedevol.smartclass.R;
import com.wedevol.smartclass.fragments.student.request_counseling.RequestCounselSelectCourseFragment;
import com.wedevol.smartclass.models.Course;
import com.wedevol.smartclass.models.Schedule;

/** Created by paolorossi on 12/9/16.*/
public class RequestCounselActivity extends AppCompatActivity {
    Course course;
    Schedule schedule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_counseling);
        setupElements();
        setupActions();
    }

    private void setupElements() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.rl_request_counseling_holder, RequestCounselSelectCourseFragment.newInstance());
        fragmentTransaction.commit();
    }

    private void setupActions() {
    }

    public void setToolbarTitle(String title) {
        TextView tv_detail_title = (TextView) findViewById(R.id.tv_detail_title);
        tv_detail_title.setText(title);

    }

    /**
     * This data is saved so the activity holds all the variables withouth need to pass them over all the flux
     */
    public void saveCourse(int courseId, String courseName) {
        course = new Course();
        course.setId(courseId);
        course.setName(courseName);
    }

    public void saveSchedule(int courseId, String courseName) {
        schedule = new Schedule();
        course.setId(courseId);
        course.setName(courseName);
    }
}