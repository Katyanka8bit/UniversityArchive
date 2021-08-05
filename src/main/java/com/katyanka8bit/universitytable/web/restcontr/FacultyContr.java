package com.katyanka8bit.universitytable.web.restcontr;

import com.katyanka8bit.universitytable.service.interf.FacultyService;
import com.katyanka8bit.universitytable.web.dto.FacultyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class FacultyContr {

    private final FacultyService facultyService;

    @Autowired
    public FacultyContr(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @RequestMapping("/newfaculty")
    public String showNewFacultyForm(Model model) {
        FacultyDTO facultyDTO = new FacultyDTO();
        model.addAttribute("facultyDTO", facultyDTO);

        return "new_faculty";
    }

    @RequestMapping(value = "/savefaculty", method = RequestMethod.POST)
    public String saveFaculty(@ModelAttribute("facultyDTO") FacultyDTO facultyDTO) {
        facultyService.frontAddFaculty(facultyDTO);

        return "redirect:/";
    }

    @GetMapping("/faculty-update/{id}")
    public String updateFacultyForm(@PathVariable("id") Integer id, Model model) {
        FacultyDTO facultyDTO = facultyService.frontGetFacultyById(id);
        model.addAttribute("facultyDTO", facultyDTO);
        return "faculty-update";
    }

    @RequestMapping(value = "/faculty-update", method = RequestMethod.POST)
    public String updateFaculty(FacultyDTO facultyDTO) {
        facultyService.updateFaculty(facultyDTO);
        return "redirect:/";
    }


    @RequestMapping("/deleteFaculty/{id}")
    public String deleteFaculty(@PathVariable(name = "id") Integer id) {
        facultyService.deleteFaculty(id);
        return "redirect:/";
    }
}
