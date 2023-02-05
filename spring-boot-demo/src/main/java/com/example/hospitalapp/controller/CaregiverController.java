package com.example.hospitalapp.controller;

import com.example.hospitalapp.dto.CaregiverDTO;
import com.example.hospitalapp.dto.CaregiverViewDTO;
import com.example.hospitalapp.dto.PairDTO;
import com.example.hospitalapp.entities.Caregiver;
import com.example.hospitalapp.entities.Patient;
import com.example.hospitalapp.services.CaregiverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin
@RequestMapping(value = "/caregiver")
public class CaregiverController {

    private final CaregiverService caregiverService;

    @Autowired
    public CaregiverController(CaregiverService caregiverService){
        this.caregiverService=caregiverService;
    }

    @GetMapping(value = "/{id}")
    public Caregiver findById(@PathVariable("id") Integer id){
        return caregiverService.findCaregiverById(id);
    }

    @GetMapping()
    public Set<CaregiverViewDTO> findAll(){
        return caregiverService.findAll();
    }

    @PostMapping()
    public Integer insertCaregiverDTO(@RequestBody CaregiverDTO caregiverDTO){
        return caregiverService.insert(caregiverDTO);
    }

    @PutMapping()
    public Caregiver updateCaregiver(@RequestBody CaregiverDTO cDTO) {
        return caregiverService.update(cDTO);
    }

    @DeleteMapping()
    public void delete(@RequestBody CaregiverDTO cDTO){
        caregiverService.delete(cDTO);
    }

    @PutMapping(value = "/login")
    public Caregiver loginCaregiver(@RequestBody PairDTO p){
        return caregiverService.loginCaregiver(p.getEmail(), p.getPassword());
    }

    @PostMapping(value="/{id}/addpatient")
    public Integer addCaregiverPatient(@PathVariable("id") Integer id, @RequestBody Patient p){
        return this.caregiverService.addPatient(id,p);
    }
}
