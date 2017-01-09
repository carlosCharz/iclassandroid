package com.wedevol.smartclass.utils.retrofit;

/** Created by Paolo on 3/14/2016*/
interface Urls {
    String LOGIN_URL = "";
    String LOGOUT = "";

    //Courses
    String ONE_COURSE = "/courses/"; //get
    String ALL_COURSES = "/courses"; //get

    //Student
    String NEW_STUDENT = "/students"; //post
    String ONE_STUDENT = "/students/{userId}"; //get
    String HOME_STUDENT = "/students/{studentId}/classes?actualDate=09/01/2017&actualTime=8&status=confirmed";


    //Instructor
    String NEW_INSTRUCTOR = "/instructors"; //post
    String ONE_INSTRUCTOR = "/instructors/{userId}"; //get
    String HOME_INSTRUCTOR = "/instructors/{instructorId}/classes";//home


    //Student Enrollment
    String GET_STUDENT_COURSES = "/students/{studentId}/courses"; //get

    //Schedule
    //String ALL_SCHEDULES = "/schedules"; //get
    String NEW_SCHEDULE = "/schedules"; //post
    String DELETE_SCHEDULE = "/schedules/{scheduleId}"; //delete
    String LIST_INSTRUCTOR_SCHEDULE = "/schedules/week"; //list instructorId=1


    //Instructor enrollment
    String ALL_INSTRUCTOR_COURSES = "/instructors/{instructorId}/courses"; //get

    //Classes
    String UPDATE_CLASS = "/classes/{classId}"; //update
}