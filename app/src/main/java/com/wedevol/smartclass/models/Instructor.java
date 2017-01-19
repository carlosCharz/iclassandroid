package com.wedevol.smartclass.models;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/** Created by paolorossi on 12/8/16.*/
public class Instructor extends User {
    private double hourlyRate = -0.1;

    public Instructor(){

    }

    public Instructor(int id, String firstname, String lastname, String phone, String email, String password,
                      Date birthday, boolean gender, String profilePictureUrl, String placeOptions,
                      String university, double rating, int level, int totalHours) {
        super( id,  firstname,  lastname,  phone,  email,  password,
                birthday,  gender,  profilePictureUrl, placeOptions,
                university,  rating,  level,  totalHours);
    }

    public String getName() {
        return getFirstname();
    }

    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    //TODO: json builder
    private static class Builder {
        private int mId;
        private String mFirstName;
        private String mLastName;
        private String mPhone;
        private String mEmail;
        private String mPassword;
        private String mPlaceOptions;
        private double mRating;
        private int mLevel;
        private int mTotalHours;

        Builder(int id) {
            mId = id;
        }

        Builder firstName(String firstName) {
            mFirstName = firstName;
            return this;
        }

        Builder lastName(String lastName) {
            mLastName = lastName;
            return this;
        }

        Builder phone(String firstName) {
            mPhone = firstName;
            return this;
        }

        Builder email(String email) {
            mEmail = email;
            return this;
        }

        Builder password(String password) {
            mPassword = password;
            return this;
        }

        Builder placeOptions(String placeOptions) {
            mPlaceOptions = placeOptions;
            return this;
        }

        Builder rating(double rating) {
            mRating = rating;
            return this;
        }

        Builder level(int level) {
            mLevel = level;
            return this;
        }

        Builder totalHours(int totalHours) {
            mTotalHours = totalHours;
            return this;
        }

        public Instructor build() {
            Instructor instructor = new Instructor();
            instructor.setId(mId);
            instructor.setFirstname(mFirstName);
            instructor.setLastname(mLastName);
            instructor.setPhone(mPhone);
            instructor.setEmail(mEmail);
            instructor.setPassword(mPassword);
            instructor.setPlaceOptions(mPlaceOptions);
            instructor.setRating(mRating);
            instructor.setLevel(mLevel);
            instructor.setTotalHours(mTotalHours);

            return instructor;
        }
    }

    public static Instructor parseInstructor(JsonObject responseObject) {
        Builder instructorBuilder;
        instructorBuilder = new Builder(responseObject.get("id").getAsInt());
        if (responseObject.has("firstName") && !responseObject.get("firstName").isJsonNull()) {
            instructorBuilder.firstName(responseObject.get("firstName").getAsString());
        }

        if (responseObject.has("lastName") && !responseObject.get("lastName").isJsonNull()) {
            instructorBuilder.lastName(responseObject.get("lastName").getAsString());
        }

        if (responseObject.has("phone") && !responseObject.get("phone").isJsonNull()) {
            instructorBuilder.phone(responseObject.get("phone").getAsString());
        }

        if (responseObject.has("email") && !responseObject.get("email").isJsonNull()) {
            instructorBuilder.email(responseObject.get("email").getAsString());
        }

        if (responseObject.has("password") && !responseObject.get("password").isJsonNull()) {
            instructorBuilder.password(responseObject.get("password").getAsString());
        }

        if (responseObject.has("placeOptions") && !responseObject.get("placeOptions").isJsonNull()) {
            instructorBuilder.placeOptions(responseObject.get("placeOptions").getAsJsonArray().toString());
        }

        if (responseObject.has("rating") && !responseObject.get("rating").isJsonNull()) {
            instructorBuilder.rating(responseObject.get("rating").getAsDouble());
        }

        if (responseObject.has("level") && !responseObject.get("level").isJsonNull()) {
            instructorBuilder.level(responseObject.get("level").getAsInt());
        }

        if (responseObject.has("totalHours") && !responseObject.get("totalHours").isJsonNull()) {
            instructorBuilder.totalHours(responseObject.get("totalHours").getAsInt());
        }

        return instructorBuilder.build();
    }

    public JsonObject toJson(){
        JsonObject jsonObject = new JsonObject();
        //"firstName": "string",
        jsonObject.addProperty("firstName", this.getFirstname());
        //"lastName": "string",
        jsonObject.addProperty("lastName", this.getLastname());
        //"phone": "string",
        jsonObject.addProperty("phone", this.getPhone());
        //"email": "string",
        jsonObject.addProperty("email", this.getEmail());
        //"password": "string",
        jsonObject.addProperty("password", this.getPassword());
        //placeOptions": ["string"],
        List<String> list = new ArrayList<>();
        list.add("university");
        list.add("house");
        Gson gson = new Gson();
        JsonElement element = gson.toJsonTree(list, new TypeToken<ArrayList<String>>() {}.getType());
        jsonObject.add("placeOptions", element);

        //"birthday": "2017-01-09T21:22:01.004Z", //DateFormat df = new SimpleDateFormat("dd/MM/yyyy"); //String birth = df.format(this.getBirthday()); //jsonObject.addProperty("birthday", birth);
        //"gender": "string", //jsonObject.addProperty("gender", "M");
        //"level": 0, //jsonObject.addProperty("level", 0);
        //"profilePictureUrl": "string", //jsonObject.addProperty("profilePictureUrl", "None");
        //"rating": 0, //jsonObject.addProperty("rating", 0);
        //"totalHours": 0, //jsonObject.addProperty("totalHours", 0);
        //"university": "string" //jsonObject.addProperty("university", "None");

        return jsonObject;
    }
}