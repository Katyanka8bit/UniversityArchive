package com.katyanka8bit.universitytable.service.interf;

import com.katyanka8bit.universitytable.model.Student;
import com.katyanka8bit.universitytable.web.dto.StudentDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface StudentService {
    //INNERS METHODS
    Student saveStudent(Student student);

    void deleteStudent(Integer id);

    List<Student> getAllStudents();

    Student getStudentById(Integer id);

    Student addStudent(StudentDTO studentDTO);

    Student updateStudent(StudentDTO studentDTO);

    List<Student> getStudentByGroupId(Integer groupId);

    //FRONTS METHODS
    List<StudentDTO> frontGetAllStudents();


    StudentDTO frontGetStudentById(Integer id);

    ResponseEntity<Object> frontDeleteById(Integer id);

    ResponseEntity<Object> frontAddStudent(StudentDTO studentDTO);

    ResponseEntity<Object> frontUpdateStudent(StudentDTO studentDTO);

    ResponseEntity<List<StudentDTO>> frontGetStudentByGroupId(Integer groupId);
}
