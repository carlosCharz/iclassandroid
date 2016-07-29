package com.wedevol.smartclass;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RequestCourseActivity extends AppCompatActivity {

    @BindView(R.id.btn_request)
    Button _requestButton;
    @BindView(R.id.course_switch)
    Switch _courseSwitch;
    @BindView(R.id.courses_spinner2)
    Spinner _coursesSpinner;
    @BindView(R.id.input_course)
    EditText _inputCourse;

    String[] courseArray = {"Cálculo 1", "Física 1", "Química 1", "Dibujo en Ingeniería"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_course);
        ButterKnife.bind(this);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Solicitud de curso");
        actionBar.setDisplayHomeAsUpEnabled(true);

        _inputCourse.setVisibility(View.GONE);

        _courseSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    _inputCourse.setVisibility(View.GONE);
                } else {
                    _inputCourse.setVisibility(View.VISIBLE);
                }
            }
        });

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, courseArray);

        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        _coursesSpinner.setAdapter(adapter2);

        _requestButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
