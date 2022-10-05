package com.rmit.sept.msregistration.service;

import com.rmit.sept.msregistration.exception.UserIdException;
import com.rmit.sept.msregistration.model.MedicalRecord;
import com.rmit.sept.msregistration.model.User;
import com.rmit.sept.msregistration.repository.MedicalRepository;
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
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MedicalRepository medicalRepository;

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


    public User saveNewUserDetails(User userInfo) {
        userInfo.setPassword(bcryptEncoder.encode(userInfo.getPassword()));
        log.info("Saving new user with userInfo={}", userInfo.toString());
        return userRepository.save(userInfo);

    }

    public User getExistingUserDetails(String email) throws Exception {
        log.info("Fetching details for user with email={}", email);
        User userDetails = userRepository.findByEmail(email);
        log.error("Fetching details for user with userDetails{}", userDetails.toString());
//        if(userDetails.getEmail() != email){
//            log.info("user email:{}", userDetails.getEmail());
//            log.info("passed through email:{}", email);
//            throw new Exception("email is not found in the database");
//        }
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
