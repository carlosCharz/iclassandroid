package com.wedevol.smartclass.utils.notifications;

import android.content.Context;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.wedevol.smartclass.models.Instructor;
import com.wedevol.smartclass.models.Student;
import com.wedevol.smartclass.models.User;
import com.wedevol.smartclass.utils.SharedPreferencesManager;
import com.wedevol.smartclass.utils.retrofit.IClassCallback;
import com.wedevol.smartclass.utils.retrofit.RestClient;

import retrofit.client.Response;

/** Created by paolo on 1/19/17.*/
public class IClassFirebaseInstanceIDService extends FirebaseInstanceIdService {
    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d("IClassFirebase", "Refreshed token: " + refreshedToken);

        RestClient restClient = new RestClient(this);
        boolean isInstructor = SharedPreferencesManager.getInstance(this).getUserType();
        final User user = SharedPreferencesManager.getInstance(this).getUserInfo();
        user.setFcmToken(refreshedToken);
        final Context context = getApplicationContext();

        if(isInstructor){
            restClient.getWebservices().updateInstructor("", user.getId(), ((Instructor)user).toJson(), new IClassCallback<JsonObject>(context){
                @Override
                public void success(JsonObject jsonObject, Response response) {
                    super.success(jsonObject, response);
                    Gson gson = new Gson();
                    SharedPreferencesManager.getInstance(context).saveUser("", gson.toJson(user));
                }
            });
        }else{
            restClient.getWebservices().updateStudent("", user.getId(), ((Student)user).toJson(), new IClassCallback<JsonObject>(context){
                @Override
                public void success(JsonObject jsonObject, Response response) {
                    super.success(jsonObject, response);
                    Gson gson = new Gson();
                    SharedPreferencesManager.getInstance(context).saveUser("", gson.toJson(user));
                }
            });
        }
    }
}