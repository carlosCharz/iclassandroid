package com.wedevol.smartclass.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/** Created by paolorossi on 12/8/16.*/
public class Instructor extends User {
    private List<Course> enrolledCourses = new ArrayList<>();
    private List<Schedule> schedules = new ArrayList<>();

    public Instructor(int id, String firstname, String lastname, String phone, String email, String password,
                      Date birthday, boolean gender, String profilePictureUrl, List<String> placeOptions,
                      String university, double rating, int level, int totalHours) {
        super( id,  firstname,  lastname,  phone,  email,  password,
                birthday,  gender,  profilePictureUrl, placeOptions,
                university,  rating,  level,  totalHours);
    }

    public List<Course> getEnrolledCourses() {
        return enrolledCourses;
    }

    public void setEnrolledCourses(List<Course> enrolledCourses) {
        this.enrolledCourses = enrolledCourses;
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }
}