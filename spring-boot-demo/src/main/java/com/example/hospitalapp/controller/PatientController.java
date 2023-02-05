package com.example.hospitalapp.controller;

import com.example.hospitalapp.dto.PairDTO;
import com.example.hospitalapp.dto.PatientDTO;
import com.example.hospitalapp.dto.PatientViewDTO;
import com.example.hospitalapp.entities.Patient;
import com.example.hospitalapp.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin
@RequestMapping(value="/patient")
public class PatientController {
    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService){
        this.patientService=patientService;
    }

    @GetMapping(value = "/{id}")
    public PatientDTO findById(@PathVariable("id") Integer id){
        return patientService.findPatientById(id);
    }

    @GetMapping
    public Set<PatientDTO> findAll(){
        return patientService.findAll();
    }

    @PostMapping
    public Integer insertPatientDTO(@RequestBody PatientDTO pDTO){
        return patientService.insert(pDTO);
    }

    @PutMapping
    public Integer updatePatient(@RequestBody PatientDTO pDTO){
        return patientService.update(pDTO);
    }

    @DeleteMapping
    public Integer deletePatientDTO(@RequestBody PatientDTO pDTO){
        return patientService.delete(pDTO);
    }

    @PutMapping(value = "/login")
    public Patient loginPatient(@RequestBody PairDTO p){
        return patientService.loginPatient(p.getEmail(), p.getPassword());
    }

    @PutMapping(value = "/find")
    public Patient findPatient(@RequestBody PairDTO p){
        return patientService.loginPatient(p.getEmail(), p.getPassword());
    }
}
