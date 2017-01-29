package com.wedevol.smartclass.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

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

    private SearchView sv_search_course;
    private ImageView iv_toolbar_back;
    private ProgressBar pb_charging;
    private boolean firstSearch;
    private ListCoursesAdapter listCoursesAdapter;
    private List<Course> currentCoursesList;
    private ImageView collapsedSearchIcon;

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
        firstSearch = true;
        RestClient restClient = new RestClient(self);

        iv_toolbar_back = (ImageView) findViewById(R.id.iv_toolbar_back);
        TextView tv_detail_title = (TextView) findViewById(R.id.tv_detail_title);
        ImageView iv_toolbar_actual_screen = (ImageView) findViewById(R.id.iv_toolbar_actual_screen);

        tv_detail_title.setText("Cursos");
        iv_toolbar_actual_screen.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_course_black));

        pb_charging = (ProgressBar) findViewById(R.id.pb_charging);
        sv_search_course = (SearchView) findViewById(R.id.sv_search_course);
        collapsedSearchIcon = (ImageView) sv_search_course.findViewById(android.support.v7.appcompat.R.id.search_button);

        rv_courses = (RecyclerView) findViewById(R.id.rv_courses);

        final TextView tv_no_courses = (TextView) findViewById(R.id.tv_no_courses);

        courses = new ArrayList<>();

        if(isStudentCourse){
            restClient.getWebservices().getStudentCourses("", SharedPreferencesManager.getInstance(self).getUserInfo().getId(),
                    "payed,free", new IClassCallback<JsonArray>(self){
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
            listCoursesAdapter = new ListCoursesAdapter(self, courses);
            rv_courses.setAdapter(listCoursesAdapter);
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

        collapsedSearchIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        sv_search_course.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) { searchCourse(query); return false;}
            @Override
            public boolean onQueryTextChange(String newText) { searchCourse(newText);return false;}
            private void searchCourse(String wordFragment) {
                if(listCoursesAdapter != null) {
                    if (firstSearch && wordFragment.length() > 2) {
                        firstSearch = false;
                        currentCoursesList = listCoursesAdapter.getItems();
                    }
                    rv_courses.setAdapter(new ListCoursesAdapter(self, searchCourseSubset(currentCoursesList, wordFragment)));
                }else{
                    Toast.makeText(self, "No hay cursos disponibles", Toast.LENGTH_SHORT).show();
                }
            }

            private List<Course> searchCourseSubset(List<Course> currentCoursesList, String wordFragment) {
                List<Course> subsetCoursesList = new ArrayList<>();
                for (int i = 0; i < currentCoursesList.size(); i++){
                    Course course = currentCoursesList.get(i);
                    if(course.getDescription().contains(wordFragment) || course.getFaculty().contains(wordFragment) ||
                            course.getName().contains(wordFragment) || course.getUniversity().contains(wordFragment)){
                        subsetCoursesList.add(course);
                    }
                }
                return subsetCoursesList;
            }
        });
    }
}