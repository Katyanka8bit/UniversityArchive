package com.katyanka8bit.universitytable.web.restcontr;

import com.katyanka8bit.universitytable.service.interf.GroupService;
import com.katyanka8bit.universitytable.web.dto.GroupDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class GroupContr {

    private final GroupService groupService;

    @Autowired
    public GroupContr(GroupService groupService) {
        this.groupService = groupService;
    }

    @RequestMapping("/newgroup")
    public String showNewGroupForm(Model model) {
        GroupDTO groupDTO = new GroupDTO();

        model.addAttribute("groupDTO", groupDTO);

        return "new_group";
    }

    @RequestMapping(value = "/savegroup", method = RequestMethod.POST)
    public String saveGroup(@ModelAttribute("groupDTO") GroupDTO groupDTO) {
        groupService.frontAddGroup(groupDTO);

        return "redirect:/";
    }

    @GetMapping("/group-update/{id}")
    public String updateGroupForm(@PathVariable("id") Integer id, Model model) {
        GroupDTO groupDTO = groupService.frontGetGroupById(id);
        model.addAttribute("groupDTO", groupDTO);
        return "group-update";
    }

    @RequestMapping(value = "/group-update", method = RequestMethod.POST)
    public String updateGroup(GroupDTO groupDTO) {
        groupService.updateGroup(groupDTO);
        return "redirect:/";
    }


    @RequestMapping("/deleteGroup/{id}")
    public String deleteGroup(@PathVariable(name = "id") Integer id) {
        groupService.deleteGroup(id);
        return "redirect:/";
    }
}
