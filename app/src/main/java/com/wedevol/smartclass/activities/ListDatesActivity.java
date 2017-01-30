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
import com.wedevol.smartclass.utils.interfaces.Constants;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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
        boolean is_simple_date = getIntent().getBooleanExtra(Constants.BUNDLE_SIMPLE_DATE,false);

        iv_toolbar_back = (ImageView) findViewById(R.id.iv_toolbar_back);
        tv_detail_title = (TextView) findViewById(R.id.tv_detail_title);
        iv_toolbar_actual_screen = (ImageView) findViewById(R.id.iv_toolbar_actual_screen);
        rv_dates = (RecyclerView) findViewById(R.id.rv_dates);
        detailedDates = new ArrayList<>();

        if(!is_simple_date){
            tv_detail_title.setText("Fechas");
            iv_toolbar_actual_screen.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_course_black));

            Calendar calendar = Calendar.getInstance();
            for(int i=0; i<14; i++) {
                SimpleDateFormat sdfDate = new SimpleDateFormat("dd/MM/yyyy");
                String now = sdfDate.format(calendar.getTime());

                SimpleDateFormat sdfDayOfWeek = new SimpleDateFormat("EEEE");
                String dayOfTheWeek = sdfDayOfWeek.format(calendar.getTime());

                detailedDates.add(dayOfTheWeek + " - " + now);
                calendar.add(Calendar.DAY_OF_MONTH, 1);
            }
        } else {
            tv_detail_title.setText("Dias de la semana");
            iv_toolbar_actual_screen.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_schedule_black));

            detailedDates.add("Lunes");
            detailedDates.add("Martes");
            detailedDates.add("Miércoles");
            detailedDates.add("Jueves");
            detailedDates.add("Viernes");
            detailedDates.add("Sábado");
            detailedDates.add("Domingo");
        }

        rv_dates.setAdapter(new ScaleInAnimationAdapter(new ListDatesAdapter(this, detailedDates, is_simple_date)));
        rv_dates.setLayoutManager(new LinearLayoutManager(this));
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