package com.wedevol.smartclass.utils.interfaces;

/** Created by paolorossi on 12/8/16.*/
public interface Constants {
    String USER_PHOTO = "user_photo";

    //THIS ONES NEED TO BE VERIFIED
    int ERROR_CODE_TOKEN_NO_LONGER_BE_REFRESHED = 403;
    int REQUEST_SERVER_ERROR_CODE = 500;
    int REQUEST_NETWORK_CONNECTION_ERROR_CODE = 300;
    int REQUEST_RETROFIT_ERROR_CODE = 205;


    int CHOOSEN_COURSE = 1;
    int CHOOSEN_DATE = 2;
    int CHOOSEN_SCHEDULE = 3;

    String BUNDLE_INSTRUCTOR = "bundle_is_instructor";
    String BUNDLE_COURSE_ID = "bundle_couse_id";
    String BUNDLE_COURSE_NAME = "bundle_course_name";
    String BUNDLE_DATE = "bundle_date_day_of_week";
    String BUNDLE_SIMPLE_DATE = "bundle_is_simple_date";
    String BUNDLE_LAYOUT_ID = "bundle_layout_id";

    boolean DO_NOT_SHOW_PRICE = false;
    String STUDENT_TYPE = "student_type";
}