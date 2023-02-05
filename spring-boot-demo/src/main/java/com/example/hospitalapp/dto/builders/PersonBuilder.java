package com.example.hospitalapp.dto.builders;

import com.example.hospitalapp.dto.PersonDTO;
import com.example.hospitalapp.entities.Person;

public class PersonBuilder {

    public PersonBuilder() {
    }

    public static PersonDTO generateDTOFromEntity(Person person){
        return new PersonDTO(
                person.getId(),
                person.getName(),
                person.getEmail(),
                person.getIban(),
                person.getAddress());
    }

    public static Person generateEntityFromDTO(PersonDTO personDTO){
        return new Person(
                personDTO.getId(),
                personDTO.getName(),
                personDTO.getEmail(),
                personDTO.getIban(),
                personDTO.getAddress());
    }
}
