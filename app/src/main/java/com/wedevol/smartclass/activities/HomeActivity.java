package com.wedevol.smartclass.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.wedevol.smartclass.R;
import com.wedevol.smartclass.fragments.BankAccountsFragment;
import com.wedevol.smartclass.fragments.ConsultationsFragment;
import com.wedevol.smartclass.fragments.CoursesFragment;
import com.wedevol.smartclass.fragments.NotificationsFragment;
import com.wedevol.smartclass.fragments.PayCourseFragment;
import com.wedevol.smartclass.fragments.ProfileFragment;
import com.wedevol.smartclass.fragments.ScheduleFragment;
import com.wedevol.smartclass.models.User;
import com.wedevol.smartclass.navigation.FragmentDrawer;
import com.wedevol.smartclass.utils.SharedPreferencesManager;

/* Created by paolorossi on 12/7/16. */
public class HomeActivity extends AppCompatActivity implements FragmentDrawer.FragmentDrawerListener {
    private DrawerLayout mDrawerLayout;
    private SharedPreferencesManager mPreferencesManager;
    private User currentUser;
    private ImageView iv_toolbar_actual_screen;
    private int fragmentDrawableId;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_home);

        //FacebookSdk.sdkInitialize(getApplicationContext());

        setupElements(savedInstanceState);
    }

    private void setupElements(Bundle savedInstanceState) {
        //initializing retrofit, PreferencesManager, User

        mPreferencesManager = SharedPreferencesManager.getInstance(this);
        currentUser = mPreferencesManager.getUserInfo();
        //Initializing fragments, toolbar and navDrawer
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        iv_toolbar_actual_screen = (ImageView) findViewById(R.id.iv_toolbar_actual_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.app_name, R.string.app_name);
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        setSupportActionBar(toolbar);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.nav_fragment_drawer, new FragmentDrawer(), "NavDrawer");
        fragmentTransaction.commitNow();

        FragmentDrawer drawerFragment = (FragmentDrawer) getSupportFragmentManager().findFragmentByTag("NavDrawer");
        drawerFragment.setUp(R.id.nav_fragment_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), toolbar);
        drawerFragment.setDrawerListener(this);

        if(savedInstanceState != null){
            fragmentDrawableId = savedInstanceState.getInt("selectedFragmentIcon");
        }else{
            fragmentTransaction.replace(R.id.main_fragment_container, new ScheduleFragment());
            fragmentDrawableId = R.drawable.ic_schedule_selected;
            fragmentTransaction.commit();
        }

        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        if(fragmentDrawableId!=0) {
            iv_toolbar_actual_screen.setImageDrawable(ContextCompat.getDrawable(this, fragmentDrawableId));
        }
    }

    @Override
    public void onDrawerItemSelected(View view, int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new ProfileFragment();
                fragmentDrawableId = R.drawable.ic_profile_selected;
                break;
            case 1:
                fragment = new ScheduleFragment();
                fragmentDrawableId = R.drawable.ic_schedule_selected;
                break;
            case 2:
                fragment = new NotificationsFragment();
                fragmentDrawableId = R.drawable.ic_notification_selected;
                break;
            case 3:
                fragment = new ConsultationsFragment();
                fragmentDrawableId = R.drawable.ic_consultation_selected;
                break;
            case 4:
                fragment = new CoursesFragment();
                fragmentDrawableId = R.drawable.ic_course_selected;
                break;
            case 5:
                fragment = new PayCourseFragment();
                fragmentDrawableId = R.drawable.ic_pay_course_selected;
                break;
            case 6:
                fragment = new BankAccountsFragment();
                fragmentDrawableId = R.drawable.ic_bank_account_selected;
                break;
            case 7:
                mPreferencesManager.logout();
            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.main_fragment_container, fragment);
            fragmentTransaction.commit();

            iv_toolbar_actual_screen.setImageDrawable(ContextCompat.getDrawable(this, fragmentDrawableId));
        }
    }

    @Override
    public void onBackPressed() {
        if(mDrawerLayout.isDrawerOpen(GravityCompat.START)){
            mDrawerLayout.closeDrawers();
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onStop () {
        super.onStop();

    }
}