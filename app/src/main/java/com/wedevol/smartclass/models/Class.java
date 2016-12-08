package com.wedevol.smartclass.models;

import java.util.Date;

/** Created by paolorossi on 12/8/16.*/
public class Class {
    private int id;
    private Instructor instructor;
    private Course course;
    private Schedule schedule;
    private Date classDate;
    private String status;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getClassDate() {
        return classDate;
    }

    public void setClassDate(Date classDate) {
        this.classDate = classDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
