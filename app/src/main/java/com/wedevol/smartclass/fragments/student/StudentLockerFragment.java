package com.wedevol.smartclass.fragments.student;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wedevol.smartclass.R;
import com.wedevol.smartclass.adapters.PendingCounselsAdapter;
import com.wedevol.smartclass.models.Class;
import com.wedevol.smartclass.models.Course;
import com.wedevol.smartclass.models.Instructor;
import com.wedevol.smartclass.models.Schedule;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/** Created by paolorossi on 12/9/16.*/
public class StudentLockerFragment extends Fragment{
    private TextView tv_no_counsels;
    private RecyclerView rv_pending_counsels;

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
        List<Class> pendingCounsels = new ArrayList<>();
        pendingCounsels.add(new Class(1, new Instructor(), new Course(), new Schedule(), new Date(), "status"));
        pendingCounsels.add(new Class(2, new Instructor(), new Course(), new Schedule(), new Date(), "status"));
        pendingCounsels.add(new Class(3, new Instructor(), new Course(), new Schedule(), new Date(), "status"));

        tv_no_counsels = (TextView) view.findViewById(R.id.tv_no_counsels);

        rv_pending_counsels = (RecyclerView) view.findViewById(R.id.rv_pending_counsels);
        rv_pending_counsels.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_pending_counsels.setAdapter(new PendingCounselsAdapter(getActivity(), pendingCounsels));

        if(pendingCounsels.size() == 0){

            tv_no_counsels.setVisibility(View.VISIBLE);
            rv_pending_counsels.setVisibility(View.GONE);
        }
    }

    private void setActions() {
    }
}