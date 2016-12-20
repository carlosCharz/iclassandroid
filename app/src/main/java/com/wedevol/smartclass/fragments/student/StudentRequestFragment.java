package com.wedevol.smartclass.fragments.student;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wedevol.smartclass.R;
import com.wedevol.smartclass.adapters.ListRequestsAdapter;
import com.wedevol.smartclass.models.Lesson;

import java.util.ArrayList;
import java.util.List;

/** Created by paolorossi on 12/8/16.*/
public class StudentRequestFragment extends Fragment{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_student_requests, container, false);
        setElements(view);
        setActions();
        return view;
    }

    private void setElements(View view){
        List<Lesson> undefinedClass = new ArrayList<>();
        undefinedClass.add(new Lesson());
        undefinedClass.add(new Lesson());
        undefinedClass.add(new Lesson());
        undefinedClass.add(new Lesson());

        RecyclerView rv_student_requests = (RecyclerView) view.findViewById(R.id.rv_student_requests);
        rv_student_requests.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_student_requests.setAdapter(new ListRequestsAdapter(getActivity(), undefinedClass));
    }

    private void setActions() {

    }
}