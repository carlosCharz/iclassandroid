package com.wedevol.smartclass.utils.retrofit;

/** Created by Paolo on 3/14/2016*/
interface Urls {
    String LOGIN_URL_INSTRUCTOR = "/auth/instructor/login";
    String LOGIN_URL_STUDENT = "/auth/student/login";
    String REFRESH_TOKEN = "/auth/token/refresh";

    //Courses
    String ALL_COURSES = "/courses"; //get
    String SUGGEST_COURSE = "/coursesuggestions";

    //Student
    String NEW_STUDENT = "/students"; //post
    String STUDENT_ENROLL_ON_COURSE = "/studentenrollments";
    String UPDATE_STUDENT = "/students/{studentId}";
    String GET_COMING_LESSONS = "/students/{studentId}/classes/coming";
    String GET_HISTORIC_LESSONS = "/students/{studentId}/classes/historic";

    //Instructor
    String NEW_INSTRUCTOR = "/instructors"; //post
    String INSTRUCTOR_ENROLL_ON_COURSE = "/instructorenrollments";
    String UPDATE_INSTRUCTOR = "/instructors/{instructorId}";
    String GET_COMING_CLASSES = "/instructors/{instructorId}/classes/coming";
    String GET_HISTORIC_CLASSES = "/instructors/{instructorId}/classes/historic";

    //Course
    String UPDATE_COURSE = "/instructors/{instructorId}/courses/{courseId}/enrollment";//put

    //Student Enrollment
    String GET_STUDENT_COURSES = "/students/{studentId}/courses"; //get

    //Schedule
    String NEW_SCHEDULE = "/schedules"; //post
    String LIST_INSTRUCTOR_SCHEDULE = "/schedules/week"; //list instructorId=1
    String DELETE_SCHEDULE = "/schedules/{scheduleId}";

    //Instructor enrollment
    String GET_INSTRUCTOR_COURSES = "/instructors/{instructorId}/courses"; //get

    //Classes
    String UPDATE_CLASS = "/classes/{classId}"; //update
    String FREE_HOURS_FOR_CLASS = "/schedules/fetch";
    String NEW_CLASS = "/classes";
    String GET_CLASS_INSTRUCTORS = "/instructors/fetch"; //?courseId=1&weekDay=mon&startTime=10&endTime=12
    String STUDENT_RATES_INSTRUCTOR = "/classes/{lessonId}/students/{studentId}/rating/{rating}";
    String INSTRUCTOR_RATES_STUDENT = "/classes/{lessonId}/instructors/{instructorId}/rating/{rating}";
    String REJECT_CLASS = "/classes/{classId}/instructors/{instructorId}/reject";
    String CONFIRM_CLASS = "/classes/{classId}/instructors/{instructorId}/confirm";

    //University
    String GET_UNIVERSITIES = "/universities";

    //Faculties
    String GET_FACULTIES_BY_UNIVERSITY = "/universities/{universityId}/faculties";
    String GET_COURSES_BY_FACULTY_BY_UNIVERSITY = "/universities/{universityId}/faculties/{facultyId}/courses";
}