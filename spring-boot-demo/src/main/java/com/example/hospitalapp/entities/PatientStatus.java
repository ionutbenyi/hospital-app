package com.example.hospitalapp.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.GenerationType.TABLE;

@Entity
@Table(name = "patientstatus")
public class PatientStatus {

    @Id
    @GeneratedValue(strategy = TABLE)
    @Column(name = "status_id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "activity", length = 100)
    private String activity;

    @Column(name = "startTime")
    private LocalDateTime startTime;

    @Column(name = "endTime")
    private LocalDateTime endTime;

    @Column(name = "patientId", nullable = false)
    private Integer patientId;

    public PatientStatus(String activity, LocalDateTime startTime, LocalDateTime endTime, Integer patientId) {
        this.activity = activity;
        this.startTime = startTime;
        this.endTime = endTime;
        this.patientId = patientId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }
}
