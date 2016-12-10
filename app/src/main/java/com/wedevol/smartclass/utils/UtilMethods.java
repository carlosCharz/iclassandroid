package com.wedevol.smartclass.utils;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.wedevol.smartclass.R;
import com.wedevol.smartclass.activities.SignupActivity;
import com.wedevol.smartclass.utils.interfaces.Constants;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            permissionCheck = ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE);
            if(permissionCheck != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(activity,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 11);
            } else {
                permissionCount++;
            }
        }else{
            permissionCount++;
        }
        return permissionCount;
    }

    public static AlertDialog createSelectionDialog(Context context, String title, List<Object> optionList, Dialog.OnClickListener onClickListener) {
        ArrayAdapter<Object> arrayAdapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, optionList);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setAdapter(arrayAdapter, onClickListener);
        AlertDialog dialog = builder.create();
        dialog.show();
        return dialog;
    }

    public static File getImageFile() {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File file;

        try {
            File storage = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
            if (!storage.isDirectory()) {
                storage.mkdirs();
            }
            file = File.createTempFile(imageFileName, ".jpg", storage);
        } catch (IOException e) {
            return null;
        }

        return file;
    }

    public static String getGalleryImagePath(Context context, Uri imageUri) {
        String path = "";
        String[] filePathColumn = {MediaStore.Images.Media.DATA};
        Cursor cursor = context.getContentResolver().query(imageUri,
                filePathColumn, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            path = cursor.getString(columnIndex);
            cursor.close();
        }

        return path;
    }
}