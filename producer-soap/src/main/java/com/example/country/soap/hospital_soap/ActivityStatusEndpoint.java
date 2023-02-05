package com.example.country.soap.hospital_soap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import patientstatus.gen.*;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Set;

@Endpoint
public class ActivityStatusEndpoint {

    private static final Logger log = LoggerFactory.getLogger(ActivityStatusEndpoint.class);
    private static final String NAMESPACE_URI = "http://www.patientStatus/gen";

    private PatientStatusRepository activityStatusRepository;
    private MedicationStatusRepository medicationStatusRepository;

    @Autowired
    public ActivityStatusEndpoint(PatientStatusRepository activityStatusRepository, MedicationStatusRepository medicationStatusRepository) {
        this.activityStatusRepository = activityStatusRepository;
        this.medicationStatusRepository=medicationStatusRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "activityListRequest")
    @ResponsePayload
    public ActivityListResponse getActivityStatus(@RequestPayload ActivityListRequest request) throws DatatypeConfigurationException {
        ActivityListResponse response = new ActivityListResponse();
        Set<PatientStatus> repoSet = this.activityStatusRepository.findByPatientId(request.getUserId());
        ArrayList<ActivityStatus> activityAl = new ArrayList<>();
        for(PatientStatus ps:repoSet){
            ActivityStatus as = new ActivityStatus();
            as.setId(ps.getId());
            as.setName(ps.getActivity());

            String startS = ps.getStartTime().toString();
            startS = startS.replace("T"," ");
            if(startS.length() < 19){
                startS = startS + ":00";
            }

            String endS = ps.getEndTime().toString();
            endS = endS.replace("T"," ");
            if(endS.length() < 19){
                endS = endS + ":00";
            }

            as.setStartDate(startS);
            as.setEndDate(endS);
            as.setPatientId(ps.getPatientId());
            activityAl.add(as);
        }
        response.getActivities().addAll(activityAl);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "recordsListRequest")
    @ResponsePayload
    public RecordsListResponse getMedicalStatus(@RequestPayload RecordsListRequest request) {
        RecordsListResponse response = new RecordsListResponse();
        List<MedicationStatus> repoSet = medicationStatusRepository.findByUserId(request.getUserId());
        ArrayList<DailyStatus> res = new ArrayList<>();
        for(MedicationStatus md: repoSet){
            DailyStatus ds = new DailyStatus();
            ds.setId(md.getId());
            ds.setMedicationName(md.getMedicationName());
            ds.setUserId(md.getUserId());
            ds.setTaken(md.getTaken());
            res.add(ds);
        }
        response.getDailyRecords().addAll(res);
        return response;
    }
}