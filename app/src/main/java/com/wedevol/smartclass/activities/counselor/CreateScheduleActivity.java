package com.wedevol.smartclass.activities.counselor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.wedevol.smartclass.R;
import com.wedevol.smartclass.activities.student.RequestCounselActivity;
import com.wedevol.smartclass.utils.interfaces.Constants;

/** Created by paolorossi on 12/14/16.*/
public class CreateScheduleActivity extends AppCompatActivity {
    private String weekOfDay;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_schedule);
        setElements();
        setActions();
    }

    private void setElements() {

    }

    private void setActions() {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if((requestCode == Constants.CHOOSEN_DATE) && (resultCode == Activity.RESULT_OK)) {
            weekOfDay = data.getStringExtra(Constants.BUNDLE_DATE);
        }
    }
}