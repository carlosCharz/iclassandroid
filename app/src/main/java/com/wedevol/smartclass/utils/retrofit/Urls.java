package com.wedevol.smartclass.utils.retrofit;

/** Created by Paolo on 3/14/2016*/
interface Urls {
    String LOGIN_URL_INSTRUCTOR = "/auth/instructor/login";
    String LOGIN_URL_STUDENT = "/auth/student/login";

    //Courses
    String ALL_COURSES = "/courses"; //get

    //Student
    String NEW_STUDENT = "/students"; //post
    String HOME_STUDENT = "/students/{studentId}/classes";
    String STUDENT_ENROLL_ON_COURSE = "/studentenrollments";

    //Instructor
    String NEW_INSTRUCTOR = "/instructors"; //post
    String HOME_INSTRUCTOR = "/instructors/{instructorId}/classes";//home
    String INSTRUCTOR_ENROLL_ON_COURSE = "/instructorenrollments";

    //Student Enrollment
    String GET_STUDENT_COURSES = "/students/{studentId}/courses"; //get

    //Schedule
    String NEW_SCHEDULE = "/schedules"; //post
    String DELETE_SCHEDULE = "/schedules/{scheduleId}"; //delete
    String LIST_INSTRUCTOR_SCHEDULE = "/schedules/week"; //list instructorId=1

    //Instructor enrollment
    String GET_INSTRUCTOR_COURSES = "/instructors/{instructorId}/courses"; //get

    //Classes
    String UPDATE_CLASS = "/classes/{classId}"; //update
    String FREE_HOURS_FOR_CLASS = "/schedules/fetch";
    String NEW_CLASS = "/classes";
    String GET_CLASS_INSTRUCTORS = "/instructors/fetch"; //?courseId=1&weekDay=mon&startTime=10&endTime=12
}