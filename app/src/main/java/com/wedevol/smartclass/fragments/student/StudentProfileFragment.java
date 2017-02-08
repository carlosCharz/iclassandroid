package com.wedevol.smartclass.fragments.student;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.wedevol.smartclass.R;
import com.wedevol.smartclass.activities.EditProfileActivity;
import com.wedevol.smartclass.models.Student;
import com.wedevol.smartclass.utils.SharedPreferencesManager;
import com.wedevol.smartclass.utils.UtilMethods;
import com.wedevol.smartclass.utils.interfaces.Constants;
import com.wedevol.smartclass.utils.retrofit.IClassCallback;
import com.wedevol.smartclass.utils.retrofit.RestClient;

import retrofit.client.Response;

/** Created by paolorossi on 12/8/16.*/
public class StudentProfileFragment extends Fragment{
    private TextView tv_student_profile_courses_taken;
    FloatingActionButton fab_edit_profile;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_student_profile, container, false);
        setElements(view);
        setActions();
        return view;
    }

    private void setElements(View view){
        Student student = (Student) SharedPreferencesManager.getInstance(getActivity()).getUserInfo();

        ImageView iv_user_profile_photo = (ImageView) view.findViewById(R.id.iv_user_profile_photo);
        TextView tv_student_level = (TextView) view.findViewById(R.id.tv_student_level);
        ProgressBar pb_student_progress = (ProgressBar) view.findViewById(R.id.pb_student_progress);
        TextView tv_student_counselled_time = (TextView) view.findViewById(R.id.tv_student_counselled_time);
        TextView tv_student_profile_type = (TextView) view.findViewById(R.id.tv_student_profile_type);
        TextView tv_student_profile_number = (TextView) view.findViewById(R.id.tv_student_profile_number);
        TextView tv_student_profile_email = (TextView) view.findViewById(R.id.tv_student_profile_email);
        tv_student_profile_courses_taken = (TextView) view.findViewById(R.id.tv_student_profile_courses_taken);
        TextView tv_student_profile_time = (TextView) view.findViewById(R.id.tv_student_profile_time);
        fab_edit_profile = (FloatingActionButton) view.findViewById(R.id.fab_edit_profile);
        TextView tv_user_faculty = (TextView) view.findViewById(R.id.tv_user_faculty);
        TextView tv_user_university = (TextView) view.findViewById(R.id.tv_user_university);

        UtilMethods.setPhoto(getActivity(), iv_user_profile_photo, student.getProfilePictureUrl(), Constants.USER_PHOTO);
        tv_student_level.setText("Nivel "+ student.getLevel());
        pb_student_progress.setProgress( (student.getTotalHours()/10) * 100);
        tv_student_counselled_time.setText(student.getTotalHours() + " hrs");
        tv_student_profile_type.setText("Alumno");
        tv_student_profile_number.setText(student.getPhone());
        tv_student_profile_email.setText(student.getEmail());
        tv_student_profile_courses_taken.setText(0+" cursos");
        tv_student_profile_time.setText(student.getTotalHours() + " hrs");
        tv_user_faculty.setText(student.getFacultyName());
        tv_user_university.setText(student.getUniversityName());

        RestClient restClient = new RestClient(getContext());
        restClient.getWebservices().getStudentCourses("",
                SharedPreferencesManager.getInstance(getActivity()).getUserInfo().getId(), "payed,free",
                new IClassCallback<JsonArray>(getActivity()){
                    @Override
                    public void success(JsonArray jsonArray, Response response) {
                        super.success(jsonArray, response);
                        if(jsonArray.size()>0){
                            if(jsonArray.size() == 1 )
                                tv_student_profile_courses_taken.setText(jsonArray.size()+" curso");
                            else
                                tv_student_profile_courses_taken.setText(jsonArray.size()+" cursos");
                        }
                    }
                });
    }

    private void setActions() {
        fab_edit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), EditProfileActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if((requestCode == Constants.EDITED_PROFILE) && (resultCode == Activity.RESULT_OK)) {
            setElements(getView());
        }
    }
}