package com.rmit.sept.msregistration.service;

import com.rmit.sept.msregistration.exception.UserIdException;
import com.rmit.sept.msregistration.model.MedicalRecord;
import com.rmit.sept.msregistration.model.Patient;
import com.rmit.sept.msregistration.model.User;
import com.rmit.sept.msregistration.repository.MedicalRepository;
import com.rmit.sept.msregistration.repository.PatientRepository;
import com.rmit.sept.msregistration.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class PatientService implements UserDetailsService {


    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private MedicalRepository medicalRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;

    @Value("${jwt.role.prefix}")
    private String rolePrefix;

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if(user == null){
            throw new UsernameNotFoundException("Invalid request, please check your username or password");
        }
        log.info("Capture the username: {} and authority: {}", user.getEmail(), getAuthorities(user));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), getAuthorities(user));
    }

    public Collection<? extends GrantedAuthority> getAuthorities(User user) {
        List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();

        list.add(new SimpleGrantedAuthority(rolePrefix + user.getRole()));
        log.info("Capture the user role: {} been passed through user service", user.getRole());
        return list;
    }

    public User saveNewPatientDetails(User userInfo) {
        log.info("Saving new patient with userInfo={}", userInfo.toString());
        Patient patientInfo = new Patient(userInfo);
        patientInfo.setPassword(bcryptEncoder.encode(userInfo.getPassword()));
        User userDetails = new User(patientRepository.save(patientInfo));
        return userDetails;

    }

    public Patient updateExistingPatientDetails(Patient newUserDetails, Integer patientId) {
        log.info("Fetching details for update existing user method with patientId={}", patientId);

        Optional<Patient> userInfo = patientRepository.findById(patientId);

        if (userInfo.isPresent()) {
            userInfo.get().setFirstName(newUserDetails.getFirstName());
            userInfo.get().setLastName(newUserDetails.getLastName());
            userInfo.get().setDoB(newUserDetails.getDoB());
            userInfo.get().setEmail(newUserDetails.getEmail());
            userInfo.get().setAddress(newUserDetails.getAddress());
            userInfo.get().setPassword(newUserDetails.getPassword());
            userInfo.get().setMobileNumber(userInfo.get().getMobileNumber());

            log.info("Fetching old user details={} from database", userInfo);
            log.info("Updating new user details={}", newUserDetails);

            return patientRepository.save(userInfo.get());
        }
        throw new UserIdException("user Id is not found in the database", patientId);
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

    public void deleteExistingUserDetails(Integer patientId) {
        patientRepository.deleteById(patientId);
    }
    public MedicalRecord saveNewMedicalInfo(MedicalRecord medicalInfo, Integer patientId) {

        Optional<Patient> userDetails = patientRepository.findById(patientId);
        if(!userDetails.isPresent()){
            throw new UserIdException("user Id is not found in the database", patientId);
        }
        return medicalRepository.save(medicalInfo);
    }
}
