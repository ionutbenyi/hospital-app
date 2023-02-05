package com.example.hospitalapp.controller;

import com.example.hospitalapp.dto.MedicationPlanDTO;
import com.example.hospitalapp.dto.PatientDTO;
import com.example.hospitalapp.services.MedicationPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/plans")
public class MedicationPlanController {

    private final MedicationPlanService medicationPlanService;

    @Autowired
    public MedicationPlanController(MedicationPlanService medicationPlanService){
        this.medicationPlanService=medicationPlanService;
    }

    @GetMapping(value = "/{id}")
    public MedicationPlanDTO findById(@PathVariable("id") Integer id){
        return medicationPlanService.findMedicationPlanById(id);
    }

    @GetMapping()
    public List<MedicationPlanDTO> findAll(){
        return medicationPlanService.findAll();
    }

    @PostMapping()
    public Integer insertMedicationPlanDTO(@RequestBody MedicationPlanDTO medDTO){
        return medicationPlanService.insert(medDTO);
    }

    @PutMapping()
    public Integer updateMedicationPlan(@RequestBody MedicationPlanDTO medDTO) {
        return medicationPlanService.update(medDTO);
    }

    @DeleteMapping()
    public void delete(@RequestBody MedicationPlanDTO medDTO) {
        medicationPlanService.delete(medDTO);
    }

    @GetMapping(value="/number/{nr}")
    public MedicationPlanDTO getByNumber(@PathVariable("nr") Integer nr){
        return medicationPlanService.getByNumber(nr);
    }

}
