package com.wedevol.smartclass.fragments.instructor;

import android.app.Activity;
import android.content.Intent;
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
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.wedevol.smartclass.R;
import com.wedevol.smartclass.activities.RateLessonActivity;
import com.wedevol.smartclass.adapters.ListPendingLessonsAdapter;
import com.wedevol.smartclass.models.Instructor;
import com.wedevol.smartclass.models.Lesson;
import com.wedevol.smartclass.utils.SharedPreferencesManager;
import com.wedevol.smartclass.utils.UtilMethods;
import com.wedevol.smartclass.utils.interfaces.Constants;
import com.wedevol.smartclass.utils.retrofit.IClassCallback;
import com.wedevol.smartclass.utils.retrofit.RestClient;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit.client.Response;

/** Created by paolorossi on 12/9/16.*/
public class InstructorDesktopFragment extends Fragment {
    RestClient restClient;
    private Activity self;

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
        self = getActivity();
        restClient = new RestClient(getContext());

        ImageView iv_user_profile_photo = (ImageView) view.findViewById(R.id.iv_user_profile_photo);
        TextView tv_counselor_level = (TextView) view.findViewById(R.id.tv_counselor_level);
        ProgressBar pb_counselor_progress = (ProgressBar) view.findViewById(R.id.pb_counselor_progress);
        TextView tv_counselor_counseling_time = (TextView) view.findViewById(R.id.tv_counselor_counseling_time);
        RatingBar rb_counselor_rating_stars = (RatingBar) view.findViewById(R.id.rb_counselor_rating_stars);
        TextView tv_counselor_rating_number = (TextView) view.findViewById(R.id.tv_counselor_rating_number);


        Drawable progress = rb_counselor_rating_stars.getProgressDrawable();
        DrawableCompat.setTint(progress, Color.WHITE);

        Instructor instructor = (Instructor) SharedPreferencesManager.getInstance(getActivity()).getUserInfo();
        UtilMethods.setPhoto(getActivity(), iv_user_profile_photo, instructor.getProfilePictureUrl(), Constants.USER_PHOTO);
        tv_counselor_rating_number.setText(""+ instructor.getRating());
        tv_counselor_level.setText("Nivel "+ instructor.getLevel());
        pb_counselor_progress.setProgress((instructor.getTotalHours() % 10) * 100);
        tv_counselor_counseling_time.setText(instructor.getTotalHours() + " hrs");
        rb_counselor_rating_stars.setRating((float)instructor.getRating());


        getInstructorLessons(view);
    }

    private void setActions() {
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == Constants.RATED_LESSON){
            if(resultCode == Activity.RESULT_OK) {
                getInstructorLessons(getView());
            } else{
                Toast.makeText(self, "Debes ponerle puntaje a tu último alumno", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void getInstructorLessons(final View view) {
        Instructor instructor = (Instructor) SharedPreferencesManager.getInstance(getActivity()).getUserInfo();
        final ProgressBar pb_charging = (ProgressBar) view.findViewById(R.id.pb_charging);
        final List<Lesson> pendingCounselleds = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        String date = calendar.get(Calendar.DAY_OF_MONTH) + "/" + (calendar.get(Calendar.MONTH)+1) + "/" + (calendar.get(Calendar.YEAR)-1);

        restClient.getWebservices().getInstructorComingClasses("", instructor.getId(), date, calendar.get(Calendar.HOUR_OF_DAY), "confirmed", new IClassCallback<JsonArray>(getActivity()) {
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
                } else {
                    Calendar calendar = Calendar.getInstance();
                    int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
                    int month = calendar.get(Calendar.MONTH) + 1;
                    int day = calendar.get(Calendar.DAY_OF_MONTH);

                    for (Lesson lesson: pendingCounselleds){
                        String[] date= lesson.getClassDate().split("/");
                        boolean datePassed = lesson.getEndTime() <= hourOfDay && Integer.parseInt(date[0]) <= day && Integer.parseInt(date[1]) <= month;
                        boolean monthPassed = Integer.parseInt(date[1]) < month;
                        if(datePassed || monthPassed) {
                            Intent intent = new Intent(self, RateLessonActivity.class);
                            intent.putExtra(Constants.BUNDLE_LESSON_ID, lesson.getId());
                            intent.putExtra(Constants.BUNDLE_COURSE_NAME, lesson.getCourseName());
                            startActivityForResult(intent, Constants.RATED_LESSON);
                        }
                    }
                }
                pb_charging.setVisibility(View.GONE);
            }
        });
    }
}