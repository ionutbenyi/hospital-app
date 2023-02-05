package com.example.hospitalapp.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.GenerationType.TABLE;

@Entity
@Table(name = "recommendation")
public class Recommendation {
    @Id
    @GeneratedValue(strategy = TABLE)
    @Column(name = "recommendation_id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "message", length = 100)
    private String message;

    @Column(name = "caregiverId")
    private Integer caregiverId;

    public Recommendation(String message, Integer caregiverId) {
        this.message = message;
        this.caregiverId = caregiverId;
    }

    public Recommendation() {
    }

    public Integer getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public Integer getCaregiverId() {
        return caregiverId;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setCaregiverId(Integer caregiverId) {
        this.caregiverId = caregiverId;
    }
}
