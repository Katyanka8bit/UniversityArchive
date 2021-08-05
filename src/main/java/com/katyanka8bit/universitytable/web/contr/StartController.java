package com.katyanka8bit.universitytable.web.contr;

import com.katyanka8bit.universitytable.model.Faculty;
import com.katyanka8bit.universitytable.model.Group;
import com.katyanka8bit.universitytable.model.Student;
import com.katyanka8bit.universitytable.model.University;
import com.katyanka8bit.universitytable.service.interf.FacultyService;
import com.katyanka8bit.universitytable.service.interf.GroupService;
import com.katyanka8bit.universitytable.service.interf.StudentService;
import com.katyanka8bit.universitytable.service.interf.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/start")
public class StartController {
    private final UniversityService universityService;
    private final FacultyService facultyService;
    private final GroupService groupService;
    private final StudentService studentService;

    @Autowired
    public StartController(UniversityService universityService, FacultyService facultyService, GroupService groupService, StudentService studentService) {
        this.universityService = universityService;
        this.facultyService = facultyService;
        this.groupService = groupService;
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<Object> getStarted() {
        //создаем объекты и заносим их в базу
        University university = new University();
        university.setName("МГУ");
        universityService.saveUniversity(university);

        Faculty faculty1 = new Faculty();
        faculty1.setUniversity(university);
        faculty1.setName("Факультет программистов");
        facultyService.saveFaculty(faculty1);

        Faculty faculty2 = new Faculty();
        faculty2.setUniversity(university);
        faculty2.setName("Факультет 'экономистов'");
        facultyService.saveFaculty(faculty2);

        Group group1 = new Group();
        group1.setFaculty(faculty1);
        group1.setName("Java разрабы");
        groupService.saveGroup(group1);

        Group group2 = new Group();
        group2.setFaculty(faculty2);
        group2.setName("Бизнес экономика");
        groupService.saveGroup(group2);

        Student student1 = new Student();
        student1.setGroup(group1);
        student1.setName("Алексей");
        student1.setSurname("Филатьев");
        student1.setAge(39);
        studentService.saveStudent(student1);

        Student student2 = new Student();
        student2.setGroup(group1);
        student2.setAge(21);
        student2.setName("Екатерина");
        student2.setSurname("Соседова");
        studentService.saveStudent(student2);

        Student student3 = new Student();
        student3.setGroup(group2);
        student3.setAge(18);
        student3.setName("Мария");
        student3.setSurname("Петрова");
        studentService.saveStudent(student3);

        Student student4 = new Student();
        student4.setGroup(group2);
        student4.setAge(20);
        student4.setName("Иван");
        student4.setSurname("Иванов");
        studentService.saveStudent(student4);

        Student student5 = new Student();
        student5.setGroup(group2);
        student5.setAge(20);
        student5.setName("Александр");
        student5.setSurname("Пушкин");
        studentService.saveStudent(student5);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}