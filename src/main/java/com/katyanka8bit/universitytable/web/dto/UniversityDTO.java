package com.katyanka8bit.universitytable.web.dto;


import com.katyanka8bit.universitytable.model.University;

public class UniversityDTO {
    private Integer id;
    private String name;

    public UniversityDTO() {
    }

    public UniversityDTO(University university) {
        this.id = university.getId();
        this.name = university.getName();


    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
