package com.wedevol.smartclass.fragments.student.request_counseling;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.wedevol.smartclass.R;
import com.wedevol.smartclass.activities.student.RequestCounselActivity;

/** Created by paolorossi on 12/12/16.*/
public class RequestCounselSelectScheduleFragment extends Fragment {
    private TextView tv_pick_date;
    private EditText et_begin_time;
    private EditText et_end_time;
    private RecyclerView rv_available_hours;
    private Button b_next;

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
        ((RequestCounselActivity)getActivity()).setToolbarTitle("Seleccionar Horario");

        tv_pick_date = (TextView) view.findViewById(R.id.tv_pick_date);
        et_begin_time = (EditText) view.findViewById(R.id.et_begin_time) ;
        et_end_time = (EditText) view.findViewById(R.id.et_end_time) ;
        rv_available_hours = (RecyclerView) view.findViewById(R.id.rv_available_hours);
        b_next = (Button) view.findViewById(R.id.b_next);

    }

    private void setupActions() {
        tv_pick_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        b_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
}