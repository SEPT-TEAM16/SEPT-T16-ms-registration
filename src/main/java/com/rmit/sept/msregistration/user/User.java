package com.rmit.sept.msregistration.user;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private String id;

    private String password;

    private String role;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private Date dob;

    @Column(name = "account_active")
    private boolean accountActive;

    private String address;

    @Column(name = "mobile_number")
    private String mobileNumber;




}
