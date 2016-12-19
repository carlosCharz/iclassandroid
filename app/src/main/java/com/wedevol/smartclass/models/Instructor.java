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
                      Date birthday, boolean gender, String profilePictureUrl, List<String> placeOptions,
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
        private double mLevel;
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
            mFirstName = firstName;
            return this;
        }

        public Builder email(String firstName) {
            mFirstName = firstName;
            return this;
        }
        public Builder mPassword(String firstName) {
            mFirstName = firstName;
            return this;
        }

        public Builder mPlaceOptions(String firstName) {
            mFirstName = firstName;
            return this;
        }

        public Builder mRating(String firstName) {
            mFirstName = firstName;
            return this;
        }

        public Builder mLevel(String firstName) {
            mFirstName = firstName;
            return this;
        }

        public Builder mTotalHours(String firstName) {
            mFirstName = firstName;
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
            //instructor.setPlaceOptions(mPlaceOptions);
            instructor.setRating(mRating);
            //instructor.setLevel(mLevel);
            instructor.setTotalHours(mTotalHours);


            return instructor;
        }
    }

    public static Instructor parseCity(JsonObject responseObject) {
        Builder cityBuilder;

        cityBuilder = new Builder(responseObject.get("id").getAsInt());

        if (responseObject.has("name") && !responseObject.get("name").isJsonNull()) {
            cityBuilder.firstName(responseObject.get("name").getAsString());
        }

        return cityBuilder.build();
    }
}