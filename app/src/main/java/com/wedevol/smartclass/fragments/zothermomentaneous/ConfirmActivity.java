package com.wedevol.smartclass.fragments.zothermomentaneous;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.wedevol.smartclass.R;

public class ConfirmActivity extends AppCompatActivity {
    Button _confirmButton;
    RadioGroup _radioPlace;
    RadioButton _rb1;
    RadioButton _rb2;
    RadioButton _rb3;
    EditText _inputPlace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);

         _confirmButton = (Button) findViewById(R.id.btn_confirm);
         _radioPlace = (RadioGroup) findViewById(R.id.radioPlace);
         _rb1= (RadioButton) findViewById(R.id.rb1);
         _rb2= (RadioButton) findViewById(R.id.rb2);
         _rb3= (RadioButton) findViewById(R.id.rb3);
         _inputPlace= (EditText) findViewById(R.id.input_place);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Confirmar");
        actionBar.setDisplayHomeAsUpEnabled(true);

        _inputPlace.setVisibility(View.GONE);

        _confirmButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });

        _radioPlace.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected
                if (_rb3.getId() == checkedId) {
                    _inputPlace.setVisibility(View.VISIBLE);
                } else {
                    _inputPlace.setVisibility(View.GONE);
                }
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
