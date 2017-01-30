package com.wedevol.smartclass.utils.interfaces;

/** Created by paolorossi on 12/8/16.*/
public interface Constants {
    String USER_PHOTO = "user_photo";

    String STUDENT_TYPE = "student_type";
    String INSTRUCTOR_TYPE = "instructor_type";
    String STUDENT_COURSE_TYPE = "student_course_type";

    boolean REQUEST_TYPE = true;
    boolean NON_REQUEST_TYPE = false;

    //THIS ONES NEED TO BE VERIFIED
    int REQUEST_SERVER_ERROR_CODE = 400;
    int REQUEST_NETWORK_CONNECTION_ERROR_CODE = 300;
    int REQUEST_RETROFIT_ERROR_CODE = 205;

    int CAMERA_REQUEST_CODE = 1;
    int GALLERY_REQUEST_CODE = 2;
    int CHOOSEN_COURSE = 3;
    int CHOOSEN_DATE = 4;
    int CHOOSEN_SCHEDULE = 5;
    int CHOOSEN_BANK = 6;
    int CHOOSEN_UNIVERSITY = 7;
    int CHOOSEN_FACULTY = 8;

    String BUNDLE_INSTRUCTOR = "bundle_is_instructor";
    String BUNDLE_COURSE_ID = "bundle_couse_id";
    String BUNDLE_COURSE_NAME = "bundle_course_name";
    String BUNDLE_DATE = "bundle_date_day_of_week";
    String BUNDLE_SIMPLE_DATE = "bundle_is_simple_date";
    String BUNDLE_LAYOUT_ID = "bundle_layout_id";
    String BUNDLE_BANK_NAME = "bundle_bank_name";
    String BUNDLE_CURRENCY = "bundle_currency_value";
    String BUNDLE_STATUS = "bundle_status";
    String BUNDLE_POSITION = "bundle_position";
    String BUNDLE_PRICE_CHANGE_LISTENER = "bundle_price_listener";
    String BUNDLE_SEARCHED_COURSES_LISTENER = "bundle_searched_courses_listener";
    String BUNDLE_LESSON_ID = "bundle_lesson_id";
    String BUNDLE_UNIVERSITY_ID = "bundle_university_id";
    String BUNDLE_UNIVERSITY_NAME = "bundle_university_name";
    String BUNDLE_FACULTY_ID = "bundle_faculty_id";
    String BUNDLE_FACULTY_NAME = "bundle_faculty_name";

    boolean SHOW_COURSE_PRICE = true;
    boolean DO_NOT_SHOW_COURSE_PRICE = false;
    boolean SELECTABLE_COURSE = false;
    boolean NOT_SELECTABLE_COURSE = true;


}