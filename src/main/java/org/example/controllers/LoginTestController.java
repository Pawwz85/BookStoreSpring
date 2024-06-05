package org.example.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.net.http.HttpResponse;

@Controller
public class LoginTestController {
    @GetMapping("/loginTest")
    public String test(){
        return "ok";
    }
}
