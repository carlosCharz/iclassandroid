package com.wedevol.smartclass.fragments.student;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wedevol.smartclass.R;

/** Created by paolorossi on 12/8/16.*/
public class StudentProfileFragment extends Fragment{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_profile_student, container, false);
        setElements(view);
        setActions();
        return view;
    }

    private void setElements(View view){
    }

    private void setActions() {

    }
}