package com.wedevol.smartclass.fragments.student.request_counseling;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.wedevol.smartclass.R;
import com.wedevol.smartclass.activities.ListDatesActivity;
import com.wedevol.smartclass.activities.student.RequestCounselActivity;
import com.wedevol.smartclass.adapters.ListTimesAdapter;
import com.wedevol.smartclass.utils.UtilMethods;
import com.wedevol.smartclass.utils.dialogs.TimePickDialogFragment;
import com.wedevol.smartclass.utils.interfaces.Constants;
import com.wedevol.smartclass.utils.interfaces.ItemClickListener;
import com.wedevol.smartclass.utils.retrofit.IClassCallback;
import com.wedevol.smartclass.utils.retrofit.RestClient;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;
import retrofit.client.Response;

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
    private ItemClickListener self;

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
        getActivity().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setupElements(view);
        setupActions();
        return view;
    }

    private void setupElements(View view) {
        oldPosition = -1;
        self = this;
        ((RequestCounselActivity)getActivity()).setToolbarTitle("Seleccionar Horario");

        tv_pick_date = (TextView) view.findViewById(R.id.tv_pick_date);
        et_begin_time = (EditText) view.findViewById(R.id.et_begin_time);
        et_begin_time.setInputType(InputType.TYPE_NULL);

        et_end_time = (EditText) view.findViewById(R.id.et_end_time);
        et_end_time.setInputType(InputType.TYPE_NULL);

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
                Intent intent = new Intent(getActivity(), ListDatesActivity.class);
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
                        Toast.makeText(getActivity(), "Debe delimitar su tiempo de asesoría.", Toast.LENGTH_SHORT).show();
                    }
                    return;
                }

                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.rl_request_counseling_holder, RequestCounselSelectInstructorFragment.newInstance())
                        .addToBackStack(null)
                        .commit();
            }
        });

        et_begin_time.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        TimePickDialogFragment timePickDialogFragment = TimePickDialogFragment.newInstance(et_begin_time);
                        timePickDialogFragment.show(getActivity().getSupportFragmentManager(), "");
                    }
                }
        );

        et_begin_time.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                UtilMethods.deactivateSoftKeyboard(v, getActivity());
            }
        });

        et_end_time.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        TimePickDialogFragment timePickDialogFragment = TimePickDialogFragment.newInstance(et_end_time);
                        timePickDialogFragment.show(getActivity().getSupportFragmentManager(), "");
                    }
                }
        );

        et_end_time.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                UtilMethods.deactivateSoftKeyboard(v, getActivity());
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
                    (dateDelimiters[1] < endTime) || (endTime == beginTime);
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

        final List<String> timesList = new ArrayList<>();

        RestClient restClient = new RestClient(getContext());
        restClient.getWebservices().getFreeHours("", ((RequestCounselActivity)getActivity()).getCourse().getId(),
                ((RequestCounselActivity)getActivity()).getWeekDayName(), new IClassCallback<JsonArray>(getActivity()) {
                    @Override
                    public void success(JsonArray jsonArray, Response response) {
                        super.success(jsonArray, response);

                        for(int i = 0 ; i< jsonArray.size(); i++){
                            JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();
                            int startTime = jsonObject.get("startTime").getAsInt();
                            int endTime= jsonObject.get("endTime").getAsInt();
                            String timeInterval = startTime + " a " + endTime;
                            timesList.add(timeInterval);
                        }

                        listTimeAdapter = new ListTimesAdapter(getActivity(), timesList, self);
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
                });
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