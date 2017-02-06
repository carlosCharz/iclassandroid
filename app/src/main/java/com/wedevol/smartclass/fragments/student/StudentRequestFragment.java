package com.wedevol.smartclass.fragments.student;

import android.os.Bundle;
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
import com.wedevol.smartclass.adapters.ListLessonsAdapter;
import com.wedevol.smartclass.models.Lesson;
import com.wedevol.smartclass.models.Student;
import com.wedevol.smartclass.utils.SharedPreferencesManager;
import com.wedevol.smartclass.utils.interfaces.Constants;
import com.wedevol.smartclass.utils.retrofit.IClassCallback;
import com.wedevol.smartclass.utils.retrofit.RestClient;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit.client.Response;

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

    private void setElements(final View view){
        RestClient restClient = new RestClient(getActivity());
        Student student = (Student) SharedPreferencesManager.getInstance(getActivity()).getUserInfo();

        final List<Lesson> requestedlessonList = new ArrayList<>();
        final ProgressBar pb_charging = (ProgressBar) view.findViewById(R.id.pb_charging);

        Calendar calendar = Calendar.getInstance();
        String date = calendar.get(Calendar.DAY_OF_MONTH) + "/" + calendar.get(Calendar.MONTH) + "/" + (calendar.get(Calendar.YEAR)-1);

        restClient.getWebservices().getStudentComingClasses("", student.getId(), date, calendar.get(Calendar.HOUR_OF_DAY), "requested", new IClassCallback<JsonArray>(getActivity()) {
            @Override
            public void success(JsonArray jsonArray, Response response) {
                super.success(jsonArray, response);
                for(JsonElement jsonElement: jsonArray){
                    requestedlessonList.add(Lesson.parseLesson(jsonElement.getAsJsonObject()));
                }

                RecyclerView rv_student_requests = (RecyclerView) view.findViewById(R.id.rv_student_requests);
                rv_student_requests.setLayoutManager(new LinearLayoutManager(getActivity()));
                rv_student_requests.setAdapter(new ListLessonsAdapter(getActivity(), requestedlessonList, Constants.REQUEST_TYPE));

                if(requestedlessonList.size() == 0){
                    TextView tv_no_counselings = (TextView) view.findViewById(R.id.tv_no_counselings);
                    tv_no_counselings.setVisibility(View.VISIBLE);
                    rv_student_requests.setVisibility(View.GONE);
                }

                pb_charging.setVisibility(View.GONE);
            }
        });
    }

    private void setActions() {
    }
}