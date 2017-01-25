package com.wedevol.smartclass.activities;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.wedevol.smartclass.R;

/** Created by paolo on 1/25/17.*/
public class RateClassActivity extends AppCompatActivity {
    private Button b_send;
    private Button b_cancel;
    private RatingBar rb_class_calification;
    private ImageView iv_toolbar_back;
    private RateClassActivity self;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_class);
        setElements();
        setActions();
    }

    private void setElements() {
        self = this;

        TextView tv_course_user_type_announcement = (TextView) findViewById(R.id.tv_course_user_type_announcement);
        rb_class_calification = (RatingBar) findViewById(R.id.rb_class_calification);
        b_send = (Button) findViewById(R.id.b_send);
        b_cancel = (Button) findViewById(R.id.b_cancel);

        //TODO correct spelling
        tv_course_user_type_announcement.setText("Acabas de completar tu asesoria! Como te parecio tu alumno? Calificalo");

        iv_toolbar_back = (ImageView) findViewById(R.id.iv_toolbar_back);

        ImageView iv_toolbar_actual_screen = (ImageView) findViewById(R.id.iv_toolbar_actual_screen);
        iv_toolbar_actual_screen.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_course_black));
        TextView tv_detail_title = (TextView) findViewById(R.id.tv_detail_title);
        tv_detail_title.setText("Calificar Curso");
    }

    private void setActions() {
        b_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int num= rb_class_calification.getNumStars();
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