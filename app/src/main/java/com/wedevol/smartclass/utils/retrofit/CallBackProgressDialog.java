package com.wedevol.smartclass.utils.retrofit;

import android.app.ProgressDialog;
import android.content.Context;

import retrofit.RetrofitError;
import retrofit.client.Response;

/** Created by Paolo on 3/15/2016.*/
public class CallBackProgressDialog <T> extends IClassCallback<T> {

    private final ProgressDialog progressDialog;

    public CallBackProgressDialog(Context context, ProgressDialog progressDialog) {
        super(context);
        this.progressDialog = progressDialog;
    }

    @Override
    public void success(T t, Response response) {
        super.success(t, response);
        if( progressDialog != null){
            progressDialog.dismiss();
        }
    }

    @Override
    public void failure(RetrofitError error) {
        super.failure(error);
        if( progressDialog != null){
            progressDialog.dismiss();
        }

    }
}