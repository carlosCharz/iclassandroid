package com.wedevol.smartclass.utils.retrofit;

import android.content.Context;

import com.squareup.okhttp.Authenticator;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.wedevol.smartclass.utils.SharedPreferencesManager;

import java.io.IOException;
import java.net.Proxy;


/** Created by Paolo on 3/15/2016.*/
public class RetrofitAuthenticator implements Authenticator{
    private final SharedPreferencesManager mPreferencesManager;
    private final RestClient restClient;

    public RetrofitAuthenticator(Context context) {
        restClient = new RestClient();
        mPreferencesManager = SharedPreferencesManager.getInstance(context);
    }


    @Override
    public Request authenticate(Proxy proxy, Response response) throws IOException {

        String newAccessToken = "welcome_to_mars";//restClient.getWebservices().refreshToken("Bearer " + mPreferencesManager.getUserToken(), "").getAsJsonObject().get("token").getAsString();

        mPreferencesManager.updateUserToken(newAccessToken);

        // Add new header to rejected request and retry it
        return response.request().newBuilder()
                .header("Authorization", String.format("Bearer %s", newAccessToken))
                .build();
    }

    @Override
    public Request authenticateProxy(Proxy proxy, Response response) throws IOException {
        //TODO not implemented
        return null;
    }
}