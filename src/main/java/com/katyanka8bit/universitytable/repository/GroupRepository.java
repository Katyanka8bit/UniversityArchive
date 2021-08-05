package com.katyanka8bit.universitytable.repository;

import com.katyanka8bit.universitytable.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group, Integer> {
    Group getGroupByName(String name);

    @Query("select u from Group u join fetch u.faculty")
    List<Group> getAll();

    @Query("select u from Group u join fetch u.faculty where u.faculty.id =?1")
    List<Group> getGroupByFacultyId(Integer facultyId);

    @Query("select u from Group u join fetch u.faculty where u.id =?1")
    Group getById(Integer groupId);
}
