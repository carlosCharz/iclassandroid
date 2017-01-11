package com.wedevol.smartclass.models;

import com.google.gson.JsonObject;

/** Created by paolorossi on 12/8/16.*/
public class Schedule {
    private int id = -1;
    private String weekDay = "";
    private int startTime = -1;
    private int endTime = -1;
    private String classDate = "";
    private int instructorId = -1;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(String weekDay) {
        this.weekDay = weekDay;
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

    public String getClassDate() {
        return classDate;
    }

    public void setClassDate(String classDate) {
        this.classDate = classDate;
    }

    /**
     * Prints the schedule in a presentation format.
    * */
    public String printSchedule() {
        return startTime + " - " + endTime;
    }

    public JsonObject toJson() {
        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("instructorId", this.getInstructorId());
        jsonObject.addProperty("weekDay", this.getWeekDay());
        jsonObject.addProperty("classDate", "10/1/2022");
        jsonObject.addProperty("startTime", this.getStartTime());
        jsonObject.addProperty("endTime", this.getEndTime());
        jsonObject.addProperty("available", true);

        return jsonObject;
    }

    public void setInstructorId(int instructorId) {
        this.instructorId = instructorId;
    }

    public int getInstructorId() {
        return instructorId;
    }

    private static class Builder {
        private int mId;
        private int mStartTime;
        private int mEndTime;
        private String mClassDate;
        private String mWeekDay;

        Builder(int id) {
            mId = id;
        }

        Schedule.Builder startTime(int startTime) {
            mStartTime = startTime;
            return this;
        }

        Schedule.Builder endTime(int endTime) {
            mEndTime = endTime;
            return this;
        }

        Schedule.Builder weekDay(String weekDay) {
            mWeekDay = weekDay;
            return this;
        }

        Schedule.Builder classDate(String classDate) {
            mClassDate = classDate;
            return this;
        }

        Schedule build() {
            Schedule schedule = new Schedule();
            schedule.setId(mId);
            schedule.setStartTime(mStartTime);
            schedule.setEndTime(mEndTime);
            schedule.setClassDate(mClassDate);
            schedule.setWeekDay(mWeekDay);
            return schedule;
        }
    }

    public static Schedule parseSchedule(JsonObject responseObject) {
        Schedule.Builder scheduleBuilder;
        //"id":2,
        scheduleBuilder = new Schedule.Builder(responseObject.get("id").getAsInt());
        //"weekDay":"mon",
        if (responseObject.has("weekDay") && !responseObject.get("weekDay").isJsonNull()) {
            scheduleBuilder.weekDay(responseObject.get("weekDay").getAsString());
        }
        //"classDate":"09/01/2017",
        if (responseObject.has("classDate") && !responseObject.get("classDate").isJsonNull()) {
            scheduleBuilder.classDate(responseObject.get("classDate").getAsString());
        }
        //"startTime":15,
        if (responseObject.has("startTime") && !responseObject.get("startTime").isJsonNull()) {
            scheduleBuilder.startTime(responseObject.get("startTime").getAsInt());
        }
        //"endTime":20,
        if (responseObject.has("endTime") && !responseObject.get("endTime").isJsonNull()) {
            scheduleBuilder.endTime(responseObject.get("endTime").getAsInt());
        }
        return scheduleBuilder.build();
    }
}