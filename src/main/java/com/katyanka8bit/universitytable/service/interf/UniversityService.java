package com.katyanka8bit.universitytable.service.interf;

import com.katyanka8bit.universitytable.model.University;
import com.katyanka8bit.universitytable.web.dto.UniversityDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UniversityService {
    //INNERS METHODS
    University saveUniversity(University university);

    void deleteUniversity(Integer id);

    List<University> getAllUniversities();

    University getUniversityById(Integer id);

    University addUniversity(UniversityDTO universityDTO);

    University updateUniversity(UniversityDTO universityDTO);

    //FRONTS METHODS
    ResponseEntity<List<UniversityDTO>> frontGetAllUniversities();


    ResponseEntity<UniversityDTO> frontGetUniversityById(Integer id);

    ResponseEntity<Object> frontDeleteById(Integer id);

    ResponseEntity<Object> frontAddUniversity(UniversityDTO universityDTO);

    ResponseEntity<Object> frontUpdateUniversity(UniversityDTO universityDTO);


}
