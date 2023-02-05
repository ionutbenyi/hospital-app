package com.example.hospitalapp.controller;


import com.example.hospitalapp.dto.PersonDTO;
import com.example.hospitalapp.dto.PersonViewDTO;
import com.example.hospitalapp.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/person")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping(value = "/{id}")
    public PersonViewDTO findById(@PathVariable("id") Integer id){
        return personService.findUserById(id);
    }

    @GetMapping()
    public List<PersonViewDTO> findAll(){
        return personService.findAll();
    }

    @PostMapping()
    public Integer insertUserDTO(@RequestBody PersonDTO personDTO){
        return personService.insert(personDTO);
    }

    @PutMapping()
    public Integer updateUser(@RequestBody PersonDTO personDTO) {
        return personService.update(personDTO);
    }

    @DeleteMapping()
    public void delete(@RequestBody PersonViewDTO personViewDTO){
        personService.delete(personViewDTO);
    }
}
