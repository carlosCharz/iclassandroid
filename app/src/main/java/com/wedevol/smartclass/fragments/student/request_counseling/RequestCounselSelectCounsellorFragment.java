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
public class RequestCounselSelectCounsellorFragment extends Fragment {

    private Button b_next;

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
        ((RequestCounselActivity)getActivity()).setToolbarTitle("Seleccionar Asesor");

        b_next = (Button) view.findViewById(R.id.b_next);
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
}