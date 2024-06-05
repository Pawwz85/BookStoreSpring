package org.example.controllers;

import org.example.model.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminPanelController {

    @GetMapping("/admin/adminpanel")
    public String adminPanel(){
        return "adminpanel";
    }
}
