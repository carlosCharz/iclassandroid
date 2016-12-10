package com.wedevol.smartclass.activities.student;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.wedevol.smartclass.R;
import com.wedevol.smartclass.fragments.student.request_counseling.RequestCounselSelectCourseFragment;

/** Created by paolorossi on 12/9/16.*/
public class RequestCounselActivity extends AppCompatActivity {
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
}