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
import com.wedevol.smartclass.adapters.ListFacultiesAdapter;
import com.wedevol.smartclass.models.Faculty;
import com.wedevol.smartclass.utils.interfaces.Constants;
import com.wedevol.smartclass.utils.retrofit.IClassCallback;
import com.wedevol.smartclass.utils.retrofit.RestClient;

import java.util.ArrayList;
import java.util.List;

import retrofit.client.Response;

/** Created by paolo on 1/29/17.*/
public class ListFacultyActivity extends AppCompatActivity{
    private RecyclerView rv_faculties;
    private List<Faculty> faculties;
    private Activity self;

    private ImageView iv_toolbar_back;
    private ProgressBar pb_charging;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_faculty_adapter);
        setElements();
        setActions();
    }

    private void setElements() {
        int universityId= getIntent().getIntExtra(Constants.BUNDLE_UNIVERSITY_ID, -1);
        self = this;
        RestClient restClient = new RestClient(self);

        iv_toolbar_back = (ImageView) findViewById(R.id.iv_toolbar_back);
        TextView tv_detail_title = (TextView) findViewById(R.id.tv_detail_title);
        ImageView iv_toolbar_actual_screen = (ImageView) findViewById(R.id.iv_toolbar_actual_screen);

        tv_detail_title.setText("Facultades");
        iv_toolbar_actual_screen.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_pay_course_black));

        pb_charging = (ProgressBar) findViewById(R.id.pb_charging);
        rv_faculties = (RecyclerView) findViewById(R.id.rv_faculties);

        final TextView tv_no_faculties = (TextView) findViewById(R.id.tv_no_faculties);

        faculties = new ArrayList<>();

        restClient.getWebservices().getUniversityFaculties("", universityId, new IClassCallback<JsonArray>(self){
            @Override
            public void success(JsonArray jsonArray, Response response) {
                super.success(jsonArray, response);
                getFaculties(jsonArray, tv_no_faculties);
            }
        });
    }

    private void getFaculties(JsonArray jsonArray, TextView tv_no_courses) {
        for(int i = 0; i<jsonArray.size(); i++){
            Faculty faculty = Faculty.parseFaculty(jsonArray.get(i).getAsJsonObject());
            faculties.add(faculty);
        }

        if(faculties.size() == 0 ){
            tv_no_courses.setVisibility(View.VISIBLE);
            rv_faculties.setVisibility(View.GONE);
        }else{
            rv_faculties.setLayoutManager(new LinearLayoutManager(self));
            rv_faculties.setAdapter(new ListFacultiesAdapter(self, faculties));
            rv_faculties.setVisibility(View.VISIBLE);
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
    }
}
