package com.wedevol.smartclass.models;

import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/** Created by paolorossi on 12/8/16.*/
public class Instructor extends User {
    private List<Course> enrolledCourses = new ArrayList<>();
    private List<Schedule> schedules = new ArrayList<>();
    private double hourlyRate;

    public Instructor(){

    }

    public Instructor(int id, String firstname, String lastname, String phone, String email, String password,
                      Date birthday, boolean gender, String profilePictureUrl, String placeOptions,
                      String university, double rating, int level, int totalHours) {
        super( id,  firstname,  lastname,  phone,  email,  password,
                birthday,  gender,  profilePictureUrl, placeOptions,
                university,  rating,  level,  totalHours);
    }

    public List<Course> getEnrolledCourses() {
        return enrolledCourses;
    }

    public void setEnrolledCourses(List<Course> enrolledCourses) {
        this.enrolledCourses = enrolledCourses;
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }

    public String getName() {
        return getFirstname();
    }

    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    //TODO: json builder
    public static class Builder {
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

        public Builder(int id) {
            mId = id;
        }

        public Builder firstName(String firstName) {
            mFirstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            mLastName = lastName;
            return this;
        }

        public Builder phone(String firstName) {
            mPhone = firstName;
            return this;
        }

        public Builder email(String email) {
            mEmail = email;
            return this;
        }
        public Builder password(String password) {
            mPassword = password;
            return this;
        }

        public Builder placeOptions(String placeOptions) {
            mPlaceOptions = placeOptions;
            return this;
        }

        public Builder rating(double rating) {
            mRating = rating;
            return this;
        }

        public Builder level(int level) {
            mLevel = level;
            return this;
        }

        public Builder totalHours(int totalHours) {
            mTotalHours = totalHours;
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

            return instructor;
        }
    }

    public static Instructor parseInstructor(JsonObject responseObject) {
        Builder cityBuilder;
        cityBuilder = new Builder(responseObject.get("id").getAsInt());
        if (responseObject.has("firstName") && !responseObject.get("firstName").isJsonNull()) {
            cityBuilder.firstName(responseObject.get("firstName").getAsString());
        }

        if (responseObject.has("lastName") && !responseObject.get("lastName").isJsonNull()) {
            cityBuilder.lastName(responseObject.get("lastName").getAsString());
        }

        if (responseObject.has("phone") && !responseObject.get("phone").isJsonNull()) {
            cityBuilder.phone(responseObject.get("phone").getAsString());
        }

        if (responseObject.has("email") && !responseObject.get("email").isJsonNull()) {
            cityBuilder.email(responseObject.get("email").getAsString());
        }

        if (responseObject.has("password") && !responseObject.get("password").isJsonNull()) {
            cityBuilder.password(responseObject.get("password").getAsString());
        }

        if (responseObject.has("placeOptions") && !responseObject.get("placeOptions").isJsonNull()) {
            cityBuilder.placeOptions(responseObject.get("placeOptions").getAsString());
        }

        if (responseObject.has("rating") && !responseObject.get("rating").isJsonNull()) {
            cityBuilder.rating(responseObject.get("rating").getAsDouble());
        }

        if (responseObject.has("level") && !responseObject.get("level").isJsonNull()) {
            cityBuilder.level(responseObject.get("level").getAsInt());
        }

        if (responseObject.has("totalHours") && !responseObject.get("totalHours").isJsonNull()) {
            cityBuilder.totalHours(responseObject.get("totalHours").getAsInt());
        }

        return cityBuilder.build();
    }
}