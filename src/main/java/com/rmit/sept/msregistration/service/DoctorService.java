package com.rmit.sept.msregistration.service;

import com.rmit.sept.msregistration.exception.UserIdException;
import com.rmit.sept.msregistration.model.Admin;
import com.rmit.sept.msregistration.model.Doctor;
import com.rmit.sept.msregistration.model.User;
import com.rmit.sept.msregistration.repository.AdminRepository;
import com.rmit.sept.msregistration.repository.DoctorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class DoctorService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;

    public User saveNewDoctorDetails(User userInfo) {
        log.info("Saving new doctor with userInfo={}", userInfo.toString());
        Doctor doctorInfo = new Doctor(userInfo);
        doctorInfo.setPassword(bcryptEncoder.encode(userInfo.getPassword()));
        User userDetails = new User(doctorRepository.save(doctorInfo));
        return userDetails;

    }

    public User saveNewAdminDetails(User userInfo) {
        log.info("Saving new admin with userInfo={}", userInfo.toString());
        Admin adminInfo = new Admin(userInfo);
        adminInfo.setPassword(bcryptEncoder.encode(userInfo.getPassword()));
        User userDetails = new User(adminRepository.save(adminInfo));
        return userDetails;

    }

    public Optional<Admin> getExistingAdminDetails(Integer userId) {
        log.info("Fetching details for admin with userId={}", userId);
        Optional<Admin> adminDetails = adminRepository.findById(userId);

        log.error("Fetching details for admin with userDetails{}", adminDetails.toString());
        if (!adminDetails.isPresent()) {
            throw new UserIdException("admin user Id is not found in the database", userId);
        }
        return adminDetails;
    }

    public Optional<Doctor> getExistingDoctorDetails(Integer userId) {
        log.info("Fetching details for doctor with userId={}", userId);
        Optional<Doctor> doctorDetails = doctorRepository.findById(userId);

        log.error("Fetching details for doctor with userDetails{}", doctorDetails.toString());
        if (!doctorDetails.isPresent()) {
            throw new UserIdException("doctor user Id is not found in the database", userId);
        }
        return doctorDetails;
    }

    public Integer getExistingDoctorDetailsByEmail(String email) {
        log.info("Fetching details for doctor with userId={}", email);
        Optional<Doctor> doctorDetails = doctorRepository.findByEmail(email);
        return doctorDetails.get().getUserId();
    }
}
