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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
