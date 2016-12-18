package com.wedevol.smartclass.utils.retrofit;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.wedevol.smartclass.utils.SharedPreferencesManager;
import com.wedevol.smartclass.utils.interfaces.Constants;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/** Created by Paolo on 3/14/2016.*/
public class IClassCallback<T> implements Callback<T> {
    private final Context context;
    private Toast requestToast;

    public IClassCallback(Context context) {
        this.context = context;
    }

    @Override
    public void success(T t, Response response) {
//        JsonObject jsonObject = (JsonObject)t;
//        if(jsonObject.has("results") && !jsonObject.get("results").isJsonNull()){
//            //requestToast = Toast.makeText(context,jsonObject.get("results").getAsJsonObject().get("message").getAsString(), Toast.LENGTH_SHORT);
//            //requestToast.show();
//        }
    }

    @Override
    public void failure(RetrofitError error) {
        ErrorMessage errorMessage = new ErrorMessage(error, context);
        Toast requestErrorToast = Toast.makeText(context, errorMessage.showError(), Toast.LENGTH_SHORT);
        if (errorMessage.getTypeError() == Constants.REQUEST_SERVER_ERROR_CODE && !validateToken(error)){
            SharedPreferencesManager mPreferencesManager = SharedPreferencesManager.getInstance(context);
            mPreferencesManager.logout();
        }else{
            requestErrorToast.show();
        }
    }

    private boolean validateToken(RetrofitError error) {
        boolean validToken = true;
        RestError errorClient = new RestError(error);
        if (errorClient.getCode() == Constants.ERROR_CODE_TOKEN_NO_LONGER_BE_REFRESHED) {
            validToken = false;
        }
        return validToken;
    }
}