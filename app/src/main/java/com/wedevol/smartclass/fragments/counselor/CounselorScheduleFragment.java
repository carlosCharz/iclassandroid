package com.wedevol.smartclass.fragments.counselor;

import android.content.Context;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wedevol.smartclass.R;

/** Created by paolorossi on 12/8/16.*/
public class CounselorScheduleFragment extends Fragment{
    private Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_schedule, container, false);
        setElements(view);
        setActions();
        return view;
    }

    private void setElements(View view){
    }

    private void setActions() {

    }
}