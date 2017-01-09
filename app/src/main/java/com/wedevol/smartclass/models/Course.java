package com.wedevol.smartclass.models;

import com.google.gson.JsonObject;

/** Created by paolorossi on 12/8/16.*/
public class Course {
    private int id;
    private String name;
    private String description;
    private String university;
    private String career;

    public Course(){
    }

    public Course(int id, String name, String description, String university, String career){
        this.id = id;
        this.name = name;
        this.description = description;
        this.university = university;
        this.career = career;
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

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }

    static class Builder {
        private int mId;
        private String mName;
        private String mDescription;
        private String mUniversity;
        private String mCareer;

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

        Course.Builder university(String university) {
            mUniversity = university;
            return this;
        }

        Course.Builder career(String career) {
            mCareer = career;
            return this;
        }

        public Course build() {
            Course course = new Course();
            course.setId(mId);
            course.setName(mName);
            course.setDescription(mDescription);
            course.setUniversity(mUniversity);
            course.setCareer(mCareer);
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

        if (responseObject.has("university") && !responseObject.get("university").isJsonNull()) {
            courseBuilder.university(responseObject.get("university").getAsString());
        }

        if (responseObject.has("career") && !responseObject.get("career").isJsonNull()) {
            courseBuilder.career(responseObject.get("career").getAsString());
        }

        return courseBuilder.build();
    }
}