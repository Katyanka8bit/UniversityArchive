package com.katyanka8bit.universitytable.web.contr;

import com.katyanka8bit.universitytable.service.interf.UniversityService;
import com.katyanka8bit.universitytable.web.dto.UniversityDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rest/university/")
public class UniversityController {
    private final UniversityService universityService;

    @Autowired
    public UniversityController(UniversityService universityService) {
        this.universityService = universityService;
    }

    @GetMapping
    public ResponseEntity<List<UniversityDTO>> getAllUniversities() {
        return universityService.frontGetAllUniversities();
    }

    @GetMapping("{id}")
    public ResponseEntity<UniversityDTO> getUniversityById(@PathVariable Integer id) {
        return universityService.frontGetUniversityById(id);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteUniversityById(@PathVariable Integer id) {
        return universityService.frontDeleteById(id);
    }

    @PostMapping
    public ResponseEntity<Object> addUniversity(@RequestBody UniversityDTO universityDTO) {
        return universityService.frontAddUniversity(universityDTO);
    }

    @PutMapping
    public ResponseEntity<Object> updateUniversity(@RequestBody UniversityDTO universityDTO) {
        return universityService.frontUpdateUniversity(universityDTO);
    }

}
