package com.rmit.sept.msregistration.controller;

import com.rmit.sept.msregistration.model.Admin;
import com.rmit.sept.msregistration.model.Doctor;
import com.rmit.sept.msregistration.service.DoctorService;
import com.rmit.sept.msregistration.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path="/api/v1")
@PreAuthorize("hasRole('DOCTOR')")
@Validated
public class DoctorDashboardController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private PatientService patientService;


    @GetMapping(path="/get-doctor/{userId}")
    public Optional<Doctor> getExistingDoctorDetails(@PathVariable Integer userId) {
        return doctorService.getExistingDoctorDetails(userId);
    }

    @GetMapping(path="/get-doctor/{email}")
    public Integer getExistingDoctorDetailsByEmail(@PathVariable String email) {
        return doctorService.getExistingDoctorDetailsByEmail(email);
    }

    @GetMapping(path="/get-admin/{userId}")
    public Optional<Admin> getExistingAdminDetails(@PathVariable Integer userId) {
        return doctorService.getExistingAdminDetails(userId);
    }

    @DeleteMapping(path="/delete-user/{patientId}")
    public void deleteExistingUserDetails(@PathVariable Integer patientId) {
        patientService.deleteExistingUserDetails(patientId);
    }
}