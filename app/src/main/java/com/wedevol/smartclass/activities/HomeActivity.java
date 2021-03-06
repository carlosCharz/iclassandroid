package com.wedevol.smartclass.activities;

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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wedevol.smartclass.R;
import com.wedevol.smartclass.fragments.BankAccountFragment;
import com.wedevol.smartclass.fragments.instructor.InstructorHistoricLessonsFragment;
import com.wedevol.smartclass.fragments.instructor.InstructorCoursesFragment;
import com.wedevol.smartclass.fragments.instructor.InstructorDesktopFragment;
import com.wedevol.smartclass.fragments.instructor.InstructorNotificationsFragment;
import com.wedevol.smartclass.fragments.instructor.InstructorPayCourseFragment;
import com.wedevol.smartclass.fragments.instructor.InstructorProfileFragment;
import com.wedevol.smartclass.fragments.instructor.InstructorScheduleFragment;
import com.wedevol.smartclass.fragments.student.StudentHistoricalLessonsFragment;
import com.wedevol.smartclass.fragments.student.StudentCoursesFragment;
import com.wedevol.smartclass.fragments.student.StudentLockerFragment;
import com.wedevol.smartclass.fragments.student.StudentPayCourseFragment;
import com.wedevol.smartclass.fragments.student.StudentProfileFragment;
import com.wedevol.smartclass.fragments.student.StudentRequestFragment;
import com.wedevol.smartclass.navigation.FragmentDrawer;
import com.wedevol.smartclass.utils.SharedPreferencesManager;

