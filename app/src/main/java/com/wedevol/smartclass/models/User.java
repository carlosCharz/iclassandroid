package com.wedevol.smartclass.models;

import com.google.gson.JsonObject;

/** Created by paolorossi on 12/7/16.*/
public class User {
    private int id;
    private String name;
    private String lastname;
    private String description;
    private String photoUrl;

    public User(){

    }

    public User(int id, String name, String description, String photoUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.photoUrl = photoUrl;
    }

    public User(int id, String name, String lastname, String description, String photoUrl) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.description = description;
        this.photoUrl = photoUrl;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getName(){
        return name;
    }

    public String getReducedName(int length){
        if(length < name.length()){
            String reducedName = name.substring(0, length-1);
            reducedName += ".";
            return reducedName;
        }else{
            return name;
        }
    }

    public String getFullName() {
        return name + " "+ lastname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public static User parseUser(JsonObject json) {
        return null;
    }

}