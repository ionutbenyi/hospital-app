package com.example.hospitalapp.services;

import com.example.hospitalapp.dto.CaregiverDTO;
import com.example.hospitalapp.dto.CaregiverViewDTO;
import com.example.hospitalapp.dto.builders.CaregiverBuilder;
import com.example.hospitalapp.dto.builders.CaregiverViewBuilder;
import com.example.hospitalapp.entities.Caregiver;
import com.example.hospitalapp.entities.Patient;
import com.example.hospitalapp.errorhandler.ResourceNotFoundException;
import com.example.hospitalapp.repositories.CaregiverRepository;
import com.example.hospitalapp.repositories.PatientRepository;
import com.example.hospitalapp.validators.CaregiverFieldValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CaregiverService {

    private final CaregiverRepository caregiverRepository;

    @Autowired
    public CaregiverService(CaregiverRepository caregiverRepository){
        this.caregiverRepository=caregiverRepository;
    }

    public Caregiver findCaregiverById(Integer id){
        Optional<Caregiver> caregiver = caregiverRepository.findById(id);
        if (!caregiver.isPresent()) {
            throw new ResourceNotFoundException("Caregiver", "user id", id);
        }
        return caregiver.get();
    }

    public Set<CaregiverViewDTO> findAll(){
        //List<Caregiver> caregivers = caregiverRepository.getAllFetch();
        Set<Caregiver> caregivers = caregiverRepository.getAllOrdered();

        return caregivers.stream()
                .map(CaregiverViewBuilder::generateDTOFromEntity)
                .collect(Collectors.toSet());
    }

    public Integer insert(CaregiverDTO cDTO) {

        CaregiverFieldValidator.validateInsertOrUpdate(cDTO);

        return caregiverRepository
                .save(CaregiverBuilder.generateEntityFromDTO(cDTO))
                .getId();
    }

    public Caregiver update(CaregiverDTO cDTO) {

        Optional<Caregiver> caregiver = caregiverRepository.findById(cDTO.getId());
        if(!caregiver.isPresent()){
            return null;
        }
        return caregiverRepository.save(CaregiverBuilder.generateEntityFromDTO(cDTO));
    }

    public void delete(CaregiverDTO cDTO){
        this.caregiverRepository.deleteById(cDTO.getId());
    }

    public Caregiver loginCaregiver(String email, String password){
        return this.caregiverRepository.loginCaregiver(email, password);
    }

    public Integer addPatient(Integer id, Patient p){
        Optional<Caregiver> caregiver = caregiverRepository.findById(id);
        if(!caregiver.isPresent()){
            return 0;
        }else {
            Caregiver cg = caregiver.get();
            Set<Patient> patSet = cg.getPatientsList();
            patSet.add(p);
            cg.setPatientsList(patSet);
            return caregiverRepository.save(cg).getId();
        }
    }
}
