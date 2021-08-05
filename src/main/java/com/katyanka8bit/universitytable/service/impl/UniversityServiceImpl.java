package com.katyanka8bit.universitytable.service.impl;

import com.katyanka8bit.universitytable.model.University;
import com.katyanka8bit.universitytable.repository.UniversityRepository;
import com.katyanka8bit.universitytable.service.interf.UniversityService;
import com.katyanka8bit.universitytable.web.dto.UniversityDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UniversityServiceImpl implements UniversityService {

    private final UniversityRepository universityRepository;

    @Autowired
    public UniversityServiceImpl(UniversityRepository universityRepository) {
        this.universityRepository = universityRepository;
    }

    @Override
    public University saveUniversity(University university) {
        if (university != null) {
            return universityRepository.save(university);
        }
        return null;
    }

    @Transactional
    @Override
    public void deleteUniversity(Integer id) {
        if (id != null) {
            universityRepository.delete(getUniversityById(id));
        }
    }

    @Override
    public List<University> getAllUniversities() {
        return universityRepository.findAll();
    }

    @Override
    public University getUniversityById(Integer id) {
        if (id != null) {
            return universityRepository.getById(id);
        }
        return null;
    }

    @Transactional
    @Override
    public University addUniversity(UniversityDTO universityDTO) {
        if (universityDTO != null) {
            if (universityRepository.getUniversityByName(universityDTO.getName()) != null) {
                return null;
            }
            University university = new University();
            university.setName(universityDTO.getName());
            saveUniversity(university);
            return university;
        }
        return null;
    }

    @Transactional
    @Override
    public University updateUniversity(UniversityDTO universityDTO) {
        University university = universityRepository.getById(universityDTO.getId());
        if (university != null) {
            if (!universityDTO.getName().equals(university.getName())) {
                university.setName(universityDTO.getName());
                return saveUniversity(university);
            }
        }
        return null;
    }

    @Override
    public ResponseEntity<List<UniversityDTO>> frontGetAllUniversities() {
        List<University> universityList = getAllUniversities();
        List<UniversityDTO> universityDTOSList = universityList.stream().map(UniversityDTO::new).collect(Collectors.toList());
        return new ResponseEntity<>(universityDTOSList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UniversityDTO> frontGetUniversityById(Integer id) {
        if (id != null) {
            return new ResponseEntity<>(new UniversityDTO(getUniversityById(id)), HttpStatus.OK);
        }
        return null;
    }

    @Transactional
    @Override
    public ResponseEntity<Object> frontDeleteById(Integer id) {
        if (id != null) {
            deleteUniversity(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Transactional
    @Override
    public ResponseEntity<Object> frontAddUniversity(UniversityDTO universityDTO) {
        if (universityDTO != null) {
            addUniversity(universityDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Transactional
    @Override
    public ResponseEntity<Object> frontUpdateUniversity(UniversityDTO universityDTO) {
        if (universityDTO != null) {
            updateUniversity(universityDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


}
