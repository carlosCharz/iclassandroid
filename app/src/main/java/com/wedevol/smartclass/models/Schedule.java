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
    private boolean available = true;

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

    public void setInstructorId(int instructorId) {
        this.instructorId = instructorId;
    }

    public int getInstructorId() {
        return instructorId;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
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
        jsonObject.addProperty("available", this.isAvailable());
        return jsonObject;
    }

    private static class Builder {
        private int mId;
        private int mInstructorId;
        private int mStartTime;
        private int mEndTime;
        private String mClassDate;
        private String mWeekDay;
        private boolean mAvailable;

        Builder(int id) {
            mId = id;
        }

        Schedule.Builder instructorId(int instructorId) {
            mInstructorId = instructorId;
            return this;
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

        Schedule.Builder available(boolean available) {
            mAvailable = available;
            return this;
        }

        Schedule build() {
            Schedule schedule = new Schedule();
            schedule.setId(mId);
            schedule.setInstructorId(mInstructorId);
            schedule.setStartTime(mStartTime);
            schedule.setEndTime(mEndTime);
            schedule.setClassDate(mClassDate);
            schedule.setWeekDay(mWeekDay);
            schedule.setAvailable(mAvailable);
            return schedule;
        }
    }

    public static Schedule parseSchedule(JsonObject responseObject) {
        Schedule.Builder scheduleBuilder;
        //"id":2,
        scheduleBuilder = new Schedule.Builder(responseObject.get("id").getAsInt());

        if (responseObject.has("instructorId") && !responseObject.get("instructorId").isJsonNull()) {
            scheduleBuilder.instructorId(responseObject.get("instructorId").getAsInt());
        }

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
        //"available":true,
        if (responseObject.has("available") && !responseObject.get("available").isJsonNull()) {
            scheduleBuilder.available(responseObject.get("available").getAsBoolean());
        }

        return scheduleBuilder.build();
    }
}