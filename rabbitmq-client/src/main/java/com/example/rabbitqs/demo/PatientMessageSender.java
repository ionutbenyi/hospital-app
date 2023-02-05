package com.example.rabbitqs.demo;

import input.process.MonitoredData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Stream;

@Service
public class PatientMessageSender {
    private static final Logger log = LoggerFactory.getLogger(PatientMessageSender.class);
    private final RabbitTemplate rabbitTemplate;
    public static final Integer patientId = 3;

    private ArrayList<MonitoredData> patientDataList;
    private int count;
    public PatientMessageSender(final RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
    }

    public static MonitoredData dateProc(String s) {
        String activity;

        String st,f;
        Date startDate, endDate;
        startDate=null;
        endDate=null;
        SimpleDateFormat f2= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        st=s.substring(0,19);
        try {
            startDate=f2.parse(st);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        f=s.substring(21,40);
        try {
            endDate=f2.parse(f);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        activity=s.substring(42);

        MonitoredData rezm=new MonitoredData(patientId,startDate,endDate,activity);

        return rezm;
    }

    public void initDataArray(){
        this.patientDataList = new ArrayList<MonitoredData>();
        this.count=0;
        Map<String, Long> mapAct = new HashMap<String, Long>();

        //read from file
        try (Stream<String> stream = Files.lines(Paths.get("Activities.txt"))) {
            stream.forEach((p) -> this.patientDataList.add(dateProc(p)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Scheduled(fixedDelay = 1000L)
    public void sendMessage(){

        if(this.patientDataList == null){
            this.initDataArray();
        }else if (this.count < this.patientDataList.size()){
            log.info("Sending message ... ");
            rabbitTemplate.convertAndSend("tips_tx","tips",this.patientDataList.get(this.count));
            count++;
        }

    }
}
