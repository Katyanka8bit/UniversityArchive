package com.katyanka8bit.universitytable.web.dto;


import com.katyanka8bit.universitytable.model.Student;

public class StudentDTO {
    private Integer id;
    private String name;
    private String surname;
    private Integer age;
    private Integer groupId;
    private String groupName;

    public StudentDTO() {
    }

    public StudentDTO(Student student) {
        if (student != null) {
            this.id = student.getId();
            this.name = student.getName();
            this.surname = student.getSurname();
            this.age = student.getAge();
            this.groupId = student.getGroup().getId();
            this.groupName = student.getGroup().getName();
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

}

