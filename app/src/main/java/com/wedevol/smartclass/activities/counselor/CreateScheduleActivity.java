package com.wedevol.smartclass.activities.counselor;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.wedevol.smartclass.R;
import com.wedevol.smartclass.activities.ListDatesActivity;
import com.wedevol.smartclass.utils.UtilMethods;
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
    private int beginTime = -1;
    private int endTime = 99999;
    private String weekOfDay;
    private ImageView iv_toolbar_back;

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

        iv_toolbar_back = (ImageView) findViewById(R.id.iv_toolbar_back);

        ImageView iv_toolbar_actual_screen = (ImageView) findViewById(R.id.iv_toolbar_actual_screen);
        iv_toolbar_actual_screen.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_schedule_black));
        TextView tv_detail_title = (TextView) findViewById(R.id.tv_detail_title);
        tv_detail_title.setText("Crear Horario");
    }

    private void setActions() {
        tv_pick_day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(self, ListDatesActivity.class);
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
                if(beginTime == -1 || endTime == 99999 || weekOfDay.isEmpty()){
                    Toast.makeText(self, "Debe completar el formulario", Toast.LENGTH_SHORT).show();
                    return;
                }
                self.finish();
            }
        });

        et_begin_time.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        UtilMethods.deactivateSoftKeyboard(view, self);
                        Calendar mcurrentTime = Calendar.getInstance();
                        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                        int minute = mcurrentTime.get(Calendar.MINUTE);
                        TimePickerDialog mTimePicker;
                        mTimePicker = new TimePickerDialog(self, new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                                if(selectedHour < endTime){
                                    et_begin_time.setText(""+selectedHour);
                                    beginTime = selectedHour;
                                }else{
                                    Toast.makeText(self, "La hora de inicio no puede ser mayor" +
                                            " a la de termino.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }, hour, minute, true);//Yes 24 hour time
                        mTimePicker.setTitle("Elige el tiempo");
                        mTimePicker.show();

                    }
                }
        );

        et_begin_time.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                UtilMethods.deactivateSoftKeyboard(v, self);
            }
        });

        et_end_time.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        UtilMethods.deactivateSoftKeyboard(view, self);
                        Calendar mcurrentTime = Calendar.getInstance();
                        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                        int minute = mcurrentTime.get(Calendar.MINUTE);
                        TimePickerDialog mTimePicker;
                        mTimePicker = new TimePickerDialog(self, new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                                if(selectedHour > beginTime){
                                    et_end_time.setText(""+selectedHour);
                                    endTime = selectedHour;
                                }else{
                                    Toast.makeText(self, "La hora de termino debe ser mayor" +
                                            " a la de inicio.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }, hour, minute, true);//Yes 24 hour time
                        mTimePicker.setTitle("Elige el tiempo");
                        mTimePicker.show();

                    }
                }
        );

        et_end_time.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                UtilMethods.deactivateSoftKeyboard(v, self);
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
        if((requestCode == Constants.CHOOSEN_DATE) && (resultCode == Activity.RESULT_OK)) {
            weekOfDay = data.getStringExtra(Constants.BUNDLE_DATE);
            tv_pick_day.setText(weekOfDay);
        }
    }
}