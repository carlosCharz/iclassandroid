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

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.wedevol.smartclass.R;
import com.wedevol.smartclass.adapters.ListPendingLessonsAdapter;
import com.wedevol.smartclass.models.Instructor;
import com.wedevol.smartclass.models.Lesson;
import com.wedevol.smartclass.utils.SharedPreferencesManager;
import com.wedevol.smartclass.utils.UtilMethods;
import com.wedevol.smartclass.utils.interfaces.Constants;
import com.wedevol.smartclass.utils.retrofit.IClassCallback;
import com.wedevol.smartclass.utils.retrofit.RestClient;

import java.util.ArrayList;
import java.util.List;

import retrofit.client.Response;

/** Created by paolorossi on 12/9/16.*/
public class InstructorDesktopFragment extends Fragment {
    RestClient restClient;
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

    private void setElements(final View view) {
        restClient = new RestClient(getContext());

        ImageView iv_user_profile_photo = (ImageView) view.findViewById(R.id.iv_user_profile_photo);
        TextView tv_counselor_level = (TextView) view.findViewById(R.id.tv_counselor_level);
        ProgressBar pb_counselor_progress = (ProgressBar) view.findViewById(R.id.pb_counselor_progress);
        TextView tv_counselor_counseling_time = (TextView) view.findViewById(R.id.tv_counselor_counseling_time);
        RatingBar rb_counselor_rating = (RatingBar) view.findViewById(R.id.rb_counselor_rating_stars);
        TextView tv_counselor_rating_number = (TextView) view.findViewById(R.id.tv_counselor_rating_number);

        Drawable progress = rb_counselor_rating.getProgressDrawable();
        DrawableCompat.setTint(progress, Color.WHITE);

        Instructor instructor = (Instructor) SharedPreferencesManager.getInstance(getActivity()).getUserInfo();

        UtilMethods.setPhoto(getActivity(), iv_user_profile_photo, instructor.getProfilePictureUrl(), Constants.USER_PHOTO);
        tv_counselor_rating_number.setText(""+ instructor.getRating());
        tv_counselor_level.setText("Nivel "+ instructor.getLevel());
        pb_counselor_progress.setProgress(((Double) instructor.getRating()).intValue());
        tv_counselor_counseling_time.setText(instructor.getTotalHours() + " hrs");
        rb_counselor_rating.setRating((float)instructor.getRating());

        final List<Lesson> pendingCounselleds = new ArrayList<>();

        restClient.getWebservices().instructorLessons("", instructor.getId(), "8/1/2017", 2, "confirmed", new IClassCallback<JsonArray>(getActivity()) {
            @Override
            public void success(JsonArray jsonArray, Response response) {
                super.success(jsonArray, response);
                for(JsonElement jsonElement: jsonArray){
                    pendingCounselleds.add(Lesson.parseLesson(jsonElement.getAsJsonObject()));
                }

                RecyclerView rv_pending_counselings = (RecyclerView) view.findViewById(R.id.rv_pending_counselings);
                rv_pending_counselings.setLayoutManager(new LinearLayoutManager(getActivity()));
                rv_pending_counselings.setAdapter(new ListPendingLessonsAdapter(getActivity(), pendingCounselleds));

                if(pendingCounselleds.size() == 0){
                    TextView tv_no_counselings = (TextView) view.findViewById(R.id.tv_no_counselings);
                    tv_no_counselings.setVisibility(View.VISIBLE);
                    rv_pending_counselings.setVisibility(View.GONE);
                }
            }
        });
    }

    private void setActions() {

    }
}