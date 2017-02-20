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

import com.google.gson.JsonObject;
import com.wedevol.smartclass.R;
import com.wedevol.smartclass.activities.ListFacultyActivity;
import com.wedevol.smartclass.activities.ListUniversityActivity;
import com.wedevol.smartclass.models.User;
import com.wedevol.smartclass.utils.SharedPreferencesManager;
import com.wedevol.smartclass.utils.interfaces.Constants;
import com.wedevol.smartclass.utils.retrofit.IClassCallback;
import com.wedevol.smartclass.utils.retrofit.RestClient;

import retrofit.client.Response;

/** Created by paolo on 12/17/16.*/
public class SuggestCourseDialogFragment extends DialogFragment {
    private int universityId = -1;
    private int facultyId = -1;
    TextView tv_dialog_university;
    TextView tv_dialog_faculty;

    public SuggestCourseDialogFragment(){

    }

    public static SuggestCourseDialogFragment newInstance(int layoutShowedId) {
        Bundle args = new Bundle();
        args.putInt(Constants.BUNDLE_LAYOUT_ID, layoutShowedId);

        SuggestCourseDialogFragment fragment = new SuggestCourseDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        int layoutShowedId = getArguments().getInt(Constants.BUNDLE_LAYOUT_ID);

        final View view = inflater.inflate(layoutShowedId/*R.layout.dialog_suggest_course*/, null);

        tv_dialog_university = (TextView) view.findViewById(R.id.tv_dialog_university);
        tv_dialog_faculty = (TextView) view.findViewById(R.id.tv_dialog_faculty);
        tv_dialog_faculty.setEnabled(false);

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
                intent.putExtra(Constants.BUNDLE_UNIVERSITY_ID, universityId);
                startActivityForResult(intent, Constants.CHOOSEN_FACULTY);
            }
        });
        builder.setView(view)
                .setPositiveButton(R.string.suggest, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(final DialogInterface dialog, int id) {
                        TextView tv_dialog_course_name = (TextView) view.findViewById(R.id.tv_dialog_course_name);
                        String courseName = tv_dialog_course_name.getText().toString();
                        if(!courseName.isEmpty()){
                            User user = SharedPreferencesManager.getInstance(getActivity()).getUserInfo();

                            JsonObject jsonObject = new JsonObject();
                            jsonObject.addProperty("name", courseName);
                            jsonObject.addProperty("description", courseName);
                            jsonObject.addProperty("facultyId", facultyId);
                            jsonObject.addProperty("universityId", universityId);
                            jsonObject.addProperty("userType", SharedPreferencesManager.getInstance(getActivity()).getUserType()? "instructor":"student");
                            jsonObject.addProperty("userId", user.getId());

                            RestClient restClient = new RestClient(getActivity());
                            restClient.getWebservices().suggestCourse(user.getAccessToken(), jsonObject, new IClassCallback<JsonObject>(getActivity()){
                                @Override
                                public void success(JsonObject jsonObject, Response response) {
                                    super.success(jsonObject, response);
                                    dialog.dismiss();
                                }
                            });
                        }
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
            tv_dialog_faculty.setEnabled(true);
            tv_dialog_university.setText(universityName);
        }

        if((requestCode == Constants.CHOOSEN_FACULTY) && (resultCode == Activity.RESULT_OK)) {
            facultyId = data.getIntExtra(Constants.BUNDLE_FACULTY_ID, -1);
            String facultyName = data.getStringExtra(Constants.BUNDLE_FACULTY_NAME);
            tv_dialog_faculty.setText(facultyName);
        }
    }
}