package com.katyanka8bit.universitytable.model;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "faculties")
public class Faculty {
    @Id
    @Column(name = "faculty_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "faculty_name")
    private String name;
    @OneToMany(mappedBy = "faculty", fetch = FetchType.LAZY)
    private List<Group> groupList;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "university_id")
    private University university;


    public Faculty() {
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

    public List<Group> getGroupList() {
        return groupList;
    }

    public void setGroupList(List<Group> groupList) {
        this.groupList = groupList;
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    @Override
    public String toString() {
        return "Faculty{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", groupList=" + groupList +
                ", university=" + university +
                '}';
    }
}
