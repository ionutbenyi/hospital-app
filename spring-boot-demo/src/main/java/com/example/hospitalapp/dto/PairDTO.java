package com.example.hospitalapp.dto;

public class PairDTO {
    private String email;
    private String password;

    public PairDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public PairDTO() {
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
