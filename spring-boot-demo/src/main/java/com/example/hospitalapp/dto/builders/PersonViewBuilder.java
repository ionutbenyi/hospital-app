package com.example.hospitalapp.dto.builders;

import com.example.hospitalapp.dto.PersonViewDTO;
import com.example.hospitalapp.entities.Person;

public class PersonViewBuilder {
    public static PersonViewDTO generateDTOFromEntity(Person person){
        return new PersonViewDTO(
                person.getId(),
                person.getName(),
                person.getEmail());
    }

    public static Person generateEntityFromDTO(PersonViewDTO personViewDTO){
        return new Person(
                personViewDTO.getId(),
                personViewDTO.getName(),
                personViewDTO.getEmail());
    }
}
