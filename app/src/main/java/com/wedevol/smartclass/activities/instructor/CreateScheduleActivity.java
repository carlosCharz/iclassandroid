package com.wedevol.smartclass.activities.instructor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.wedevol.smartclass.R;
import com.wedevol.smartclass.activities.ListDatesActivity;
import com.wedevol.smartclass.models.Schedule;
import com.wedevol.smartclass.models.User;
import com.wedevol.smartclass.utils.SharedPreferencesManager;
import com.wedevol.smartclass.utils.dialogs.TimePickDialogFragment;
import com.wedevol.smartclass.utils.interfaces.Constants;
import com.wedevol.smartclass.utils.retrofit.IClassCallback;
import com.wedevol.smartclass.utils.retrofit.RestClient;

import retrofit.client.Response;

/** Created by paolorossi on 12/14/16.*/
public class CreateScheduleActivity extends AppCompatActivity {
    private TextView tv_pick_day;
    private EditText et_begin_time;
    private EditText et_end_time;
    private Button b_cancel;
    private Button b_save;
    private Activity self;
    private String weekOfDay;
    private ImageView iv_toolbar_back;
    private RestClient restClient;

    private String choosenWeekDay;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_schedule);
        setElements();
        setActions();
    }

    private void setElements() {
        self = this;
        restClient = new RestClient(this);

        tv_pick_day = (TextView) findViewById(R.id.tv_pick_day);
        et_begin_time = (EditText) findViewById(R.id.et_begin_time);
        et_begin_time.setInputType(InputType.TYPE_NULL);

        et_end_time = (EditText) findViewById(R.id.et_end_time);
        et_end_time.setInputType(InputType.TYPE_NULL);

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
                int validated = validateTime();
                if(!(validated==2)){
                    et_begin_time.requestFocus();
                    if(validated==1){
                        Toast.makeText(self, "Debes reservar por lo menos 1 hora. La hora de inicio tiene que ser menor que la hora de termino.", Toast.LENGTH_SHORT).show();
                    }else if (validated==0){
                        Toast.makeText(self, "Debe delimitar su tiempo de asesoría.", Toast.LENGTH_SHORT).show();
                    }
                    return;
                }

                Schedule schedule = new Schedule();
                schedule.setWeekDay(choosenWeekDay);
                schedule.setStartTime(Integer.parseInt(et_begin_time.getText().toString()));
                schedule.setEndTime(Integer.parseInt(et_end_time.getText().toString()));
                schedule.setInstructorId(SharedPreferencesManager.getInstance(self).getUserInfo().getId());

                User user = SharedPreferencesManager.getInstance(self).getUserInfo();

                restClient.getWebservices().newSchedule(user.getAccessToken(), schedule.toJson(), new IClassCallback<JsonObject>(self) {
                    @Override
                    public void success(JsonObject jsonObject, Response response) {
                        super.success(jsonObject, response);
                        setResult(Activity.RESULT_OK);
                        self.finish();
                    }
                });
            }
        });

        et_begin_time.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        TimePickDialogFragment timePickDialogFragment = TimePickDialogFragment.newInstance(et_begin_time);
                        timePickDialogFragment.show(((FragmentActivity)self).getSupportFragmentManager(), "");
                    }
                }
        );

        et_end_time.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        TimePickDialogFragment timePickDialogFragment = TimePickDialogFragment.newInstance(et_end_time);
                        timePickDialogFragment.show(((FragmentActivity)self).getSupportFragmentManager(), "");
                    }
                }
        );

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

            switch (weekOfDay){
                case "Lunes":
                    choosenWeekDay = "mon";
                    break;
                case "Martes":
                    choosenWeekDay = "tue";
                    break;
                case "Miércoles":
                    choosenWeekDay = "wed";
                    break;
                case "Jueves":
                    choosenWeekDay = "thu";
                    break;
                case "Viernes":
                    choosenWeekDay = "fri";
                    break;
                case "Sábado":
                    choosenWeekDay = "sat";
                    break;
                case "Domingo":
                    choosenWeekDay = "sun";
                    break;
            }

        }
    }

    private int validateTime() {
        if(et_begin_time.getText().toString().isEmpty()||et_end_time.getText().toString().isEmpty()) {
            return 0;
        }else{
            int endTime = Integer.parseInt(et_end_time.getText().toString());
            int beginTime = Integer.parseInt(et_begin_time.getText().toString());

            boolean wrong = ((endTime - beginTime) < 0) || (endTime == beginTime);
            if(wrong){
                return 1;
            }
        }
        return 2;
    }
}