package com.wedevol.smartclass.utils.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.wedevol.smartclass.R;
import com.wedevol.smartclass.activities.ListFacultyActivity;
import com.wedevol.smartclass.activities.ListUniversityActivity;
import com.wedevol.smartclass.utils.interfaces.Constants;
import com.wedevol.smartclass.utils.interfaces.SearchedCoursesListener;

/** Created by paolo on 1/29/17.*/
public class GetFacultyandUniversityDialogFragment extends DialogFragment {
    private int universityId = -1;
    private int facultyId = -1;
    TextView tv_dialog_university;
    TextView tv_dialog_faculty;

    public GetFacultyandUniversityDialogFragment(){

    }

    public static GetFacultyandUniversityDialogFragment newInstance(SearchedCoursesListener searchedCoursesListener) {
        Bundle args = new Bundle();
        args.putParcelable(Constants.BUNDLE_SEARCHED_COURSES_LISTENER, searchedCoursesListener);

        GetFacultyandUniversityDialogFragment fragment = new GetFacultyandUniversityDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        final SearchedCoursesListener searchedCoursesListener = getArguments().getParcelable(Constants.BUNDLE_SEARCHED_COURSES_LISTENER);

        View view = inflater.inflate(R.layout.dialog_get_faculty_and_university, null);
        tv_dialog_university = (TextView) view.findViewById(R.id.tv_dialog_university);
        tv_dialog_faculty = (TextView) view.findViewById(R.id.tv_dialog_faculty);

        tv_dialog_university.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ListUniversityActivity.class);
                startActivityForResult(intent, Constants.CHOOSEN_UNIVERSITY);
            }
        });

        tv_dialog_faculty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ListFacultyActivity.class);
                startActivityForResult(intent, Constants.CHOOSEN_FACULTY);
            }
        });

        builder.setView(view)
                // Add action buttons
                .setPositiveButton(R.string.suggest, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(final DialogInterface dialog, int id) {
                        assert searchedCoursesListener != null;
                        searchedCoursesListener.onCourseSearched(universityId, facultyId);
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        return builder.create();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if((requestCode == Constants.CHOOSEN_UNIVERSITY) && (resultCode == Activity.RESULT_OK)) {

            universityId = data.getIntExtra(Constants.BUNDLE_UNIVERSITY_ID, -1);
            String universityName = data.getStringExtra(Constants.BUNDLE_UNIVERSITY_NAME);
            tv_dialog_university.setText(universityName);
        }

        if((requestCode == Constants.CHOOSEN_FACULTY) && (resultCode == Activity.RESULT_OK)) {
            facultyId = data.getIntExtra(Constants.BUNDLE_FACULTY_ID, -1);
            String facultyName = data.getStringExtra(Constants.BUNDLE_FACULTY_NAME);
            tv_dialog_faculty.setText(facultyName);
        }


    }

}