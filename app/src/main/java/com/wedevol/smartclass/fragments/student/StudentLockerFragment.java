package com.wedevol.smartclass.fragments.student;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.wedevol.smartclass.R;
import com.wedevol.smartclass.activities.student.RequestCounselActivity;
import com.wedevol.smartclass.adapters.ListPendingLessonsAdapter;
import com.wedevol.smartclass.models.Lesson;
import com.wedevol.smartclass.models.Student;
import com.wedevol.smartclass.utils.SharedPreferencesManager;
import com.wedevol.smartclass.utils.UtilMethods;
import com.wedevol.smartclass.utils.interfaces.Constants;
import com.wedevol.smartclass.utils.retrofit.IClassCallback;
import com.wedevol.smartclass.utils.retrofit.RestClient;

import java.util.ArrayList;
import java.util.List;

import retrofit.client.Response;

/** Created by paolorossi on 12/9/16.*/
public class StudentLockerFragment extends Fragment{
    private Button b_ask_counsel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_student_locker, container, false);
        setElements(view);
        setActions();
        return view;
    }

    private void setElements(final View view) {
        RestClient restClient = new RestClient(getContext());

        b_ask_counsel = (Button) view.findViewById(R.id.b_ask_counsel);

        ImageView iv_user_profile_photo = (ImageView) view.findViewById(R.id.iv_user_profile_photo);
        TextView tv_student_level = (TextView) view.findViewById(R.id.tv_student_level);
        ProgressBar pb_student_progress = (ProgressBar) view.findViewById(R.id.pb_student_progress);
        TextView tv_student_counselled_time = (TextView) view.findViewById(R.id.tv_student_counselled_time);

        UtilMethods.setPhoto(getActivity(), iv_user_profile_photo, "", Constants.USER_PHOTO);

        Student student = (Student) SharedPreferencesManager.getInstance(getActivity()).getUserInfo();
        UtilMethods.setPhoto(getActivity(), iv_user_profile_photo, student.getProfilePictureUrl(), Constants.USER_PHOTO);
        tv_student_level.setText("Nivel "+ student.getLevel());
        pb_student_progress.setProgress(((Double)student.getRating()).intValue());
        tv_student_counselled_time.setText(student.getTotalHours() + " hrs");

        final List<Lesson> pendingLessons = new ArrayList<>();

        restClient.getWebservices().studentLessons("", student.getId(), "8/1/2017", 2, "confirmed", new IClassCallback<JsonArray>(getActivity()) {
            @Override
            public void success(JsonArray jsonArray, Response response) {
                super.success(jsonArray, response);
                for(JsonElement jsonElement: jsonArray){
                    pendingLessons.add(Lesson.parseLesson(jsonElement.getAsJsonObject()));
                }

                TextView tv_no_counsels = (TextView) view.findViewById(R.id.tv_no_counsels);
                RecyclerView rv_pending_counsels = (RecyclerView) view.findViewById(R.id.rv_pending_counsels);
                rv_pending_counsels.setLayoutManager(new LinearLayoutManager(getActivity()));
                rv_pending_counsels.setAdapter(new ListPendingLessonsAdapter(getActivity(), pendingLessons));

                if(pendingLessons.size() == 0){
                    tv_no_counsels.setVisibility(View.VISIBLE);
                    rv_pending_counsels.setVisibility(View.GONE);
                }
            }
        });
    }

    private void setActions() {
        b_ask_counsel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), RequestCounselActivity.class);
                getActivity().startActivity(intent);
            }
        });
    }
}