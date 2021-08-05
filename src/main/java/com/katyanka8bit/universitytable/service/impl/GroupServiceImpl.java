package com.katyanka8bit.universitytable.service.impl;

import com.katyanka8bit.universitytable.model.Group;
import com.katyanka8bit.universitytable.repository.FacultyRepository;
import com.katyanka8bit.universitytable.repository.GroupRepository;
import com.katyanka8bit.universitytable.service.interf.FacultyService;
import com.katyanka8bit.universitytable.service.interf.GroupService;
import com.katyanka8bit.universitytable.web.dto.GroupDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupServiceImpl implements GroupService {
    private final FacultyService facultyService;
    private final GroupRepository groupRepository;


    @Autowired
    public GroupServiceImpl(FacultyRepository facultyRepository, FacultyService facultyService, GroupRepository groupRepository) {
        this.facultyService = facultyService;
        this.groupRepository = groupRepository;
    }


    @Override
    public Group saveGroup(Group group) {
        if (group != null) {
            return groupRepository.save(group);
        }
        return null;
    }

    @Transactional
    @Override
    public void deleteGroup(Integer id) {
        if (id != null) {
            groupRepository.delete(getGroupById(id));
        }
    }

    @Override
    public List<Group> getAllGroups() {
        return groupRepository.getAll();
    }

    @Override
    public Group getGroupById(Integer id) {
        if (id != null) {
            return groupRepository.getById(id);
        }
        return null;
    }

    @Transactional
    @Override
    public Group addGroup(GroupDTO groupDTO) {
        if (groupDTO != null) {
            if (groupRepository.getGroupByName(groupDTO.getName()) != null) {
                return null;
            }
            Group group = new Group();
            group.setName(groupDTO.getName());
            group.setFaculty(facultyService.getFacultyById(groupDTO.getFacultyId()));
            saveGroup(group);
            return group;
        }
        return null;
    }

    @Transactional
    @Override
    public Group updateGroup(GroupDTO groupDTO) {
        Group group = groupRepository.getById(groupDTO.getId());
        if (group != null) {
            if (!groupDTO.getName().equals(group.getName())) {
                group.setName(groupDTO.getName());
                return group;
            }
        }
        return null;
    }

    @Override
    public List<Group> getGroupByFacultyId(Integer facultyId) {
        if (facultyId != null) {
            return groupRepository.getGroupByFacultyId(facultyId);
        }
        return null;
    }

    @Override
    public List<GroupDTO> frontGetAllGroups() {
        List<Group> groupList = getAllGroups();
        return groupList.stream().map(GroupDTO::new).collect(Collectors.toList());
    }

    @Override
    public GroupDTO frontGetGroupById(Integer id) {
        if (id != null) {
            return new GroupDTO(getGroupById(id));
        }
        return null;
    }

    @Transactional
    @Override
    public ResponseEntity<Object> frontDeleteById(Integer id) {
        if (id != null) {
            deleteGroup(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Transactional
    @Override
    public ResponseEntity<Object> frontAddGroup(GroupDTO groupDTO) {
        if (groupDTO != null) {
            addGroup(groupDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Transactional
    @Override
    public ResponseEntity<Object> frontUpdateGroup(GroupDTO groupDTO) {
        if (groupDTO != null) {
            updateGroup(groupDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<List<GroupDTO>> frontGetGroupByFacultyId(Integer facultyId) {
        if (facultyId != null) {
            List<GroupDTO> list = getGroupByFacultyId(facultyId).stream().map(GroupDTO::new).collect(Collectors.toList());
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
        return null;
    }
}
