package com.katyanka8bit.universitytable.service.impl;

import com.katyanka8bit.universitytable.model.Faculty;
import com.katyanka8bit.universitytable.repository.FacultyRepository;
import com.katyanka8bit.universitytable.service.interf.FacultyService;
import com.katyanka8bit.universitytable.service.interf.UniversityService;
import com.katyanka8bit.universitytable.web.dto.FacultyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FacultyServiceImpl implements FacultyService {
    private final UniversityService universityService;
    private final FacultyRepository facultyRepository;

    @Autowired
    public FacultyServiceImpl(UniversityService universityService, FacultyRepository facultyRepository) {
        this.universityService = universityService;
        this.facultyRepository = facultyRepository;
    }


    @Override
    public Faculty saveFaculty(Faculty faculty) {
        if (faculty != null) {
            return facultyRepository.save(faculty);
        }
        return null;
    }


    @Transactional
    @Override
    public void deleteFaculty(Integer id) {
        if (id != null) {
            facultyRepository.delete(getFacultyById(id));
        }
    }

    @Override
    public List<Faculty> getAllFaculties() {
        return facultyRepository.getAll();
    }

    @Override
    public Faculty getFacultyById(Integer id) {
        if (id != null) {
            return facultyRepository.getById(id);
        }
        return null;
    }

    @Transactional
    @Override
    public Faculty addFaculty(FacultyDTO facultyDTO) {
        if (facultyDTO != null) {
            if (facultyRepository.getFacultyByName(facultyDTO.getName()) != null) {
                return null;
            }
            Faculty faculty = new Faculty();
            faculty.setName(facultyDTO.getName());
            faculty.setUniversity(universityService.getUniversityById(facultyDTO.getUniversityId()));
            saveFaculty(faculty);
            return faculty;
        }
        return null;
    }

    @Transactional
    @Override
    public Faculty updateFaculty(FacultyDTO facultyDTO) {
        Faculty faculty = facultyRepository.getById(facultyDTO.getId());
        if (faculty != null) {
            faculty.setName(facultyDTO.getName());
            return faculty;
        }
        return null;
    }

    @Override
    public List<Faculty> getFacultyByUniversityId(Integer universityId) {
        if (universityId != null) {
            return facultyRepository.getFacultyByUniversityId(universityId);
        }
        return null;
    }

    @Override
    public List<FacultyDTO> frontGetAllFaculties() {
        List<Faculty> facultyList = getAllFaculties();
        return facultyList.stream().map(FacultyDTO::new).collect(Collectors.toList());
    }

    @Override
    public FacultyDTO frontGetFacultyById(Integer id) {
        if (id != null) {
            return new FacultyDTO(getFacultyById(id));
        }
        return null;
    }

    @Transactional
    @Override
    public ResponseEntity<Object> frontDeleteById(Integer id) {
        if (id != null) {
            deleteFaculty(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Transactional
    @Override
    public ResponseEntity<Object> frontAddFaculty(FacultyDTO facultyDTO) {
        if (facultyDTO != null) {
            addFaculty(facultyDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Transactional
    @Override
    public ResponseEntity<Object> frontUpdateFaculty(FacultyDTO facultyDTO) {
        if (facultyDTO != null) {
            updateFaculty(facultyDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<List<FacultyDTO>> frontGetFacultyByUniversityId(Integer universityId) {
        if (universityId != null) {
            List<FacultyDTO> list = getFacultyByUniversityId(universityId).stream().map(FacultyDTO::new).collect(Collectors.toList());
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
        return null;
    }
}
