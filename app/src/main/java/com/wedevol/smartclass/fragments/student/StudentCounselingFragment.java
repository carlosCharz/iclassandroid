package com.wedevol.smartclass.fragments.student;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wedevol.smartclass.R;
import com.wedevol.smartclass.adapters.ListCourseStateAdapter;
import com.wedevol.smartclass.adapters.ListLessonsAdapter;
import com.wedevol.smartclass.models.Lesson;
import com.wedevol.smartclass.utils.interfaces.Constants;

import java.util.ArrayList;
import java.util.List;

/** Created by paolorossi on 12/8/16.*/
public class StudentCounselingFragment extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_student_counselings, container, false);
        setElements(view);
        setActions();
        return view;
    }

    private void setElements(View view) {
        List<Lesson> undefinedClass = new ArrayList<>();
        RecyclerView rv_counseling_history = (RecyclerView) view.findViewById(R.id.rv_counseling_history);
        rv_counseling_history.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_counseling_history.setAdapter(new ListLessonsAdapter(getActivity(), undefinedClass,
                Constants.NON_REQUEST_TYPE));
    }

    private void setActions() {

    }
}