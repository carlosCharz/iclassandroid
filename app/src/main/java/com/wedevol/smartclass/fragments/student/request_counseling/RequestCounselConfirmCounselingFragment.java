package com.wedevol.smartclass.fragments.student.request_counseling;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.wedevol.smartclass.R;
import com.wedevol.smartclass.activities.student.RequestCounselActivity;

/** Created by paolorossi on 12/12/16.*/
public class RequestCounselConfirmCounselingFragment extends Fragment {
    private Button b_finish;

    public static Fragment newInstance() {
        RequestCounselConfirmCounselingFragment requestCounselConfirmCounselingFragment = new RequestCounselConfirmCounselingFragment();
        Bundle args = new Bundle();
        requestCounselConfirmCounselingFragment.setArguments(args);
        return requestCounselConfirmCounselingFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_request_counsel_confirm_counseling, container, false);

        setupElements(view);
        setupActions();
        return view;
    }

    private void setupElements(View view) {
        ((RequestCounselActivity)getActivity()).setToolbarTitle("Confirmar Asesoria");
        b_finish = (Button) view.findViewById(R.id.b_finish);
    }

    private void setupActions() {
        b_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
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
}