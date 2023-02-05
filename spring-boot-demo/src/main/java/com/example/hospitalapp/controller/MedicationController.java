package com.example.hospitalapp.controller;

import com.example.hospitalapp.dto.MedicationDTO;
import com.example.hospitalapp.services.MedicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping(value = "/medication")
public class MedicationController {

    private final MedicationService medicationService;

    @Autowired
    public MedicationController(MedicationService medicationService){
        this.medicationService=medicationService;
    }

    @GetMapping(value = "/{id}")
    public MedicationDTO findById(@PathVariable("id") Integer id){
        return medicationService.findMedicationById(id);
    }

    @GetMapping()
    public List<MedicationDTO> findAll(){
        return medicationService.findAll();
    }

    @PostMapping()
    public Integer insertMedicationDTO(@RequestBody MedicationDTO medDTO){
        return medicationService.insert(medDTO);
    }

    @PutMapping()
    public Integer updateMedication(@RequestBody Map<String, String> med) {
        return medicationService.update(med);
    }

    @DeleteMapping()
    public Integer delete(@RequestBody MedicationDTO med){
        return medicationService.delete(med);
    }
}