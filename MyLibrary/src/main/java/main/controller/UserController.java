package main.controller;

import main.model.Role;
import main.model.User;
import main.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String getUsersList(Model model){
        model.addAttribute("users", userRepository.findAll());
        return "usersList";
    }

    @GetMapping("/{userId}")
    public String userEditForm(@PathVariable long userId, Model model){
        User user = userRepository.findById(userId);
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        model.addAttribute("isUser", user.getRoles().contains(Role.USER));
        model.addAttribute("isAdmin", user.getRoles().contains(Role.ADMIN));
        return "userEdit";
    }

    @PostMapping
    public String userSave(
            @RequestParam String username,
            @RequestParam("id") User user,
            @RequestParam(required = false) Boolean USER,
            @RequestParam(required = false) Boolean ADMIN,
            Model model){
        user.setUsername(username);
        userRepository.save(user);
        return "redirect:/users";
    }
}
