package com.wedevol.smartclass.models;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.Date;

/** Created by paolorossi on 12/7/16.*/
public class User {
    private int id = -1;
    private String firstname = "";
    private String lastname = "";
    private String phone = "";
    private String email = "";
    private String password = "";
    private Date birthday = new Date();
    private boolean gender = false;
    private String profilePictureUrl = "";
    private String placeOptions = "";
    private String universityName = "";
    private double rating = -1;
    private int level = -1;
    private int totalHours = -1;
    private String fcmToken = "";
    private int universityId = -1;
    private int facultyId = -1;
    private String facultyName = "";

    User(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * This method is for getting a reduced version of the complete name. Useful for showing partial views of such items
     * @param length the length you wish to show
     * */
    public String getReducedName(int length){
        if(length < getFirstname().length()){
            String reducedName = getFirstname().substring(0, length-1);
            reducedName += ".";
            return reducedName;
        }else{
            return getFirstname();
        }
    }

    /**
     * This method is for getting a full version of the complete name
     * */
    public String getFullName() {
        return getFirstname() + " "+ getLastname();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }

    public String getPlaceOptions() {
        return placeOptions;
    }

    public void setPlaceOptions(String placeOptions) {
        this.placeOptions = placeOptions;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(int totalHours) {
        this.totalHours = totalHours;
    }

    public String getFcmToken() {
        return fcmToken;
    }

    public void setFcmToken(String fcmToken) {
        this.fcmToken = fcmToken;
    }

    public String toJsonString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public int getUniversityId() {
        return universityId;
    }

    public void setUniversityId(int universityId) {
        this.universityId = universityId;
    }

    public int getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(int facultyId) {
        this.facultyId = facultyId;
    }

    public JsonObject toJsonUpdate() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("phone", getPhone());
        jsonObject.addProperty("universityId", getUniversityId());
        jsonObject.addProperty("facultyId", getFacultyId());
        jsonObject.addProperty("fcmToken", getFcmToken());
        return jsonObject;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }
}