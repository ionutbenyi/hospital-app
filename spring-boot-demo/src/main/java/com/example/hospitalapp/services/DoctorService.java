package com.example.hospitalapp.services;

import com.example.hospitalapp.dto.DoctorDTO;
import com.example.hospitalapp.dto.DoctorViewDTO;
import com.example.hospitalapp.dto.builders.DoctorBuilder;
import com.example.hospitalapp.dto.builders.DoctorViewBuilder;
import com.example.hospitalapp.entities.Doctor;
import com.example.hospitalapp.entities.Recommendation;
import com.example.hospitalapp.errorhandler.ResourceNotFoundException;
import com.example.hospitalapp.repositories.DoctorRepository;
import com.example.hospitalapp.repositories.RecommendationRepository;
import com.example.hospitalapp.validators.DoctorFieldValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final RecommendationRepository recommendationRepository;

    @Autowired
    public DoctorService(DoctorRepository doctorRepository, RecommendationRepository recommendationRepository){
        this.doctorRepository=doctorRepository;
        this.recommendationRepository = recommendationRepository;
    }

    public DoctorViewDTO findDoctorById(Integer id){
        Optional<Doctor> doctor = doctorRepository.findById(id);
        if (!doctor.isPresent()) {
            throw new ResourceNotFoundException("Doctor", "user id", id);
        }
        return DoctorViewBuilder.generateDTOFromEntity(doctor.get());
    }

    public List<DoctorViewDTO> findAll(){
        List<Doctor> doctors = doctorRepository.getAllOrdered();

        return doctors.stream()
                .map(DoctorViewBuilder::generateDTOFromEntity)
                .collect(Collectors.toList());
    }

    public Integer insert(DoctorDTO docDTO) {

        DoctorFieldValidator.validateInsertOrUpdate(docDTO);

        return doctorRepository
                .save(DoctorBuilder.generateEntityFromDTO(docDTO))
                .getId();
    }

    public Integer update(DoctorDTO docDTO) {

        Optional<Doctor> doctor = doctorRepository.findById(docDTO.getId());

        if(!doctor.isPresent()){
            throw new ResourceNotFoundException("Doctor", "user id", docDTO.getId().toString());
        }
        DoctorFieldValidator.validateInsertOrUpdate(docDTO);

        return doctorRepository.save(DoctorBuilder.generateEntityFromDTO(docDTO)).getId();
    }

    public void delete(DoctorDTO docDTO){
        this.doctorRepository.deleteById(docDTO.getId());
    }

    public Doctor loginDoctor(String email, String password){
        return this.doctorRepository.loginDoctor(email, password);
    }

    public Recommendation addRecommendation(Recommendation r){
        this.recommendationRepository.save(r);
        return r;
    }

}
