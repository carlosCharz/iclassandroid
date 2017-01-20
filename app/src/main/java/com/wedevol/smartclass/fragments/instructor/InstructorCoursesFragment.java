package com.wedevol.smartclass.fragments.instructor;

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
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.wedevol.smartclass.R;
import com.wedevol.smartclass.activities.HomeActivity;
import com.wedevol.smartclass.activities.instructor.EnableCourseActivity;
import com.wedevol.smartclass.adapters.ListCourseStateAdapter;
import com.wedevol.smartclass.models.Course;
import com.wedevol.smartclass.utils.interfaces.Constants;
import com.wedevol.smartclass.utils.retrofit.IClassCallback;
import com.wedevol.smartclass.utils.retrofit.RestClient;

import java.util.ArrayList;
import java.util.List;

import retrofit.client.Response;

/** Created by paolorossi on 12/8/16.*/
public class InstructorCoursesFragment extends Fragment{
    private Button b_new_counselor_course;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_instructor_courses, container, false);
        setElements(view);
        setActions();
        return view;
    }

    private void setElements(final View view) {
        b_new_counselor_course = (Button) view.findViewById(R.id.b_new_counselor_course);
        getCourses(view);
    }

    private void setActions() {
        b_new_counselor_course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), EnableCourseActivity.class);
                startActivityForResult(intent, Constants.CHOOSEN_COURSE);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if((requestCode == Constants.CHOOSEN_COURSE) && (resultCode == Activity.RESULT_OK)) {
            getCourses(this.getView());
        }
    }

    private void getCourses(final View view) {
        final ProgressBar pb_charging = (ProgressBar) view.findViewById(R.id.pb_charging);
        int id = ((HomeActivity)getActivity()).getmPreferencesManager().getUserInfo().getId();
        RestClient restClient = new RestClient(getActivity());

        restClient.getWebservices().getInstructorCourses("", id, new IClassCallback<JsonArray>(getActivity()){
            @Override
            public void success(JsonArray jsonArray, Response response) {
                super.success(jsonArray, response);

                List<Course> payedCourseList = new ArrayList<>(), freeCourseList = new ArrayList<>(),
                        openCourseList = new ArrayList<>(), pendingCourseList = new ArrayList<>(),
                        veryfiedCourseList = new ArrayList<>();

                for(int i = 0; i < jsonArray.size(); i++){
                    JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();
                    Course course = Course.parseCourse(jsonObject);
                    switch (course.getStatus()){
                        case "payed":
                            payedCourseList.add(course);
                            break;
                        case "pendingPayment":
                            pendingCourseList.add(course);
                            break;
                        case "verifyingPayment":
                            veryfiedCourseList.add(course);
                            break;
                        case "open":
                            openCourseList.add(course);
                            break;
                        case "free":
                            freeCourseList.add(course);
                            break;
                    }
                }

                RecyclerView rv_payed = (RecyclerView) view.findViewById(R.id.rv_payed);
                RecyclerView rv_pending_payment = (RecyclerView) view.findViewById(R.id.rv_pending_payment);
                RecyclerView rv_verify_payment = (RecyclerView) view.findViewById(R.id.rv_verify_payment);
                RecyclerView rv_open = (RecyclerView) view.findViewById(R.id.rv_open);
                RecyclerView rv_free = (RecyclerView) view.findViewById(R.id.rv_free);

                if(payedCourseList.size()>0) {
                    rv_payed.setLayoutManager(new LinearLayoutManager(getActivity()));
                    rv_payed.setAdapter(new ListCourseStateAdapter(getActivity(), payedCourseList,
                            "PAGADO", "Eres un asesor de este curso", Constants.SHOW_COURSE_PRICE,
                            Constants.SELECTABLE_COURSE));
                }

                if(pendingCourseList.size()>0) {
                    rv_pending_payment.setLayoutManager(new LinearLayoutManager(getActivity()));
                    rv_pending_payment.setAdapter(new ListCourseStateAdapter(getActivity(), pendingCourseList,
                            "PENDIENTE DE PAGO", "Necesitar pagar el curso para poder dictarlo",
                            Constants.SHOW_COURSE_PRICE,
                            Constants.SELECTABLE_COURSE));
                }

                if(veryfiedCourseList.size()>0) {
                    rv_verify_payment.setLayoutManager(new LinearLayoutManager(getActivity()));
                    rv_verify_payment.setAdapter(new ListCourseStateAdapter(getActivity(), veryfiedCourseList,
                            "VERIFICANDO PAGO", "Maximo 24 horas", Constants.SHOW_COURSE_PRICE,
                            Constants.SELECTABLE_COURSE));
                }

                if(openCourseList.size()>0) {
                    rv_open.setLayoutManager(new LinearLayoutManager(getActivity()));
                    rv_open.setAdapter(new ListCourseStateAdapter(getActivity(), openCourseList,
                            "ABIERTO", "Para todos", Constants.SHOW_COURSE_PRICE,
                            Constants.SELECTABLE_COURSE));
                }

                if(freeCourseList.size()>0) {
                    rv_free.setLayoutManager(new LinearLayoutManager(getActivity()));
                    rv_free.setAdapter(new ListCourseStateAdapter(getActivity(), freeCourseList,
                            "GRATIS", "Por promocion", Constants.SHOW_COURSE_PRICE,
                            Constants.SELECTABLE_COURSE));
                }

                if(payedCourseList.size() == 0 && openCourseList.size() == 0 && pendingCourseList.size() == 0 &&
                        veryfiedCourseList.size() == 0 && freeCourseList.size() == 0){
                    rv_payed.setVisibility(View.GONE);
                    TextView tv_no_courses = (TextView) view.findViewById(R.id.tv_no_courses);
                    tv_no_courses.setVisibility(View.VISIBLE);
                }

                pb_charging.setVisibility(View.GONE);
            }
        });
    }
}