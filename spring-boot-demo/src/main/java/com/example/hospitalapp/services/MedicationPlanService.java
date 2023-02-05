package com.example.hospitalapp.services;

import com.example.hospitalapp.dto.MedicationPlanDTO;
import com.example.hospitalapp.dto.PatientDTO;
import com.example.hospitalapp.dto.builders.MedicationPlanBuilder;
import com.example.hospitalapp.dto.builders.PatientBuilder;
import com.example.hospitalapp.entities.MedicationPlan;
import com.example.hospitalapp.entities.Patient;
import com.example.hospitalapp.errorhandler.ResourceNotFoundException;
import com.example.hospitalapp.repositories.MedicationPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MedicationPlanService {

    private final MedicationPlanRepository medicationPlanRepository;

    @Autowired
    public MedicationPlanService(MedicationPlanRepository medicationPlanRepository){
        this.medicationPlanRepository=medicationPlanRepository;
    }

    public MedicationPlanDTO findMedicationPlanById(Integer id){
        Optional<MedicationPlan> medicationp = medicationPlanRepository.findById(id);
        if(!medicationp.isPresent()){
            throw new ResourceNotFoundException("MedicationPlan", "medicationplan id", id);
        }
        return MedicationPlanBuilder.generateDTOFromEntity(medicationp.get());
    }

    public List<MedicationPlanDTO> findAll(){
        List<MedicationPlan> medicationPlans = medicationPlanRepository.findAll();
        return medicationPlans.stream()
                .map(MedicationPlanBuilder::generateDTOFromEntity)
                .collect(Collectors.toList());
    }

    public Integer insert(MedicationPlanDTO medDTO){
        return medicationPlanRepository.save(MedicationPlanBuilder.generateEntityFromDTO(medDTO)).getId();
    }

    public Integer update(MedicationPlanDTO medDTO){
        Optional<MedicationPlan> medication = medicationPlanRepository.findById(medDTO.getId());
        if(!medication.isPresent()){
            throw new ResourceNotFoundException("Medication", "id", medDTO.getId().toString());
        }
        return medicationPlanRepository.save(MedicationPlanBuilder.generateEntityFromDTO(medDTO)).getId();
    }

    public void delete(MedicationPlanDTO medDTO){
        this.medicationPlanRepository.deleteById(medDTO.getId());
    }

    public MedicationPlanDTO getByNumber(Integer nr){
        MedicationPlan mp = this.medicationPlanRepository.getByNumber(nr);
        return MedicationPlanBuilder.generateDTOFromEntity(mp);
    }

    public List<MedicationPlanDTO> getByUserId(Integer nr){
        List<MedicationPlan> mp = this.medicationPlanRepository.findByUserId(nr);
        return mp.stream()
                .map(MedicationPlanBuilder::generateDTOFromEntity)
                .collect(Collectors.toList());
    }

    public List<PatientDTO> getMedplansPatients(){
        List<Patient> patients = medicationPlanRepository.getMedplansPatients();
        return patients.stream()
                .map(PatientBuilder::generateDTOFromEntity)
                .collect(Collectors.toList());
    }
}
