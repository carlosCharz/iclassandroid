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

    public Class(){

    }

    public Class(int id, Instructor instructor, Course course, Schedule schedule, Date classDate, String status){
        this.id = id;
        this.instructor = instructor;
        this.course = course;
        this.schedule = schedule;
        this.classDate = classDate;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public Date getClassDate() {
        return classDate;
    }

    public void setClassDate(Date classDate) {
        this.classDate = classDate;
    }
}
