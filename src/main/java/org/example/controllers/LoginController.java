package org.example.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
class LoginController {
    @GetMapping("/login")
    public String login(
            @RequestParam (value = "error", required = false) String error,
            @RequestParam (value = "logout", required = false) String logout,
            Model model
    ) {
        if (error != null)
            model.addAttribute("errorMessage", "Nieprawidłowa nazwa użytkownika bądź haslo");

        if (logout != null)
            model.addAttribute("logoutMessage", "Pomylśnie wylogowano");

        return "login";
    }
}