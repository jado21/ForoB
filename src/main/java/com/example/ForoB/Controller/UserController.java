package com.example.ForoB.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class UserController {

    @GetMapping("/sec")
    public String LoginWelcome(){
        return "Welcome to the werification page";
    }

}
