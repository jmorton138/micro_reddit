package com.microreddit.MicroReddit.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserRegistrationController {
    @Autowired
    private UserService userService;
    @GetMapping("/user-registration")
    public String showUserRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "user-registration";
    }

    @PostMapping("/user-registration")
    public String registerNewUser(@ModelAttribute("user") User user, Model model) {
        userService.addUser(user);
        return "login";
    }
}
