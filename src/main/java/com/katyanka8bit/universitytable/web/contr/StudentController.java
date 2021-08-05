package com.katyanka8bit.universitytable.web.contr;

import com.katyanka8bit.universitytable.service.interf.StudentService;
import com.katyanka8bit.universitytable.web.dto.StudentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rest/students/")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    @GetMapping
    public List<StudentDTO> getAllStudents() {
        return studentService.frontGetAllStudents();
    }

    @GetMapping("{id}")
    public StudentDTO getStudentById(@PathVariable Integer id) {
        return studentService.frontGetStudentById(id);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteStudentById(@PathVariable Integer id) {
        return studentService.frontDeleteById(id);
    }

    @PostMapping
    public ResponseEntity<Object> addStudent(@RequestBody StudentDTO studentDTO) {
        return studentService.frontAddStudent(studentDTO);
    }

    @PutMapping
    public ResponseEntity<Object> updateStudent(@RequestBody StudentDTO studentDTO) {
        return studentService.frontUpdateStudent(studentDTO);
    }

    @GetMapping("getbygroupid/{id}")
    public ResponseEntity<List<StudentDTO>> getByGroupId(@PathVariable Integer id) {
        return studentService.frontGetStudentByGroupId(id);
    }
}
