package com.wedevol.smartclass.fragments.student;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wedevol.smartclass.R;

/** Created by paolorossi on 12/9/16.*/
public class StudentLockerFragment extends Fragment{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_student_locker, container, false);
        setElements(view);
        setActions();
        return view;
    }

    private void setElements(View view) {
        //rv_pending_counsels
        //tv_no_counsels
    }

    private void setActions() {
    }
}