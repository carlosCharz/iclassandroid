package com.wedevol.smartclass.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/** Created by paolorossi on 12/8/16.*/
public class Student extends User{
    private List<Course> courses = new ArrayList<>();
    private List<String> coursesStatus = new ArrayList<>();
    private List<Class> classes = new ArrayList<>();


    public Student(int id, String firstname, String lastname, String phone, String email, String password,
                   Date birthday, boolean gender, String profilePictureUrl, List<String> placeOptions,
                   String university, double rating, int level, int totalHours) {
        super( id,  firstname,  lastname,  phone,  email,  password,
                birthday,  gender,  profilePictureUrl, placeOptions,
                university,  rating,  level,  totalHours);
    }

    public Student() {
        super();
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public List<String> getCoursesStatus() {
        return coursesStatus;
    }

    public void setCoursesStatus(List<String> coursesStatus) {
        this.coursesStatus = coursesStatus;
    }

    public List<Class> getClasses() {
        return classes;
    }

    public void setClasses(List<Class> classes) {
        this.classes = classes;
    }
}