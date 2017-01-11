package com.wedevol.smartclass.models;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

/** Created by paolorossi on 12/8/16.*/
public class Lesson {
    private int id = -1;
    private int startTime = -1;
    private int endTime = -1;
    private String classDate = "";
    private String status = "";
    private int courseId = -1;
    private String courseName = "";
    private String userType = "";
    private int senderId = -1;
    private String senderFirstName = "";
    private String senderLastName = "";
    private int receptorId = -1;
    private String phone = "";
    private int price = -1;
    private String currency = "";

    public Lesson(){

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

    public String getClassDate() {
        return classDate;
        //return new Date(Date.parse(classDate));
    }

    public void setClassDate(String classDate) {
        this.classDate = classDate;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public String getSenderFirstName() {
        return senderFirstName;
    }

    public void setSenderFirstName(String senderFirstName) {
        this.senderFirstName = senderFirstName;
    }

    public String getSenderLastName() {
        return senderLastName;
    }

    public void setSenderLastName(String senderLastName) {
        this.senderLastName = senderLastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getReceptorId() {
        return receptorId;
    }

    public void setReceptorId(int receptorId) {
        this.receptorId = receptorId;
    }

    private static class Builder {
        private int mId;
        private int mStartTime;
        private int mEndTime;
        private String mClassDate;
        private String mStatus;
        private int mCourseId;
        private String mCourseName;
        private String mUserType;
        private int mObjectiveId;
        private String mObjectveFirstName;
        private String mObjectveLastName;
        private String mPhone;
        private int mPrice;
        private String mCurrency;

        Builder(int id) {
            mId = id;
        }

        Lesson.Builder startTime(int startTime) {
            mStartTime = startTime;
            return this;
        }

        Lesson.Builder endTime(int endTime) {
            mEndTime = endTime;
            return this;
        }

        Lesson.Builder classDate(String classDate) {
            mClassDate = classDate;
            return this;
        }

        Lesson.Builder status(String status) {
            mStatus = status;
            return this;
        }

        Lesson.Builder courseId(int courseId) {
            mCourseId = courseId;
            return this;
        }

        Lesson.Builder courseName(String courseName) {
            mCourseName = courseName;
            return this;
        }

        Lesson.Builder userType(String userType) {
            mUserType = userType;
            return this;
        }

        Lesson.Builder objectiveId(int objectiveId) {
            mObjectiveId = objectiveId;
            return this;
        }

        Lesson.Builder objectveFirstName(String objectveFirstName) {
            mObjectveFirstName = objectveFirstName;
            return this;
        }

        Lesson.Builder objectveLastName(String objectveLastName) {
            mObjectveLastName = objectveLastName;
            return this;
        }

        Lesson.Builder phone(String phone) {
            mPhone = phone;
            return this;
        }

        Lesson.Builder price(int price) {
            mPrice = price;
            return this;
        }

        Lesson.Builder currency(String currency) {
            mCurrency = currency;
            return this;
        }

        Lesson build() {
            Lesson lesson = new Lesson();
            lesson.setId(mId);
            lesson.setStartTime(mStartTime);
            lesson.setEndTime(mEndTime);
            lesson.setClassDate(mClassDate);
            lesson.setStatus(mStatus);
            lesson.setCourseId(mCourseId);
            lesson.setCourseName(mCourseName);
            lesson.setUserType(mUserType);
            lesson.setSenderId(mObjectiveId);
            lesson.setSenderFirstName(mObjectveFirstName);
            lesson.setSenderLastName(mObjectveLastName);
            lesson.setPhone(mPhone);
            lesson.setPrice(mPrice);
            lesson.setCurrency(mCurrency);

            return lesson;
        }
    }

    public static Lesson parseLesson(JsonObject responseObject) {
        Lesson.Builder lessonBuilder;
        //"classId":1
        lessonBuilder = new Lesson.Builder(responseObject.get("classId").getAsInt());
        //"startTime":8,
        if (responseObject.has("startTime") && !responseObject.get("startTime").isJsonNull()) {
            lessonBuilder.startTime(responseObject.get("startTime").getAsInt());
        }
        //"endTime":10,
        if (responseObject.has("endTime") && !responseObject.get("endTime").isJsonNull()) {
            lessonBuilder.endTime(responseObject.get("endTime").getAsInt());
        }
        //"classDate":"09/01/2017",
        if (responseObject.has("classDate") && !responseObject.get("classDate").isJsonNull()) {
            lessonBuilder.classDate(responseObject.get("classDate").getAsString());
        }
        //"classStatus":"requested",
        if (responseObject.has("classStatus") && !responseObject.get("classStatus").isJsonNull()) {
            lessonBuilder.status(responseObject.get("classStatus").getAsString());
        }
        //"courseId":1,
        if (responseObject.has("courseId") && !responseObject.get("courseId").isJsonNull()) {
            lessonBuilder.courseId(responseObject.get("courseId").getAsInt());
        }
        //"courseName":"Calculo 1",
        if (responseObject.has("courseName") && !responseObject.get("courseName").isJsonNull()) {
            lessonBuilder.courseName(responseObject.get("courseName").getAsString());
        }
        //"userType":"student",
        if (responseObject.has("userType") && !responseObject.get("userType").isJsonNull()) {
            lessonBuilder.userType(responseObject.get("userType").getAsString());
        }
        //"userId":1,
        if (responseObject.has("userId") && !responseObject.get("userId").isJsonNull()) {
            lessonBuilder.objectiveId(responseObject.get("userId").getAsInt());
        }
        //"firstName":"Luis",
        if (responseObject.has("firstName") && !responseObject.get("firstName").isJsonNull()) {
            lessonBuilder.objectveFirstName(responseObject.get("firstName").getAsString());
        }
        //"lastName":"Becerra",
        if (responseObject.has("lastName") && !responseObject.get("lastName").isJsonNull()) {
            lessonBuilder.objectveLastName(responseObject.get("lastName").getAsString());
        }
        //"phone":"78013455",
        if (responseObject.has("phone") && !responseObject.get("phone").isJsonNull()) {
            lessonBuilder.phone(responseObject.get("phone").getAsString());
        }
        //"price":20,
        if (responseObject.has("price") && !responseObject.get("price").isJsonNull()) {
            lessonBuilder.price(responseObject.get("price").getAsInt());
        }
        //"currency":"S/."
        if (responseObject.has("currency") && !responseObject.get("currency").isJsonNull()) {
            lessonBuilder.currency(responseObject.get("currency").getAsString());
        }
        return lessonBuilder.build();
    }

    public JsonObject toJson() {
        JsonObject jsonObject = new JsonObject();
        //"classDate": 0,
        jsonObject.addProperty("classDate", this.getClassDate());
        //"courseId": 0,
        jsonObject.addProperty("courseId", this.getCourseId());
        //"endTime": 0,
        jsonObject.addProperty("endTime", this.getEndTime());
        // "instructorId": 0,
        jsonObject.addProperty("instructorId", this.getReceptorId());
        //"startTime": 0,
        jsonObject.addProperty("startTime", this.getStartTime());
        //"status": "string",
        jsonObject.addProperty("status", this.getStatus());
        //"studentId": 0,
        jsonObject.addProperty("studentId", this.getSenderId());
        //"weekDay": "string"
        //jsonObject.addProperty("weekDay", this.getW());

        /* Missing { "requestedAt": "2017-01-09T21:22:00.933Z" }*/
        return jsonObject;
    }
}