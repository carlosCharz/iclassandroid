package com.wedevol.smartclass.fragments.instructor;

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

import com.wedevol.smartclass.R;
import com.wedevol.smartclass.activities.instructor.CreateScheduleActivity;
import com.wedevol.smartclass.adapters.ListScheduleTimeWindowAdapter;
import com.wedevol.smartclass.utils.interfaces.Constants;
import com.wedevol.smartclass.utils.interfaces.ScheduleClickListener;

import java.util.ArrayList;
import java.util.List;

/** Created by paolorossi on 12/8/16.*/
public class CounselorScheduleFragment extends Fragment implements ScheduleClickListener {
    private FloatingActionButton fab_edit_schedule;
    private RecyclerView rv_monday_time;
    private RecyclerView rv_tuesday_time;
    private RecyclerView rv_wednesday_time;
    private RecyclerView rv_thursday_time;
    private RecyclerView rv_friday_time;
    private RecyclerView rv_saturday_time;
    private RecyclerView rv_sunday_time;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_counselor_schedule, container, false);
        setElements(view);
        setActions();
        return view;
    }

    private void setElements(View view){
        List<String> timeList = new ArrayList<>();
        timeList.add("8:00 a 12:00");
        timeList.add("13:00 a 15:00");
        timeList.add("16:00 a 22:00");

        rv_monday_time = (RecyclerView) view.findViewById(R.id.rv_monday_time);
        rv_monday_time.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_monday_time.setAdapter(new ListScheduleTimeWindowAdapter(getActivity(), timeList, "LUNES", this));

        timeList = new ArrayList<>();
        timeList.add("8:00 a 12:00");
        timeList.add("13:00 a 15:00");
        timeList.add("16:00 a 22:00");

        rv_tuesday_time = (RecyclerView) view.findViewById(R.id.rv_tuesday_time);
        rv_tuesday_time.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_tuesday_time.setAdapter(new ListScheduleTimeWindowAdapter(getActivity(), timeList, "MARTES", this));

        timeList = new ArrayList<>();
        timeList.add("8:00 a 12:00");
        timeList.add("13:00 a 15:00");
        timeList.add("16:00 a 22:00");

        rv_wednesday_time = (RecyclerView) view.findViewById(R.id.rv_wednesday_time);
        rv_wednesday_time.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_wednesday_time.setAdapter(new ListScheduleTimeWindowAdapter(getActivity(), timeList, "MIERCOLES", this));

        timeList = new ArrayList<>();
        timeList.add("8:00 a 12:00");
        timeList.add("13:00 a 15:00");
        timeList.add("16:00 a 22:00");

        rv_thursday_time = (RecyclerView) view.findViewById(R.id.rv_thursday_time);
        rv_thursday_time.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_thursday_time.setAdapter(new ListScheduleTimeWindowAdapter(getActivity(), timeList, "JUEVES", this));

        timeList = new ArrayList<>();
        timeList.add("8:00 a 12:00");
        timeList.add("13:00 a 15:00");
        timeList.add("16:00 a 22:00");

        rv_friday_time = (RecyclerView) view.findViewById(R.id.rv_friday_time);
        rv_friday_time.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_friday_time.setAdapter(new ListScheduleTimeWindowAdapter(getActivity(), timeList, "VIERNES", this));

        timeList = new ArrayList<>();
        timeList.add("8:00 a 12:00");
        timeList.add("13:00 a 15:00");
        timeList.add("16:00 a 22:00");

        rv_saturday_time = (RecyclerView) view.findViewById(R.id.rv_saturday_time);
        rv_saturday_time.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_saturday_time.setAdapter(new ListScheduleTimeWindowAdapter(getActivity(), timeList, "SABADO", this));

        timeList = new ArrayList<>();
        timeList.add("8:00 a 12:00");
        timeList.add("13:00 a 15:00");
        timeList.add("16:00 a 22:00");

        rv_sunday_time = (RecyclerView) view.findViewById(R.id.rv_sunday_time);
        rv_sunday_time.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_sunday_time.setAdapter(new ListScheduleTimeWindowAdapter(getActivity(), timeList, "DOMINGO", this));

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
            //Toast.makeText(getActivity(), "La data si volvio" , Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onItemClicked(String day, int position) {
        switch (day){
            case "LUNES":
                if(position==-1){
                    ((ListScheduleTimeWindowAdapter)rv_monday_time.getAdapter()).deleteAll();
                }else{
                    ((ListScheduleTimeWindowAdapter)rv_monday_time.getAdapter()).deletePosition(position);
                }
                break;
            case "MARTES":
                if(position==-1){
                    ((ListScheduleTimeWindowAdapter)rv_tuesday_time.getAdapter()).deleteAll();
                }else{
                    ((ListScheduleTimeWindowAdapter)rv_tuesday_time.getAdapter()).deletePosition(position);
                }
                break;
            case "MIERCOLES":
                if(position==-1){
                    ((ListScheduleTimeWindowAdapter)rv_wednesday_time.getAdapter()).deleteAll();
                }else{
                    ((ListScheduleTimeWindowAdapter)rv_wednesday_time.getAdapter()).deletePosition(position);
                }
                break;
            case "JUEVES":
                if(position==-1){
                    ((ListScheduleTimeWindowAdapter)rv_thursday_time.getAdapter()).deleteAll();
                }else{
                    ((ListScheduleTimeWindowAdapter)rv_thursday_time.getAdapter()).deletePosition(position);
                }
                break;
            case "VIERNES":
                if(position==-1){
                    ((ListScheduleTimeWindowAdapter)rv_friday_time.getAdapter()).deleteAll();
                }else{
                    ((ListScheduleTimeWindowAdapter)rv_friday_time.getAdapter()).deletePosition(position);
                }
                break;
            case "SABADO":
                if(position==-1){
                    ((ListScheduleTimeWindowAdapter)rv_saturday_time.getAdapter()).deleteAll();
                }else{
                    ((ListScheduleTimeWindowAdapter)rv_saturday_time.getAdapter()).deletePosition(position);
                }
                break;
            case "DOMINGO":
                if(position==-1){
                    ((ListScheduleTimeWindowAdapter)rv_sunday_time.getAdapter()).deleteAll();
                }else{
                    ((ListScheduleTimeWindowAdapter)rv_sunday_time.getAdapter()).deletePosition(position);
                }
                break;
        }
    }
}