package com.example.hospitalapp.entities;

import java.time.Duration;
import java.time.LocalDateTime;

public class DailyMedTracker {

    private String activity;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Integer patientId;

    private boolean ok;
    private int duration;

    public DailyMedTracker(String activity1, LocalDateTime startTime1, LocalDateTime endTime1, Integer patientId1) {

        this.activity = activity1.replace("\t","");
        this.startTime = startTime1;
        this.endTime = endTime1;
        this.patientId = patientId1;
        this.duration = -1;
    }

    public String getActivity() {
        return activity;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public boolean isOk() {
        return ok;
    }

    public int getDuration() {
        return duration;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public void setDuration() {
        Duration dif = Duration.between(startTime,endTime);
        this.duration= (int) Math.abs(dif.toHours());
    }

    public boolean check(){
        if(this.activity.equals("Sleeping")){
            if(this.duration>12){
                return false;
            }
        }
        if(this.activity.equals("Leaving")){
            if(this.duration>12){
                return false;
            }
        }
        if(this.activity.equals("Showering") || activity.equals("Toileting") || activity.equals("Grooming")){
            if(this.duration>1){
                return false;
            }
        }
        return true;
    }
}
