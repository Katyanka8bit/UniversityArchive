package com.katyanka8bit.universitytable.web.restcontr;

import com.katyanka8bit.universitytable.repository.UserRepository;
import com.katyanka8bit.universitytable.service.interf.UserService;
import com.katyanka8bit.universitytable.web.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserContr {
    private final UserService userService;
    private final UserRepository userRepository;

    @Autowired
    public UserContr(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @RequestMapping("/newuser")
    public String showNewUserForm(Model model) {
        UserDTO userDTO = new UserDTO();

        model.addAttribute("userDTO", userDTO);

        return "new_user";
    }

    @RequestMapping(value = "/saveuser", method = RequestMethod.POST)
    public String saveUser(@ModelAttribute("userDTO") UserDTO userDTO) {
        userService.frontAddUser(userDTO);

        return "redirect:/";
    }

    @GetMapping("/user-update/{email}")
    public String updateUserForm(@PathVariable("email") String email, Model model) {
        UserDTO userDTO = userService.frontLoadUserByUsername(email);
        model.addAttribute("userDTO", userDTO);
        return "user-update";
    }

    @RequestMapping(value = "/user-update", method = RequestMethod.POST)
    public String updateUser(UserDTO userDTO) {
        userService.updateUser(userDTO);
        return "redirect:/";
    }


    @RequestMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable(name = "id") Integer id) {
        userService.deleteUser(id);
        return "redirect:/";
    }
}
