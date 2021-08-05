package com.katyanka8bit.universitytable.service.interf;

import com.katyanka8bit.universitytable.model.Group;
import com.katyanka8bit.universitytable.web.dto.GroupDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface GroupService {
    //INNERS METHODS
    Group saveGroup(Group group);

    void deleteGroup(Integer id);

    List<Group> getAllGroups();

    Group getGroupById(Integer id);

    Group addGroup(GroupDTO groupDTO);

    Group updateGroup(GroupDTO groupDTO);

    List<Group> getGroupByFacultyId(Integer facultyId);

    //FRONTS METHODS
    List<GroupDTO> frontGetAllGroups();


    GroupDTO frontGetGroupById(Integer id);

    ResponseEntity<Object> frontDeleteById(Integer id);

    ResponseEntity<Object> frontAddGroup(GroupDTO groupDTO);

    ResponseEntity<Object> frontUpdateGroup(GroupDTO groupDTO);

    ResponseEntity<List<GroupDTO>> frontGetGroupByFacultyId(Integer facultyId);

}
