package com.example.hospitalapp.services;

import com.example.hospitalapp.entities.PatientStatus;
import com.example.hospitalapp.repositories.PatientStatusRepository;
import com.example.hospitalapp.wsocketing.PatientMessage;
import com.example.hospitalapp.wsocketing.PatientMessageEvent;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Service
public class PatientStatusService {
    private final PatientStatusRepository patientStatusRepository;

    @Autowired
    private SimpMessagingTemplate template;

    @Autowired
    public PatientStatusService(PatientStatusRepository patientSRepository){
        this.patientStatusRepository=patientSRepository;
    }

    public Integer insert(PatientStatus ps){
        return patientStatusRepository.save(ps)
                .getId();
    }

    public void sendStatus(PatientMessage pm) {
        this.template.convertAndSend("/topic/app", pm);
    }

    //message listener side
    private static final Logger log = LoggerFactory.getLogger(PatientStatusService.class);
    @RabbitListener(queues = "generic_queue")
    public void receiveMessageG(final Message message){

        //the fun starts here
        String charsertName = "UTF-8";
        String msgDecoded = "default";
        try{
            msgDecoded = new String(message.getBody(), charsertName);
        }catch(Exception ex) {
            ex.printStackTrace();
        }

        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode actualNode = mapper.readTree(msgDecoded);
            String activity = actualNode.get("activity").textValue();
            Integer patientId = actualNode.get("id").asInt();
            log.info("activity "+ activity+", patient:"+patientId);

            log.info("start: "+ actualNode.get("startTime"));
            log.info("end: "+ actualNode.get("endTime"));

            DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime startTime = Instant.ofEpochMilli(actualNode.get("startTime").asLong()).atZone(ZoneId.systemDefault()).toLocalDateTime();
            LocalDateTime endTime = Instant.ofEpochMilli(actualNode.get("endTime").asLong()).atZone(ZoneId.systemDefault()).toLocalDateTime();
            log.info("start date: "+startTime.toString()+", endDate: "+endTime.toString());

            Duration duration = Duration.between(startTime,endTime);
            long differenceH = Math.abs(duration.toHours());
            log.info("duration (hours): "+ differenceH);

            this.insert(new PatientStatus(activity,startTime,endTime,patientId));

            if(activity.contains("Sleeping")){
                if(differenceH>12){
                    log.info("ALERT - sleep too much");
                    this.sendStatus(new PatientMessage("Sleeping too much", patientId));
                }
            }
            if(activity.contains("Leaving")){
                if(differenceH>12){
                    log.info("ALERT - out too much");
                    this.sendStatus(new PatientMessage("Out too much", patientId));
                }
            }
            if(activity.contains("Showering") || activity.contains("Toileting") || activity.contains("Grooming")){
                if(differenceH>1){
                    log.info("ALERT - bathroom too much");
                    this.sendStatus(new PatientMessage("Bathroom too much", patientId));
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
