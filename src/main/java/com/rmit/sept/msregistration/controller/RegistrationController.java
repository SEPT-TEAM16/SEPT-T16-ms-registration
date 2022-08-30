package com.rmit.sept.msregistration.controller;

import com.rmit.sept.msregistration.model.User;
import com.rmit.sept.msregistration.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping(path="/api/v1") // This means URL's start with /register (after Application path)
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @PostMapping(path="/register") // Map ONLY POST Requests
    public ResponseEntity<User> registerNewUser(@RequestBody User userDetails) {
        return new ResponseEntity<>(registrationService.saveNewUserDetails(userDetails), HttpStatus.OK);
    }

    @GetMapping(path="/get-user/{userId}")
    public Optional<User> getExistingUserDetails(@PathVariable Integer userId) {
        return registrationService.getExistingUserDetails(userId);
    }
}
