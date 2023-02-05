package com.example.hospitalapp.services;

import com.example.hospitalapp.dto.MedicationPlanDTO;
import com.example.hospitalapp.dto.PatientDTO;
import com.example.hospitalapp.entities.MedicationStatus;
import com.example.hospitalapp.repositories.MedicationStatusRepository;
import com.google.protobuf.RepeatedFieldBuilder;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.rpcclient.rpcclient.grpc.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@GRpcService
public class PillboxService
    extends MedicationServiceGrpc.MedicationServiceImplBase {
        private static final Logger LOGGER = LoggerFactory.getLogger(PillboxService.class);

        private final MedicationPlanService medicationPlanService;
        private final MedicationStatusRepository medicationStatusRepository;

        @Autowired
        public PillboxService(MedicationPlanService medicationPlanService, MedicationStatusRepository medicationStatusRepository){
            this.medicationPlanService=medicationPlanService;
            this.medicationStatusRepository = medicationStatusRepository;
        }

        @Override
        public void sendMedication(MedicationRequest request, StreamObserver<MedicationResponse> responseObserver){
            LOGGER.info("server received {}", request);
            String message = "Hello " + request.getMedicationName() + " "
                    + request.getStartHour() + " " + request.getEndHour()  + "!";
            MedicationResponse greeting =
                    MedicationResponse.newBuilder().setTaken(message).build();
            LOGGER.info("server responded {}", greeting);
            responseObserver.onNext(greeting);
            responseObserver.onCompleted();
        }

        @Override
        public void sendTakeRequest(TakeRequest request, StreamObserver<Medications> responseObserver){
            LOGGER.info("server received get medicines request{}", request);
            String message = "Received patient "
                    + request.getPatientId() + "!";

            List<MedicationPlanDTO> mp = this.medicationPlanService.getByUserId(new Integer(request.getPatientId()));
            List<TakeResponse> meds = new ArrayList<>();

            Medications medListResponse = null;
            Medications.Builder mBuilder = Medications.newBuilder();
            if(!mp.isEmpty()){
                for(MedicationPlanDTO mpd: mp){
                    TakeResponse tr = TakeResponse.newBuilder()
                            .setMedicationName(mpd.getMedication().getName())
                            .setStartHour(mpd.getHourStart().toString())
                            .setEndHour(mpd.getHourEnd().toString()).build();
                    mBuilder.addMedications(tr);
                }
            }
            medListResponse = mBuilder.build();

            LOGGER.info("server responded to take {}", medListResponse.toString());
            responseObserver.onNext(medListResponse);
            responseObserver.onCompleted();
        }

        @Override
        public void askForRegisteredPatients(AskUsersRequest request, StreamObserver<Users> responseObserver){

            LOGGER.info("server received ask for users request {}", request);
            List<PatientDTO> patientsRegistered = this.medicationPlanService.getMedplansPatients();
            List<AskUsersResponse> responses = new ArrayList<>();

            Users userListResponse = null;
            Users.Builder uBuilder = Users.newBuilder();

            if(!patientsRegistered.isEmpty()){
                for(PatientDTO p: patientsRegistered){
                    AskUsersResponse au = AskUsersResponse.newBuilder()
                            .setPatientId(p.getId().toString()).setPatientName(p.getName()).build();
                    if(!responses.contains(au)){
                        responses.add(au);
                        uBuilder.addUsers(au);
                    }
                }
            }

            userListResponse = uBuilder.build();
            LOGGER.info("server responded to get users {}", userListResponse.toString());
            responseObserver.onNext(userListResponse);
            responseObserver.onCompleted();
        }

        @Override
        public void confirmTakenMedicine(TakenConfirmationRequest request, StreamObserver<TakenConfirmationResponse> responseObserver){
            LOGGER.info("server received confirm take request {}", request);
            medicationStatusRepository.save(new MedicationStatus(new Integer(request.getPatientId()), true, request.getMedicationName()));
            TakenConfirmationResponse resp =  TakenConfirmationResponse.newBuilder().setMsg("Success").build();
            LOGGER.info("server responded {}", resp.getMsg());
            responseObserver.onNext(resp);
            responseObserver.onCompleted();
        }

        @Override
        public void confirmNotTakenMedicine(NotTakenConfirmationRequest request, StreamObserver<NotTakenConfirmationResponse> responseObserver){
            LOGGER.info("server received confirm take request {}", request);
            medicationStatusRepository.save(new MedicationStatus(new Integer(request.getPatientId()), false, request.getMedicationName()));
            NotTakenConfirmationResponse resp =  NotTakenConfirmationResponse.newBuilder().setMsg("Failure").build();
            LOGGER.info("server responded {}", resp.getMsg());
            responseObserver.onNext(resp);
            responseObserver.onCompleted();
        }
}
