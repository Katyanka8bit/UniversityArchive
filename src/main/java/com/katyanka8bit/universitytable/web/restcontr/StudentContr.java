package com.katyanka8bit.universitytable.web.restcontr;

import com.katyanka8bit.universitytable.service.interf.StudentService;
import com.katyanka8bit.universitytable.web.dto.StudentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class StudentContr {

    private final StudentService studentService;

    @Autowired
    public StudentContr(StudentService studentService) {
        this.studentService = studentService;
    }


    @RequestMapping("/newstudent")
    public String showNewStudentForm(Model model) {
        StudentDTO studentDTO = new StudentDTO();

        model.addAttribute("studentDTO", studentDTO);

        return "new_student";
    }

    @RequestMapping(value = "/savestudent", method = RequestMethod.POST)
    public String saveStudent(@ModelAttribute("studentDTO") StudentDTO studentDTO) {
        studentService.frontAddStudent(studentDTO);

        return "redirect:/";
    }

    @GetMapping("/student-update/{id}")
    public String updateStudentForm(@PathVariable("id") Integer id, Model model) {
        StudentDTO studentDTO = studentService.frontGetStudentById(id);
        model.addAttribute("studentDTO", studentDTO);
        return "student-update";
    }

    @RequestMapping(value = "/student-update", method = RequestMethod.POST)
    public String updateStudent(StudentDTO studentDTO) {
        studentService.updateStudent(studentDTO);
        return "redirect:/";
    }


    @RequestMapping("/deleteStudent/{id}")
    public String deleteStudent(@PathVariable(name = "id") Integer id) {
        studentService.deleteStudent(id);
        return "redirect:/";
    }
}
