package com.wedevol.smartclass.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.wedevol.smartclass.activities.LoginActivity;
import com.wedevol.smartclass.models.Instructor;
import com.wedevol.smartclass.models.Student;
import com.wedevol.smartclass.models.User;

/**
 * This class controls the data that is persisted in the android session for this particular app.
 * Use it to identify already logged users or take "global variables" for your use.
 * */
public class SharedPreferencesManager {

    private static final String PREFERENCES_NAME = "blcc";
    private static final String TOKEN_USER = "userToken";
    private static final String UUID = "uuid";
    private static final String CURRENT_USER = "currentUser";
    private static final String FACEBOOK_PHOTO = null;
    private static final String CURRENT_USER_TYPE = "currentUserType";
    private static final String PASSWORD = "password";

    private static SharedPreferencesManager self;
    private final SharedPreferences mPreferences;
    private final Context context;



    private SharedPreferencesManager(Context context) {
        this.context = context;
        mPreferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    public static SharedPreferencesManager getInstance(Context context) {
        if (self == null) {
            self = new SharedPreferencesManager(context);
        }

        return self;
    }

    public void saveUser(String token, String jsonUserData){
        SharedPreferences.Editor editor = mPreferences.edit();
        //editor.putString(TOKEN_USER, token);
        editor.putString(CURRENT_USER, jsonUserData);
        editor.apply();
    }

    public void saveUserType(boolean userType){
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putBoolean(CURRENT_USER_TYPE, userType);
        editor.apply();
    }

    public void saveUuid(String uuid){
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putString(UUID, uuid);
        editor.apply();
    }

    public void saveTruePassword(String password) {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putString(PASSWORD, password);
        editor.apply();
    }

    private void deleteUser() {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putString(TOKEN_USER, null);
        editor.putString(CURRENT_USER, null);
        editor.putString(UUID, null);
        editor.putString(FACEBOOK_PHOTO, null);
        editor.apply();
    }

    public User getUserInfo(){
        JsonParser jsonParser = new JsonParser();
        String userJson = mPreferences.getString(CURRENT_USER, "");

        if(!userJson.isEmpty()){
            JsonObject json = (JsonObject)jsonParser.parse(userJson);
            if(getUserType()){//true = isInstructor
                return  Instructor.parseInstructor(json);
            }else{
                return  Student.parseStudent(json);
            }
        }else{
            return  null;
        }
    }

    public boolean getUserType(){
        return mPreferences.getBoolean(CURRENT_USER_TYPE, false);
    }

    public String getUserToken(){
        return mPreferences.getString(TOKEN_USER, "");
    }

    public String getUuid(){
        return  mPreferences.getString(UUID, "");
    }

    public void updateUserToken(String token){
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putString(TOKEN_USER, token);
        editor.apply();
    }

    public String getUserTruePassword(){
        return mPreferences.getString(PASSWORD, "");
    }

    public boolean isUserLogged(){
        return (getUserInfo() != null && mPreferences.contains(UUID) && !getUuid().equals(""));
    }

    public void logout(){
        deleteUser();
        Intent intent = new Intent(context, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_NO_ANIMATION);
        context.startActivity(intent);
        if(Activity.class.isInstance(context)){
            ((Activity)context).finish();
        }
    }

    public boolean shouldWeAskPermissions(String permission){
        return (mPreferences.getBoolean(permission, true));
    }

    public void markAsAskedPermissions(String permission){
        mPreferences.edit().putBoolean(permission, false).apply();
    }
}