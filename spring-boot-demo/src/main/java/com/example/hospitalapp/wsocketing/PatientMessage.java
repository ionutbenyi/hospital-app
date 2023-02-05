package com.example.hospitalapp.wsocketing;

public class PatientMessage {

    private String message;
    private Integer patientId;

    public PatientMessage(String message, Integer patientId) {

        this.message = message;
        this.patientId = patientId;
    }

    public PatientMessage() {

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }
}
