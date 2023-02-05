package com.example.hospitalapp.services;

import com.example.hospitalapp.dto.MedicationDTO;
import com.example.hospitalapp.dto.builders.MedicationBuilder;
import com.example.hospitalapp.entities.Medication;
import com.example.hospitalapp.errorhandler.ResourceNotFoundException;
import com.example.hospitalapp.repositories.MedicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MedicationService {

    private final MedicationRepository medicationRepository;

    @Autowired
    public MedicationService(MedicationRepository medicationRepository){
        this.medicationRepository=medicationRepository;
    }

    public MedicationDTO findMedicationById(Integer id){
        Optional<Medication> medication = medicationRepository.findById(id);
        if(!medication.isPresent()){
            throw new ResourceNotFoundException("Medication", "medication id", id);
        }
        return MedicationBuilder.generateDTOFromEntity(medication.get());
    }

    public List<MedicationDTO> findAll(){
        List<Medication> medications = medicationRepository.findAll();
        return medications.stream()
                .map(MedicationBuilder::generateDTOFromEntity)
                .collect(Collectors.toList());
    }

    public Integer insert(MedicationDTO medDTO){
        return medicationRepository.save(MedicationBuilder.generateEntityFromDTO(medDTO)).getId();
    }

    public Integer update(Map<String, String> medData){
        Medication m = medicationRepository.findMedication(medData.get("name"), medData.get("sideEffects"));
        return medicationRepository.save(m).getId();
    }

    public Integer delete(MedicationDTO medData){
        Medication m = this.medicationRepository.findMedication(medData.getName(), medData.getSideEffects());
        this.medicationRepository.deleteById(m.getId());
        return m.getId();
    }
}
