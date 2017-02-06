package com.wedevol.smartclass.utils.retrofit;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;
import retrofit.http.Query;

/** Created by Paolo on 3/14/2016.*/
public interface IClassServices {
    /**Login methods*/
    @POST(Urls.LOGIN_URL_INSTRUCTOR)
    void authInstructor(@Header("Authorization") String authorization, @Body JsonObject request, Callback<JsonObject> callback);

    @POST(Urls.LOGIN_URL_STUDENT)
    void authStudent(@Header("Authorization") String authorization, @Body JsonObject request, Callback<JsonObject> callback);

    @POST(Urls.REFRESH_TOKEN)
    void refreshToken(@Header("Authorization") String authorization, @Body JsonObject request, Callback<JsonObject> callback);

    /*Courses*/
    @GET(Urls.ALL_COURSES)
    void getAllCourses(@Header("Authorization") String authorization, Callback<JsonArray> callback);

    @POST(Urls.SUGGEST_COURSE)
    void suggestCourse(@Header("Authorization") String authorization, @Body JsonObject course,  Callback<JsonObject> callback);

    @GET(Urls.GET_COURSES_BY_FACULTY_BY_UNIVERSITY)
    void getCoursesByFacultyByUniversity(@Header("Authorization") String authorization, @Path("universityId") int universityId,
                                         @Path("facultyId") int facultyId, Callback<JsonArray> callback);
    /**Students*/
    @POST(Urls.NEW_STUDENT)
    void newStudent(@Header("Authorization") String authorization, @Body JsonObject student,
                    Callback<JsonObject> callback);

    @GET(Urls.HOME_STUDENT)
    void studentLessons(@Header("Authorization") String authorization, @Path("studentId") int studentId,
                        @Query("actualDate") String date, @Query("actualTime") int actualTime,
                        @Query("status") String status, Callback<JsonArray> callback);

    @POST(Urls.STUDENT_ENROLL_ON_COURSE)
    void enrollOnCourseStudent(@Header("Authorization") String authorization, @Body JsonObject course, IClassCallback<JsonObject> iClassCallback);

    @PUT(Urls.UPDATE_STUDENT)
    void updateStudent(@Header("Authorization") String authorization, @Path("studentId") int studentId,
                       @Body JsonObject student, IClassCallback<JsonObject> iClassCallback);

    @POST(Urls.STUDENT_RATES_INSTRUCTOR)
    void rateInstructor(@Header("Authorization") String authorization, @Path("lessonId") int lessonId, @Path("studentId") int studentId,
                        @Path("rating") int rating, @Body JsonObject jsonObject,  Callback<JsonObject> callback);

    @GET(Urls.GET_COMING_LESSONS)
    void getStudentComingClasses(@Header("Authorization") String authorization, @Path("studentId") int studentId,
                                    @Query("actualDate") String date, @Query("actualTime") int actualTime,
                                    @Query("status") String status, Callback<JsonArray> callback);

    @GET(Urls.GET_HISTORIC_LESSONS)
    void getStudentHistoricClasses(@Header("Authorization") String authorization, @Path("studentId") int studentId,
                                 @Query("actualDate") String date, @Query("actualTime") int actualTime,
                                 @Query("status") String status, Callback<JsonArray> callback);


    /**Instructors*/
    @POST(Urls.NEW_INSTRUCTOR)
    void newInstructor(@Header("Authorization") String authorization, @Body JsonObject instructor,
                       Callback<JsonObject> callback);

    @GET(Urls.HOME_INSTRUCTOR)
    void instructorLessons(@Header("Authorization") String authorization, @Path("instructorId") int instructorId,
                           @Query("actualDate") String date, @Query("actualTime") int actualTime,
                           @Query("status") String status, Callback<JsonArray> callback);

    @POST(Urls.INSTRUCTOR_ENROLL_ON_COURSE)
    void enrollOnCourseInstructor(@Header("Authorization") String authorization, @Body JsonObject instructorEnrollment,
                                  Callback<JsonObject> callback);

