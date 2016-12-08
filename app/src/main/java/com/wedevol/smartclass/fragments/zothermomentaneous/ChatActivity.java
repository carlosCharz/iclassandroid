package com.wedevol.smartclass.fragments.zothermomentaneous;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.wedevol.smartclass.R;

public class ChatActivity extends AppCompatActivity {

    ImageView _homeLink;
    ImageView _uniLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        _homeLink = (ImageView) findViewById(R.id.home_link);
        _uniLink = (ImageView) findViewById(R.id.uni_link);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Alex Jara - Cálculo 1");
        actionBar.setDisplayHomeAsUpEnabled(true);

        _homeLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "El alumno vive en San Isidro", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        _uniLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "El alumno puede recibir asesorías en la unviersidad. De preferencia: Tinkuy y Biblioteca Central.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
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
