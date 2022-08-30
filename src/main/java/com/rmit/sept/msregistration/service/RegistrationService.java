package com.rmit.sept.msregistration.service;

import com.rmit.sept.msregistration.model.User;
import com.rmit.sept.msregistration.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegistrationService {

    @Autowired
    private UserRepository userRepository;

    public User saveNewUserDetails(User userInfo) {

        return userRepository.save(userInfo);

    }

    public Optional<User> getExistingUserDetails(String userId) {

        return userRepository.findById(userId);
    }
}
