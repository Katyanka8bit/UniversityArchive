package com.katyanka8bit.universitytable.model;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "universities")
public class University {
    @Id
    @Column(name = "university_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "university_name")
    private String name;
    @OneToMany(mappedBy = "university", fetch = FetchType.LAZY)
    private List<Faculty> facultyList;

    public University() {
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

    public List<Faculty> getFacultyList() {
        return facultyList;
    }

    public void setFacultyList(List<Faculty> facultyList) {
        this.facultyList = facultyList;
    }

    @Override
    public String toString() {
        return "University{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", facultyList=" + facultyList +
                '}';
    }
}
