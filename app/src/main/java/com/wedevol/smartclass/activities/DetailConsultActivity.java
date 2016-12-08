package com.wedevol.smartclass.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.wedevol.smartclass.R;

public class DetailConsultActivity extends AppCompatActivity {
    Button _validateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asesorias_detail);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Asesoria");
        actionBar.setDisplayHomeAsUpEnabled(true);

        _validateButton = (Button) findViewById(R.id.btn_validate);
        _validateButton.setOnClickListener(new View.OnClickListener() {

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
