package com.rmit.sept.msregistration.controller;

import com.rmit.sept.msregistration.constants.AppRole;
import com.rmit.sept.msregistration.model.Admin;
import com.rmit.sept.msregistration.model.Doctor;
import com.rmit.sept.msregistration.model.Patient;
import com.rmit.sept.msregistration.model.User;
import com.rmit.sept.msregistration.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;


@RestController
@RequestMapping(path="/api/v1") // This means URL's start with /register (after Application path)
@Validated
public class RegistrationController {

    Logger log = LoggerFactory.getLogger(this.getClass().getName());
    @Autowired
    private RegistrationService registrationService;

    @PostMapping(path="/register") // Map ONLY POST Requests
    public ResponseEntity<User> registerNewUser(@RequestBody User userDetails) {
        log.info("new user with userInfo={}", userDetails.toString());
        if(userDetails.getRole().equals(AppRole.DOCTOR)){
            return new ResponseEntity<>(registrationService.saveNewDoctorDetails(userDetails), HttpStatus.OK);
        }
        else if(userDetails.getRole().equals(AppRole.PATIENT)){
            return new ResponseEntity<>(registrationService.saveNewPatientDetails(userDetails), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(registrationService.saveNewAdminDetails(userDetails), HttpStatus.OK);
        }
        //return new ResponseEntity<>(registrationService.saveNewDoctorDetails(userDetails), HttpStatus.OK);
    }

    /*
    @GetMapping(path="/get-user/{userId}")
    public Optional<User> getExistingUserDetails(@PathVariable Integer userId) {
        return registrationService.getExistingUserDetails(userId);
    }

     */
    @GetMapping(path="/get-doctor/{userId}")
    public Optional<Doctor> getExistingDoctorDetails(@PathVariable Integer userId) {
        return registrationService.getExistingDoctorDetails(userId);
    }

    @GetMapping(path="/get-admin/{userId}")
    public Optional<Admin> getExistingAdminDetails(@PathVariable Integer userId) {
        return registrationService.getExistingAdminDetails(userId);
    }

    @GetMapping(path="/get-patient/{userId}")
    public Optional<Patient> getExistingPatientDetails(@PathVariable Integer userId) {
        return registrationService.getExistingPatientDetails(userId);
    }
}
