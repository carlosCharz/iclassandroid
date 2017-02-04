package com.wedevol.smartclass.utils.notifications;

import android.content.Context;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.google.gson.JsonObject;
import com.wedevol.smartclass.utils.DeviceUuidFactory;
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
        final Context context = getApplicationContext();

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("deviceId", String.valueOf(new DeviceUuidFactory(context).getDeviceUuid()));
        jsonObject.addProperty("fcmToken", refreshedToken);

        restClient.getWebservices().refreshToken("", jsonObject, new IClassCallback<JsonObject>(context) {
            @Override
            public void success(JsonObject jsonObject, Response response) {
                super.success(jsonObject, response);
            }
        });
    }
}