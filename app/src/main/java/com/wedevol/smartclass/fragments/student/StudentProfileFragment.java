package com.wedevol.smartclass.fragments.student;

import android.graphics.Color;
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
import com.wedevol.smartclass.activities.HomeActivity;
import com.wedevol.smartclass.models.Student;
import com.wedevol.smartclass.utils.SharedPreferencesManager;
import com.wedevol.smartclass.utils.UtilMethods;
import com.wedevol.smartclass.utils.interfaces.Constants;
import com.wedevol.smartclass.utils.retrofit.IClassCallback;
import com.wedevol.smartclass.utils.retrofit.RestClient;

import retrofit.client.Response;

/** Created by paolorossi on 12/8/16.*/
public class StudentProfileFragment extends Fragment{
    ImageView iv_user_profile_photo;
    TextView tv_student_level;
    ProgressBar pb_student_progress;
    TextView tv_student_counselled_time;
    TextView tv_student_profile_type;
    TextView tv_student_profile_number;
    TextView tv_student_profile_email;
    TextView tv_student_profile_courses_taken;
    TextView tv_student_profile_time;
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

        iv_user_profile_photo = (ImageView)  view.findViewById(R.id.iv_user_profile_photo);
        tv_student_level = (TextView) view.findViewById(R.id.tv_student_level);
        pb_student_progress = (ProgressBar) view.findViewById(R.id.pb_student_progress);
        tv_student_counselled_time = (TextView) view.findViewById(R.id.tv_student_counselled_time);
        tv_student_profile_type = (TextView) view.findViewById(R.id.tv_student_profile_type);
        tv_student_profile_number = (TextView) view.findViewById(R.id.tv_student_profile_number);
        tv_student_profile_email = (TextView) view.findViewById(R.id.tv_student_profile_email);
        tv_student_profile_courses_taken = (TextView) view.findViewById(R.id.tv_student_profile_courses_taken);
        tv_student_profile_time = (TextView) view.findViewById(R.id.tv_student_profile_time);
        fab_edit_profile = (FloatingActionButton) view.findViewById(R.id.fab_edit_profile);

        UtilMethods.setPhoto(getActivity(), iv_user_profile_photo, student.getProfilePictureUrl(), Constants.USER_PHOTO);
        tv_student_level.setText("Nivel "+ student.getLevel());
        pb_student_progress.setProgress(((Double)student.getRating()).intValue());
        tv_student_counselled_time.setText(student.getTotalHours() + " hrs");
        tv_student_profile_type.setText("Alumno");
        tv_student_profile_number.setText(student.getPhone());
        tv_student_profile_email.setText(student.getEmail());
        tv_student_profile_courses_taken.setText(0+" cursos");
        tv_student_profile_time.setText(student.getTotalHours() + " hrs");

        RestClient restClient = new RestClient(getContext());
        restClient.getWebservices().getStudentCourses("",
                SharedPreferencesManager.getInstance(getActivity()).getUserInfo().getId(), "payed",
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
                UtilMethods.showSnackbar(((HomeActivity)getActivity()).getMainLayout(), "Funcion aun no implementada", Color.YELLOW, "REDO", Color.MAGENTA);
            }
        });
    }
}