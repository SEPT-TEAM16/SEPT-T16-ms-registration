package com.rmit.sept.msregistration.service;

import com.rmit.sept.msregistration.exception.UserIdException;
import com.rmit.sept.msregistration.model.Admin;
import com.rmit.sept.msregistration.model.Doctor;
import com.rmit.sept.msregistration.model.Patient;
import com.rmit.sept.msregistration.model.User;
import com.rmit.sept.msregistration.repository.AdminRepository;
import com.rmit.sept.msregistration.repository.DoctorRepository;
import com.rmit.sept.msregistration.repository.PatientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegistrationService {

    Logger log = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    /*
    public User saveNewUserDetails(User userInfo) {
        log.info("Saving new user with userInfo={}", userInfo.toString());
        return userRepository.save(userInfo);

    }

     */

    public User saveNewDoctorDetails(User userInfo) {
        log.info("Saving new doctor with userInfo={}", userInfo.toString());
        Doctor doctorInfo = new Doctor(userInfo);
        User userDetails = new User(doctorRepository.save(doctorInfo));
        return userDetails;

    }

    public User saveNewAdminDetails(User userInfo) {
        log.info("Saving new admin with userInfo={}", userInfo.toString());
        Admin adminInfo = new Admin(userInfo);
        User userDetails = new User(adminRepository.save(adminInfo));
        return userDetails;

    }

    public User saveNewPatientDetails(User userInfo) {
        log.info("Saving new patient with userInfo={}", userInfo.toString());
        Patient patientInfo = new Patient(userInfo);

        User userDetails = new User(patientRepository.save(patientInfo));
        return userDetails;

    }

    /*
    public Optional<User> getExistingUserDetails(Integer userId) {
        log.info("Fetching details for user with userId={}", userId);
        Optional<User> userDetails = userRepository.findById(userId);

        log.error("Fetching details for user with userDetails{}", userDetails.toString());
        if(!userDetails.isPresent()){
            throw new UserIdException("user Id is not found in the database", userId);
        }
        return userDetails;
    }*/

    public Optional<Admin> getExistingAdminDetails(Integer userId) {
        log.info("Fetching details for admin with userId={}", userId);
        Optional<Admin> adminDetails = adminRepository.findById(userId);

        log.error("Fetching details for admin with userDetails{}", adminDetails.toString());
        if(!adminDetails.isPresent()){
            throw new UserIdException("admin user Id is not found in the database", userId);
        }
        return adminDetails;
    }

    public Optional<Doctor> getExistingDoctorDetails(Integer userId) {
        log.info("Fetching details for doctor with userId={}", userId);
        Optional<Doctor> doctorDetails = doctorRepository.findById(userId);

        log.error("Fetching details for doctor with userDetails{}", doctorDetails.toString());
        if(!doctorDetails.isPresent()){
            throw new UserIdException("doctor user Id is not found in the database", userId);
        }
        return doctorDetails;
    }

    public Optional<Patient> getExistingPatientDetails(Integer userId) {
        log.info("Fetching details for patient with userId={}", userId);
        Optional<Patient> patientDetails = patientRepository.findById(userId);

        log.error("Fetching details for patient with userDetails{}", patientDetails.toString());
        if(!patientDetails.isPresent()){
            throw new UserIdException("patient user Id is not found in the database", userId);
        }
        return patientDetails;
    }
}
