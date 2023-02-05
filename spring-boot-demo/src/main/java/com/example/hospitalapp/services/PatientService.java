package com.example.hospitalapp.services;

import com.example.hospitalapp.dto.PatientDTO;
import com.example.hospitalapp.dto.builders.PatientBuilder;
import com.example.hospitalapp.entities.Patient;
import com.example.hospitalapp.errorhandler.ResourceNotFoundException;
import com.example.hospitalapp.repositories.PatientRepository;
import com.example.hospitalapp.validators.PatientFieldValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository){
        this.patientRepository=patientRepository;
    }

    public PatientDTO findPatientById(Integer id){
        Optional<Patient> patient = patientRepository.findById(id);
        if(!patient.isPresent()){
            throw new ResourceNotFoundException("Patient", "user id", id);
        }
        return PatientBuilder.generateDTOFromEntity(patient.get());
    }

    public Set<PatientDTO> findAll(){
        //List<Patient> patients = patientRepository.getAllFetch();
        Set<Patient> patients = patientRepository.getAllOrdered();
        return patients.stream()
                .map(PatientBuilder::generateDTOFromEntity)
                .collect(Collectors.toSet());
    }

    public Integer insert(PatientDTO pDTO){
        PatientFieldValidator.validateInsertOrUpdate(pDTO);
        return patientRepository.save(PatientBuilder.generateEntityFromDTO(pDTO))
                .getId();
    }

    public Integer update(PatientDTO pDTO){
        Optional<Patient> patient = patientRepository.findById(pDTO.getId());
        if(!patient.isPresent()){
            throw new ResourceNotFoundException("Patient", "user id", pDTO.getId());
        }
        return patientRepository.save(PatientBuilder.generateEntityFromDTO(pDTO)).getId();
    }

    public Integer delete(PatientDTO pDTO){
        patientRepository.delete(PatientBuilder.generateEntityFromDTO(pDTO));
        return 1;
    }

    public Patient loginPatient(String email, String password){
        return this.patientRepository.loginPatient(email, password);
    }
}
