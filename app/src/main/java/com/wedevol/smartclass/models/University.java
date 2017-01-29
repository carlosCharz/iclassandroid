package com.wedevol.smartclass.models;

import com.google.gson.JsonObject;

/** Created by paolo on 1/29/17.*/
public class University {
    private int id;
    private String name;
    private String shortName;

    public University(){

    }

    /*{
        "name": "Pontificia Universidad Catolica del Perú",
            "shortName": "PUCP"
    }*/

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

    static class Builder {
        private int mId;
        private String mName;
        private String mShortName;
        private int mUniversityId;

        Builder(int id) {
            mId = id;
        }

        University.Builder name(String name) {
            mName = name;
            return this;
        }

        University.Builder shortName(String shortName) {
            mShortName = shortName;
            return this;
        }

        public University build() {
            University university = new University();
            university.setId(mId);
            university.setName(mName);
            university.setShortName(mShortName);
            return university;
        }
    }

    public static University parseUniversity(JsonObject responseObject) {
        University.Builder universityBuilder;
        universityBuilder = new University.Builder(responseObject.get("id").getAsInt());

        if (responseObject.has("name") && !responseObject.get("name").isJsonNull()) {
            universityBuilder.name(responseObject.get("name").getAsString());
        }

        if (responseObject.has("shortName") && !responseObject.get("shortName").isJsonNull()) {
            universityBuilder.shortName(responseObject.get("shortName").getAsString());
        }

        return universityBuilder.build();
    }
}
