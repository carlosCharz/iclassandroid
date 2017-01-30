package com.wedevol.smartclass.models;

import com.google.gson.JsonObject;

/** Created by paolorossi on 12/8/16.*/
public class Course {
    private int id = -1;
    private String name = "";
    private String description = "";
    private String faculty = "";
    private String university = "";
    private String status;
    private int price;
    private String currency;
    private int facultyId;
    private int universityId;

    public Course(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public int getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(int facultyId) {
        this.facultyId = facultyId;
    }

    public int getUniversityId() {
        return universityId;
    }

    public void setUniversityId(int universityId) {
        this.universityId = universityId;
    }

    static class Builder {
        private int mId;
        private String mName;
        private String mDescription;
        private String mFaculty;
        private String mUniversity;
        private String mStatus;
        private int mPrice;
        private String mCurrency;
        private int mFacultyId;
        private int mUniversityId;

        Builder(int id) {
            mId = id;
        }

        Course.Builder name(String name) {
            mName = name;
            return this;
        }

        Course.Builder description(String description) {
            mDescription = description;
            return this;
        }

        Course.Builder faculty(String faculty) {
            mFaculty = faculty;
            return this;
        }

        Course.Builder university(String university) {
            mUniversity = university;
            return this;
        }

        Course.Builder status(String status) {
            mStatus = status;
            return this;
        }

        Course.Builder price(int price) {
            mPrice = price;
            return this;
        }

        Course.Builder currency(String currency) {
            mCurrency = currency;
            return this;
        }

        Course.Builder facultyId(int facultyId) {
            mFacultyId = facultyId;
            return this;
        }

        Course.Builder universityId(int universityId) {
            mUniversityId = universityId;
            return this;
        }

        public Course build() {
            Course course = new Course();
            course.setId(mId);
            course.setName(mName);
            course.setDescription(mDescription);
            course.setFaculty(mFaculty);
            course.setUniversity(mUniversity);
            course.setStatus(mStatus);
            course.setPrice(mPrice);
            course.setCurrency(mCurrency);
            course.setFacultyId(mFacultyId);
            course.setUniversityId(mUniversityId);
            return course;
        }
    }

    public static Course parseCourse(JsonObject responseObject) {
        Course.Builder courseBuilder;
        courseBuilder = new Course.Builder(responseObject.get("id").getAsInt());
        if (responseObject.has("name") && !responseObject.get("name").isJsonNull()) {
            courseBuilder.name(responseObject.get("name").getAsString());
        }

        if (responseObject.has("description") && !responseObject.get("description").isJsonNull()) {
            courseBuilder.description(responseObject.get("description").getAsString());
        }

        if (responseObject.has("faculty") && !responseObject.get("faculty").isJsonNull()) {
            courseBuilder.faculty(responseObject.get("faculty").getAsString());
        }

        if (responseObject.has("university") && !responseObject.get("university").isJsonNull()) {
            courseBuilder.university(responseObject.get("university").getAsString());
        }

        if (responseObject.has("status") && !responseObject.get("status").isJsonNull()) {
            courseBuilder.status(responseObject.get("status").getAsString());
        }

        if (responseObject.has("price") && !responseObject.get("price").isJsonNull()) {
            courseBuilder.price(responseObject.get("price").getAsInt());
        }

        if (responseObject.has("currency") && !responseObject.get("currency").isJsonNull()) {
            courseBuilder.currency(responseObject.get("currency").getAsString());
        }

        if (responseObject.has("facultyId") && !responseObject.get("facultyId").isJsonNull()) {
            courseBuilder.facultyId(responseObject.get("facultyId").getAsInt());
        }

        if (responseObject.has("universityId") && !responseObject.get("universityId").isJsonNull()) {
            courseBuilder.universityId(responseObject.get("universityId").getAsInt());
        }

        return courseBuilder.build();
    }
}