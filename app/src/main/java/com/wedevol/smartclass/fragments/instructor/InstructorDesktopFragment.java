package com.wedevol.smartclass.fragments.instructor;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.wedevol.smartclass.R;
import com.wedevol.smartclass.adapters.ListPendingCounselsAdapter;
import com.wedevol.smartclass.models.Lesson;
import com.wedevol.smartclass.models.Course;
import com.wedevol.smartclass.models.Instructor;
import com.wedevol.smartclass.models.Schedule;
import com.wedevol.smartclass.utils.UtilMethods;
import com.wedevol.smartclass.utils.interfaces.Constants;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/** Created by paolorossi on 12/9/16.*/
public class InstructorDesktopFragment extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_instructor_desktop, container, false);
        setElements(view);
        setActions();
        return view;
    }

    private void setElements(View view) {
        ImageView iv_user_profile_photo = (ImageView) view.findViewById(R.id.iv_user_profile_photo);
        TextView tv_counselor_level = (TextView) view.findViewById(R.id.tv_counselor_level);
        ProgressBar pb_counselor_progress = (ProgressBar) view.findViewById(R.id.pb_counselor_progress);
        TextView tv_counselor_counseling_time = (TextView) view.findViewById(R.id.tv_counselor_counseling_time);
        RatingBar rb_counselor_rating = (RatingBar) view.findViewById(R.id.rb_counselor_rating_stars);
        TextView tv_counselor_rating_number = (TextView) view.findViewById(R.id.tv_counselor_rating_number);

        Drawable progress = rb_counselor_rating.getProgressDrawable();
        DrawableCompat.setTint(progress, Color.WHITE);
        rb_counselor_rating.setRating(4.7f);

        tv_counselor_rating_number.setText("4.7");

        UtilMethods.setPhoto(getActivity(), iv_user_profile_photo, "", Constants.USER_PHOTO);
        //student.getLessons().size() + " cursos"
        tv_counselor_level.setText("Nivel 2");
        //(int) student.getRating()
        pb_counselor_progress.setProgress(45);
        //student.getTotalHours() + " hrs"
        tv_counselor_counseling_time.setText("50 hrs");

        List<Lesson> pendingCounselleds = new ArrayList<>();
        pendingCounselleds.add(new Lesson(1, new Instructor(), new Course(), new Schedule(), new Date(), "status"));
        pendingCounselleds.add(new Lesson(2, new Instructor(), new Course(), new Schedule(), new Date(), "status"));
        pendingCounselleds.add(new Lesson(3, new Instructor(), new Course(), new Schedule(), new Date(), "status"));

        TextView tv_no_counselings = (TextView) view.findViewById(R.id.tv_no_counselings);

        RecyclerView rv_pending_counselings = (RecyclerView) view.findViewById(R.id.rv_pending_counselings);
        rv_pending_counselings.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_pending_counselings.setAdapter(new ListPendingCounselsAdapter(getActivity(), pendingCounselleds));

        if(pendingCounselleds.size() == 0){
            tv_no_counselings.setVisibility(View.VISIBLE);
            rv_pending_counselings.setVisibility(View.GONE);
        }
    }

    private void setActions() {

    }
}