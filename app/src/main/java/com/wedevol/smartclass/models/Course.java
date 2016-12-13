package com.wedevol.smartclass.models;

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

}