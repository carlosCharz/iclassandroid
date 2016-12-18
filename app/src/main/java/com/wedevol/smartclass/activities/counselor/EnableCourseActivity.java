package com.wedevol.smartclass.activities.counselor;

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

import com.wedevol.smartclass.R;
import com.wedevol.smartclass.activities.ListCoursesActivity;
import com.wedevol.smartclass.utils.dialogs.SuggestCourseDialogFragment;
import com.wedevol.smartclass.utils.interfaces.Constants;

/** Created by paolo on 12/17/16.*/
public class EnableCourseActivity extends AppCompatActivity{
    private Button b_next;
    private Button b_suggest;
    private TextView tv_pick_day;
    private Activity self;
    private ImageView iv_toolbar_back;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enable_course);
        setElements();
        setActions();
    }

    private void setElements() {
        self = this;
        b_next = (Button) findViewById(R.id.b_next);
        b_suggest = (Button) findViewById(R.id.b_suggest);
        tv_pick_day = (TextView) findViewById(R.id.tv_pick_day);

        iv_toolbar_back = (ImageView) findViewById(R.id.iv_toolbar_back);

        ImageView iv_toolbar_actual_screen = (ImageView) findViewById(R.id.iv_toolbar_actual_screen);
        iv_toolbar_actual_screen.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_course_black));
        TextView tv_detail_title = (TextView) findViewById(R.id.tv_detail_title);
        tv_detail_title.setText("Habilitar Curso");
    }

    private void setActions() {
        b_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                self.finish();
            }
        });

        b_suggest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SuggestCourseDialogFragment suggestCourseDialogFragment = SuggestCourseDialogFragment.newInstance(R.layout.dialog_suggest_course);
                suggestCourseDialogFragment.show(((FragmentActivity)self).getSupportFragmentManager(), "SuggestCourseDialog");
            }
        });

        tv_pick_day.setOnClickListener(new View.OnClickListener() {
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
            tv_pick_day.setText(courseName);
        }
    }
}