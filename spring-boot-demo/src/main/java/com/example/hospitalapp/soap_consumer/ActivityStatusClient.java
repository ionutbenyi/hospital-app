package com.example.hospitalapp.soap_consumer;

import com.example.hospitalapp.entities.*;
import com.example.hospitalapp.wsdl.*;
import org.apache.tomcat.jni.Local;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ActivityStatusClient extends WebServiceGatewaySupport {

    private static final Logger log = LoggerFactory.getLogger(ActivityStatusClient.class);

    public List<DailyMedTracker> getActivities(Integer userId){

        List<DailyMedTracker> res = new ArrayList<>();

        ActivityListRequest request = new ActivityListRequest();
        request.setUserId(userId);
        log.info("Requesting user id "+ userId);

        ActivityListResponse response = (ActivityListResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8083/ws/activityStatuses", request,
                        new SoapActionCallback( "http://spring.io/guides/gs-producing-web-service/GetActivityStatusRequest"));
        List<ActivityStatus>activityStatusAl = response.getActivities();
        for(ActivityStatus as: activityStatusAl){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime startTime = LocalDateTime.parse(as.getStartDate(), formatter);
            LocalDateTime endTime = LocalDateTime.parse(as.getEndDate(), formatter);
            DailyMedTracker ps = new DailyMedTracker(as.getName(), startTime,endTime,as.getPatientId());
            ps.setDuration();
            ps.setOk(ps.check());
            res.add(ps);
        }

        return res;
    }

    public List<DailyStatus> getDailyRecords(Integer userId){

        RecordsListRequest request = new RecordsListRequest();
        request.setUserId(userId);
        log.info("Requesting records user id "+ userId);

        RecordsListResponse response = (RecordsListResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8083/ws/activityStatuses", request,
                        new SoapActionCallback( "http://spring.io/guides/gs-producing-web-service/GetActivityStatusRequest"));

        return response.getDailyRecords();
    }
}