package com.wedevol.smartclass.activities.counselor;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.wedevol.smartclass.R;
import com.wedevol.smartclass.activities.ListCalendarDatesActivity;
import com.wedevol.smartclass.utils.interfaces.Constants;

import java.util.Calendar;

/** Created by paolorossi on 12/14/16.*/
public class CreateScheduleActivity extends AppCompatActivity {
    private TextView tv_pick_day;
    private EditText et_begin_time;
    private EditText et_end_time;
    private Button b_cancel;
    private Button b_save;
    private Activity self;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_schedule);
        setElements();
        setActions();
    }

    private void setElements() {
        self = this;

        tv_pick_day = (TextView) findViewById(R.id.tv_pick_day);
        et_begin_time = (EditText) findViewById(R.id.et_begin_time);
        et_end_time = (EditText) findViewById(R.id.et_end_time);
        b_cancel = (Button) findViewById(R.id.b_cancel);
        b_save = (Button) findViewById(R.id.b_save);
    }

    private void setActions() {
        tv_pick_day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(self, ListCalendarDatesActivity.class);
                intent.putExtra(Constants.BUNDLE_SIMPLE_DATE, true);
                startActivityForResult(intent, Constants.CHOOSEN_DATE);
            }
        });

        b_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                self.finish();
            }
        });

        b_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                self.finish();
            }
        });

        et_begin_time.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Calendar mcurrentTime = Calendar.getInstance();
                        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                        int minute = mcurrentTime.get(Calendar.MINUTE);
                        TimePickerDialog mTimePicker;
                        mTimePicker = new TimePickerDialog(self, new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                                et_begin_time.setText(""+selectedHour);
                            }
                        }, hour, minute, true);//Yes 24 hour time
                        mTimePicker.setTitle("Select Time");
                        mTimePicker.show();

                    }
                }
        );

        et_end_time.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Calendar mcurrentTime = Calendar.getInstance();
                        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                        int minute = mcurrentTime.get(Calendar.MINUTE);
                        TimePickerDialog mTimePicker;
                        mTimePicker = new TimePickerDialog(self, new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                                et_end_time.setText(""+selectedHour);
                            }
                        }, hour, minute, true);//Yes 24 hour time
                        mTimePicker.setTitle("Select Time");
                        mTimePicker.show();

                    }
                }
        );

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if((requestCode == Constants.CHOOSEN_DATE) && (resultCode == Activity.RESULT_OK)) {
            String weekOfDay = data.getStringExtra(Constants.BUNDLE_DATE);
            tv_pick_day.setText(weekOfDay);
        }
    }
}