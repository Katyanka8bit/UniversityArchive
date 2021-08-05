package com.katyanka8bit.universitytable.service.interf;

import com.katyanka8bit.universitytable.model.Faculty;
import com.katyanka8bit.universitytable.web.dto.FacultyDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface FacultyService {
    //INNERS METHODS
    Faculty saveFaculty(Faculty faculty);

    void deleteFaculty(Integer id);

    List<Faculty> getAllFaculties();

    Faculty getFacultyById(Integer id);

    Faculty addFaculty(FacultyDTO facultyDTO);

    Faculty updateFaculty(FacultyDTO facultyDTO);

    List<Faculty> getFacultyByUniversityId(Integer universityId);

    //FRONTS METHODS
    List<FacultyDTO> frontGetAllFaculties();


    FacultyDTO frontGetFacultyById(Integer id);

    ResponseEntity<Object> frontDeleteById(Integer id);

    ResponseEntity<Object> frontAddFaculty(FacultyDTO facultyDTO);

    ResponseEntity<Object> frontUpdateFaculty(FacultyDTO facultyDTO);

    ResponseEntity<List<FacultyDTO>> frontGetFacultyByUniversityId(Integer universityId);

}
