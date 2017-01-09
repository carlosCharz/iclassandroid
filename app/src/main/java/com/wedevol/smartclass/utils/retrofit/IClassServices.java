package com.wedevol.smartclass.utils.retrofit;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;

/** Created by Paolo on 3/14/2016.*/
public interface IClassServices {
    /**Login methods*/
    @FormUrlEncoded
    @POST(Urls.LOGIN_URL)
    void loginEmailUser(@Field("email") String email, @Field("password") String password, Callback<JsonObject> callback);

    @FormUrlEncoded
    @POST(Urls.LOGOUT)
    void logout(@Header("Authorization") String authorization, Callback<JsonObject> callback);

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
    void newStudent(@Header("Authorization") String authorization, @Body String student,
                    Callback<JsonObject> callback);

    @GET(Urls.HOME_INSTRUCTOR)
    void studentLessons(@Header("Authorization") String authorization, @Path("studentId") int studentId,
                        @Query("actualDate") String date, @Query("actualTime") int actualTime,
                        @Query("status") String status, Callback<JsonArray> callback);

    /**Instructors*/
    @GET(Urls.ONE_INSTRUCTOR)
    void getInstructor(@Header("Authorization") String authorization, @Path("userId") int userId,
                       Callback<JsonObject> callback);

    @POST(Urls.NEW_INSTRUCTOR)
    void newInstructor(@Header("Authorization") String authorization, @Body String instructor,
                       Callback<JsonObject> callback);

    @GET(Urls.HOME_INSTRUCTOR)
    void instructorLessons(@Header("Authorization") String authorization, @Path("instructorId") int instructorId,
                           @Query("actualDate") String date, @Query("actualTime") int actualTime,
                           @Query("status") String status, Callback<JsonArray> callback);

    /**Student enrollment */
    @GET(Urls.GET_STUDENT_COURSES)
    void getStudentCourses(@Header("Authorization") String authorization, @Path("studentId") int studentId, Callback<JsonArray> callback);

    @DELETE(Urls.DELETE_SCHEDULE)
    void deleteSchedule(@Header("Authorization") String authorization, @Path("scheduleId") int scheduleId, Callback<JsonObject> callback);

    /**Schedule */
    @POST(Urls.NEW_SCHEDULE)
    void newSchedule(@Header("Authorization") String authorization, @Body String schedule, Callback<JsonObject> callback);

    @GET(Urls.LIST_INSTRUCTOR_SCHEDULE)
    void listSchedule(@Header("Authorization") String authorization, @Query("instructorId") int instructorId, Callback<JsonArray> callback);

    /**Instructor enrollment */
    @GET(Urls.ALL_INSTRUCTOR_COURSES)
    void getInstructorCourses(@Header("Authorization") String authorization, @Path("instructorId") int instructorId, Callback<JsonObject> callback);

    /**Classes*/
    @POST(Urls.UPDATE_CLASS)
    void updateLesson(@Header("Authorization") String authorization, @Body String lesson, Callback<JsonObject> callback);


}