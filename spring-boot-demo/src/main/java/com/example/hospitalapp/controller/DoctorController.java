package com.example.hospitalapp.controller;

import com.example.hospitalapp.dto.DoctorDTO;
import com.example.hospitalapp.dto.DoctorViewDTO;
import com.example.hospitalapp.dto.PairDTO;
import com.example.hospitalapp.entities.DailyMedTracker;
import com.example.hospitalapp.entities.Doctor;
import com.example.hospitalapp.entities.PatientStatus;
import com.example.hospitalapp.entities.Recommendation;
import com.example.hospitalapp.services.DoctorService;
import com.example.hospitalapp.soap_consumer.ActivityStatusClient;
import com.example.hospitalapp.wsdl.ActivityStatus;
import com.example.hospitalapp.wsdl.DailyStatus;
import org.jboss.logging.annotations.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping(value = "/doctor")
public class DoctorController {

    private final DoctorService doctorService;
    private final ActivityStatusClient activityStatusClient;

    @Autowired
    public DoctorController(DoctorService doctorService, ActivityStatusClient ac){
        this.doctorService=doctorService;
        this.activityStatusClient=ac;
    }

    @GetMapping(value = "/{id}")
    public DoctorViewDTO findById(@PathVariable("id") Integer id){
        return doctorService.findDoctorById(id);
    }

    @GetMapping()
    public List<DoctorViewDTO> findAll(){
        return doctorService.findAll();
    }

    @PostMapping()
    public Integer insertDoctorDTO(@RequestBody DoctorDTO doctorDTO){
        return doctorService.insert(doctorDTO);
    }

    @PutMapping()
    public Integer updateDoctor(@RequestBody DoctorDTO docDTO) {
        return doctorService.update(docDTO);
    }

    @DeleteMapping()
    public void delete(@RequestBody DoctorDTO docDTO){
        doctorService.delete(docDTO);
    }

    @PutMapping(value = "/login")
    public Doctor loginDoctor(@RequestBody PairDTO p){
        return doctorService.loginDoctor(p.getEmail(), p.getPassword());
    }

    @GetMapping (value = "/activityStatuses/{id}")
    public List<DailyMedTracker> getActivities(@PathVariable("id") Integer id){
        return activityStatusClient.getActivities(id);
    }

    @GetMapping (value = "/dailyRecords/{id}")
    public List<DailyStatus> getRecords(@PathVariable("id") Integer id){
        return activityStatusClient.getDailyRecords(id);
    }

    @PostMapping (value = "/recommend")
    public Recommendation addRecommendation(@RequestBody Recommendation r){
        return doctorService.addRecommendation(r);
    }
}
