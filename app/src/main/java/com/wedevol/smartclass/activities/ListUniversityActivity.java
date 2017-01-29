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
import com.wedevol.smartclass.adapters.ListUniversitiesAdapter;
import com.wedevol.smartclass.models.University;
import com.wedevol.smartclass.utils.retrofit.IClassCallback;
import com.wedevol.smartclass.utils.retrofit.RestClient;

import java.util.ArrayList;
import java.util.List;

import retrofit.client.Response;

/** Created by paolo on 1/29/17.*/
public class ListUniversityActivity extends AppCompatActivity{
    private RecyclerView rv_universities;
    private List<University> universities;
    private Activity self;

    private ImageView iv_toolbar_back;
    private ProgressBar pb_charging;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_university_adapter);
        setElements();
        setActions();
    }

    private void setElements() {
        self = this;
        RestClient restClient = new RestClient(self);

        iv_toolbar_back = (ImageView) findViewById(R.id.iv_toolbar_back);
        TextView tv_detail_title = (TextView) findViewById(R.id.tv_detail_title);
        ImageView iv_toolbar_actual_screen = (ImageView) findViewById(R.id.iv_toolbar_actual_screen);

        tv_detail_title.setText("Universidades");
        iv_toolbar_actual_screen.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_pay_course_black));

        pb_charging = (ProgressBar) findViewById(R.id.pb_charging);
        rv_universities = (RecyclerView) findViewById(R.id.rv_universities);

        final TextView tv_no_universities = (TextView) findViewById(R.id.tv_no_universities);

        universities = new ArrayList<>();

        restClient.getWebservices().getUniversities("", new IClassCallback<JsonArray>(self){
            @Override
            public void success(JsonArray jsonArray, Response response) {
                super.success(jsonArray, response);
                getUniversities(jsonArray, tv_no_universities);
            }
        });
    }

    private void getUniversities(JsonArray jsonArray, TextView tv_no_courses) {

        for(int i = 0; i<jsonArray.size(); i++){
            University university = University.parseUniversity(jsonArray.get(i).getAsJsonObject());
            universities.add(university);
        }

        if(universities.size() == 0 ){
            tv_no_courses.setVisibility(View.VISIBLE);
            rv_universities.setVisibility(View.GONE);
        }else{
            rv_universities.setLayoutManager(new LinearLayoutManager(self));
            rv_universities.setAdapter(new ListUniversitiesAdapter(self, universities));
            rv_universities.setVisibility(View.VISIBLE);
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
