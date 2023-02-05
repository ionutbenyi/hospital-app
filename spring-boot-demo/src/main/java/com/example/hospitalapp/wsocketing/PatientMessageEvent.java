package com.example.hospitalapp.wsocketing;

import org.springframework.context.ApplicationEvent;

public class PatientMessageEvent extends ApplicationEvent {

    private PatientMessage pm;

    public PatientMessageEvent(Object source, PatientMessage pm) {
        super(source);
        this.pm = pm;
    }

    public PatientMessage getPm() {
        return pm;
    }

    public void setPm(PatientMessage pm) {
        this.pm = pm;
    }
}
