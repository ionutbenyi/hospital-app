package com.example.hospitalapp.wsocketing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class PatientMessageController {

    private static final Logger log = LoggerFactory.getLogger(PatientMessageEvent.class);
    @EventListener(PatientMessageEvent.class)
    public void gotStatus(PatientMessageEvent pe){
        log.info("got the alert: "+ pe.getPm().getMessage());
    }

}
