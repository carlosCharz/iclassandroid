package com.wedevol.smartclass.fragments.counselor;

import android.app.Activity;
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
import android.widget.Toast;

import com.wedevol.smartclass.R;
import com.wedevol.smartclass.activities.counselor.EnableCourseActivity;
import com.wedevol.smartclass.adapters.ListCounselorCourseStateAdapter;

import java.util.ArrayList;
import java.util.List;

/** Created by paolorossi on 12/8/16.*/
public class CounselorCoursesFragment extends Fragment{
    private Button b_new_counselor_course;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_counselor_courses, container, false);
        setElements(view);
        setActions();
        return view;
    }

    private void setElements(View view) {

        b_new_counselor_course = (Button) view.findViewById(R.id.b_new_counselor_course);

        List<Pair<String,String>> pairList = new ArrayList<>();
        pairList.add (new Pair<>("Calculo 1","S/.15"));
        pairList.add (new Pair<>("Calculo 2","S/.20"));
        pairList.add (new Pair<>("Calculo 3","S/.25"));

        RecyclerView rv_payed = (RecyclerView) view.findViewById(R.id.rv_payed);
        rv_payed.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_payed.setAdapter(new ListCounselorCourseStateAdapter(getActivity(), pairList,
                "PAGADO", "Eres un asesor de este curso", true));

        pairList = new ArrayList<>();
        pairList.add (new Pair<>("Fisica 1","S/.15"));
        pairList.add (new Pair<>("Fisica 3","S/.20"));

        RecyclerView rv_verify_payment = (RecyclerView) view.findViewById(R.id.rv_verify_payment);
        rv_verify_payment.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_verify_payment.setAdapter(new ListCounselorCourseStateAdapter(getActivity(), pairList,
                "VERIFICANDO PAGO", "Maximo 24 horas", true));

        pairList = new ArrayList<>();
        pairList.add (new Pair<>("Fisica 2","S/.25"));

        RecyclerView rv_pending_payment = (RecyclerView) view.findViewById(R.id.rv_pending_payment);
        rv_pending_payment.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_pending_payment.setAdapter(new ListCounselorCourseStateAdapter(getActivity(), pairList,
                "PENDIENTE DE PAGO", "Necesitar pagar el curso para poder dictarlo", true));

        pairList = new ArrayList<>();
        pairList.add (new Pair<>("Matematicas Basicas 1","S/.25"));
        pairList.add (new Pair<>("Tecnicas de Programacion","S/.50"));
        pairList.add (new Pair<>("Algoritmia","S/.25"));

        RecyclerView rv_to_validated = (RecyclerView) view.findViewById(R.id.rv_to_validated);
        rv_to_validated.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_to_validated.setAdapter(new ListCounselorCourseStateAdapter(getActivity(), pairList,
                "POR VALIDAR", "Mira tu correo para terminar la verificacion", true));
    }

    private void setActions() {
        b_new_counselor_course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), EnableCourseActivity.class);
                startActivity(intent);
            }
        });
    }
}