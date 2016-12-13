package com.wedevol.smartclass.fragments.student.request_counseling;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.wedevol.smartclass.R;
import com.wedevol.smartclass.activities.student.RequestCounselActivity;
import com.wedevol.smartclass.adapters.ListCounselorsAdapter;
import com.wedevol.smartclass.models.Instructor;
import com.wedevol.smartclass.utils.interfaces.ItemClickListener;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;

/** Created by paolorossi on 12/12/16.*/
public class RequestCounselSelectCounsellorFragment extends Fragment implements ItemClickListener {
    private RecyclerView rv_elligible_counsellors;
    private Button b_next;
    private int oldPosition;
    private ListCounselorsAdapter listCounselorsAdapter;

    public static Fragment newInstance() {
        RequestCounselSelectCounsellorFragment RequestCounselSelectCounsellorFragment = new RequestCounselSelectCounsellorFragment();
        Bundle args = new Bundle();
        RequestCounselSelectCounsellorFragment.setArguments(args);
        return RequestCounselSelectCounsellorFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_request_counsel_select_counsellor, container, false);

        setupElements(view);
        setupActions();
        return view;
    }

    private void setupElements(View view) {
        oldPosition = -1;
        ((RequestCounselActivity)getActivity()).setToolbarTitle("Seleccionar Asesor");


        b_next = (Button) view.findViewById(R.id.b_next);

        List<Instructor> counsellorList = new ArrayList<>();

        Instructor counsellor = new Instructor();
        counsellor.setId(1);
        counsellor.setHourlyRate(55);
        counsellor.setFirstname("Paolo");
        counsellor.setRating(4.4);
        counsellorList.add(counsellor);

        counsellor = new Instructor();
        counsellor.setId(2);
        counsellor.setHourlyRate(55);
        counsellor.setFirstname("Luis");
        counsellor.setRating(5.0);
        counsellorList.add(counsellor);

        counsellor = new Instructor();
        counsellor.setId(3);
        counsellor.setHourlyRate(55);
        counsellor.setFirstname("Richard");
        counsellor.setRating(1.0);
        counsellorList.add(counsellor);

        counsellor = new Instructor();
        counsellor.setId(4);
        counsellor.setHourlyRate(55);
        counsellor.setFirstname("Carlos");
        counsellor.setRating(2.4);
        counsellorList.add(counsellor);

        rv_elligible_counsellors = (RecyclerView) view.findViewById(R.id.rv_elligible_counsellor);
        rv_elligible_counsellors.setHasFixedSize(true);
        rv_elligible_counsellors.setLayoutManager(new LinearLayoutManager(getActivity()));

        listCounselorsAdapter = new ListCounselorsAdapter(getActivity(), counsellorList, this);
        rv_elligible_counsellors.setAdapter(new ScaleInAnimationAdapter(listCounselorsAdapter));
    }

    private void setupActions() {
        b_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.rl_request_counseling_holder, RequestCounselConfirmCounselingFragment.newInstance())
                        .addToBackStack(null)
                        .commit();
            }
        });

        ((RequestCounselActivity)getActivity()).setToolbarBackButtonAction(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity()
                        .getSupportFragmentManager()
                        .popBackStack();
            }
        });
    }

    @Override
    public void onItemClicked(int position) {
        if(oldPosition!=-1){
            listCounselorsAdapter.updatePosition(false, oldPosition);
            listCounselorsAdapter.updatePosition(true, position);
        }else{
            listCounselorsAdapter.updatePosition(true, position);
        }
        oldPosition = position;
    }
}