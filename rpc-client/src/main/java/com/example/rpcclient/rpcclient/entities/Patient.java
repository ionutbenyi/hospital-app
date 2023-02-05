package com.example.rpcclient.rpcclient.entities;

public class Patient {

    private String name;
    private Integer id;

    public Patient(String name, Integer id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }
}
