package com.katyanka8bit.universitytable.repository;


import com.katyanka8bit.universitytable.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User getUserByEmail(String email);

    void deleteUserByEmail(String email);

}

