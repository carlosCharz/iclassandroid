package com.wedevol.smartclass;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.RatingBar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CoachProfileActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.ratingBar)
    RatingBar _ratingBar;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.course_list)
    ListView listView;

    CustomCoachAdapter customCoachAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coach_profile);
        ButterKnife.bind(this);

        _ratingBar.setRating(3);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Luis Becerra");

        customCoachAdapter = new CustomCoachAdapter();
        listView.setAdapter(customCoachAdapter);
        listView.setDivider(null);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Se procesará tu solicitud para dictar un nuevo curso", Snackbar.LENGTH_LONG)
                //        .setAction("Action", null).show();
                Intent intent = new Intent(getApplicationContext(), RequestCourseActivity.class);
                startActivity(intent);

            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.coach_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.profile_link) {
            Intent intent = new Intent(getApplicationContext(), MyCoachProfileActivity.class);
            startActivity(intent);
        } else if (id == R.id.schedule_link) {
            Intent intent = new Intent(getApplicationContext(), ScheduleMainActivity.class);
            startActivity(intent);
        } else if (id == R.id.notifications_link) {
            Intent intent = new Intent(getApplicationContext(), NotificationsActivity.class);
            startActivity(intent);
        } else if (id == R.id.asesorias_link) {
            Intent intent = new Intent(getApplicationContext(), AsesoriasActivity.class);
            startActivity(intent);
        } else if (id == R.id.config_link) {
            Intent intent = new Intent(getApplicationContext(), ConfigActivity.class);
            startActivity(intent);
        } else if (id == R.id.signout_link) {
            finish();
        }

        //DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        //drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
