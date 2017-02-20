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
import com.wedevol.smartclass.adapters.ListInstructorsAdapter;
import com.wedevol.smartclass.models.Instructor;
import com.wedevol.smartclass.models.User;
import com.wedevol.smartclass.utils.SharedPreferencesManager;
import com.wedevol.smartclass.utils.interfaces.ItemClickListener;
import com.wedevol.smartclass.utils.retrofit.IClassCallback;
import com.wedevol.smartclass.utils.retrofit.RestClient;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;
import retrofit.client.Response;

/** Created by paolorossi on 12/12/16.*/
public class RequestCounselSelectInstructorFragment extends Fragment implements ItemClickListener {
    private Button b_next;
    private int oldPosition;
    private ListInstructorsAdapter listInstructorsAdapter;
    private ItemClickListener self;
    private RequestCounselActivity requestCounselActivity;

    public static Fragment newInstance() {
        RequestCounselSelectInstructorFragment RequestCounselSelectCounsellorFragment = new RequestCounselSelectInstructorFragment();
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
        requestCounselActivity = ((RequestCounselActivity)getActivity());
        requestCounselActivity.setToolbarTitle("Seleccionar Asesor");
        b_next = (Button) view.findViewById(R.id.b_next);
        User user = SharedPreferencesManager.getInstance(requestCounselActivity).getUserInfo();

        final List<Instructor> instructorList = new ArrayList<>();

        RestClient restClient = new RestClient(getContext());

        //TODO think is not gonna work.
        restClient.getWebservices().getInstructorsForClass(user.getAccessToken(), requestCounselActivity.getCourse().getId(),
                requestCounselActivity.getWeekDayName(), Integer.parseInt(requestCounselActivity.getBeginTime()),
                Integer.parseInt(requestCounselActivity.getEndTime()), /** Need to see where to pursue assesor for X hour*/
                new IClassCallback<JsonArray>(getActivity()) {
                    @Override
                    public void success(JsonArray jsonArray, Response response) {
                        super.success(jsonArray, response);

                        for(int i = 0; i < jsonArray.size(); i++){
                            instructorList.add(Instructor.parseInstructor(jsonArray.get(i).getAsJsonObject()));
                        }


                        RecyclerView rv_elligible_counsellors = (RecyclerView) view.findViewById(R.id.rv_elligible_counsellor);
                        rv_elligible_counsellors.setHasFixedSize(true);
                        rv_elligible_counsellors.setLayoutManager(new LinearLayoutManager(getActivity()));

                        listInstructorsAdapter = new ListInstructorsAdapter(getActivity(), instructorList, self);
                        rv_elligible_counsellors.setAdapter(new ScaleInAnimationAdapter(listInstructorsAdapter));

                        if(requestCounselActivity.getInstructor()==null){
                            b_next.setEnabled(false);
                        }else {
                            for(int i = 0; i< instructorList.size(); i++){
                                if(instructorList.get(i).getId() == requestCounselActivity.getInstructor().getId()){
                                    listInstructorsAdapter.updatePosition(true, i);
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
                requestCounselActivity.setInstructor(listInstructorsAdapter.getItemInPosition(oldPosition));
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.rl_request_counseling_holder, RequestCounselConfirmCounselingFragment.newInstance())
                        .addToBackStack(null)
                        .commit();
            }
        });

        requestCounselActivity.setToolbarBackButtonAction(new View.OnClickListener() {
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
            listInstructorsAdapter.updatePosition(false, oldPosition);
            listInstructorsAdapter.updatePosition(true, position);
        }else{
            listInstructorsAdapter.updatePosition(true, position);
        }
        oldPosition = position;
        b_next.setEnabled(true);
    }
}