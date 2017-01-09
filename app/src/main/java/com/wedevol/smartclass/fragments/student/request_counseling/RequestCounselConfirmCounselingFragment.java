package com.wedevol.smartclass.fragments.student.request_counseling;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.wedevol.smartclass.R;
import com.wedevol.smartclass.activities.student.RequestCounselActivity;
import com.wedevol.smartclass.models.Lesson;
import com.wedevol.smartclass.utils.SharedPreferencesManager;
import com.wedevol.smartclass.utils.retrofit.IClassCallback;
import com.wedevol.smartclass.utils.retrofit.RestClient;

import retrofit.client.Response;

/** Created by paolorossi on 12/12/16.*/
public class RequestCounselConfirmCounselingFragment extends Fragment {
    private Button b_finish;
    private RequestCounselActivity requestCounselActivity;

    public static Fragment newInstance() {
        RequestCounselConfirmCounselingFragment requestCounselConfirmCounselingFragment = new RequestCounselConfirmCounselingFragment();
        Bundle args = new Bundle();
        requestCounselConfirmCounselingFragment.setArguments(args);
        return requestCounselConfirmCounselingFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_request_counsel_confirm_counseling, container, false);

        setupElements(view);
        setupActions();
        return view;
    }

    private void setupElements(View view) {
        requestCounselActivity = ((RequestCounselActivity)getActivity());

        requestCounselActivity.setToolbarTitle("Confirmar Asesoría");
        b_finish = (Button) view.findViewById(R.id.b_finish);


        TextView tv_counsellor_name = (TextView) view.findViewById(R.id.tv_selected_counsellor_name);
        tv_counsellor_name.setText("El asesor seleccionado es : " + requestCounselActivity.getInstructorName());

        TextView tv_selected_course_name = (TextView) view.findViewById(R.id.tv_selected_course_name);
        tv_selected_course_name.setText("Curso : " + requestCounselActivity.getCourse().getName());

        TextView tv_selected_date = (TextView) view.findViewById(R.id.tv_selected_date);
        tv_selected_date.setText("Fecha : " + requestCounselActivity.getDateName());

        TextView tv_selected_hour = (TextView) view.findViewById(R.id.tv_selected_hour);
        tv_selected_hour.setText("Hora :" + requestCounselActivity.getBeginTime() + " - "
                + requestCounselActivity.getEndTime());

        TextView tv_selected_hourly_rate = (TextView) view.findViewById(R.id.tv_selected_hourly_rate);
        tv_selected_hourly_rate.setText("Tarifa S/./H : " + requestCounselActivity.getInstructorHourlyRate());
    }

    private void setupActions() {
        b_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RestClient restClient = new RestClient(getContext());

                Lesson lesson = new Lesson();
                lesson.setStatus("requested");
                lesson.setCurrency("S/.");
                lesson.setClassDate("8/1/2017");
                lesson.setStartTime(Integer.parseInt(requestCounselActivity.getBeginTime()));
                lesson.setEndTime(Integer.parseInt(requestCounselActivity.getEndTime()));
                lesson.setCourseId(requestCounselActivity.getCourse().getId());
                lesson.setSenderId(SharedPreferencesManager.getInstance(getContext()).getUserInfo().getId());
                lesson.setReceptorId(1);
                //lesson.setReceptorId(requestCounselActivity.getInstructorId());

                restClient.getWebservices().newLesson("", lesson.toJson(), new IClassCallback<JsonObject>(getActivity()) {
                            @Override
                            public void success(JsonObject jsonObject, Response response) {
                                super.success(jsonObject, response);
                                getActivity().finish();
                            }
                        }
                );
            }
        });

        ((RequestCounselActivity)getActivity()).setToolbarBackButtonAction(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
    }
}