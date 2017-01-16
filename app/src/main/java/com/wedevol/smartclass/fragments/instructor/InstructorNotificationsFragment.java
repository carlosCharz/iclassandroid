package com.wedevol.smartclass.fragments.instructor;

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
import com.wedevol.smartclass.adapters.ListRequestedLessonsAdapter;
import com.wedevol.smartclass.models.Instructor;
import com.wedevol.smartclass.models.Lesson;
import com.wedevol.smartclass.utils.SharedPreferencesManager;
import com.wedevol.smartclass.utils.retrofit.IClassCallback;
import com.wedevol.smartclass.utils.retrofit.RestClient;

import java.util.ArrayList;
import java.util.List;

import retrofit.client.Response;

/** Created by paolorossi on 12/8/16.*/
public class InstructorNotificationsFragment extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_instructor_notifications, container, false);
        setElements(view);
        setActions();
        return view;
    }

    private void setElements(final View view) {
        RestClient restClient = new RestClient(getContext());
        final ProgressBar pb_charging = (ProgressBar) view.findViewById(R.id.pb_charging);
        final List<Lesson> requestedlessonList = new ArrayList<>();
        Instructor instructor = (Instructor) SharedPreferencesManager.getInstance(getActivity()).getUserInfo();

        restClient.getWebservices().instructorLessons("", instructor.getId(), "8/1/2017", 2, "requested", new IClassCallback<JsonArray>(getActivity()) {
            @Override
            public void success(JsonArray jsonArray, Response response) {
                super.success(jsonArray, response);
                for(JsonElement jsonElement: jsonArray){
                    requestedlessonList.add(Lesson.parseLesson(jsonElement.getAsJsonObject()));
                }

                RecyclerView rv_notifications = (RecyclerView) view.findViewById(R.id.rv_notifications);
                rv_notifications.setLayoutManager(new LinearLayoutManager(getActivity()));
                rv_notifications.setAdapter(new ListRequestedLessonsAdapter(getActivity(), requestedlessonList));

                if(requestedlessonList.size() == 0){
                    TextView tv_no_counselings = (TextView) view.findViewById(R.id.tv_no_counselings);
                    tv_no_counselings.setVisibility(View.VISIBLE);
                    rv_notifications.setVisibility(View.GONE);
                }

                pb_charging.setVisibility(View.GONE);
            }
        });
    }

    private void setActions() {

    }
}