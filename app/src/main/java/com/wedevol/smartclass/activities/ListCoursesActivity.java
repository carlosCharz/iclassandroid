package com.wedevol.smartclass.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.wedevol.smartclass.R;
import com.wedevol.smartclass.adapters.ListCoursesAdapter;
import com.wedevol.smartclass.models.Course;
import com.wedevol.smartclass.utils.SharedPreferencesManager;
import com.wedevol.smartclass.utils.interfaces.Constants;
import com.wedevol.smartclass.utils.retrofit.IClassCallback;
import com.wedevol.smartclass.utils.retrofit.RestClient;

import java.util.ArrayList;
import java.util.List;

import retrofit.client.Response;

/** Created by paolorossi on 12/9/16.*/
public class ListCoursesActivity extends AppCompatActivity{
    private RecyclerView rv_courses;
    private List<Course> courses;
    private Activity self;

    private ImageView iv_toolbar_back;
    private ImageView iv_toolbar_actual_screen;
    private ProgressBar pb_charging;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_course_adapter);
        setElements();
        setActions();
    }

    private void setElements() {
        boolean isStudentCourse= getIntent().getBooleanExtra(Constants.STUDENT_COURSE_TYPE, false);
        self = this;
        RestClient restClient = new RestClient(self);

        iv_toolbar_back = (ImageView) findViewById(R.id.iv_toolbar_back);
        TextView tv_detail_title = (TextView) findViewById(R.id.tv_detail_title);
        iv_toolbar_actual_screen = (ImageView) findViewById(R.id.iv_toolbar_actual_screen);

        tv_detail_title.setText("Cursos");
        iv_toolbar_actual_screen.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_course_black));

        pb_charging = (ProgressBar) findViewById(R.id.pb_charging);
        //sv_search_course = (SearchView) findViewById(R.id.sv_search_course);
        rv_courses = (RecyclerView) findViewById(R.id.rv_courses);

        final TextView tv_no_courses = (TextView) findViewById(R.id.tv_no_courses);

        courses = new ArrayList<>();

        if(isStudentCourse){
            restClient.getWebservices().getStudentCourses("",
                    SharedPreferencesManager.getInstance(self).getUserInfo().getId(),
                    new IClassCallback<JsonArray>(self){
                        @Override
                        public void success(JsonArray jsonArray, Response response) {
                            super.success(jsonArray, response);
                            getCourses(jsonArray, tv_no_courses);
                        }
                    });
        }else{
            restClient.getWebservices().getAllCourses("", new IClassCallback<JsonArray>(self){
                @Override
                public void success(JsonArray jsonArray, Response response) {
                    super.success(jsonArray, response);
                    getCourses(jsonArray, tv_no_courses);
                }
            });
        }
    }

    private void getCourses(JsonArray jsonArray, TextView tv_no_courses) {

        for(int i = 0; i<jsonArray.size(); i++){
            Course course = Course.parseCourse(jsonArray.get(i).getAsJsonObject());
            courses.add(course);
        }

        if(courses.size() == 0 ){
            tv_no_courses.setVisibility(View.VISIBLE);
            rv_courses.setVisibility(View.GONE);
        }else{
            rv_courses.setLayoutManager(new LinearLayoutManager(self));
            rv_courses.setAdapter(new ListCoursesAdapter(self, courses));
            rv_courses.setVisibility(View.VISIBLE);
            tv_no_courses.setVisibility(View.GONE);
        }
        pb_charging.setVisibility(View.GONE);
    }

    private void setActions() {
        iv_toolbar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                self.finish();
            }
        });

        iv_toolbar_actual_screen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*TODO needs to open a drawer that enters from the right*/
            }
        });
        /* sv_search_course.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) { searchCourse(query); return false;}
            @Override
            public boolean onQueryTextChange(String newText) { searchCourse(newText);return false;}
            private void searchCourse(String wordFragment) { List<Course> searchedCourses = new ArrayList<>();rv_courses.setAdapter(new ListCoursesAdapter(self, searchedCourses));}
        });*/
    }
}