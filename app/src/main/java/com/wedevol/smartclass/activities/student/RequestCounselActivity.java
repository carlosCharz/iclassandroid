package com.wedevol.smartclass.activities.student;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wedevol.smartclass.R;
import com.wedevol.smartclass.fragments.student.request_counseling.RequestCounselSelectCourseFragment;
import com.wedevol.smartclass.models.Course;
import com.wedevol.smartclass.models.Instructor;

/** Created by paolorossi on 12/9/16.*/
public class RequestCounselActivity extends AppCompatActivity {
    private Course course;
    private ImageView iv_toolbar_back;
    private String dateName;
    private String beginTime;
    private String endTime;
    private Instructor instructor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_counseling);
        setupElements();
        setupActions();
    }

    private void setupElements() {
        iv_toolbar_back = (ImageView) findViewById(R.id.iv_toolbar_back);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.rl_request_counseling_holder, RequestCounselSelectCourseFragment.newInstance());
        fragmentTransaction.commit();
    }

    private void setupActions() {

    }

    @Override
    public void onBackPressed() {
        int count = getFragmentManager().getBackStackEntryCount();
        if (count == 0) {
            super.onBackPressed();
        } else {
            getFragmentManager().popBackStack();
        }
    }

    public void setToolbarTitle(String title) {
        TextView tv_detail_title = (TextView) findViewById(R.id.tv_detail_title);
        tv_detail_title.setText(title);
    }

    public void setToolbarBackButtonAction(View.OnClickListener onClickListener) {
        iv_toolbar_back.setOnClickListener(onClickListener);
    }

    /** This data is saved so the activity holds all the variables withouth need to pass them over all the flux*/
    public void saveCourse(int courseId, String courseName) {
        course = new Course();
        course.setId(courseId);
        course.setName(courseName);
    }

    public void saveSchedule(String dateName) {
        this.dateName = dateName;
    }

    public void saveScheduleTimes(String beginTime, String endTime) {
        this.beginTime = beginTime;
        this.endTime = endTime;
    }

    public void setCounsellor(Instructor instructor) {
        this.instructor = instructor;
    }

    public String getInstructorName() {

        return instructor.getName();
    }

    public String getInstructorHourlyRate() {
        return ""+instructor.getHourlyRate();
    }

    public String getCourseName() {
        return course.getName();
    }

    public String getDateName() {
        return dateName;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public String getEndTime() {
        return endTime;
    }
}