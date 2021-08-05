package com.katyanka8bit.universitytable.web.contr;

import com.katyanka8bit.universitytable.service.interf.FacultyService;
import com.katyanka8bit.universitytable.web.dto.FacultyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rest/faculty/")
public class FacultyController {
    private final FacultyService facultyService;

    @Autowired
    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }


    @GetMapping
    public List<FacultyDTO> getAllFaculties() {
        return facultyService.frontGetAllFaculties();
    }

    @GetMapping("{id}")
    public FacultyDTO getFacultyById(@PathVariable Integer id) {
        return facultyService.frontGetFacultyById(id);
    }

    @DeleteMapping("deleteFaculty/{id}")
    public ResponseEntity<Object> deleteFacultyById(@PathVariable Integer id) {
        return facultyService.frontDeleteById(id);
    }

    @PostMapping("newFaculty/")
    public ResponseEntity<Object> addFaculty(@RequestBody FacultyDTO facultyDTO) {
        return facultyService.frontAddFaculty(facultyDTO);

    }

    @PutMapping("editFaculty/")
    public ResponseEntity<Object> updateFaculty(@RequestBody FacultyDTO facultyDTO) {
        return facultyService.frontUpdateFaculty(facultyDTO);
    }

    @GetMapping("getbyuniversityid/{id}")
    public ResponseEntity<List<FacultyDTO>> getByUniversityId(@PathVariable Integer id) {
        return facultyService.frontGetFacultyByUniversityId(id);
    }
}
