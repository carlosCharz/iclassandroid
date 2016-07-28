package com.wedevol.smartclass;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RatingBar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StudentProfileActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.btn_request)
    Button _requestButton;

    @BindView(R.id.ratingBar2)
    RatingBar _ratingBar2;

    String[] mobileArray = {"Cálculo 1 - Jue 2:00 - 4:00 PM", "Física 1 - Mie 6:30 - 9:30 PM"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);
        ButterKnife.bind(this);

        _ratingBar2.setRating(1);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Tops Zúñiga");

        _requestButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SelectCourseActivity.class);
                startActivity(intent);
            }
        });

        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.activity_student_profile_list_view, mobileArray);

        ListView listView = (ListView) findViewById(R.id.asesorias_list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position, long id){
                //ItemClicked item = adapter.getItemAtPosition(position);
                //Intent intent = new Intent(getApplicationContext(), CourseActivity.class);
                //startActivity(intent);
            }


        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout2);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view2);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout2);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.student_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings2) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.profile_link2) {

        } else if (id == R.id.schedule_link2) {
            //Intent intent = new Intent(getApplicationContext(), ScheduleActivity.class);
            //startActivity(intent);
        } else if (id == R.id.notifications_link2) {
            //Intent intent = new Intent(getApplicationContext(), NotificationsActivity.class);
            //startActivity(intent);
        } else if (id == R.id.asesorias_link2) {
            //Intent intent = new Intent(getApplicationContext(), AsesoriasActivity.class);
            //startActivity(intent);
        } else if (id == R.id.config_link2) {
            //Intent intent = new Intent(getApplicationContext(), ConfigActivity.class);
            //startActivity(intent);
        } else if (id == R.id.signout_link2) {
            finish();
        }

        //DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout2);
        //drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
