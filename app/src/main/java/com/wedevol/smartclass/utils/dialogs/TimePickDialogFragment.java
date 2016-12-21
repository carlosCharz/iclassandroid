package com.wedevol.smartclass.utils.dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.wedevol.smartclass.R;
import com.wedevol.smartclass.utils.interfaces.Constants;

/** Created by paolo on 12/21/16.*/

public class TimePickDialogFragment  extends DialogFragment {
    public TimePickDialogFragment(){

    }

    public static TimePickDialogFragment newInstance(EditText et_time) {
        Bundle args = new Bundle();
        args.putSerializable(Constants.BUNDLE_LAYOUT_ID, et_time.getId());

        TimePickDialogFragment fragment = new TimePickDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        final int layoutShowedId = getArguments().getInt(Constants.BUNDLE_LAYOUT_ID);

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        View v = inflater.inflate(R.layout.dialog_time_picker, null);

        final NumberPicker np_hour_picker = (NumberPicker) v.findViewById(R.id.np_hour_picker);
        np_hour_picker.setMaxValue(22);
        np_hour_picker.setMinValue(6);

        NumberPicker np_minute_picker = (NumberPicker) v.findViewById(R.id.np_minute_picker);
        np_minute_picker.setMinValue(1);
        np_minute_picker.setMaxValue(2);
        np_minute_picker.setDisplayedValues(new String[]{"0","30"});

        builder.setView(v).setPositiveButton(R.string.suggest, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // TODO this should send a message to our services for suggesting the new course
                        TextView tv_dialog_time = (TextView) getActivity().findViewById(layoutShowedId);
                        tv_dialog_time.setText(""+np_hour_picker.getValue());
                        dialog.dismiss();
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