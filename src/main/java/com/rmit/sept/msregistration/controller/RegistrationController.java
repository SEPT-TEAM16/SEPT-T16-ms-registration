package com.rmit.sept.msregistration.controller;

import com.rmit.sept.msregistration.constants.AppRole;
import com.rmit.sept.msregistration.model.JwtToken;
import com.rmit.sept.msregistration.model.User;
import com.rmit.sept.msregistration.model.UserLogin;
import com.rmit.sept.msregistration.security.JwtTokenGenerator;
import com.rmit.sept.msregistration.service.DoctorService;
import com.rmit.sept.msregistration.service.PatientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path="/api/v1")
@Validated
@Slf4j
public class RegistrationController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenGenerator jwtTokenUtil;


    @PostMapping(value = "/authenticate")
    public ResponseEntity<?> generateToken(@RequestBody UserLogin userLoginRequest) throws Exception {

        try {
            final Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userLoginRequest.getUsername(), userLoginRequest.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            final String token = jwtTokenUtil.generateToken(authentication);
            return ResponseEntity.ok(new JwtToken(token));
        }
        catch (BadCredentialsException e) {
            log.info("username: {}", userLoginRequest.getUsername());
            log.info("password: {}", userLoginRequest.getPassword());
            throw new Exception("Incorrect username or password", e);

        }
    }

    @PostMapping(path="/register") // Map ONLY POST Requests
    public ResponseEntity<User> registerNewUser(@RequestBody User userDetails) {
        log.info("new user with userInfo={}", userDetails.toString());
        if(userDetails.getRole().equals(AppRole.DOCTOR)){
            return new ResponseEntity<>(doctorService.saveNewDoctorDetails(userDetails), HttpStatus.OK);
        }
        else if(userDetails.getRole().equals(AppRole.PATIENT)){
            return new ResponseEntity<>(patientService.saveNewPatientDetails(userDetails), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(doctorService.saveNewAdminDetails(userDetails), HttpStatus.OK);
        }
    }
}
