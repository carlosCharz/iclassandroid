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
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.wedevol.smartclass.R;
import com.wedevol.smartclass.activities.instructor.CreateScheduleActivity;
import com.wedevol.smartclass.adapters.ListScheduleTimeWindowAdapter;
import com.wedevol.smartclass.models.Schedule;
import com.wedevol.smartclass.utils.SharedPreferencesManager;
import com.wedevol.smartclass.utils.interfaces.Constants;
import com.wedevol.smartclass.utils.interfaces.ScheduleClickListener;
import com.wedevol.smartclass.utils.retrofit.IClassCallback;
import com.wedevol.smartclass.utils.retrofit.RestClient;

import java.util.ArrayList;
import java.util.List;

import retrofit.client.Response;

/** Created by paolorossi on 12/8/16.*/
public class InstructorScheduleFragment extends Fragment implements ScheduleClickListener {
    private ScheduleClickListener self;
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
        View view = inflater.inflate(R.layout.fragment_instructor_schedule, container, false);
        setElements(view);
        setActions();
        return view;
    }

    private void setElements(final View view){
        self = this;
        getWeekSchedule(view);

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
            getWeekSchedule(this.getView());
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

    private void getWeekSchedule(final View view) {
        final ProgressBar pb_charging = (ProgressBar) view.findViewById(R.id.pb_charging);
        RestClient restClient = new RestClient(getContext());

        int instructorId = SharedPreferencesManager.getInstance(getContext()).getUserInfo().getId();
        restClient.getWebservices().listSchedule("", instructorId, new IClassCallback<JsonArray>(getActivity()) {
            @Override
            public void success(JsonArray jsonArray, Response response) {
                super.success(jsonArray, response);
                List<Schedule> monList = new ArrayList<>(),
                        tueList = new ArrayList<>(),
                        wedList = new ArrayList<>(),
                        thuList = new ArrayList<>(),
                        friList = new ArrayList<>(),
                        satList = new ArrayList<>(),
                        sunList = new ArrayList<>();

                for(JsonElement jsonElement: jsonArray){
                    Schedule schedule = Schedule.parseSchedule(jsonElement.getAsJsonObject());
                    if(schedule.isAvailable()){
                        switch (schedule.getWeekDay()){
                            case "mon":
                                monList.add(schedule);
                                break;
                            case "tue":
                                tueList.add(schedule);
                                break;
                            case "wed":
                                wedList.add(schedule);
                                break;
                            case "thu":
                                thuList.add(schedule);
                                break;
                            case "fri":
                                friList.add(schedule);
                                break;
                            case "sat":
                                satList.add(schedule);
                                break;
                            case "sun":
                                sunList.add(schedule);
                                break;
                        }
                    }
                }

                if(monList.size()>0) {
                    rv_monday_time = (RecyclerView) view.findViewById(R.id.rv_monday_time);
                    rv_monday_time.setLayoutManager(new LinearLayoutManager(getActivity()));
                    rv_monday_time.setAdapter(new ListScheduleTimeWindowAdapter(getActivity(), monList, "LUNES", self));
                }

                if(tueList.size()>0) {
                    rv_tuesday_time = (RecyclerView) view.findViewById(R.id.rv_tuesday_time);
                    rv_tuesday_time.setLayoutManager(new LinearLayoutManager(getActivity()));
                    rv_tuesday_time.setAdapter(new ListScheduleTimeWindowAdapter(getActivity(), tueList, "MARTES", self));
                }

                if(wedList.size()>0) {
                    rv_wednesday_time = (RecyclerView) view.findViewById(R.id.rv_wednesday_time);
                    rv_wednesday_time.setLayoutManager(new LinearLayoutManager(getActivity()));
                    rv_wednesday_time.setAdapter(new ListScheduleTimeWindowAdapter(getActivity(), wedList, "MIERCOLES", self));
                }

                if(thuList.size()>0) {
                    rv_thursday_time = (RecyclerView) view.findViewById(R.id.rv_thursday_time);
                    rv_thursday_time.setLayoutManager(new LinearLayoutManager(getActivity()));
                    rv_thursday_time.setAdapter(new ListScheduleTimeWindowAdapter(getActivity(), thuList, "JUEVES", self));
                }

                if(friList.size()>0) {
                    rv_friday_time = (RecyclerView) view.findViewById(R.id.rv_friday_time);
                    rv_friday_time.setLayoutManager(new LinearLayoutManager(getActivity()));
                    rv_friday_time.setAdapter(new ListScheduleTimeWindowAdapter(getActivity(), friList, "VIERNES", self));
                }

                if(satList.size()>0) {
                    rv_saturday_time = (RecyclerView) view.findViewById(R.id.rv_saturday_time);
                    rv_saturday_time.setLayoutManager(new LinearLayoutManager(getActivity()));
                    rv_saturday_time.setAdapter(new ListScheduleTimeWindowAdapter(getActivity(), satList, "SABADO", self));
                }

                if(sunList.size() > 0) {
                    rv_sunday_time = (RecyclerView) view.findViewById(R.id.rv_sunday_time);
                    rv_sunday_time.setLayoutManager(new LinearLayoutManager(getActivity()));
                    rv_sunday_time.setAdapter(new ListScheduleTimeWindowAdapter(getActivity(), sunList, "DOMINGO", self));
                }

                TextView tv_no_schedules = (TextView) view.findViewById(R.id.tv_no_schedules);
                if(monList.size() == 0 && tueList.size() == 0 && wedList.size() == 0 && thuList.size() == 0
                        && friList.size() == 0 && satList.size() == 0 && sunList.size() == 0){
                    tv_no_schedules.setVisibility(View.VISIBLE);
                }else{
                    tv_no_schedules.setVisibility(View.GONE);
                }

                pb_charging.setVisibility(View.GONE);
            }
        });
    }
}