package com.example.rpcclient.rpcclient;

import com.example.rpcclient.rpcclient.entities.Medicine;
import com.example.rpcclient.rpcclient.entities.Patient;
import com.example.rpcclient.rpcclient.grpc.AskUsersResponse;
import com.example.rpcclient.rpcclient.grpc.MedicationRequest;
import com.example.rpcclient.rpcclient.grpc.TakeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value="/patient/daily")
public class ProcedureCaller {

    private SendMedicationClient client;

    @Autowired
    public ProcedureCaller(SendMedicationClient client){
        this.client=client;
    }

    @PostMapping(value="/get_list/{uid}")
    public List<Medicine> askForMedicine(@PathVariable("uid") Integer uid){
        return client.askForMedicineList(uid.toString());
    }

    @PostMapping(value="/get_patients")
    public List<Patient> askForPatients(){
        return client.askForAllUsers();
    }

    @PostMapping(value="/confirm/{uid}/{medName}")
    public String confirmTaking(@PathVariable("uid") Integer uid, @PathVariable("medName") String medName){
        return client.confirmTakenMedicine(medName,uid);
    }

    @PostMapping(value="/infirm/{uid}/{medName}")
    public String confirmNotTaking(@PathVariable("uid") Integer uid, @PathVariable("medName") String medName){
        return client.confirmNotTakenMedicine(medName,uid);
    }
}
