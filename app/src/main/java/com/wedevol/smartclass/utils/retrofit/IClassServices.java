package com.wedevol.smartclass.utils.retrofit;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;

/** Created by Paolo on 3/14/2016.*/
public interface IClassServices {
    /**Login methods*/
    @POST(Urls.LOGIN_URL_INSTRUCTOR)
    void authInstructor(@Header("email") String authorization, @Body JsonObject request, Callback<JsonObject> callback);

    @POST(Urls.LOGIN_URL_STUDENT)
    void authStudent(@Header("email") String authorization, @Body JsonObject request, Callback<JsonObject> callback);

    /**Courses*/
    @GET(Urls.ONE_COURSE)
    void getCourse(@Header("Authorization") String authorization, @Query("courseId") int courseId,
                   Callback<JsonObject> callback);

    @GET(Urls.ALL_COURSES)
    void getAllCourses(@Header("Authorization") String authorization, Callback<JsonArray> callback);

    /**Students*/
    @GET(Urls.ONE_STUDENT)
    void getStudent(@Header("Authorization") String authorization, @Path("userId") int userId,
                    Callback<JsonObject> callback);

    @POST(Urls.NEW_STUDENT)
    void newStudent(@Header("Authorization") String authorization, @Body JsonObject student,
                    Callback<JsonObject> callback);

    @GET(Urls.HOME_STUDENT)
    void studentLessons(@Header("Authorization") String authorization, @Path("studentId") int studentId,
                        @Query("actualDate") String date, @Query("actualTime") int actualTime,
                        @Query("status") String status, Callback<JsonArray> callback);

    @POST(Urls.STUDENT_ENROLL_ON_COURSE)
    void enrollOnCourseStudent(@Header("Authorization") String authorization, @Body JsonObject course, IClassCallback<JsonObject> iClassCallback);

    /**Instructors*/
    @GET(Urls.ONE_INSTRUCTOR)
    void getInstructor(@Header("Authorization") String authorization, @Path("userId") int userId,
                       Callback<JsonObject> callback);

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

    /**Student enrollment */
    @GET(Urls.GET_STUDENT_COURSES)
    void getStudentCourses(@Header("Authorization") String authorization,
                           @Path("studentId") int studentId, Callback<JsonArray> callback);

    @DELETE(Urls.DELETE_SCHEDULE)
    void deleteSchedule(@Header("Authorization") String authorization,
                        @Path("scheduleId") int scheduleId, Callback<JsonObject> callback);

    /**Schedule */
    @POST(Urls.NEW_SCHEDULE)
    void newSchedule(@Header("Authorization") String authorization,
                     @Body JsonObject schedule, Callback<JsonObject> callback);

    @GET(Urls.LIST_INSTRUCTOR_SCHEDULE)
    void listSchedule(@Header("Authorization") String authorization,
                      @Query("instructorId") int instructorId, Callback<JsonArray> callback);

    /**Instructor enrollment */
    @GET(Urls.GET_INSTRUCTOR_COURSES)
    void getInstructorCourses(@Header("Authorization") String authorization,
                              @Path("instructorId") int instructorId, Callback<JsonArray> callback);

    /**Classes*/
    @POST(Urls.UPDATE_CLASS)
    void updateLesson(@Header("Authorization") String authorization, @Body JsonObject lesson,
                      Callback<JsonObject> callback);

    @GET(Urls.FREE_HOURS_FOR_CLASS)
    void getFreeHours(@Header("Authorization") String authorization, @Query("courseId") int courseId,
                      @Query("weekDay") String weekDay, Callback<JsonArray> callback);

    @POST(Urls.NEW_CLASS)
    void newLesson(@Header("Authorization") String authorization, @Body JsonObject lesson,
                   Callback<JsonObject> callback);

    @GET(Urls.GET_CLASS_INSTRUCTORS ) //?courseId=1&weekDay=mon&startTime=10&endTime=12
    void getInstructorsForClass(@Header("Authorization") String authorization, @Query("courseId") int courseId,
                                @Query("weekDay") String weekDay, @Query("startTime") int startTime,
                                @Query("endTime") int endTime, Callback<JsonArray> callback);

}