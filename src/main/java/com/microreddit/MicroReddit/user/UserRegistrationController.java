package com.microreddit.MicroReddit.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

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
        boolean passwordConfirmed = userService.isPasswordConfirmed(user);
        if (!passwordConfirmed) {
            model.addAttribute("message", "Passwords don't match");
            return "user-registration";
        }
        boolean userExists = userService.isUserPresent(user);
        if (userExists) {
            model.addAttribute("message", "User already exists");
            return "user-registration";
        }
        userService.addUser(user);
        return "redirect:/login";
    }
}
