package com.rmit.sept.msregistration.service;

import com.rmit.sept.msregistration.exception.UserIdException;
import com.rmit.sept.msregistration.model.User;
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

    public User saveNewUserDetails(User userInfo) {
        log.info("Saving new user with userInfo={}", userInfo.toString());
        return userRepository.save(userInfo);

    }

    public Optional<User> getExistingUserDetails(Integer userId) {
        log.info("Fetching details for user with userId={}", userId);
        Optional<User> userDetails = userRepository.findById(userId);

        log.error("Fetching details for user with userDetails{}", userDetails.toString());
        if(!userDetails.isPresent()){
            throw new UserIdException("user Id is not found in the database", userId);
        }
        return userDetails;
    }
}
