package com.wedevol.smartclass.utils;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.wedevol.smartclass.R;
import com.wedevol.smartclass.utils.interfaces.Constants;

/** Created by paolo on 11/16/16.*/
public class UtilMethods {

    public static void setPhoto(Activity activity, final ImageView imageView, String photoUrl, final String type) {
        int errorResource;
        switch (type){
            case Constants.USER_PHOTO:
                errorResource = R.drawable.ic_profile_black;
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

    public static void showSnackbar(View parentView, String message, int messageColor, String optionMessage, int optionMessageColor ){
        Snackbar snackbar = Snackbar.make(parentView, message, Snackbar.LENGTH_LONG).setAction(optionMessage, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        // Changing action button text color
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(messageColor);
        // Changing option message text color
        snackbar.setActionTextColor(optionMessageColor);

        snackbar.show();
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