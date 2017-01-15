package com.wedevol.smartclass.utils.retrofit;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import retrofit.RetrofitError;

/** Created by Paolo on 3/14/2016*/
class RestError {
    private int code;
    private String message;

    RestError(RetrofitError error) {
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonError = (JsonObject) jsonParser.parse(error.getBody().toString());
        code = jsonError.get("errorCode").getAsInt();
        message = jsonError.get("errorMessage").getAsString();
    }

    int getCode() {
        return code;
    }

    String getMessage() {
        return message;
    }
}