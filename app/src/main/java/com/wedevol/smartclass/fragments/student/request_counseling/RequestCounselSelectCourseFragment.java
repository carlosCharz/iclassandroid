package com.wedevol.smartclass.fragments.student.request_counseling;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.wedevol.smartclass.R;
import com.wedevol.smartclass.activities.student.RequestCounselActivity;
import com.wedevol.smartclass.activities.ListCoursesActivity;
import com.wedevol.smartclass.utils.interfaces.Constants;

/** Created by paolorossi on 12/9/16.*/
public class RequestCounselSelectCourseFragment extends Fragment{
    private Button b_next;
    //private Button b_suggest_new_course;
    private TextView tv_pick_course;

    public RequestCounselSelectCourseFragment() {

    }

    public static Fragment newInstance() {
        RequestCounselSelectCourseFragment requestCounselSelectCourseFragment = new RequestCounselSelectCourseFragment();
        Bundle args = new Bundle();
        //args.putBoolean("isInstructor", isInstructor);
        requestCounselSelectCourseFragment.setArguments(args);

        return requestCounselSelectCourseFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_request_counsel_select_course, container, false);

        setupElements(view);
        setupActions();
        return view;
    }

    private void setupElements(View view) {
        ((RequestCounselActivity)getActivity()).setToolbarTitle("Seleccionar Curso");

        tv_pick_course = (TextView) view.findViewById(R.id.tv_pick_course);
        b_next = (Button) view.findViewById(R.id.b_next);

        if(((RequestCounselActivity)getActivity()).getCourse() == null){
            b_next.setEnabled(false);
        }else{
            tv_pick_course.setText(((RequestCounselActivity)getActivity()).getCourse().getName());
        }
        //b_suggest_new_course = (Button) view.findViewById(R.id.b_suggest_new_course);
    }

    private void setupActions() {
        tv_pick_course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ListCoursesActivity.class);
                intent.putExtra(Constants.STUDENT_COURSE_TYPE, true);
                startActivityForResult(intent, Constants.CHOOSEN_COURSE);
            }
        });

        b_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.rl_request_counseling_holder, RequestCounselSelectScheduleFragment.newInstance())
                        .addToBackStack(null)
                        .commit();
            }
        });

        ((RequestCounselActivity)getActivity()).setToolbarBackButtonAction(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });

        /*b_suggest_new_course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment newFragment = new ChangePriceDialogFragment();
                newFragment.show(getActivity().getSupportFragmentManager(), "Sugerir Curso");
            }
        });*/
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if((requestCode == Constants.CHOOSEN_COURSE) && (resultCode == Activity.RESULT_OK)) {
            int courseId = data.getIntExtra(Constants.BUNDLE_COURSE_ID, -1);
            String courseName = data.getStringExtra(Constants.BUNDLE_COURSE_NAME);
            ((RequestCounselActivity)getActivity()).saveCourse(courseId, courseName);
            tv_pick_course.setText(courseName);
            b_next.setEnabled(true);
        }
    }
}