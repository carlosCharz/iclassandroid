package com.wedevol.smartclass.models;

import com.google.gson.JsonObject;

/** Created by paolo on 1/29/17.*/
public class Faculty {
    private int id;
    private String name;
    private String shortName;
    private int universityId;

    public Faculty(){
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

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
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
        private String mShortName;
        private int mUniversityId;

        Builder(int id) {
            mId = id;
        }

        Faculty.Builder name(String name) {
            mName = name;
            return this;
        }

        Faculty.Builder shortName(String shortName) {
            mShortName = shortName;
            return this;
        }

        Faculty.Builder universityId(int universityId) {
            mUniversityId = universityId;
            return this;
        }

        public Faculty build() {
            Faculty faculty = new Faculty();
            faculty.setId(mId);
            faculty.setName(mName);
            faculty.setShortName(mShortName);
            faculty.setUniversityId(mUniversityId);
            return faculty;
        }
    }

    public static Faculty parseFaculty(JsonObject responseObject) {
        Faculty.Builder facultyBuilder;
        facultyBuilder = new Faculty.Builder(responseObject.get("id").getAsInt());

        if (responseObject.has("name") && !responseObject.get("name").isJsonNull()) {
            facultyBuilder.name(responseObject.get("name").getAsString());
        }

        if (responseObject.has("shortName") && !responseObject.get("shortName").isJsonNull()) {
            facultyBuilder.shortName(responseObject.get("shortName").getAsString());
        }

        if (responseObject.has("universityId") && !responseObject.get("universityId").isJsonNull()) {
            facultyBuilder.universityId(responseObject.get("universityId").getAsInt());
        }

        return facultyBuilder.build();
    }
}
