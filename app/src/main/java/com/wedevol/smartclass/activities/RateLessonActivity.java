package com.wedevol.smartclass.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.wedevol.smartclass.R;
import com.wedevol.smartclass.models.User;
import com.wedevol.smartclass.utils.SharedPreferencesManager;
import com.wedevol.smartclass.utils.interfaces.Constants;
import com.wedevol.smartclass.utils.retrofit.IClassCallback;
import com.wedevol.smartclass.utils.retrofit.RestClient;

import retrofit.client.Response;

/** Created by paolo on 1/25/17.*/
public class RateLessonActivity extends AppCompatActivity {
    private Button b_send;
    private Button b_cancel;
    private RatingBar rb_class_calification;
    private ImageView iv_toolbar_back;
    private RateLessonActivity self;
    private boolean isInstructor;
    private int lessonId;
    private RestClient restClient;
    private User user;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_lesson);
        setElements();
        setActions();
    }

    private void setElements() {
        self = this;
        isInstructor = SharedPreferencesManager.getInstance(this).getUserType();
        user = SharedPreferencesManager.getInstance(this).getUserInfo();
        String courseName = getIntent().getStringExtra(Constants.BUNDLE_COURSE_NAME);
        lessonId = getIntent().getIntExtra(Constants.BUNDLE_LESSON_ID, -1);
        restClient = new RestClient(this);


        TextView tv_course_user_type_announcement = (TextView) findViewById(R.id.tv_course_user_type_announcement);
        rb_class_calification = (RatingBar) findViewById(R.id.rb_class_calification);
        b_send = (Button) findViewById(R.id.b_send);
        b_cancel = (Button) findViewById(R.id.b_cancel);

        String formatableAnnouncement = "¡Acabas de completar tu asesoría de %s! ¿Como te pareció tu %s? Califícalo";
        String announcement;

        if (isInstructor) {
            announcement = String.format(formatableAnnouncement, courseName, "alumno");
        }else{
            announcement = String.format(formatableAnnouncement, courseName, "asesor");
        }
        tv_course_user_type_announcement.setText(announcement);

        iv_toolbar_back = (ImageView) findViewById(R.id.iv_toolbar_back);

        ImageView iv_toolbar_actual_screen = (ImageView) findViewById(R.id.iv_toolbar_actual_screen);
        iv_toolbar_actual_screen.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_star_black));
        TextView tv_detail_title = (TextView) findViewById(R.id.tv_detail_title);
        tv_detail_title.setText("Calificar Curso");
    }

    private void setActions() {
        b_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Double stars= Double.parseDouble(Float.toString(rb_class_calification.getRating()));
                if(isInstructor){
                    restClient.getWebservices().rateStudent("", lessonId, user.getId(), stars.intValue(), new JsonObject(), new IClassCallback<JsonObject>(self) {
                        @Override
                        public void success(JsonObject jsonObject, Response response) {
                            super.success(jsonObject, response);
                            setResult(Activity.RESULT_OK);
                            finish();
                        }
                    });
                }else{
                    restClient.getWebservices().rateInstructor("", lessonId, user.getId(), stars.intValue(), new JsonObject(), new IClassCallback<JsonObject>(self) {
                        @Override
                        public void success(JsonObject jsonObject, Response response) {
                            super.success(jsonObject, response);
                            setResult(Activity.RESULT_OK);
                            finish();
                        }
                    });
                }
            }
        });

        b_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                self.finish();
            }
        });

        iv_toolbar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                self.finish();
            }
        });
    }
}