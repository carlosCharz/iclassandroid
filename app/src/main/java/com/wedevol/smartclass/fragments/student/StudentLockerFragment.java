package com.wedevol.smartclass.fragments.student;

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
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.wedevol.smartclass.R;
import com.wedevol.smartclass.activities.RateLessonActivity;
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
import java.util.Calendar;
import java.util.List;

import retrofit.client.Response;

/** Created by paolorossi on 12/9/16.*/
public class StudentLockerFragment extends Fragment{
    private Button b_ask_counsel;
    private Activity self;

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
        self = getActivity();
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

        getStudentLessons(view);
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == Constants.RATED_LESSON){
            if(resultCode == Activity.RESULT_OK) {
                getStudentLessons(getView());
            } else{
                Toast.makeText(self, "Debes ponerle puntaje a tu último asesor", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void getStudentLessons(final View view) {
        final List<Lesson> pendingLessons = new ArrayList<>();
        Student student = (Student) SharedPreferencesManager.getInstance(getActivity()).getUserInfo();
        RestClient restClient = new RestClient(getContext());
        final ProgressBar pb_charging = (ProgressBar) view.findViewById(R.id.pb_charging);

        Calendar calendar = Calendar.getInstance();
        String date = calendar.get(Calendar.DAY_OF_MONTH) + "/" + (calendar.get(Calendar.MONTH)+1) + "/" + (calendar.get(Calendar.YEAR)-1);

        restClient.getWebservices().getStudentComingClasses("", student.getId(), date, calendar.get(Calendar.HOUR_OF_DAY), "confirmed", new IClassCallback<JsonArray>(getActivity()) {
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
                } else {
                    Calendar calendar = Calendar.getInstance();
                    int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
                    int month = calendar.get(Calendar.MONTH) + 1;
                    int day = calendar.get(Calendar.DAY_OF_MONTH);

                    for (Lesson lesson: pendingLessons){
                        String[] date= lesson.getClassDate().split("/");
                        boolean datePassed = lesson.getEndTime() <= hourOfDay && Integer.parseInt(date[0]) <= day && Integer.parseInt(date[1]) <= month;
                        boolean monthPassed = Integer.parseInt(date[1]) < month;
                        if(datePassed || monthPassed) {
                            Intent intent = new Intent(self, RateLessonActivity.class);
                            intent.putExtra(Constants.BUNDLE_LESSON_ID, lesson.getId());
                            intent.putExtra(Constants.BUNDLE_COURSE_NAME, lesson.getCourseName());
                            startActivityForResult(intent, Constants.RATED_LESSON);
                        }
                    }
                }
                pb_charging.setVisibility(View.GONE);
            }
        });
    }
}