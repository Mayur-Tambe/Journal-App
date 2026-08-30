package com.mayur.journalApp.controller;

import com.mayur.journalApp.entity.User;
import com.mayur.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicCntroller {
    @Autowired
    private UserService userService;

    @GetMapping("/health-check")
    public String healthCheck(){
        return "OK!";
    }

    @PostMapping("/create-user")
    public void createUser(@RequestBody User userEntry){
        userService.saveNewUser(userEntry);
    }
}
