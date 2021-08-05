package com.katyanka8bit.universitytable.repository;

import com.katyanka8bit.universitytable.model.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Integer> {
    Faculty getFacultyByName(String name);

    @Query("select u from Faculty u join fetch u.university")
    List<Faculty> getAll();

    @Query("select u from Faculty u join fetch u.university where u.university.id =?1")
    List<Faculty> getFacultyByUniversityId(Integer universityId);

    @Query("select u from Faculty u join fetch u.university where u.id =?1")
    Faculty getById(Integer facultyId);
}
