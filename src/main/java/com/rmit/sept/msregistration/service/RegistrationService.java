package com.rmit.sept.msregistration.service;

import com.rmit.sept.msregistration.constants.AppRole;
import com.rmit.sept.msregistration.exception.UserIdException;
import com.rmit.sept.msregistration.model.MedicalRecord;
import com.rmit.sept.msregistration.model.User;
import com.rmit.sept.msregistration.repository.MedicalRepository;
import com.rmit.sept.msregistration.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegistrationService {

    Logger log = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MedicalRepository medicalRepository;

    public User saveNewUserDetails(User userInfo) {

        log.info("Saving new user with userInfo={}", userInfo.toString());
        return userRepository.save(userInfo);

    }

    public Optional<User> getExistingUserDetails(Integer userId) {
        log.info("Fetching details for user with userId={}", userId);
        Optional<User> userDetails = userRepository.findById(userId);

        log.error("Fetching details for user with userDetails{}", userDetails.toString());
        if(userDetails.isEmpty()){
            throw new UserIdException("user Id is not found in the database", userId);
        }
        return userDetails;
    }

    public User updateExistingUserDetails(User newUserDetails, Integer userId) {
        log.info("Fetching details for update existing user method with userId={}", userId);

        Optional<User> userInfo = userRepository.findById(userId);

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

            return userRepository.save(userInfo.get());
        }
        throw new UserIdException("user Id is not found in the database", userId);
    }

    public void deleteExistingUserDetails(Integer userId) {
       userRepository.deleteById(userId);
    }
    public MedicalRecord saveNewMedicalInfo(MedicalRecord medicalInfo, Integer userId) {

        Optional<User> userDetails = userRepository.findById(userId);
        if(!userDetails.isPresent()){
            throw new UserIdException("user Id is not found in the database", userId);
        }
        return medicalRepository.save(medicalInfo);
    }
}
