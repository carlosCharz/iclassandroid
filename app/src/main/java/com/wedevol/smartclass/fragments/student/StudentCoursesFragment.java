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
public class StudentCoursesFragment extends Fragment{
    private Button b_new_course;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_student_courses, container, false);
        setElements(view);
        setActions();
        return view;
    }

    private void setElements(final View view) {
        RestClient restClient = new RestClient(getActivity());
        final ProgressBar pb_charging = (ProgressBar) view.findViewById(R.id.pb_charging);
        b_new_course = (Button) view.findViewById(R.id.b_new_course);

        int id = ((HomeActivity)getActivity()).getmPreferencesManager().getUserInfo().getId();
        restClient.getWebservices().getStudentCourses("", id, new IClassCallback<JsonArray>(getActivity()){
            @Override
            public void success(JsonArray jsonArray, Response response) {
                super.success(jsonArray, response);
                List<Course> courseList = new ArrayList<>();

                for(int i = 0; i < jsonArray.size(); i++){
                    JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();
                    courseList.add(Course.parseCourse(jsonObject));
                }

                RecyclerView rv_payed = (RecyclerView) view.findViewById(R.id.rv_payed);

                if(courseList.size()>0){
                    rv_payed.setLayoutManager(new LinearLayoutManager(getActivity()));
                    rv_payed.setAdapter(new ListCourseStateAdapter(getActivity(), courseList,
                            "PAGADO", "Eres un asesor de este curso", Constants.DO_NOT_SHOW_COURSE_PRICE, Constants.NOT_SELECTABLE_COURSE));

                /* RecyclerView rv_verify_payment = (RecyclerView) view.findViewById(R.id.rv_verify_payment);
                rv_verify_payment.setLayoutManager(new LinearLayoutManager(getActivity()));
                rv_verify_payment.setAdapter(new ListCourseStateAdapter(getActivity(), courseList,
                        "VERIFICANDO PAGO", "Maximo 24 horas", Constants.DO_NOT_SHOW_COURSE_PRICE, Constants.NOT_SELECTABLE_COURSE ));

                RecyclerView rv_pending_payment = (RecyclerView) view.findViewById(R.id.rv_pending_payment);
                rv_pending_payment.setLayoutManager(new LinearLayoutManager(getActivity()));
                rv_pending_payment.setAdapter(new ListCourseStateAdapter(getActivity(), courseList,
                        "PENDIENTE DE PAGO", "Necesitar pagar el curso para poder dictarlo", Constants.DO_NOT_SHOW_COURSE_PRICE,
                        Constants.NOT_SELECTABLE_COURSE));

                RecyclerView rv_to_validated = (RecyclerView) view.findViewById(R.id.rv_to_validated);
                rv_to_validated.setLayoutManager(new LinearLayoutManager(getActivity()));
                rv_to_validated.setAdapter(new ListCourseStateAdapter(getActivity(), courseList,
                        "POR VALIDAR", "Mira tu correo para terminar la verificacion", Constants.DO_NOT_SHOW_COURSE_PRICE,
                        Constants.NOT_SELECTABLE_COURSE));*/
                } else {
                    rv_payed.setVisibility(View.GONE);
                    TextView tv_no_courses = (TextView) view.findViewById(R.id.tv_no_courses);
                    tv_no_courses.setVisibility(View.VISIBLE);
                }

                pb_charging.setVisibility(View.GONE);
            }
        });
    }

    private void setActions() {
        b_new_course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), EnableCourseActivity.class);
                intent.putExtra(Constants.STUDENT_TYPE, true);
                startActivity(intent);
            }
        });
    }
}