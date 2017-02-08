package com.wedevol.smartclass.fragments.instructor;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.wedevol.smartclass.R;
import com.wedevol.smartclass.activities.EditProfileActivity;
import com.wedevol.smartclass.models.Instructor;
import com.wedevol.smartclass.utils.SharedPreferencesManager;
import com.wedevol.smartclass.utils.UtilMethods;
import com.wedevol.smartclass.utils.interfaces.Constants;
import com.wedevol.smartclass.utils.retrofit.IClassCallback;
import com.wedevol.smartclass.utils.retrofit.RestClient;

import retrofit.client.Response;

/** Created by paolorossi on 12/8/16.*/
public class InstructorProfileFragment extends Fragment{
    FloatingActionButton fab_edit_profile;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_instructor_profile, container, false);
        setElements(view);
        setActions();
        return view;
    }

    private void setElements(View view){
        Instructor instructor = (Instructor) SharedPreferencesManager.getInstance(getActivity()).getUserInfo();

        ImageView iv_user_profile_photo = (ImageView)  view.findViewById(R.id.iv_user_profile_photo);
        TextView tv_counselor_level = (TextView) view.findViewById(R.id.tv_counselor_level);
        TextView tv_counselor_rating_number = (TextView) view.findViewById(R.id.tv_counselor_rating_number);
        RatingBar rb_counselor_rating_stars = (RatingBar) view.findViewById(R.id.rb_counselor_rating_stars);

        ProgressBar pb_counselor_progress = (ProgressBar) view.findViewById(R.id.pb_counselor_progress);
        TextView tv_counselor_counseling_time = (TextView) view.findViewById(R.id.tv_counselor_counseling_time);
        TextView tv_counselor_profile_type = (TextView) view.findViewById(R.id.tv_counselor_profile_type);
        TextView tv_counselor_profile_number = (TextView) view.findViewById(R.id.tv_counselor_profile_number);
        TextView tv_counselor_profile_email = (TextView) view.findViewById(R.id.tv_counselor_profile_email);
        final TextView tv_counselor_profile_courses_afilliated = (TextView) view.findViewById(R.id.tv_counselor_profile_courses_afilliated);
        TextView tv_counselor_profile_time_teaching = (TextView) view.findViewById(R.id.tv_counselor_profile_time_teaching);
        fab_edit_profile = (FloatingActionButton) view.findViewById(R.id.fab_edit_profile);
        TextView tv_user_faculty = (TextView) view.findViewById(R.id.tv_user_faculty);
        TextView tv_user_university = (TextView) view.findViewById(R.id.tv_user_university);

        tv_counselor_rating_number.setText(""+ instructor.getRating());

        Drawable progress = rb_counselor_rating_stars.getProgressDrawable();
        DrawableCompat.setTint(progress, Color.WHITE);

        UtilMethods.setPhoto(getActivity(), iv_user_profile_photo, instructor.getProfilePictureUrl(), Constants.USER_PHOTO);
        tv_counselor_level.setText("Nivel "+ instructor.getLevel());
        pb_counselor_progress.setProgress(instructor.getTotalHours()/10);
        tv_counselor_counseling_time.setText(instructor.getTotalHours() + " hrs");
        tv_counselor_profile_type.setText("Instructor");
        tv_counselor_profile_number.setText(instructor.getPhone());
        tv_counselor_profile_email.setText(instructor.getEmail());
        tv_counselor_profile_courses_afilliated.setText(0 +" cursos");
        tv_counselor_profile_time_teaching.setText(instructor.getTotalHours() + " hrs");
        tv_user_faculty.setText(instructor.getFacultyName());
        tv_user_university.setText(instructor.getUniversityName());
        rb_counselor_rating_stars.setRating((float)instructor.getRating());

        RestClient restClient = new RestClient(getContext());
        restClient.getWebservices().getInstructorCourses("", SharedPreferencesManager.getInstance(getActivity()).getUserInfo().getId(),
                "payed,free", new IClassCallback<JsonArray>(getActivity()){
                    @Override
                    public void success(JsonArray jsonArray, Response response) {
                        super.success(jsonArray, response);
                        if(jsonArray.size()>0){
                            if(jsonArray.size() == 1 )
                                tv_counselor_profile_courses_afilliated.setText(jsonArray.size()+" curso");
                            else
                                tv_counselor_profile_courses_afilliated.setText(jsonArray.size()+" cursos");
                        }
                    }
                });
    }

    private void setActions() {
        fab_edit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), EditProfileActivity.class);
                startActivityForResult(intent, Constants.EDITED_PROFILE);
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