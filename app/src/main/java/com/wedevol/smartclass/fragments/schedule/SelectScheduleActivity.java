package com.wedevol.smartclass.fragments.schedule;

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
import com.wedevol.smartclass.fragments.course.SelectCourseActivity;
import com.wedevol.smartclass.fragments.paycourse.ConfirmCourseActivity;

import java.util.ArrayList;
import java.util.List;

public class SelectScheduleActivity extends AppCompatActivity {

    Button _next2Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_schedule);

        _next2Button = (Button) findViewById(R.id.btn_next2);


        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Horario");
        actionBar.setDisplayHomeAsUpEnabled(true);

        List<String> day =  new ArrayList<>();
        day.add("Lunes");
        day.add("Martes");
        day.add("Miércoles");
        day.add("Jueves");
        day.add("Viernes");
        day.add("Sábado");
        day.add("Domingo");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, day);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner sItems = (Spinner) findViewById(R.id.days_spinner);
        sItems.setAdapter(adapter);

        _next2Button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ConfirmCourseActivity.class);
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
                Intent intent = new Intent(getApplicationContext(), SelectCourseActivity.class);
                startActivity(intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
