package com.wedevol.smartclass.models;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/** Created by paolorossi on 12/8/16.*/
public class Student extends User{

    public Student() {
        super();
    }

    private static class Builder {
        private int mId;
        private String mFirstName;
        private String mLastName;
        private String mPhone;
        private String mEmail;
        private String mPassword;
        private String mPlaceOptions;
        private double mRating;
        private int mLevel;
        private int mTotalHours;
        private int mFacultyId;
        private int mUniversityId;
        private String mFcmToken;
        private String mFacultyName;
        private String mUniversityName;
        private String mDeviceId;
        private int mRatingCount;
        private String mAccessToken;

        Builder(int id) {
            mId = id;
        }

        Student.Builder firstName(String firstName) {
            mFirstName = firstName;
            return this;
        }

        Student.Builder lastName(String lastName) {
            mLastName = lastName;
            return this;
        }

        Student.Builder phone(String firstName) {
            mPhone = firstName;
            return this;
        }

        Student.Builder email(String email) {
            mEmail = email;
            return this;
        }

        Student.Builder password(String password) {
            mPassword = password;
            return this;
        }

        Student.Builder placeOptions(String placeOptions) {
            mPlaceOptions = placeOptions;
            return this;
        }

        Student.Builder rating(double rating) {
            mRating = rating;
            return this;
        }

        Student.Builder level(int level) {
            mLevel = level;
            return this;
        }

        Student.Builder totalHours(int totalHours) {
            mTotalHours = totalHours;
            return this;
        }

        Student.Builder facultyId(int facultyId) {
            mFacultyId = facultyId;
            return this;
        }

        Student.Builder facultyName(String facultyName) {
            mFacultyName = facultyName;
            return this;
        }

        Student.Builder universityId(int universityId) {
            mUniversityId = universityId;
            return this;
        }

        Student.Builder universityName(String universityName) {
            mUniversityName = universityName;
            return this;
        }

        Student.Builder fcmToken(String fcmToken) {
            mFcmToken = fcmToken;
            return this;
        }

        Student.Builder deviceId(String deviceId) {
            mDeviceId = deviceId;
            return this;
        }

        Student.Builder ratingCount(int ratingCount) {
            mRatingCount = ratingCount;
            return this;
        }

        Student.Builder accessToken(String accessToken) {
            mAccessToken = accessToken;
            return this;
        }

        public Student build() {
            Student student = new Student();
            student.setId(mId);
            student.setFirstname(mFirstName);
            student.setLastname(mLastName);
            student.setPhone(mPhone);
            student.setEmail(mEmail);
            student.setPassword(mPassword);
            student.setPlaceOptions(mPlaceOptions);
            student.setRating(mRating);
            student.setLevel(mLevel);
            student.setTotalHours(mTotalHours);
            student.setUniversityId(mUniversityId);
            student.setUniversityName(mUniversityName);
            student.setFacultyId(mFacultyId);
            student.setFacultyName(mFacultyName);
            student.setFcmToken(mFcmToken);
            student.setDeviceId(mDeviceId);
            student.setRatingCount(mRatingCount);
            student.setAccessToken(mAccessToken);
            return student;
        }
    }

    public static Student parseStudent(JsonObject responseObject) {
        Student.Builder studentBuilder;
        studentBuilder = new Student.Builder(responseObject.get("id").getAsInt());
        if (responseObject.has("firstName") && !responseObject.get("firstName").isJsonNull()) {
            studentBuilder.firstName(responseObject.get("firstName").getAsString());
        }

        if (responseObject.has("lastName") && !responseObject.get("lastName").isJsonNull()) {
            studentBuilder.lastName(responseObject.get("lastName").getAsString());
        }

        if (responseObject.has("phone") && !responseObject.get("phone").isJsonNull()) {
            studentBuilder.phone(responseObject.get("phone").getAsString());
        }

        if (responseObject.has("email") && !responseObject.get("email").isJsonNull()) {
            studentBuilder.email(responseObject.get("email").getAsString());
        }

        if (responseObject.has("password") && !responseObject.get("password").isJsonNull()) {
            studentBuilder.password(responseObject.get("password").getAsString());
        }

        if (responseObject.has("placeOptions") && !responseObject.get("placeOptions").isJsonNull()) {
            Gson gson = new Gson();
            studentBuilder.placeOptions(gson.toJson(responseObject.get("placeOptions").getAsJsonArray()));
        }

        if (responseObject.has("rating") && !responseObject.get("rating").isJsonNull()) {
            studentBuilder.rating(responseObject.get("rating").getAsDouble());
        }

        if (responseObject.has("level") && !responseObject.get("level").isJsonNull()) {
            studentBuilder.level(responseObject.get("level").getAsInt());
        }

        if (responseObject.has("totalHours") && !responseObject.get("totalHours").isJsonNull()) {
            studentBuilder.totalHours(responseObject.get("totalHours").getAsInt());
        }

        if (responseObject.has("universityId") && !responseObject.get("universityId").isJsonNull()) {
            studentBuilder.universityId(responseObject.get("universityId").getAsInt());
        }

        if (responseObject.has("universityName") && !responseObject.get("universityName").isJsonNull()) {
            studentBuilder.universityName(responseObject.get("universityName").getAsString());
        }

        if (responseObject.has("facultyId") && !responseObject.get("facultyId").isJsonNull()) {
            studentBuilder.facultyId(responseObject.get("facultyId").getAsInt());
        }

        if (responseObject.has("facultyName") && !responseObject.get("facultyName").isJsonNull()) {
            studentBuilder.facultyName(responseObject.get("facultyName").getAsString());
        }

        if (responseObject.has("fcmToken") && !responseObject.get("fcmToken").isJsonNull()) {
            studentBuilder.fcmToken(responseObject.get("fcmToken").getAsString());
        }

        if (responseObject.has("deviceId") && !responseObject.get("deviceId").isJsonNull()) {
            studentBuilder.deviceId(responseObject.get("deviceId").getAsString());
        }

        if (responseObject.has("ratingCount") && !responseObject.get("ratingCount").isJsonNull()) {
            studentBuilder.ratingCount(responseObject.get("ratingCount").getAsInt());
        }

        if (responseObject.has("accessToken") && !responseObject.get("accessToken").isJsonNull()) {
            studentBuilder.accessToken(responseObject.get("accessToken").getAsString());
        }

        return studentBuilder.build();
    }

    public JsonObject toJson() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("firstName", this.getFirstname());
        jsonObject.addProperty("lastName", this.getLastname());
        jsonObject.addProperty("phone", this.getPhone());
        jsonObject.addProperty("email", this.getEmail());
        jsonObject.addProperty("password", this.getPassword());
        jsonObject.addProperty("fcmToken", this.getFcmToken());
        jsonObject.addProperty("facultyId", this.getFacultyId());
        jsonObject.addProperty("universityId", this.getUniversityId());
        jsonObject.addProperty("deviceId", this.getDeviceId());

        List<String> list = new ArrayList<>();
        list.add("university");
        list.add("house");
        Gson gson = new Gson();
        JsonElement element = gson.toJsonTree(list, new TypeToken<ArrayList<String>>() {}.getType());
        jsonObject.add("placeOptions", element);
        return jsonObject;
    }
}