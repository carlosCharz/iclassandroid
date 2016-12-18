package com.wedevol.smartclass.utils.retrofit;

/** Created by Paolo on 3/14/2016*/
public interface Urls {
    String LOGIN_URL = "";
    String LOGOUT = "";

    String NEW_COURSE = "/courses"; //post
    String ONE_COURSE = "/courses/"; //get
    String ALL_COURSES = "/courses"; //get

    String NEW_STUDENT = "/students"; //post
    String ONE_STUDENT = "/students/"; //get
    String ALL_STUDENTS = "/students"; //get

    String NEW_INSTRUCTOR = "/instructors"; //post
    String ONE_INSTRUCTOR = "/instructors/"; //get
    String ALL_INSTRUCTORS = "/instructors"; //get
}