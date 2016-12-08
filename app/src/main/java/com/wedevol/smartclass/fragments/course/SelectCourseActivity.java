package com.wedevol.smartclass.fragments.course;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.wedevol.smartclass.R;
import com.wedevol.smartclass.fragments.schedule.SelectScheduleActivity;

import java.util.ArrayList;
import java.util.List;

public class SelectCourseActivity extends AppCompatActivity {
    Button _nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_course);

        _nextButton = (Button) findViewById(R.id.btn_next);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Curso");
        actionBar.setDisplayHomeAsUpEnabled(true);

        List<String> courses =  new ArrayList<String>();
        courses.add("Cálculo 1");
        courses.add("Física 1");
        courses.add("Dibujo en Ingeniería");
        courses.add("Química 1");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, courses);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner sItems = (Spinner) findViewById(R.id.courses_spinner2);
        sItems.setAdapter(adapter);

        _nextButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SelectScheduleActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
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
