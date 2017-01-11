package com.wedevol.smartclass.activities.instructor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.wedevol.smartclass.R;
import com.wedevol.smartclass.activities.ListCoursesActivity;
import com.wedevol.smartclass.utils.SharedPreferencesManager;
import com.wedevol.smartclass.utils.dialogs.SuggestCourseDialogFragment;
import com.wedevol.smartclass.utils.interfaces.Constants;
import com.wedevol.smartclass.utils.retrofit.IClassCallback;
import com.wedevol.smartclass.utils.retrofit.RestClient;

import retrofit.client.Response;

/** Created by paolo on 12/17/16.*/
public class EnableCourseActivity extends AppCompatActivity{
    private Button b_next;
    private Button b_suggest;
    private TextView tv_pick_course;
    private TextView tv_course_selection_option;
    private Activity self;
    private ImageView iv_toolbar_back;
    private boolean studentType;
    private RestClient restClient;
    private int courseId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enable_course);
        setElements();
        setActions();
    }

    private void setElements() {
        studentType = getIntent().getBooleanExtra(Constants.STUDENT_TYPE, false);
        self = this;
        restClient = new RestClient(this);

        b_next = (Button) findViewById(R.id.b_next);
        b_suggest = (Button) findViewById(R.id.b_suggest);
        tv_pick_course = (TextView) findViewById(R.id.tv_pick_course);
        tv_course_selection_option = (TextView) findViewById(R.id.tv_course_selection_option);

        iv_toolbar_back = (ImageView) findViewById(R.id.iv_toolbar_back);

        ImageView iv_toolbar_actual_screen = (ImageView) findViewById(R.id.iv_toolbar_actual_screen);
        iv_toolbar_actual_screen.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_course_black));
        TextView tv_detail_title = (TextView) findViewById(R.id.tv_detail_title);
        tv_detail_title.setText("Habilitar Curso");

        if(studentType){
            tv_course_selection_option.setText("Selecciona el curso en el que desees buscar asesorías.");
        }
    }

    private void setActions() {
        b_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JsonObject jsonObject = new JsonObject();

                JsonObject jsonObjectId = new JsonObject();
                jsonObjectId.addProperty("instructorId", SharedPreferencesManager.getInstance(self).getUserInfo().getId());
                jsonObjectId.addProperty("courseId", courseId);
                jsonObject.add("id", jsonObjectId);

                jsonObject.addProperty("status", "payed");//jsonObject.addProperty("status", "pendingPayment");
                jsonObject.addProperty("price", 25);
                jsonObject.addProperty("currency", "S/.");

                restClient.getWebservices().enrollOnCourseInstructor("", jsonObject, new IClassCallback<JsonObject>(self){
                    @Override
                    public void success(JsonObject jsonObject, Response response) {
                        super.success(jsonObject, response);
                        self.finish();
                    }
                });
            }
        });

        b_suggest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SuggestCourseDialogFragment suggestCourseDialogFragment = SuggestCourseDialogFragment.newInstance(R.layout.dialog_suggest_course);
                suggestCourseDialogFragment.show(((FragmentActivity)self).getSupportFragmentManager(), "SuggestCourseDialog");
            }
        });

        tv_pick_course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(self, ListCoursesActivity.class);
                startActivityForResult(intent, Constants.CHOOSEN_COURSE);
            }
        });

        iv_toolbar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                self.finish();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if((requestCode == Constants.CHOOSEN_COURSE) && (resultCode == Activity.RESULT_OK)) {
            String courseName = data.getStringExtra(Constants.BUNDLE_COURSE_NAME);
            courseId = data.getIntExtra(Constants.BUNDLE_COURSE_ID, -1);
            tv_pick_course.setText(courseName);
        }
    }
}