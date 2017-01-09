package com.wedevol.smartclass.fragments.student.request_counseling;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.gson.JsonArray;
import com.wedevol.smartclass.R;
import com.wedevol.smartclass.activities.student.RequestCounselActivity;
import com.wedevol.smartclass.adapters.ListCounselorsAdapter;
import com.wedevol.smartclass.models.Instructor;
import com.wedevol.smartclass.utils.interfaces.ItemClickListener;
import com.wedevol.smartclass.utils.retrofit.IClassCallback;
import com.wedevol.smartclass.utils.retrofit.RestClient;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;
import retrofit.client.Response;

/** Created by paolorossi on 12/12/16.*/
public class RequestCounselSelectCounsellorFragment extends Fragment implements ItemClickListener {
    private Button b_next;
    private int oldPosition;
    private ListCounselorsAdapter listCounselorsAdapter;
    private ItemClickListener self;

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

    private void setupElements(final View view) {
        self = this;
        oldPosition = -1;
        ((RequestCounselActivity)getActivity()).setToolbarTitle("Seleccionar Asesor");
        b_next = (Button) view.findViewById(R.id.b_next);

        final List<Instructor> instructorList = new ArrayList<>();

        RestClient restClient = new RestClient(getContext());
        RequestCounselActivity requestCounselActivity = ((RequestCounselActivity)getActivity());

        restClient.getWebservices().getFreeHours("", requestCounselActivity.getCourse().getId(),
                "8/1/2017", Integer.parseInt(requestCounselActivity.getBeginTime()),
                Integer.parseInt(requestCounselActivity.getEndTime()),
                new IClassCallback<JsonArray>(getActivity()) {
                    @Override
                    public void success(JsonArray jsonArray, Response response) {
                        super.success(jsonArray, response);

                        Instructor instructor = new Instructor();
                        instructor.setId(1);
                        instructor.setHourlyRate(55);
                        instructor.setFirstname("Paolo");
                        instructor.setRating(4.4);
                        instructorList.add(instructor);

                        instructor = new Instructor();
                        instructor.setId(2);
                        instructor.setHourlyRate(55);
                        instructor.setFirstname("Luis");
                        instructor.setRating(5.0);
                        instructorList.add(instructor);

                        instructor = new Instructor();
                        instructor.setId(3);
                        instructor.setHourlyRate(55);
                        instructor.setFirstname("Richard");
                        instructor.setRating(1.0);
                        instructorList.add(instructor);

                        instructor = new Instructor();
                        instructor.setId(4);
                        instructor.setHourlyRate(55);
                        instructor.setFirstname("Carlos");
                        instructor.setRating(2.4);
                        instructorList.add(instructor);



                        RecyclerView rv_elligible_counsellors = (RecyclerView) view.findViewById(R.id.rv_elligible_counsellor);
                        rv_elligible_counsellors.setHasFixedSize(true);
                        rv_elligible_counsellors.setLayoutManager(new LinearLayoutManager(getActivity()));

                        listCounselorsAdapter = new ListCounselorsAdapter(getActivity(), instructorList, self);
                        rv_elligible_counsellors.setAdapter(new ScaleInAnimationAdapter(listCounselorsAdapter));

                        if(((RequestCounselActivity)getActivity()).getCounsellor()==null){
                            b_next.setEnabled(false);
                        }else {
                            for(int i = 0; i< instructorList.size(); i++){
                                if(instructorList.get(i).getId() == ((RequestCounselActivity)getActivity()).getCounsellor().getId()){
                                    listCounselorsAdapter.updatePosition(true, i);
                                    oldPosition = i;
                                }
                            }
                        }
                    }
                }
        );
    }

    private void setupActions() {
        b_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((RequestCounselActivity)getActivity()).setCounsellor(listCounselorsAdapter.getItemInPosition(oldPosition));

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
        b_next.setEnabled(true);
    }
}