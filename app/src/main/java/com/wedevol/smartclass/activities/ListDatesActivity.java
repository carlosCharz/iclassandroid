package com.wedevol.smartclass.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wedevol.smartclass.R;
import com.wedevol.smartclass.adapters.ListDatesAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;

/** Created by paolo on 12/13/16.*/
public class ListDatesActivity extends AppCompatActivity {
    //private SearchView sv_search_course;
    private RecyclerView rv_dates;
    private List<String> detailedDates;
    private Activity self;

    private ImageView iv_toolbar_back;
    private TextView tv_detail_title;
    private ImageView iv_toolbar_actual_screen;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_dates_adapter);
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
        rv_dates = (RecyclerView) findViewById(R.id.rv_courses);

        detailedDates = new ArrayList<>();
        Date date = new Date();
        for(int i=0; i<14; i++) {
            date.setDate(date.getDay() + 1);
            SimpleDateFormat sdfDate = new SimpleDateFormat("MM/dd/yy", Locale.US);
            String now = sdfDate.format(date);

            SimpleDateFormat sdfDayOfWeek = new SimpleDateFormat("EEEE", Locale.US);
            String dayOfTheWeek = sdfDayOfWeek.format(date);

            detailedDates.add(dayOfTheWeek + " - " + now);
        }

        rv_dates.setLayoutManager(new LinearLayoutManager(this));
        rv_dates.setAdapter(new ScaleInAnimationAdapter(new ListDatesAdapter(this, detailedDates)));
        rv_dates.setVisibility(View.VISIBLE);
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