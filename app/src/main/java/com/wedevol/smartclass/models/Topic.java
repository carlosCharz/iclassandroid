package com.wedevol.smartclass.models;

/** Created by paolorossi on 12/8/16.*/
public class Topic {
    private int id;
    private Course course;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}