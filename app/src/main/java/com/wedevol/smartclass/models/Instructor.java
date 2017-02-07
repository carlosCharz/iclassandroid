package com.wedevol.smartclass.models;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/** Created by paolorossi on 12/8/16.*/
public class Instructor extends User {
    private int price = -1;

    public Instructor(){
        super();
    }

    public String getName() {
        return getFirstname();
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
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
        private int mPrice;
        private int mFacultyId;
        private int mUniversityId;
        private String mFcmToken;
        private String mFacultyName;
        private String mUniversityName;
        private String mDeviceId;
        private int mRatingCount;

        Builder(int id) {
            mId = id;
        }

        Builder firstName(String firstName) {
            mFirstName = firstName;
            return this;
        }

        Builder lastName(String lastName) {
            mLastName = lastName;
            return this;
        }

        Builder phone(String firstName) {
            mPhone = firstName;
            return this;
        }

        Builder email(String email) {
            mEmail = email;
            return this;
        }

        Builder password(String password) {
            mPassword = password;
            return this;
        }

        Builder placeOptions(String placeOptions) {
            mPlaceOptions = placeOptions;
            return this;
        }

        Builder rating(double rating) {
            mRating = rating;
            return this;
        }

        Builder level(int level) {
            mLevel = level;
            return this;
        }

        Builder totalHours(int totalHours) {
            mTotalHours = totalHours;
            return this;
        }

        Builder price(int price) {
            mPrice = price;
            return this;
        }

        Builder facultyId(int facultyId) {
            mFacultyId = facultyId;
            return this;
        }

        Builder facultyName(String facultyName) {
            mFacultyName = facultyName;
            return this;
        }

        Builder universityId(int universityId) {
            mUniversityId = universityId;
            return this;
        }

        Builder universityName(String universityName) {
            mUniversityName = universityName;
            return this;
        }

        Builder fcmToken(String fcmToken) {
            mFcmToken = fcmToken;
            return this;
        }

        Builder deviceId(String deviceId) {
            mDeviceId = deviceId;
            return this;
        }

        Builder ratingCount(int ratingCount) {
            mRatingCount = ratingCount;
            return this;
        }

        public Instructor build() {
            Instructor instructor = new Instructor();
            instructor.setId(mId);
            instructor.setFirstname(mFirstName);
            instructor.setLastname(mLastName);
            instructor.setPhone(mPhone);
            instructor.setEmail(mEmail);
            instructor.setPassword(mPassword);
            instructor.setPlaceOptions(mPlaceOptions);
            instructor.setRating(mRating);
            instructor.setLevel(mLevel);
            instructor.setTotalHours(mTotalHours);
            instructor.setPrice(mPrice);
            instructor.setUniversityId(mUniversityId);
            instructor.setUniversityName(mUniversityName);
            instructor.setFacultyId(mFacultyId);
            instructor.setFacultyName(mFacultyName);
            instructor.setFcmToken(mFcmToken);
            instructor.setDeviceId(mDeviceId);
            instructor.setRatingCount(mRatingCount);

            return instructor;
        }
    }

    public static Instructor parseInstructor(JsonObject responseObject) {
        Builder instructorBuilder;
        instructorBuilder = new Builder(responseObject.get("id").getAsInt());
        if (responseObject.has("firstName") && !responseObject.get("firstName").isJsonNull()) {
            instructorBuilder.firstName(responseObject.get("firstName").getAsString());
        }

        if (responseObject.has("lastName") && !responseObject.get("lastName").isJsonNull()) {
            instructorBuilder.lastName(responseObject.get("lastName").getAsString());
        }

        if (responseObject.has("phone") && !responseObject.get("phone").isJsonNull()) {
            instructorBuilder.phone(responseObject.get("phone").getAsString());
        }

        if (responseObject.has("email") && !responseObject.get("email").isJsonNull()) {
            instructorBuilder.email(responseObject.get("email").getAsString());
        }

        if (responseObject.has("password") && !responseObject.get("password").isJsonNull()) {
            instructorBuilder.password(responseObject.get("password").getAsString());
        }

        if (responseObject.has("placeOptions") && !responseObject.get("placeOptions").isJsonNull()) {
            Gson gson = new Gson();
            instructorBuilder.placeOptions(gson.toJson(responseObject.get("placeOptions").getAsJsonArray()));
        }

        if (responseObject.has("rating") && !responseObject.get("rating").isJsonNull()) {
            instructorBuilder.rating(responseObject.get("rating").getAsDouble());
        }

        if (responseObject.has("level") && !responseObject.get("level").isJsonNull()) {
            instructorBuilder.level(responseObject.get("level").getAsInt());
        }

        if (responseObject.has("totalHours") && !responseObject.get("totalHours").isJsonNull()) {
            instructorBuilder.totalHours(responseObject.get("totalHours").getAsInt());
        }

        if (responseObject.has("price") && !responseObject.get("price").isJsonNull()) {
            instructorBuilder.price(responseObject.get("price").getAsInt());
        }

        if (responseObject.has("universityId") && !responseObject.get("universityId").isJsonNull()) {
            instructorBuilder.universityId(responseObject.get("universityId").getAsInt());
        }

        if (responseObject.has("universityName") && !responseObject.get("universityName").isJsonNull()) {
            instructorBuilder.universityName(responseObject.get("universityName").getAsString());
        }

        if (responseObject.has("facultyId") && !responseObject.get("facultyId").isJsonNull()) {
            instructorBuilder.facultyId(responseObject.get("facultyId").getAsInt());
        }

        if (responseObject.has("facultyName") && !responseObject.get("facultyName").isJsonNull()) {
            instructorBuilder.facultyName(responseObject.get("facultyName").getAsString());
        }

        if (responseObject.has("fcmToken") && !responseObject.get("fcmToken").isJsonNull()) {
            instructorBuilder.fcmToken(responseObject.get("fcmToken").getAsString());
        }

        if (responseObject.has("deviceId") && !responseObject.get("deviceId").isJsonNull()) {
            instructorBuilder.deviceId(responseObject.get("deviceId").getAsString());
        }

        if (responseObject.has("ratingCount") && !responseObject.get("ratingCount").isJsonNull()) {
            instructorBuilder.ratingCount(responseObject.get("ratingCount").getAsInt());
        }

        return instructorBuilder.build();
    }

    public JsonObject toJson(){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("firstName", this.getFirstname());
        jsonObject.addProperty("lastName", this.getLastname());
        jsonObject.addProperty("phone", this.getPhone());
        jsonObject.addProperty("email", this.getEmail());
        jsonObject.addProperty("password", this.getPassword());
        jsonObject.addProperty("fcmToken", this.getFcmToken());
        jsonObject.addProperty("facultyId", this.getFacultyId());
        jsonObject.addProperty("universityId", this.getUniversityId());
        jsonObject.addProperty("fcmToken", getFcmToken());
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