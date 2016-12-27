package com.wedevol.smartclass.fragments.student;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.wedevol.smartclass.R;
import com.wedevol.smartclass.activities.instructor.EnableCourseActivity;
import com.wedevol.smartclass.adapters.ListCourseStateAdapter;
import com.wedevol.smartclass.utils.interfaces.Constants;

import java.util.ArrayList;
import java.util.List;

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

    private void setElements(View view) {
        b_new_course = (Button) view.findViewById(R.id.b_new_course);

        List<Pair<String,String>> pairList = new ArrayList<>();
        pairList.add (new Pair<>("Cálculo 1",""));
        pairList.add (new Pair<>("Cálculo 2",""));
        pairList.add (new Pair<>("Cálculo 3",""));

        RecyclerView rv_payed = (RecyclerView) view.findViewById(R.id.rv_payed);
        rv_payed.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_payed.setAdapter(new ListCourseStateAdapter(getActivity(), pairList,
                "PAGADO", "Eres un asesor de este curso", Constants.DO_NOT_SHOW_COURSE_PRICE, Constants.NOT_SELECTABLE_COURSE));

        pairList = new ArrayList<>();
        pairList.add (new Pair<>("Fisica 1",""));
        pairList.add (new Pair<>("Fisica 3",""));

        RecyclerView rv_verify_payment = (RecyclerView) view.findViewById(R.id.rv_verify_payment);
        rv_verify_payment.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_verify_payment.setAdapter(new ListCourseStateAdapter(getActivity(), pairList,
                "VERIFICANDO PAGO", "Maximo 24 horas", Constants.DO_NOT_SHOW_COURSE_PRICE, Constants.NOT_SELECTABLE_COURSE ));

        pairList = new ArrayList<>();
        pairList.add (new Pair<>("Fisica 2",""));

        RecyclerView rv_pending_payment = (RecyclerView) view.findViewById(R.id.rv_pending_payment);
        rv_pending_payment.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_pending_payment.setAdapter(new ListCourseStateAdapter(getActivity(), pairList,
                "PENDIENTE DE PAGO", "Necesitar pagar el curso para poder dictarlo", Constants.DO_NOT_SHOW_COURSE_PRICE,
                Constants.NOT_SELECTABLE_COURSE));

        pairList = new ArrayList<>();
        pairList.add (new Pair<>("Matematicas Basicas 1",""));
        pairList.add (new Pair<>("Tecnicas de Programacion",""));
        pairList.add (new Pair<>("Algoritmia",""));

        RecyclerView rv_to_validated = (RecyclerView) view.findViewById(R.id.rv_to_validated);
        rv_to_validated.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_to_validated.setAdapter(new ListCourseStateAdapter(getActivity(), pairList,
                "POR VALIDAR", "Mira tu correo para terminar la verificacion", Constants.DO_NOT_SHOW_COURSE_PRICE,
                Constants.NOT_SELECTABLE_COURSE));
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