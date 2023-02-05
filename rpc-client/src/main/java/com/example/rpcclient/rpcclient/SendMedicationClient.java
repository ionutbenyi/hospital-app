package com.example.rpcclient.rpcclient;

import com.example.rpcclient.rpcclient.entities.Medicine;
import com.example.rpcclient.rpcclient.entities.Patient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import com.example.rpcclient.rpcclient.grpc.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class SendMedicationClient {
    private static final Logger LOGGER =
            LoggerFactory.getLogger(SendMedicationClient.class);

    private MedicationServiceGrpc.MedicationServiceBlockingStub sendMedicationServiceBlockingStub;

    @PostConstruct
    private void init() {
        ManagedChannel managedChannel = ManagedChannelBuilder
                .forAddress("localhost", 6565).usePlaintext().build();

        sendMedicationServiceBlockingStub =
                MedicationServiceGrpc.newBlockingStub(managedChannel);
    }

    public List<Medicine> askForMedicineList(String patientId){
        List<Medicine> res = new ArrayList<>();

        TakeRequest takeReq = TakeRequest.newBuilder().setPatientId(patientId).build();
        LOGGER.info("client sending take request {}", takeReq);

        List<TakeResponse> resp = sendMedicationServiceBlockingStub.sendTakeRequest(takeReq).getMedicationsList();
        for(TakeResponse r: resp){
            LOGGER.info("client received {}", r);
            Medicine m = new Medicine(r.getMedicationName(),new Integer(r.getStartHour()),new Integer(r.getEndHour()));
            res.add(m);
        }
        return res;
    }

    public List<Patient> askForAllUsers(){
        List<Patient> res = new ArrayList<>();

        AskUsersRequest userReq = AskUsersRequest.newBuilder().setMsg("all").build();
        LOGGER.info("client sending take request {}", userReq);

        List<AskUsersResponse> resp = sendMedicationServiceBlockingStub.askForRegisteredPatients(userReq).getUsersList();
        for(AskUsersResponse r: resp){
            LOGGER.info("client received {}", r);
            Patient p = new Patient(r.getPatientName(), new Integer(r.getPatientId()));
            res.add(p);
        }
        return res;
    }

    public String confirmTakenMedicine(String medicationName, Integer userId){
        TakenConfirmationRequest req = TakenConfirmationRequest.newBuilder()
                .setPatientId(userId.toString()).setMedicationName(medicationName).build();
        LOGGER.info("client confirming - take request {}", req);

        TakenConfirmationResponse resp = sendMedicationServiceBlockingStub.confirmTakenMedicine(req);
        LOGGER.info("client received after confirm {}", resp.getMsg());
        return resp.getMsg();
    }

    public String confirmNotTakenMedicine(String medicationName, Integer userId){
        NotTakenConfirmationRequest req = NotTakenConfirmationRequest.newBuilder()
                .setPatientId(userId.toString()).setMedicationName(medicationName).build();
        LOGGER.info("client confirm - didn't take request {}", req);

        NotTakenConfirmationResponse resp = sendMedicationServiceBlockingStub.confirmNotTakenMedicine(req);
        LOGGER.info("client received after confirm {}", resp.getMsg());
        return resp.getMsg();
    }






}
