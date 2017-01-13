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
    private ImageView iv_toolbar_back;
    private Course course = null;
    private String dateName = null;
    private String beginTime = null;
    private String endTime = null;
    private Instructor instructor = null;
    private String strDate;
    private String strDateName;

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
        String [] dateAndDatename = dateName.split("-");
        this.strDate = dateAndDatename[1].trim();
        switch(dateAndDatename[0].trim()){
            case "lunes":
                this.strDateName = "mon";
                break;
            case "martes":
                this.strDateName = "tue";
                break;
            case "miércoles":
                this.strDateName = "wed";
                break;
            case "jueves":
                this.strDateName = "thu";
                break;
            case "viernes":
                this.strDateName = "fri";
                break;
            case "sábado":
                this.strDateName = "sat";
                break;
            case "domingo":
                this.strDateName = "sun";
                break;
        }
    }

    public void saveScheduleTimes(String beginTime, String endTime) {
        this.beginTime = beginTime;
        this.endTime = endTime;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public String getInstructorName() {

        return instructor.getName();
    }

    public String getInstructorHourlyRate() {
        return ""+instructor.getHourlyRate();
    }

    public int getInstructorId() {
        return instructor.getId();
    }

    public String getDateName() {
        return dateName;
    }

    public String getDate() {
        return strDate;
    }

    public String getWeekDayName() {
        return strDateName;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public Course getCourse() {
        return course;
    }
}