package com.wedevol.smartclass.fragments.paycourse;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.wedevol.smartclass.R;
import com.wedevol.smartclass.fragments.schedule.SelectScheduleActivity;

public class ConfirmCourseActivity extends AppCompatActivity {
    Button _confirmButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_course);

        _confirmButton = (Button) findViewById(R.id.btn_confirm_course);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Confirmación");
        actionBar.setDisplayHomeAsUpEnabled(true);

        _confirmButton.setOnClickListener(new View.OnClickListener() {

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
                Intent intent = new Intent(getApplicationContext(), SelectScheduleActivity.class);
                startActivity(intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
