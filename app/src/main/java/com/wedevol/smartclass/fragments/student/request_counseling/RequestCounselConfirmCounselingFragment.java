package com.wedevol.smartclass.fragments.student.request_counseling;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.wedevol.smartclass.R;

/** Created by paolorossi on 12/12/16.*/
public class RequestCounselConfirmCounselingFragment extends Fragment {
    private Button b_finish;

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
        View view = inflater.inflate(R.layout.fragment_request_counsel_confirm_counseling, container, false);

        setupElements(view);
        setupActions();
        return view;
    }

    private void setupElements(View view) {
        b_finish = (Button) view.findViewById(R.id.b_finish);
    }

    private void setupActions() {
        b_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });
    }
}