package com.example.rpcclient.rpcclient.entities;

public class Medicine {

    private String name;
    private Integer startHour;
    private Integer endHour;

    public Medicine(String name, Integer startHour, Integer endHour) {
        this.name = name;
        this.startHour = startHour;
        this.endHour = endHour;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStartHour() {
        return startHour;
    }

    public void setStartHour(Integer startHour) {
        this.startHour = startHour;
    }

    public Integer getEndHour() {
        return endHour;
    }

    public void setEndHour(Integer endHour) {
        this.endHour = endHour;
    }
}
