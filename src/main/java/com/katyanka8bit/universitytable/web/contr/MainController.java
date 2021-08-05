package com.katyanka8bit.universitytable.web.contr;

import com.katyanka8bit.universitytable.repository.UserRepository;
import com.katyanka8bit.universitytable.service.interf.FacultyService;
import com.katyanka8bit.universitytable.service.interf.GroupService;
import com.katyanka8bit.universitytable.service.interf.StudentService;
import com.katyanka8bit.universitytable.service.interf.UserService;
import com.katyanka8bit.universitytable.web.dto.FacultyDTO;
import com.katyanka8bit.universitytable.web.dto.GroupDTO;
import com.katyanka8bit.universitytable.web.dto.StudentDTO;
import com.katyanka8bit.universitytable.web.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MainController {

    private final StudentService studentService;
    private final FacultyService facultyService;
    private final UserService userService;
    private final UserRepository userRepository;
    private final GroupService groupService;

    @Autowired
    public MainController(StudentService studentService, FacultyService facultyService, UserService userService, UserRepository userRepository, GroupService groupService) {
        this.studentService = studentService;
        this.facultyService = facultyService;
        this.userService = userService;
        this.userRepository = userRepository;
        this.groupService = groupService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/")
    public String home(Model model) {

        List<FacultyDTO> listFaculty = facultyService.frontGetAllFaculties();
        model.addAttribute("listFaculty", listFaculty);

        List<GroupDTO> listGroup = groupService.frontGetAllGroups();
        model.addAttribute("listGroup", listGroup);
        List<StudentDTO> listStudents = studentService.frontGetAllStudents();
        model.addAttribute("listStudents", listStudents);
        List<UserDTO> listUsers = userService.frontGetAllUsers();
        model.addAttribute("listUsers", listUsers);

        return "index";
    }


}
