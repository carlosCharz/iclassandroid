package com.wedevol.smartclass.activities;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.crashlytics.android.Crashlytics;
import com.wedevol.smartclass.R;
import com.wedevol.smartclass.utils.SharedPreferencesManager;
import com.wedevol.smartclass.utils.UtilMethods;
import io.fabric.sdk.android.Fabric;

/** Created by paolorossi on 12/8/16.*/
public class SplashActivity extends AppCompatActivity{
    private final static int SPLASH_TIME_OUT = 3000;
    private SharedPreferencesManager mSharedPreferencesManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_splash);
        mSharedPreferencesManager = SharedPreferencesManager.getInstance(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if(UtilMethods.checkMarshmallowPermissions(this)==4){
                login();
            }
        }else{
            login();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 10: {// If request is cancelled, the result arrays are empty.
                if (grantResults.length == 0 || grantResults[0] != PackageManager.PERMISSION_GRANTED) {// permission denied, boo! Disable the functionality that depends on this permission.
                    this.finish();
                }
                break;
            }
            case 11: {
                if (grantResults.length == 0 || grantResults[0] != PackageManager.PERMISSION_GRANTED) {// permission denied, boo! Disable the functionality that depends on this permission.
                    this.finish();
                }
                break;
            }
            case 12: {
                if (grantResults.length == 0 || grantResults[0] != PackageManager.PERMISSION_GRANTED) {// permission denied, boo! Disable the functionality that depends on this permission.
                    this.finish();
                }
                break;
            }
            case 13: {
                if (grantResults.length == 0 || grantResults[0] != PackageManager.PERMISSION_GRANTED) {// permission denied, boo! Disable the functionality that depends on this permission.
                    this.finish();
                }
                login();
                break;
            }
        }
    }

    private void login() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mSharedPreferencesManager.isUserLogged()) {
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                }

                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}