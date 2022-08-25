package com.rmit.sept.msregistration.controller;

import com.rmit.sept.msregistration.model.User;
import com.rmit.sept.msregistration.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class LoginController {

    @Autowired
    LoginService loginService;

    @GetMapping("/login")
    public User loginExistingUser (
            @RequestParam String email){
        return loginService.loginExistingUser(email);
    }
}
