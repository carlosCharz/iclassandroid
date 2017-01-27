package com.wedevol.smartclass.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.wedevol.smartclass.R;
import com.wedevol.smartclass.models.User;
import com.wedevol.smartclass.utils.SharedPreferencesManager;
import com.wedevol.smartclass.utils.UtilMethods;
import com.wedevol.smartclass.utils.interfaces.Constants;

/** Created by paolo on 1/27/17.*/
public class EditProfileActivity  extends AppCompatActivity {
    FloatingActionButton fab_finish_edit_profile;
    boolean isInstructor;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        setupElements();
        setupActions();
    }

    private void setupElements() {
        isInstructor = SharedPreferencesManager.getInstance(this).getUserType();
        user = SharedPreferencesManager.getInstance(this).getUserInfo();


        ImageView iv_user_profile_photo = (ImageView)  findViewById(R.id.iv_user_profile_photo);
        TextView tv_user_level = (TextView) findViewById(R.id.tv_user_level);
        ProgressBar pb_user_progress = (ProgressBar) findViewById(R.id.pb_user_progress);
        TextView tv_user_counselled_time = (TextView) findViewById(R.id.tv_user_counselled_time);
        TextView tv_user_profile_type = (TextView) findViewById(R.id.tv_user_profile_type);
        EditText tv_user_profile_number = (EditText) findViewById(R.id.et_user_profile_number);
        TextView tv_user_profile_email = (TextView) findViewById(R.id.tv_user_profile_email);
        TextView tv_user_faculty = (TextView) findViewById(R.id.tv_user_faculty);
        TextView tv_user_university = (TextView) findViewById(R.id.tv_user_university);
        fab_finish_edit_profile = (FloatingActionButton) findViewById(R.id.fab_finish_edit_profile);

        UtilMethods.setPhoto(this, iv_user_profile_photo, user.getProfilePictureUrl(), Constants.USER_PHOTO);
        tv_user_level.setText("Nivel "+ user.getLevel());
        pb_user_progress.setProgress(((Double)user.getRating()).intValue());
        tv_user_counselled_time.setText(user.getTotalHours() + " hrs");
        tv_user_profile_type.setText(isInstructor?"Instructor":"Alumno");
        tv_user_profile_number.setText(user.getPhone());
        tv_user_profile_email.setText(user.getEmail());
        tv_user_faculty.setText("None");
        tv_user_university.setText(user.getUniversity());
    }

    private void setupActions() {
        fab_finish_edit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
