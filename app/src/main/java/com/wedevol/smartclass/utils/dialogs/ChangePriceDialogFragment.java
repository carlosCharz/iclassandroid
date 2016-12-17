package com.wedevol.smartclass.utils.dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.wedevol.smartclass.R;
import com.wedevol.smartclass.utils.interfaces.Constants;

/** Created by paolorossi on 12/10/16.*/
public class ChangePriceDialogFragment extends DialogFragment {
    public ChangePriceDialogFragment(){

    }

    public static ChangePriceDialogFragment newInstance(int layoutShowedId) {
        Bundle args = new Bundle();
        args.putInt(Constants.BUNDLE_LAYOUT_ID, layoutShowedId);

        ChangePriceDialogFragment fragment = new ChangePriceDialogFragment();
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
        builder.setView(inflater.inflate(layoutShowedId/*R.layout.dialog_suggest_course*/, null))
                // Add action buttons
                .setPositiveButton(R.string.suggest, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // TODO this should send a message to our services for suggesting the new course
                        TextView tv_dialog_course_name = (TextView) getActivity().findViewById(R.id.tv_dialog_course_name);
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