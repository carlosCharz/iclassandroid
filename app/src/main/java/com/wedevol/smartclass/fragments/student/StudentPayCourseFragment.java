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
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.wedevol.smartclass.R;
import com.wedevol.smartclass.activities.PayCoursesActivity;
import com.wedevol.smartclass.adapters.ListCourseStateAdapter;
import com.wedevol.smartclass.models.Course;
import com.wedevol.smartclass.models.User;
import com.wedevol.smartclass.utils.SharedPreferencesManager;
import com.wedevol.smartclass.utils.interfaces.Constants;
import com.wedevol.smartclass.utils.retrofit.IClassCallback;
import com.wedevol.smartclass.utils.retrofit.RestClient;

import java.util.ArrayList;
import java.util.List;

import retrofit.client.Response;

/** Created by paolorossi on 12/8/16.*/
public class StudentPayCourseFragment extends Fragment {
    private Button b_next;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_student_pay_course, container, false);
        setElements(view);
        setActions();
        return view;
    }

    private void setElements(final View view) {
        final ProgressBar pb_charging = (ProgressBar) view.findViewById(R.id.pb_charging);
        b_next = (Button) view.findViewById(R.id.b_next);
        b_next.setEnabled(false);

        RestClient restClient = new RestClient(getActivity());
        User user =SharedPreferencesManager.getInstance(getActivity()).getUserInfo();
        restClient.getWebservices().getStudentCourses(user.getAccessToken(), user.getId(),
                "pendingPayment,verifyingPayment", new IClassCallback<JsonArray>(getActivity()){
                    @Override
                    public void success(JsonArray jsonArray, Response response) {
                        super.success(jsonArray, response);

                        List<Course>  pendingCourseList = new ArrayList<>(), veryfiedCourseList = new ArrayList<>();

                        for(int i = 0; i < jsonArray.size(); i++){
                            JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();
                            Course course = Course.parseCourse(jsonObject);
                            switch (course.getStatus()){
                                case "pendingPayment":
                                    pendingCourseList.add(course);
                                    break;
                                case "verifyingPayment":
                                    veryfiedCourseList.add(course);
                                    break;
                            }
                        }

                        RecyclerView rv_verify_payment = (RecyclerView) view.findViewById(R.id.rv_verify_payment);
                        RecyclerView rv_pending_payment = (RecyclerView) view.findViewById(R.id.rv_pending_payment);

                        if(pendingCourseList.size() > 0){
                            rv_verify_payment.setLayoutManager(new LinearLayoutManager(getActivity()));
                            rv_verify_payment.setAdapter(new ListCourseStateAdapter(getActivity(), pendingCourseList,
                                    "VERIFICANDO PAGO", "Maximo 24 horas", Constants.DO_NOT_SHOW_COURSE_PRICE,
                                    Constants.SELECTABLE_COURSE));
                        }

                        if(veryfiedCourseList.size() > 0){
                            rv_pending_payment.setLayoutManager(new LinearLayoutManager(getActivity()));
                            rv_pending_payment.setAdapter(new ListCourseStateAdapter(getActivity(), veryfiedCourseList,
                                    "PENDIENTE DE PAGO", "Necesitar pagar el curso para poder pedir asesores", Constants.DO_NOT_SHOW_COURSE_PRICE,
                                    Constants.SELECTABLE_COURSE));
                        }
                        if(pendingCourseList.size() == 0 && veryfiedCourseList.size() == 0 ){
                            TextView tv_no_courses = (TextView) view.findViewById(R.id.tv_no_courses);
                            tv_no_courses.setVisibility(View.VISIBLE);
                            b_next.setEnabled(false);
                        } else {
                            b_next.setEnabled(true);
                        }

                        pb_charging.setVisibility(View.GONE);
                    }
                });
    }

    private void setActions() {
        b_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PayCoursesActivity.class);
                startActivity(intent);
            }
        });
    }
}