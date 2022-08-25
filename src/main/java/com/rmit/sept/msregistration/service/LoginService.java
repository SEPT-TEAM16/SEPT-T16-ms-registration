package com.rmit.sept.msregistration.service;

import com.rmit.sept.msregistration.model.User;
import com.rmit.sept.msregistration.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    UserRepository userRepository;

    public User loginExistingUser(String email) {

        return userRepository.findByEmail(email);
    }
}
