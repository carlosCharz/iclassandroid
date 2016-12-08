package com.wedevol.smartclass.fragments.schedule;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import com.wedevol.smartclass.R;

import java.util.ArrayList;
import java.util.List;

public class ScheduleActivity extends AppCompatActivity {
    Button _saveScheduleButton;
    EditText _from1Text;
    EditText _to1Text;
    Button _cancelScheduleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

         _saveScheduleButton = (Button) findViewById(R.id.btn_save_schedule);
         _from1Text = (EditText) findViewById(R.id.from1Text);
         _to1Text = (EditText) findViewById(R.id.to1Text);
         _cancelScheduleButton = (Button) findViewById(R.id.btn_cancel_schedule);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Agregar horario");
        actionBar.setDisplayHomeAsUpEnabled(true);

        List<String> daysArray =  new ArrayList<String>();
        daysArray.add("Lunes");
        daysArray.add("Martes");
        daysArray.add("Miercoles");
        daysArray.add("Jueves");
        daysArray.add("Viernes");
        daysArray.add("Sábado");
        daysArray.add("Domingo");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, daysArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        final Spinner sItems = (Spinner) findViewById(R.id.days_spinner2);
        sItems.setAdapter(adapter);

        _saveScheduleButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();

                intent.putExtra("day", sItems.getSelectedItem().toString());
                intent.putExtra("hourSince", _from1Text.getText().toString());
                intent.putExtra("hourTo", _to1Text.getText().toString());
                setResult(RESULT_OK, intent);

                finish();
            }
        });

        _from1Text.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(ScheduleActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        _from1Text.setText(hourOfDay + ":" + minute);
                    }
                }, 0, 0, true);
                timePickerDialog.show();
            }
        });

        _to1Text.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(ScheduleActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        _to1Text.setText(hourOfDay + ":" + minute);
                    }
                }, 0, 0, true);
                timePickerDialog.show();
            }
        });

        _cancelScheduleButton.setOnClickListener(new View.OnClickListener() {

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
                // app icon in action bar clicked; go home
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