/* Created by paolorossi on 12/7/16. */
public class HomeActivity extends AppCompatActivity implements FragmentDrawer.FragmentDrawerListener {
    private DrawerLayout mDrawerLayout;
    private SharedPreferencesManager mPreferencesManager;
    private ImageView iv_toolbar_actual_screen;
    private int fragmentDrawableId;
    private boolean isInstructor;
    private RelativeLayout rl_activity_main;
    private String fragmentTitle;
    private TextView tv_reception_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //FacebookSdk.sdkInitialize(getApplicationContext());
        setupElements(savedInstanceState);
    }

    private void setupElements(Bundle savedInstanceState) {
        //initializing retrofit, PreferencesManager, User
        mPreferencesManager = SharedPreferencesManager.getInstance(this);
        isInstructor = mPreferencesManager.getUserType();
        //Initializing fragments, toolbar and navDrawer
        rl_activity_main = (RelativeLayout) findViewById(R.id.rl_activity_main);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        iv_toolbar_actual_screen = (ImageView) findViewById(R.id.iv_toolbar_actual_screen);
        tv_reception_title = (TextView) findViewById(R.id.tv_reception_title);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.app_name, R.string.app_name);
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        setSupportActionBar(toolbar);

        //isInstructor = getIntent().getBooleanExtra(Constants.BUNDLE_INSTRUCTOR, false);

        //TODO we should validate the kind of user here, given the type of user given at login
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if(getSupportFragmentManager().findFragmentByTag("NavDrawer") == null) {
            fragmentTransaction.replace(R.id.nav_fragment_drawer, FragmentDrawer.newInstance(isInstructor), "NavDrawer");
            fragmentTransaction.commitNow();

            FragmentDrawer drawerFragment = (FragmentDrawer) getSupportFragmentManager().findFragmentByTag("NavDrawer");
            drawerFragment.setUp(R.id.nav_fragment_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), toolbar);
            drawerFragment.setDrawerListener(this);
        }

        if(savedInstanceState != null){
            fragmentDrawableId = savedInstanceState.getInt("selectedFragmentIcon");
            //fragmentTitle = "Mi Escritorio";
        }else{
            if(isInstructor){
                fragmentTransaction.replace(R.id.main_fragment_container, new InstructorDesktopFragment());
                fragmentDrawableId = R.drawable.ic_desktop_black;
                fragmentTitle = "Home";
                fragmentTransaction.commit();
            }else{
                fragmentTransaction.replace(R.id.main_fragment_container, new StudentLockerFragment());
                fragmentDrawableId = R.drawable.ic_locker_black;
                fragmentTitle = "Home";
                fragmentTransaction.commit();
            }
        }

        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        if(fragmentDrawableId!=0) {
            iv_toolbar_actual_screen.setImageDrawable(ContextCompat.getDrawable(this, fragmentDrawableId));
            tv_reception_title.setText(fragmentTitle);
        }
    }

    @Override
    public void onDrawerItemSelected(View view, int position) {
        Fragment fragment = null;
        if(isInstructor){
            switch (position) {
                case 0:
                    fragment = new InstructorDesktopFragment();
                    fragmentDrawableId = R.drawable.ic_desktop_black;
                    fragmentTitle = "Home";
                    break;
                case 1:
                    fragment = new InstructorProfileFragment();
                    fragmentDrawableId = R.drawable.ic_profile_black;
                    fragmentTitle = mPreferencesManager.getUserInfo().getFullName();
                    break;
                case 2:
                    fragment = new InstructorScheduleFragment();
                    fragmentDrawableId = R.drawable.ic_schedule_black;
                    fragmentTitle = "Horario";
                    break;
                case 3:
                    fragment = new InstructorNotificationsFragment();
                    fragmentDrawableId = R.drawable.ic_notification_black;
                    fragmentTitle = "Mis Notificaciones";
                    break;
                case 4:
                    fragment = new InstructorHistoricLessonsFragment();
                    fragmentDrawableId = R.drawable.ic_counsel_black;
                    fragmentTitle = "Mis Asesorías";
                    break;
                case 5:
                    fragment = new InstructorCoursesFragment();
                    fragmentDrawableId = R.drawable.ic_course_black;
                    fragmentTitle = "Mis Cursos";
                    break;
                case 6:
                    fragment = new InstructorPayCourseFragment();
                    fragmentDrawableId = R.drawable.ic_pay_course_black;
                    fragmentTitle = "Pagar Curso";
                    break;
                case 7:
                    fragment = BankAccountFragment.newInstance(true);
                    fragmentDrawableId = R.drawable.ic_bank_account_black;
                    fragmentTitle = "Cuentas Bancarias";
                    break;
                case 8:
                    mPreferencesManager.logout();
                default:
                    break;
            }
        } else {
            switch (position) {
                case 0:
                    fragment = new StudentLockerFragment();
                    fragmentDrawableId = R.drawable.ic_locker_black;
                    fragmentTitle = "Home";
                    break;
                case 1:
                    fragment = new StudentProfileFragment();
                    fragmentDrawableId = R.drawable.ic_profile_black;
                    fragmentTitle = mPreferencesManager.getUserInfo().getFullName();
                    break;
                case 2:
                    fragment = new StudentCoursesFragment();
                    fragmentDrawableId = R.drawable.ic_course_black;
                    fragmentTitle = "Mis Cursos";
                    break;
                case 3:
                    fragment = new StudentRequestFragment();
                    fragmentDrawableId = R.drawable.ic_notification_black;
                    fragmentTitle = "Mis Solicitudes";
                    break;
                case 4:
                    fragment = new StudentHistoricalLessonsFragment();
                    fragmentDrawableId = R.drawable.ic_counsel_black;
                    fragmentTitle = "Mis Asesorías";
                    break;
                case 5:
                    fragment = new StudentPayCourseFragment();
                    fragmentDrawableId = R.drawable.ic_pay_course_black;
                    fragmentTitle = "Pagar Curso";
                    break;
                case 6:
                    fragment = BankAccountFragment.newInstance(false);
                    fragmentDrawableId = R.drawable.ic_bank_account_black;
                    fragmentTitle = "Cuentas Bancarias";
                    break;
                case 7:
                    mPreferencesManager.logout();
                default:
                    break;
            }
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.main_fragment_container, fragment);
            fragmentTransaction.commit();

            iv_toolbar_actual_screen.setImageDrawable(ContextCompat.getDrawable(this, fragmentDrawableId));
            tv_reception_title.setText(fragmentTitle);
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

    public View getMainLayout(){
        return rl_activity_main;
    }

    public SharedPreferencesManager getmPreferencesManager (){
        return mPreferencesManager;
    }

    public void goToStudentCourses() {

    }

    public void goToCourses() {
        Fragment fragment;
        if(SharedPreferencesManager.getInstance(this).getUserType()){
            fragment = new InstructorCoursesFragment();
        }else{
            fragment = new StudentCoursesFragment();
        }

        fragmentDrawableId = R.drawable.ic_course_black;
        fragmentTitle = "Mis Cursos";

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_fragment_container, fragment);
        fragmentTransaction.commit();

        iv_toolbar_actual_screen.setImageDrawable(ContextCompat.getDrawable(this, fragmentDrawableId));
        tv_reception_title.setText(fragmentTitle);
    }
}