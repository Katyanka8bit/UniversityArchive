package com.katyanka8bit.universitytable.web.dto;


import com.katyanka8bit.universitytable.model.Group;

public class GroupDTO {
    private Integer id;
    private String name;
    private Integer facultyId;
    private String facultyName;

    public GroupDTO() {
    }

    public GroupDTO(Group group) {
        if (group != null) {
            this.id = group.getId();
            this.name = group.getName();
            this.facultyId = group.getFaculty().getId();
            this.facultyName = group.getFaculty().getName();
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

    public Integer getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(Integer facultyId) {
        this.facultyId = facultyId;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }
}
