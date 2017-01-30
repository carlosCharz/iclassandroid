package com.wedevol.smartclass.utils.dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.wedevol.smartclass.R;
import com.wedevol.smartclass.models.User;
import com.wedevol.smartclass.utils.SharedPreferencesManager;
import com.wedevol.smartclass.utils.interfaces.Constants;
import com.wedevol.smartclass.utils.retrofit.IClassCallback;
import com.wedevol.smartclass.utils.retrofit.RestClient;

import retrofit.client.Response;

/** Created by paolo on 12/17/16.*/
public class SuggestCourseDialogFragment extends DialogFragment {
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

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        final View view = inflater.inflate(layoutShowedId/*R.layout.dialog_suggest_course*/, null);
        builder.setView(view)
                // Add action buttons
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
                            jsonObject.addProperty("facultyId", user.getFacultyId());
                            jsonObject.addProperty("universityId", user.getUniversityId());
                            if(SharedPreferencesManager.getInstance(getActivity()).getUserType()){
                                jsonObject.addProperty("userType", "instructor");
                            }else{
                                jsonObject.addProperty("userType", "student");
                            }
                            jsonObject.addProperty("userId", user.getId());

                            RestClient restClient = new RestClient(getActivity());
                            restClient.getWebservices().suggestCourse("", jsonObject, new IClassCallback<JsonObject>(getActivity()){
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
}