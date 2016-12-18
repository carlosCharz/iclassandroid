package com.wedevol.smartclass.utils.retrofit;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import retrofit.RetrofitError;

/** Created by Paolo on 3/14/2016*/
public class RestError {
    private int code;
    private String message;
    private String reasons;

    public RestError(RetrofitError error) {
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonError = (JsonObject) jsonParser.parse(error.getBody().toString());
        code = jsonError.get("code").getAsInt();
        message = jsonError.get("message").getAsString();
        reasons = jsonError.get("reasons").getAsString();
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getReasons() {
        return reasons;
    }
}