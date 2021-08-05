package com.katyanka8bit.universitytable.web.dto;


import com.katyanka8bit.universitytable.model.Faculty;

public class FacultyDTO {
    private Integer id;
    private String name;
    private Integer universityId;
    private String universityName;

    public FacultyDTO() {
    }

    public FacultyDTO(Faculty faculty) {
        if (faculty != null) {
            this.id = faculty.getId();
            this.name = faculty.getName();
            this.universityId = faculty.getUniversity().getId();
            this.universityName = faculty.getUniversity().getName();
        }
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

    public Integer getUniversityId() {
        return universityId;
    }

    public void setUniversityId(Integer universityId) {
        this.universityId = universityId;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }
}
