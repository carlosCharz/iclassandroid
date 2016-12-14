package com.wedevol.smartclass.fragments.counselor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.wedevol.smartclass.R;
import com.wedevol.smartclass.activities.counselor.CreateScheduleActivity;
import com.wedevol.smartclass.adapters.ListScheduleTimeWindowAdapter;
import com.wedevol.smartclass.utils.interfaces.Constants;

import java.util.ArrayList;
import java.util.List;

/** Created by paolorossi on 12/8/16.*/
public class CounselorScheduleFragment extends Fragment{
    private FloatingActionButton fab_edit_schedule;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        List<String> timeList = new ArrayList<>();
        timeList.add("8:00 a 12:00");
        timeList.add("13:00 a 15:00");
        timeList.add("16:00 a 22:00");

        RecyclerView rv_monday_time = (RecyclerView) view.findViewById(R.id.rv_monday_time);
        rv_monday_time.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_monday_time.setAdapter(new ListScheduleTimeWindowAdapter(getActivity(), timeList, "LUNES"));

        RecyclerView rv_tuesday_time = (RecyclerView) view.findViewById(R.id.rv_tuesday_time);
        rv_tuesday_time.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_tuesday_time.setAdapter(new ListScheduleTimeWindowAdapter(getActivity(), timeList, "MARTES"));

        RecyclerView rv_wednesday_time = (RecyclerView) view.findViewById(R.id.rv_wednesday_time);
        rv_wednesday_time.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_wednesday_time.setAdapter(new ListScheduleTimeWindowAdapter(getActivity(), timeList, "MIERCOLES"));

        RecyclerView rv_thursday_time = (RecyclerView) view.findViewById(R.id.rv_thursday_time);
        rv_thursday_time.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_thursday_time.setAdapter(new ListScheduleTimeWindowAdapter(getActivity(), timeList, "JUEVES"));

        RecyclerView rv_friday_time = (RecyclerView) view.findViewById(R.id.rv_friday_time);
        rv_friday_time.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_friday_time.setAdapter(new ListScheduleTimeWindowAdapter(getActivity(), timeList, "VIERNES"));

        RecyclerView rv_saturday_time = (RecyclerView) view.findViewById(R.id.rv_saturday_time);
        rv_saturday_time.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_saturday_time.setAdapter(new ListScheduleTimeWindowAdapter(getActivity(), timeList, "SABADO"));

        RecyclerView rv_sunday_time = (RecyclerView) view.findViewById(R.id.rv_sunday_time);
        rv_sunday_time.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_sunday_time.setAdapter(new ListScheduleTimeWindowAdapter(getActivity(), timeList, "DOMINGO"));

        fab_edit_schedule = (FloatingActionButton) view.findViewById(R.id.fab_edit_schedule);
    }

    private void setActions() {
        fab_edit_schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CreateScheduleActivity.class);
                intent.putExtra(Constants.BUNDLE_SIMPLE_DATE, true);
                startActivityForResult(intent, Constants.CHOOSEN_SCHEDULE);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if((requestCode == Constants.CHOOSEN_SCHEDULE) && (resultCode == Activity.RESULT_OK)) {
            //data.getByteArrayExtra();
            Toast.makeText(getActivity(), "La data si volvio" , Toast.LENGTH_SHORT).show();
        }
    }
}