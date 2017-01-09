package com.wedevol.smartclass.fragments.instructor;

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

import com.wedevol.smartclass.R;
import com.wedevol.smartclass.activities.HomeActivity;
import com.wedevol.smartclass.models.Instructor;
import com.wedevol.smartclass.utils.SharedPreferencesManager;
import com.wedevol.smartclass.utils.UtilMethods;
import com.wedevol.smartclass.utils.interfaces.Constants;

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

        ProgressBar pb_counselor_progress = (ProgressBar) view.findViewById(R.id.pb_counselor_progress);
        TextView tv_counselor_counseling_time = (TextView) view.findViewById(R.id.tv_counselor_counseling_time);
        TextView tv_counselor_profile_type = (TextView) view.findViewById(R.id.tv_counselor_profile_type);
        TextView tv_counselor_profile_number = (TextView) view.findViewById(R.id.tv_counselor_profile_number);
        TextView tv_counselor_profile_email = (TextView) view.findViewById(R.id.tv_counselor_profile_email);
        TextView tv_counselor_profile_courses_afilliated = (TextView) view.findViewById(R.id.tv_counselor_profile_courses_afilliated);
        TextView tv_counselor_profile_time_teaching = (TextView) view.findViewById(R.id.tv_counselor_profile_time_teaching);
        fab_edit_profile = (FloatingActionButton) view.findViewById(R.id.fab_edit_profile);

        tv_counselor_rating_number.setText(""+ instructor.getRating());

        UtilMethods.setPhoto(getActivity(), iv_user_profile_photo, instructor.getProfilePictureUrl(), Constants.USER_PHOTO);
        tv_counselor_level.setText("Nivel "+ instructor.getLevel());
        pb_counselor_progress.setProgress(((Double) instructor.getRating()).intValue());
        tv_counselor_counseling_time.setText(instructor.getTotalHours() + " hrs");
        tv_counselor_profile_type.setText("Instructor");
        tv_counselor_profile_number.setText(instructor.getPhone());
        tv_counselor_profile_email.setText(instructor.getEmail());
        tv_counselor_profile_courses_afilliated.setText(instructor.getEnrolledCourses().size()+" cursos");
        tv_counselor_profile_time_teaching.setText(instructor.getTotalHours() + " hrs");
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