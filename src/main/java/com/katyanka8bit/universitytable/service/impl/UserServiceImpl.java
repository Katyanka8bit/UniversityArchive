package com.katyanka8bit.universitytable.service.impl;

import com.katyanka8bit.universitytable.model.Role;
import com.katyanka8bit.universitytable.model.User;
import com.katyanka8bit.universitytable.repository.RoleRepository;
import com.katyanka8bit.universitytable.repository.UserRepository;
import com.katyanka8bit.universitytable.service.interf.UserService;
import com.katyanka8bit.universitytable.web.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final MailSender mailSender;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(MailSender mailSender, UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.mailSender = mailSender;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.getUserByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    public User getUserByEmail(String email) {
        if (email != null) {
            return userRepository.getUserByEmail(email);

        }
        return null;
    }

    public User saveUser(User user) {
        if (user != null) {
            userRepository.save(user);
            return user;
        }
        return null;
    }

    @Override
    public User getUserById(Integer id) {
        if (id != null) {
            return userRepository.getById(id);
        }
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(UserDTO userDTO) {
        User user = userRepository.getUserByEmail(userDTO.getEmail());
        if (user != null) {
            user.setId(userDTO.getId());
            user.setFirstName(userDTO.getFirstName());
            user.setLastName(userDTO.getLastName());
            user.setPassword(userDTO.getPassword());

            return user;
        }

        return null;
    }

    @Override
    public void deleteUserByLogin(String email) {
        if (email != null) {
            userRepository.deleteUserByEmail(email);
        }
    }


    @Override
    public User addUser(UserDTO userDTO) {
        if (userDTO != null) {
            if (userRepository.getUserByEmail(userDTO.getEmail()) == null) {
                User user = new User(userDTO.getFirstName(),
                        userDTO.getLastName(), userDTO.getEmail(),
                        bCryptPasswordEncoder.encode(userDTO.getPassword()), Arrays.asList(new Role("ROLE_USER")));

                if (!StringUtils.isEmpty(user.getEmail())) {
                    String message = String.format(
                            "Hello, %s! \n" +
                                    "Welcome to University" + " " +
                                    "Your login: " + userDTO.getEmail() + " " +
                                    "Your password: " + userDTO.getPassword(),
                            userDTO.getEmail(),
                            userDTO.getPassword()
                    );

                    mailSender.send(user.getEmail(), "Welcome!", message);
                }
                user.setEmail(user.getEmail().toLowerCase());
                return saveUser(user);
            }
            //такой пользователь уже существует
            return null;
        }
        return null;
    }


    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void deleteUser(Integer id) {
        if (id != null) {
            userRepository.delete(getUserById(id));
        }
    }

    @Override
    public List<UserDTO> frontGetAllUsers() {
        List<User> userList = getAllUsers();
        return userList.stream().map(UserDTO::new).collect(Collectors.toList());

    }

    @Override
    public ResponseEntity<Object> frontDeleteUser(Integer id) {
        if (id != null) {
            deleteUser(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Object> frontAddUser(UserDTO userDTO) {
        if (userDTO != null) {
            addUser(userDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    @Override
    public UserDTO frontLoadUserByUsername(String email) {
        if (email != null) {
            return new UserDTO(getUserByEmail(email));
        }
        return null;
    }

    @Override
    public UserDTO frontGetUserById(Integer id) {
        if (id != null) {
            return new UserDTO(getUserById(id));
        }
        return null;
    }


}
