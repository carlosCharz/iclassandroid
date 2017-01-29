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
import com.wedevol.smartclass.utils.SharedPreferencesManager;
import com.wedevol.smartclass.utils.interfaces.Constants;
import com.wedevol.smartclass.utils.interfaces.PriceChangeListener;
import com.wedevol.smartclass.utils.retrofit.IClassCallback;
import com.wedevol.smartclass.utils.retrofit.RestClient;

import retrofit.client.Response;

/** Created by paolorossi on 12/10/16.*/
public class ChangePriceDialogFragment extends DialogFragment {
    public ChangePriceDialogFragment(){

    }

    public static ChangePriceDialogFragment newInstance(int layoutShowedId, int courseId, String currency,
                                                        String status, int position, PriceChangeListener priceChangeListener) {
        Bundle args = new Bundle();
        args.putInt(Constants.BUNDLE_LAYOUT_ID, layoutShowedId);
        args.putInt(Constants.BUNDLE_COURSE_ID, courseId);
        args.putString(Constants.BUNDLE_CURRENCY, currency);
        args.putString(Constants.BUNDLE_STATUS, status);
        args.putInt(Constants.BUNDLE_POSITION, position);
        args.putParcelable(Constants.BUNDLE_PRICE_CHANGE_LISTENER, priceChangeListener);


        ChangePriceDialogFragment fragment = new ChangePriceDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final RestClient restClient = new RestClient(getActivity());
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        int layoutShowedId = getArguments().getInt(Constants.BUNDLE_LAYOUT_ID);
        final int courseId = getArguments().getInt(Constants.BUNDLE_COURSE_ID);
        final String currency = getArguments().getString(Constants.BUNDLE_CURRENCY);
        final String status = getArguments().getString(Constants.BUNDLE_STATUS);
        final int position = getArguments().getInt(Constants.BUNDLE_POSITION);
        final PriceChangeListener priceChangeListener = getArguments().getParcelable(Constants.BUNDLE_PRICE_CHANGE_LISTENER);

        View view = inflater.inflate(layoutShowedId/*R.layout.dialog_suggest_course*/, null);
        final TextView tv_dialog_course_price = (TextView) view.findViewById(R.id.tv_dialog_course_price);

        builder.setView(view)
                // Add action buttons
                .setPositiveButton(R.string.suggest, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(final DialogInterface dialog, int id) {
                        String course_price = tv_dialog_course_price.getText().toString();

                        if(!course_price.isEmpty()) {
                            final int instructorPrice = Integer.parseInt(course_price);
                            int instructorId = SharedPreferencesManager.getInstance(getActivity()).getUserInfo().getId();

                            JsonObject jsonCourse = new JsonObject();

                            JsonObject jsonEnrollmentId = new JsonObject();
                            jsonEnrollmentId.addProperty("courseId", courseId);
                            jsonEnrollmentId.addProperty("instructorId", instructorId);
                            jsonCourse.add("instructorEnrollmentId",jsonEnrollmentId);

                            JsonObject jsonId = new JsonObject();
                            jsonId.addProperty("courseId", courseId);
                            jsonId.addProperty("instructorId", instructorId);
                            jsonCourse.add("id",jsonId);

                            jsonCourse.addProperty("price", instructorPrice);
                            jsonCourse.addProperty("currency", currency);
                            jsonCourse.addProperty("status", status);

                            restClient.getWebservices().updateInstructorCourse("", instructorId, courseId, jsonCourse,
                                    new IClassCallback<JsonObject>(getActivity()) {
                                @Override
                                public void success(JsonObject jsonObject, Response response) {
                                    super.success(jsonObject, response);
                                    assert priceChangeListener != null;
                                    priceChangeListener.onPriceChanged(position, instructorPrice);
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