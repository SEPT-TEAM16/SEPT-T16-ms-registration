package com.rmit.sept.msregistration.controller;

import com.rmit.sept.msregistration.model.*;
import com.rmit.sept.msregistration.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path="/api/v1")
@PreAuthorize("hasRole('PATIENT')")
@Validated
public class PatientDashboardController {

    @Autowired
    private PatientService patientService;

    @PutMapping(path="/update-user/{patientId}")
    public ResponseEntity<Patient> updateExistingUserDetails(@RequestBody Patient newPatientDetails, @PathVariable Integer patientId) {
        return new ResponseEntity<>(patientService.updateExistingPatientDetails(newPatientDetails, patientId), HttpStatus.OK);
    }

    @PostMapping(path="/medical-record/{patientId}") // Map ONLY POST Requests
    public ResponseEntity<MedicalRecord> addMedicalInfo(@RequestBody MedicalRecord medicalInfo, @PathVariable Integer patientId) {
        return new ResponseEntity<>(patientService.saveNewMedicalInfo(medicalInfo, patientId), HttpStatus.CREATED);
    }

    @GetMapping(path="/get-patient/{email}")
    public Integer getExistingPatientDetailsByEmail(@PathVariable String email) {
        return patientService.getExistingPatientDetailsByEmail(email);
    }
}
