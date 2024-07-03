package org.example.controllers;

import org.example.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @Autowired
    private UserService userService;
    @GetMapping({"/home", "/"})
    public String home(){
        if(userService.getCurrentUser().getRoles().stream().anyMatch(role -> role.getName().equals("ADMIN")))
            return "adminpanel.html";
        else
            return "home.html";
    }
}
