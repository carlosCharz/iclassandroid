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
import com.wedevol.smartclass.activities.PayCoursesActivity;
import com.wedevol.smartclass.adapters.ListCourseStateAdapter;
import com.wedevol.smartclass.utils.interfaces.Constants;

import java.util.ArrayList;
import java.util.List;

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

    private void setElements(View view) {

        List<Pair<String,String>> pairList = new ArrayList<>();
        pairList.add (new Pair<>("Fisica 1",""));
        pairList.add (new Pair<>("Fisica 3",""));

        RecyclerView rv_verify_payment = (RecyclerView) view.findViewById(R.id.rv_verify_payment);
        rv_verify_payment.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_verify_payment.setAdapter(new ListCourseStateAdapter(getActivity(), pairList,
                "VERIFICANDO PAGO", "Maximo 24 horas", Constants.DO_NOT_SHOW_COURSE_PRICE,
                Constants.SELECTABLE_COURSE));

        pairList = new ArrayList<>();
        pairList.add (new Pair<>("Fisica 2",""));

        RecyclerView rv_pending_payment = (RecyclerView) view.findViewById(R.id.rv_pending_payment);
        rv_pending_payment.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_pending_payment.setAdapter(new ListCourseStateAdapter(getActivity(), pairList,
                "PENDIENTE DE PAGO", "Necesitar pagar el curso para poder pedir asesores", Constants.DO_NOT_SHOW_COURSE_PRICE,
                Constants.SELECTABLE_COURSE));

        b_next = (Button) view.findViewById(R.id.b_next);
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