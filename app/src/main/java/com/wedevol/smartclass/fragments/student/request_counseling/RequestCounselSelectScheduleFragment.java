package com.wedevol.smartclass.fragments.student.request_counseling;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.wedevol.smartclass.R;
import com.wedevol.smartclass.activities.ListCalendarDatesActivity;
import com.wedevol.smartclass.activities.student.RequestCounselActivity;
import com.wedevol.smartclass.adapters.ListTimesAdapter;
import com.wedevol.smartclass.utils.interfaces.Constants;
import com.wedevol.smartclass.utils.interfaces.ItemClickListener;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;

/** Created by paolorossi on 12/12/16.*/
public class RequestCounselSelectScheduleFragment extends Fragment implements ItemClickListener {
    private TextView tv_pick_date;
    private EditText et_begin_time;
    private EditText et_end_time;
    private RecyclerView rv_available_hours;
    private Button b_next;
    private ListTimesAdapter listTimeAdapter;
    private int oldPosition;
    private int[] dateDelimiters = new int[2];

    public static Fragment newInstance() {
        RequestCounselSelectScheduleFragment requestCounselSelectScheduleFragment = new RequestCounselSelectScheduleFragment();
        Bundle args = new Bundle();
        //args.putInt(Constants.BUNDLE_COURSE_ID, courseId);
        requestCounselSelectScheduleFragment.setArguments(args);
        return requestCounselSelectScheduleFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //courseId = getArguments().getInt(Constants.BUNDLE_COURSE_ID);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_request_counsel_select_schedule, container, false);

        setupElements(view);
        setupActions();
        return view;
    }

    private void setupElements(View view) {
        oldPosition = -1;

        ((RequestCounselActivity)getActivity()).setToolbarTitle("Seleccionar Horario");

        tv_pick_date = (TextView) view.findViewById(R.id.tv_pick_date);
        et_begin_time = (EditText) view.findViewById(R.id.et_begin_time) ;
        et_end_time = (EditText) view.findViewById(R.id.et_end_time) ;
        b_next = (Button) view.findViewById(R.id.b_next);
        rv_available_hours = (RecyclerView) view.findViewById(R.id.rv_available_hours);

        if(!(((RequestCounselActivity)getActivity()).getDateName()==null)){
            tv_pick_date.setText(((RequestCounselActivity)getActivity()).getDateName());
            setHoursRecycler();
        }
    }

    private void setupActions() {
        tv_pick_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ListCalendarDatesActivity.class);
                startActivityForResult(intent, Constants.CHOOSEN_DATE);
            }
        });

        b_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int validated = validateTime();
                if(validated==2){
                    ((RequestCounselActivity)getActivity()).saveScheduleTimes(et_begin_time.getText().toString(), et_end_time.getText().toString());
                }else{
                    et_begin_time.requestFocus();
                    if(validated==1){
                        Toast.makeText(getActivity(), "Debes reservar por lo menos 1 hora. La hora de inicio tiene que ser menor que la hora de termino.", Toast.LENGTH_SHORT).show();
                    }else if (validated==0){
                        Toast.makeText(getActivity(), "Debe delimitar su tiempo de asesoria.", Toast.LENGTH_SHORT).show();
                    }
                    return;
                }

                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.rl_request_counseling_holder, RequestCounselSelectCounsellorFragment.newInstance())
                        .addToBackStack(null)
                        .commit();
            }
        });

        ((RequestCounselActivity)getActivity()).setToolbarBackButtonAction(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity()
                        .getSupportFragmentManager()
                        .popBackStack();
            }
        });
    }

    private int validateTime() {
        if(et_begin_time.getText().toString().isEmpty()||et_end_time.getText().toString().isEmpty()) {
            return 0;
        }else{
            int endTime = Integer.parseInt(et_end_time.getText().toString());
            int beginTime = Integer.parseInt(et_begin_time.getText().toString());

            boolean wrong = ((endTime - beginTime) < 0) || (dateDelimiters[0] > beginTime) ||
                    (dateDelimiters[1] < endTime);
            if(wrong){
                return 1;
            }
        }
        return 2;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        getActivity();
        if((requestCode == Constants.CHOOSEN_DATE) && (resultCode == Activity.RESULT_OK)) {
            String dateName = data.getStringExtra(Constants.BUNDLE_DATE);
            ((RequestCounselActivity)getActivity()).saveSchedule(dateName);
            tv_pick_date.setText(dateName);
            setHoursRecycler();
        }
    }

    private void setHoursRecycler() {
        rv_available_hours.setHasFixedSize(true);
        rv_available_hours.setLayoutManager(new LinearLayoutManager(getActivity()));

        List<String> timesList = new ArrayList<>();
        timesList.add("8 a 10");
        timesList.add("11 a 15");
        timesList.add("16 a 20");
        timesList.add("21 a 23");
        listTimeAdapter = new ListTimesAdapter(getActivity(), timesList, this);
        rv_available_hours.setAdapter(new ScaleInAnimationAdapter(listTimeAdapter));

        if(((RequestCounselActivity)getActivity()).getBeginTime()!=null &&
                ((RequestCounselActivity)getActivity()).getEndTime()!=null){
            for(int i = 0 ; i< timesList.size(); i++){
                if(timesList.get(i).equals( ((RequestCounselActivity)getActivity()).getBeginTime() + " a " +
                        ((RequestCounselActivity)getActivity()).getEndTime())){
                    listTimeAdapter.updatePosition(true, i);
                    oldPosition = i;
                }
            }
        }
    }

    @Override
    public void onItemClicked(int position) {
        if(oldPosition!=-1){
            listTimeAdapter.updatePosition(false, oldPosition);
            listTimeAdapter.updatePosition(true, position);
        }else{
            listTimeAdapter.updatePosition(true, position);
        }
        oldPosition = position;
        String strTime = listTimeAdapter.getItem(position);
        String[] strBeginEndTime=strTime.split("a");
        dateDelimiters[0]= Integer.parseInt(strBeginEndTime[0].trim());
        dateDelimiters[1]= Integer.parseInt(strBeginEndTime[1].trim());
    }
}