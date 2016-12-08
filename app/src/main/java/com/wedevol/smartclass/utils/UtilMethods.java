package com.wedevol.smartclass.utils;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.wedevol.smartclass.utils.interfaces.Constants;

/** Created by paolo on 11/16/16.*/
public class UtilMethods {
    public static void setUserPhoto(Activity activity, ImageView iv_user_image, SharedPreferencesManager mPreferencesManager) {

    }

    public static void setPhoto(Activity activity, final ImageView imageView, String photoUrl, final String type) {
        int errorResource = 0;
        switch (type){
            case Constants.NONE:
                errorResource = -1;//R.drawable.placeholder_product;
                break;
            default:
                errorResource = -1;
                break;
        }

        final int finalErrorResource = errorResource;
        Glide.with(activity)
                .load(photoUrl)
                .placeholder(finalErrorResource)
                .error(errorResource)
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        imageView.setImageResource(finalErrorResource);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        imageView.setImageDrawable(resource.getCurrent());
                        return false;
                    }
                }).into(imageView);
    }

    public static int checkMarshmallowPermissions(Activity activity) {
        int permissionCheck, permissionCount=0;
        permissionCheck = ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if(permissionCheck != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 10);
        } else {
            permissionCount++;
        }

        permissionCheck = ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION);
        if(permissionCheck != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 11);
        } else {
            permissionCount++;
        }

        permissionCheck = ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION);
        if(permissionCheck != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 12);
        } else {
            permissionCount++;
        }

        return permissionCount;
    }
}