package com.katyanka8bit.universitytable.repository;

import com.katyanka8bit.universitytable.model.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UniversityRepository extends JpaRepository<University, Integer> {
    University getUniversityByName(String name);
}