    @PUT(Urls.UPDATE_INSTRUCTOR)
    void updateInstructor(@Header("Authorization") String authorization, @Path("instructorId") int instructorId,
                          @Body JsonObject instructor, IClassCallback<JsonObject> iClassCallback);

    @POST(Urls.INSTRUCTOR_RATES_STUDENT)
    void rateStudent(@Header("Authorization") String authorization, @Path("lessonId") int lessonId, @Path("instructorId") int instructorId,
                     @Path("rating") int rating, @Body JsonObject jsonObject, Callback<JsonObject> callback);

    @GET(Urls.GET_COMING_CLASSES)
    void getInstructorComingClasses(@Header("Authorization") String authorization, @Path("instructorId") int instructorId,
                           @Query("actualDate") String date, @Query("actualTime") int actualTime,
                           @Query("status") String status, Callback<JsonArray> callback);

    @GET(Urls.GET_HISTORIC_CLASSES)
    void getInstructorHistoricClasses(@Header("Authorization") String authorization, @Path("instructorId") int instructorId,
                                    @Query("actualDate") String date, @Query("actualTime") int actualTime,
                                    @Query("status") String status, Callback<JsonArray> callback);

    /**Student enrollment */
    @GET(Urls.GET_STUDENT_COURSES)
    void getStudentCourses(@Header("Authorization") String authorization,
                           @Path("studentId") int studentId, @Query("status") String status, Callback<JsonArray> callback);

    /**Schedule */
    @POST(Urls.NEW_SCHEDULE)
    void newSchedule(@Header("Authorization") String authorization,
                     @Body JsonObject schedule, Callback<JsonObject> callback);

    @GET(Urls.LIST_INSTRUCTOR_SCHEDULE)
    void listSchedule(@Header("Authorization") String authorization,
                      @Query("instructorId") int instructorId, Callback<JsonArray> callback);

    @DELETE(Urls.DELETE_SCHEDULE)
    void deleteSchedule(@Header("Authorization") String authorization, @Path("scheduleId") int scheduleId,
                        Callback<JsonObject> callback);

    /**Instructor enrollment */
    @GET(Urls.GET_INSTRUCTOR_COURSES)
    void getInstructorCourses(@Header("Authorization") String authorization,
                              @Path("instructorId") int instructorId, @Query("status") String status, Callback<JsonArray> callback);

    @PUT(Urls.UPDATE_COURSE)
    void updateInstructorCourse(@Header("Authorization") String authorization, @Path("instructorId") int instructorId,
                                @Path("courseId") int courseId, @Body JsonObject course, Callback<JsonObject> callback);


    /**Classes*/
    @PUT(Urls.UPDATE_CLASS)
    void updateLesson(@Header("Authorization") String authorization, @Path("classId") int classId, @Body JsonObject lesson,
                      Callback<JsonObject> callback);

    @GET(Urls.FREE_HOURS_FOR_CLASS)
    void getFreeHours(@Header("Authorization") String authorization, @Query("courseId") int courseId,
                      @Query("weekDay") String weekDay, Callback<JsonArray> callback);

    @POST(Urls.NEW_CLASS)
    void newLesson(@Header("Authorization") String authorization, @Body JsonObject lesson,
                   Callback<JsonObject> callback);

    @GET(Urls.GET_CLASS_INSTRUCTORS) //?courseId=1&weekDay=mon&startTime=10&endTime=12
    void getInstructorsForClass(@Header("Authorization") String authorization, @Query("courseId") int courseId,
                                @Query("weekDay") String weekDay, @Query("startTime") int startTime,
                                @Query("endTime") int endTime, Callback<JsonArray> callback);

    /**Universities*/
    @GET(Urls.GET_UNIVERSITIES)
    void getUniversities(@Header("Authorization") String authorization, Callback<JsonArray> callback);

    /**Faculties*/
    @GET(Urls.GET_FACULTIES_BY_UNIVERSITY)
    void getUniversityFaculties(@Header("Authorization") String authorization, @Path("universityId") int universityId,
                                Callback<JsonArray> callback);
}