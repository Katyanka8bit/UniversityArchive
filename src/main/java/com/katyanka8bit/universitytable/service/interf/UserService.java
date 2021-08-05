package com.katyanka8bit.universitytable.service.interf;


import com.katyanka8bit.universitytable.model.User;
import com.katyanka8bit.universitytable.web.dto.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    //INNERS METHODS
    UserDetails loadUserByUsername(String login);

    List<User> getAllUsers();

    User getUserByEmail(String email);

    User getUserById(Integer id);

    User saveUser(User user);

    User addUser(UserDTO userDTO);

    void deleteUser(Integer id);

    public User updateUser(UserDTO userDTO);

    void deleteUserByLogin(String email);

    //FRONTS METHODS

    public UserDTO frontGetUserById(Integer id);

    List<UserDTO> frontGetAllUsers();

    ResponseEntity<Object> frontDeleteUser(Integer id);

    ResponseEntity<Object> frontAddUser(UserDTO userDTO);

    UserDTO frontLoadUserByUsername(String email);

}
