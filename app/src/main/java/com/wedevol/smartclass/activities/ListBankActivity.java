package com.wedevol.smartclass.activities;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.wedevol.smartclass.R;
import com.wedevol.smartclass.adapters.ListBanksAdapter;
import com.wedevol.smartclass.utils.retrofit.RestClient;

import java.util.ArrayList;
import java.util.List;

/** Created by paolo on 12/20/16.*/
public class ListBankActivity extends AppCompatActivity {
    private ListBankActivity self;
    private ImageView iv_toolbar_back;
    private TextView tv_detail_title;
    private ImageView iv_toolbar_actual_screen;
    private ProgressBar pb_charging;
    private RecyclerView rv_banks;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_bank_adapter);
        setElements();
        setActions();
    }

    private void setElements() {
        self = this;
        RestClient restClient = new RestClient(this);

        iv_toolbar_back = (ImageView) findViewById(R.id.iv_toolbar_back);
        tv_detail_title = (TextView) findViewById(R.id.tv_detail_title);
        iv_toolbar_actual_screen = (ImageView) findViewById(R.id.iv_toolbar_actual_screen);

        tv_detail_title.setText("Bancos");
        iv_toolbar_actual_screen.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_pay_course_black));

        pb_charging = (ProgressBar) findViewById(R.id.pb_charging);
        rv_banks = (RecyclerView) findViewById(R.id.rv_banks);

        List<String> banks = new ArrayList<>();
        banks.add("BCP");
        banks.add("MiBanco");
        banks.add("Wiesse");
        banks.add("Interbank");
        banks.add("El bolsillo de Luchito");

        if(banks.size() == 0 ){
            rv_banks.setVisibility(View.GONE);
        }else{
            rv_banks.setLayoutManager(new LinearLayoutManager(self));
            rv_banks.setAdapter(new ListBanksAdapter(self, banks));
            rv_banks.setVisibility(View.VISIBLE);
        }

        pb_charging.setVisibility(View.GONE);
        /*
        restClient.getWebservices().getAllCourses("", new IClassCallback<JsonArray>(self){
            @Override
            public void success(JsonArray jsonArray, Response response) {
                super.success(jsonArray, response);
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
        });*/
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
    }
}