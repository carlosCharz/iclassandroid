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
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.wedevol.smartclass.R;
import com.wedevol.smartclass.activities.student.RequestCounselActivity;
import com.wedevol.smartclass.adapters.ListPendingCounselsAdapter;
import com.wedevol.smartclass.models.Class;
import com.wedevol.smartclass.models.Course;
import com.wedevol.smartclass.models.Instructor;
import com.wedevol.smartclass.models.Schedule;
import com.wedevol.smartclass.utils.UtilMethods;
import com.wedevol.smartclass.utils.interfaces.Constants;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/** Created by paolorossi on 12/9/16.*/
public class StudentLockerFragment extends Fragment{
    private Button b_ask_counsel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_student_locker, container, false);
        setElements(view);
        setActions();
        return view;
    }

    private void setElements(View view) {
        b_ask_counsel = (Button) view.findViewById(R.id.b_ask_counsel);

        TextView tv_detail_title = (TextView) view.findViewById(R.id.tv_detail_title);
        tv_detail_title.setText("Seleccionar Curso");

        ImageView iv_user_profile_photo = (ImageView) view.findViewById(R.id.iv_user_profile_photo);
        TextView tv_student_level = (TextView) view.findViewById(R.id.tv_student_level);
        ProgressBar pb_student_progress = (ProgressBar) view.findViewById(R.id.pb_student_progress);
        TextView tv_student_counselled_time = (TextView) view.findViewById(R.id.tv_student_counselled_time);

        UtilMethods.setPhoto(getActivity(), iv_user_profile_photo, "", Constants.USER_PHOTO);
        //student.getClasses().size() + " cursos"
        tv_student_level.setText("20 cursos");
        //(int) student.getRating()
        pb_student_progress.setProgress(45);
        //student.getTotalHours() + " hrs"
        tv_student_counselled_time.setText("50 hrs");

        List<Class> pendingCounsels = new ArrayList<>();
        pendingCounsels.add(new Class(1, new Instructor(), new Course(), new Schedule(), new Date(), "status"));
        pendingCounsels.add(new Class(2, new Instructor(), new Course(), new Schedule(), new Date(), "status"));
        pendingCounsels.add(new Class(3, new Instructor(), new Course(), new Schedule(), new Date(), "status"));

        TextView tv_no_counsels = (TextView) view.findViewById(R.id.tv_no_counsels);

        RecyclerView rv_pending_counsels = (RecyclerView) view.findViewById(R.id.rv_pending_counsels);
        rv_pending_counsels.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_pending_counsels.setAdapter(new ListPendingCounselsAdapter(getActivity(), pendingCounsels));

        if(pendingCounsels.size() == 0){
            tv_no_counsels.setVisibility(View.VISIBLE);
            rv_pending_counsels.setVisibility(View.GONE);
        }
    }

    private void setActions() {
        b_ask_counsel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), RequestCounselActivity.class);
                getActivity().startActivity(intent);
            }
        });
    }
}