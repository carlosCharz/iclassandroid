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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ConfigActivity extends AppCompatActivity {

    @BindView(R.id.btn_save)
    Button _saveButton;
    @BindView(R.id.home_switch)
    Switch _homeSwitch;
    @BindView(R.id.distrito_spinner)
    Spinner _distritoSpinner;
    @BindView(R.id.frequency_spinner)
    Spinner _frequencySpinner;
    @BindView(R.id.input_address)
    EditText _inputAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);
        ButterKnife.bind(this);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Ajustes");
        actionBar.setDisplayHomeAsUpEnabled(true);

        _homeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    _distritoSpinner.setVisibility(View.VISIBLE);
                    _inputAddress.setVisibility(View.VISIBLE);
                } else {
                    _distritoSpinner.setVisibility(View.GONE);
                    _inputAddress.setVisibility(View.GONE);
                }
            }
        });

        List<String> distritosArray =  new ArrayList<String>();
        distritosArray.add("Los Olivos");
        distritosArray.add("San Isidro");
        distritosArray.add("San Martín de Porres");
        distritosArray.add("Barranco");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, distritosArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        _distritoSpinner.setAdapter(adapter);

        List<String> frecuenciaArray =  new ArrayList<String>();
        frecuenciaArray.add("1 semana");
        frecuenciaArray.add("2 semanas");
        frecuenciaArray.add("1 mes");
        frecuenciaArray.add("nunca");


        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, frecuenciaArray);

        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        _frequencySpinner.setAdapter(adapter2);

        _saveButton.setOnClickListener(new View.OnClickListener() {

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
