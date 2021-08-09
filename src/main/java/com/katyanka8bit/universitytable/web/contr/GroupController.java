package com.katyanka8bit.universitytable.web.contr;

import com.katyanka8bit.universitytable.service.interf.GroupService;
import com.katyanka8bit.universitytable.web.dto.GroupDTO;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/rest/groups/")
public class GroupController {
    private final GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }


    @GetMapping
    public List<GroupDTO> getAllGroups() {
        return groupService.frontGetAllGroups();
    }

    @GetMapping("{id}")
    public GroupDTO getGroupById(@PathVariable Integer id) {
        return groupService.frontGetGroupById(id);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteGroupById(@PathVariable Integer id) {
        return groupService.frontDeleteById(id);
    }

    @PostMapping
    public ResponseEntity<Object> addGroup(@RequestBody GroupDTO groupDTO) {
        return groupService.frontAddGroup(groupDTO);
    }

    @PutMapping
    public ResponseEntity<Object> updateGroup(@RequestBody GroupDTO groupDTO) {
        return groupService.frontUpdateGroup(groupDTO);
    }

    @GetMapping("getbyfacultyid/{id}")
    public ResponseEntity<List<GroupDTO>> getByFacultyId(@PathVariable Integer id) {
        return groupService.frontGetGroupByFacultyId(id);
    }
}
