package com.katyanka8bit.universitytable.repository;

import com.katyanka8bit.universitytable.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    Student getStudentBySurname(String surname);

    Student getStudentByName(String name);

    @Query("select u from Student u join fetch u.group")
    List<Student> getAll();

    @Query("select u from Student u join fetch u.group where u.group.id =?1")
    List<Student> getStudentByGroupId(Integer groupId);

    @Query("select u from Student u join fetch u.group where u.id =?1")
    Student getById(Integer studentId);
}
