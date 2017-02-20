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
import com.wedevol.smartclass.adapters.ListLessonsAdapter;
import com.wedevol.smartclass.models.Instructor;
import com.wedevol.smartclass.models.Lesson;
import com.wedevol.smartclass.utils.SharedPreferencesManager;
import com.wedevol.smartclass.utils.interfaces.Constants;
import com.wedevol.smartclass.utils.retrofit.IClassCallback;
import com.wedevol.smartclass.utils.retrofit.RestClient;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit.client.Response;

/** Created by paolorossi on 12/8/16.*/
public class InstructorHistoricLessonsFragment extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_instructor_counseled, container, false);
        setElements(view);
        setActions();
        return view;
    }

    private void setElements(final View view) {
        RestClient restClient = new RestClient(getActivity());
        final ProgressBar pb_charging = (ProgressBar) view.findViewById(R.id.pb_charging);
        final List<Lesson> historicalLessons = new ArrayList<>();
        Instructor instructor = (Instructor) SharedPreferencesManager.getInstance(getActivity()).getUserInfo();

        Calendar calendar = Calendar.getInstance();
        String date = calendar.get(Calendar.DAY_OF_MONTH) + "/" + (calendar.get(Calendar.MONTH)+1) + "/" + calendar.get(Calendar.YEAR);

        restClient.getWebservices().getInstructorHistoricClasses(instructor.getAccessToken(), instructor.getId(), date, calendar.get(Calendar.HOUR_OF_DAY), "done,confirmed,requested,rejected,ignored,cancelled", new IClassCallback<JsonArray>(getActivity()) {
            @Override
            public void success(JsonArray jsonArray, Response response) {
                super.success(jsonArray, response);
                for(JsonElement jsonElement: jsonArray){
                    historicalLessons.add(Lesson.parseLesson(jsonElement.getAsJsonObject()));
                }

                RecyclerView rv_counseled_history = (RecyclerView) view.findViewById(R.id.rv_counseled_history);
                rv_counseled_history.setLayoutManager(new LinearLayoutManager(getActivity()));
                rv_counseled_history.setAdapter(new ListLessonsAdapter(getActivity(), historicalLessons,
                        Constants.NON_REQUEST_TYPE));

                if(historicalLessons.size() == 0){
                    TextView tv_no_counselings = (TextView) view.findViewById(R.id.tv_no_counselings);
                    tv_no_counselings.setVisibility(View.VISIBLE);
                    rv_counseled_history.setVisibility(View.GONE);
                }

                pb_charging.setVisibility(View.GONE);
            }
        });
    }

    private void setActions() {

    }
}