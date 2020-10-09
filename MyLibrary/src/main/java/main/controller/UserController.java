package main.controller;

import main.model.Role;
import main.model.User;
import main.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@Controller
@RequestMapping("/user")
@PreAuthorize("hasAuthority('USER')")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String userList(Model model){
        model.addAttribute("users", userRepository.findAll());
        return "userList";
    }

    @GetMapping("{user}")
    public String userEditForm(@PathVariable User user, Model model){
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
//        if(USER != null || ADMIN != null){
//            user.getRoles().clear();
//            if(USER){
//                user.setRoles(Collections.singleton(Role.USER));
//            }
//            if(ADMIN){
//                user.setRoles(Collections.singleton(Role.ADMIN));
//            }
//        }
        userRepository.save(user);
        return "redirect:/user";
    }
}
