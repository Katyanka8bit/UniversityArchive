package com.katyanka8bit.universitytable.service.impl;

import com.katyanka8bit.universitytable.model.Student;
import com.katyanka8bit.universitytable.repository.StudentRepository;
import com.katyanka8bit.universitytable.service.interf.GroupService;
import com.katyanka8bit.universitytable.service.interf.StudentService;
import com.katyanka8bit.universitytable.web.dto.StudentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final GroupService groupService;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository, GroupService groupService) {
        this.studentRepository = studentRepository;
        this.groupService = groupService;
    }


    @Override
    public Student saveStudent(Student student) {
        if (student != null) {
            return studentRepository.save(student);
        }
        return null;
    }

    @Transactional
    @Override
    public void deleteStudent(Integer id) {
        if (id != null) {
            studentRepository.delete(getStudentById(id));
        }
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.getAll();
    }

    @Override
    public Student getStudentById(Integer id) {
        if (id != null) {
            return studentRepository.getById(id);
        }
        return null;
    }

    @Transactional
    @Override
    public Student addStudent(StudentDTO studentDTO) {
        if (studentDTO != null) {
            if (studentRepository.getStudentBySurname(studentDTO.getSurname()) != null) {
                return null;
            }
            Student student = new Student();
            student.setId(studentDTO.getId());
            student.setSurname(studentDTO.getSurname());
            student.setName(studentDTO.getName());
            student.setGroup(groupService.getGroupById(studentDTO.getGroupId()));
            saveStudent(student);
            return student;
        }
        return null;
    }

    @Transactional
    @Override
    public Student updateStudent(StudentDTO studentDTO) {
        Student student = studentRepository.getById(studentDTO.getId());
        if (student != null) {
            if (!studentDTO.getName().equals(student.getName())) {
                student.setName(studentDTO.getName());
            }
            if (!studentDTO.getSurname().equals(student.getSurname())) {
                student.setSurname(studentDTO.getSurname());
            }
            if (!studentDTO.getAge().equals(student.getAge())) {
                student.setAge(studentDTO.getAge());

            }
            return student;
        }
        return null;
    }

    @Override
    public List<Student> getStudentByGroupId(Integer groupId) {
        if (groupId != null) {
            return studentRepository.getStudentByGroupId(groupId);
        }
        return null;
    }

    @Override
    public List<StudentDTO> frontGetAllStudents() {
        List<Student> studentList = getAllStudents();
        return studentList.stream().map(StudentDTO::new).collect(Collectors.toList());
    }

    @Override
    public StudentDTO frontGetStudentById(Integer id) {
        if (id != null) {
            return new StudentDTO(getStudentById(id));
        }
        return null;
    }

    @Transactional
    @Override
    public ResponseEntity<Object> frontDeleteById(Integer id) {
        if (id != null) {
            deleteStudent(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Transactional
    @Override
    public ResponseEntity<Object> frontAddStudent(StudentDTO studentDTO) {
        if (studentDTO != null) {
            if (groupService.getGroupById(studentDTO.getGroupId()) != null) {
                addStudent(studentDTO);
                return new ResponseEntity<>(HttpStatus.OK);
            }

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Transactional
    @Override
    public ResponseEntity<Object> frontUpdateStudent(StudentDTO studentDTO) {
        if (studentDTO != null) {
            if (groupService.getGroupById(studentDTO.getGroupId()) != null) {
                updateStudent(studentDTO);
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
      return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<List<StudentDTO>> frontGetStudentByGroupId(Integer groupId) {
        if (groupId != null) {
            List<StudentDTO> list = getStudentByGroupId(groupId).stream().map(StudentDTO::new).collect(Collectors.toList());
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
        return null;
    }
}
