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
import android.widget.TextView;

import com.wedevol.smartclass.R;
import com.wedevol.smartclass.adapters.ListCoursesAdapter;
import com.wedevol.smartclass.models.Course;

import java.util.ArrayList;
import java.util.List;

/** Created by paolorossi on 12/9/16.*/
public class ListCoursesActivity extends AppCompatActivity{
    private SearchView sv_search_course;
    private RecyclerView rv_courses;
    private List<Course> courses;
    private Activity self;

    private ImageView iv_toolbar_back;
    private TextView tv_detail_title;
    private ImageView iv_toolbar_actual_screen;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_course_adapter);
        setElements();
        setActions();
    }

    private void setElements() {
        self = this;

        iv_toolbar_back = (ImageView) findViewById(R.id.iv_toolbar_back);
        tv_detail_title = (TextView) findViewById(R.id.tv_detail_title);
        iv_toolbar_actual_screen = (ImageView) findViewById(R.id.iv_toolbar_actual_screen);

        tv_detail_title.setText("Cursos");
        iv_toolbar_actual_screen.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_course_black));

        //sv_search_course = (SearchView) findViewById(R.id.sv_search_course);
        rv_courses = (RecyclerView) findViewById(R.id.rv_courses);

        courses = new ArrayList<>();

        Course course = new Course(1, "IMU", "No hay descripcion", "PUCP", "EGC");
        courses.add(course);
        course = new Course(2, "Mineria de datos", "No hay descripcion", "PUCP", "Ing. Industrial");
        courses.add(course);
        course = new Course(3, "Bases de Datos", "No hay descripcion", "PUCP", "Ing. Informatica");
        courses.add(course);
        course = new Course(4, "Gestion de Empresas", "No hay descripcion", "PUCP", "Ing. Industrial");
        courses.add(course);
        course = new Course(5, "Lenguajes de Programacion 1", "No hay descripcion", "PUCP", "Ing. Informatica");
        courses.add(course);
        course = new Course(6, "Estructuras I", "No hay descripcion", "PUCP", "Ing. Civil");
        courses.add(course);
        course = new Course(7, "Filosofia VI", "No hay descripcion", "PUCP", "Filosofia");
        courses.add(course);
        course = new Course(8, "Introduccion a la vida universitaria", "No hay descripcion", "PUCP", "EGL");
        courses.add(course);
        course = new Course(9, "Matematicas I", "No hay descripcion", "PUCP", "EGL");
        courses.add(course);
        course = new Course(10, "Principios de Ciencias de la Computacion", "No hay descripcion", "PUCP", "Ing. Informatica");
        courses.add(course);

        rv_courses.setLayoutManager(new LinearLayoutManager(this));
        rv_courses.setAdapter(new ListCoursesAdapter(this, courses));
        rv_courses.setVisibility(View.VISIBLE);
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
/*
        sv_search_course.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchCourse(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchCourse(newText);
                return false;
            }

            private void searchCourse(String wordFragment) {
                List<Course> searchedCourses = new ArrayList<>();


                rv_courses.setAdapter(new ListCoursesAdapter(self, searchedCourses));
            }
        });
*/

    }
}