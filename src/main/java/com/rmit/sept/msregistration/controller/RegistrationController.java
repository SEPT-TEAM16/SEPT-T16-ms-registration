package com.rmit.sept.msregistration.controller;

import com.rmit.sept.msregistration.model.MedicalRecord;
import com.rmit.sept.msregistration.model.User;
import com.rmit.sept.msregistration.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping(path="/api/v1") // This means URL's start with /register (after Application path)
@Validated
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @PostMapping(path="/register") // Map ONLY POST Requests
    public ResponseEntity<User> registerNewUser(@RequestBody User userDetails) {
        return new ResponseEntity<>(registrationService.saveNewUserDetails(userDetails), HttpStatus.CREATED);
    }

    @GetMapping(path="/get-user/{userId}")
    public Optional<User> getExistingUserDetails(@PathVariable Integer userId) {
        return registrationService.getExistingUserDetails(userId);
    }

    @PutMapping(path="/update-user/{userId}")
    public ResponseEntity<User> updateExistingUserDetails(@RequestBody User newUserDetails, @PathVariable Integer userId) {
        return new ResponseEntity<>(registrationService.updateExistingUserDetails(newUserDetails, userId), HttpStatus.OK);
    }

    @DeleteMapping(path="/delete-user/{userId}")
    public void deleteExistingUserDetails(@PathVariable Integer userId) {
        registrationService.deleteExistingUserDetails(userId);
    }

    @PostMapping(path="/medical-record/{userId}") // Map ONLY POST Requests
    public ResponseEntity<MedicalRecord> addMedicalInfo(@RequestBody MedicalRecord medicalInfo, @PathVariable Integer userId) {
        return new ResponseEntity<>(registrationService.saveNewMedicalInfo(medicalInfo, userId), HttpStatus.CREATED);
    }
}
