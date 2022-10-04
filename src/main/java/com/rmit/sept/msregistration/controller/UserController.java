package com.rmit.sept.msregistration.controller;

import com.rmit.sept.msregistration.model.JwtToken;
import com.rmit.sept.msregistration.model.MedicalRecord;
import com.rmit.sept.msregistration.model.User;
import com.rmit.sept.msregistration.model.UserLogin;
import com.rmit.sept.msregistration.security.JwtTokenGenerator;
import com.rmit.sept.msregistration.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path="/api/v1") // This means URL's start with /register (after Application path)
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenGenerator jwtTokenUtil;

    @PostMapping(value = "/authenticate")
    public ResponseEntity<?> generateToken(@RequestBody UserLogin userLoginRequest) throws AuthenticationException {

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userLoginRequest.getUsername(),
                        userLoginRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = jwtTokenUtil.generateToken(authentication);
        return ResponseEntity.ok(new JwtToken(token));
    }

    @PostMapping(path="/register") // Map ONLY POST Requests
    public ResponseEntity<User> registerNewUser(@RequestBody User userDetails) {
        return new ResponseEntity<>(userService.saveNewUserDetails(userDetails), HttpStatus.CREATED);
    }

    @GetMapping(path="/get-user/{userId}")
    public Optional<User> getExistingUserDetails(@PathVariable Integer userId) {
        return userService.getExistingUserDetails(userId);
    }

    @PutMapping(path="/update-user/{userId}")
    public ResponseEntity<User> updateExistingUserDetails(@RequestBody User newUserDetails, @PathVariable Integer userId) {
        return new ResponseEntity<>(userService.updateExistingUserDetails(newUserDetails, userId), HttpStatus.OK);
    }

    @DeleteMapping(path="/delete-user/{userId}")
    public void deleteExistingUserDetails(@PathVariable Integer userId) {
        userService.deleteExistingUserDetails(userId);
    }

    @PostMapping(path="/medical-record/{userId}") // Map ONLY POST Requests
    public ResponseEntity<MedicalRecord> addMedicalInfo(@RequestBody MedicalRecord medicalInfo, @PathVariable Integer userId) {
        return new ResponseEntity<>(userService.saveNewMedicalInfo(medicalInfo, userId), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('PATIENT')")
    @GetMapping(value="/patient")
    public String patientEndpoint(){
        return "Only patients can access this endpoint";
    }

    @PreAuthorize("hasRole('DOCTOR')")
    @GetMapping(value="/doctor")
    public String doctorEndpoint(){
        return "Only doctors can access this endpoint";
    }
}
