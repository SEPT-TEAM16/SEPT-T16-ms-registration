package com.rmit.sept.msregistration.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "user_id")
    private Integer userId;

    @Column(name="email", nullable=false, unique=true)
    private String email;

    @Column(name="first_name", nullable=false)
    private String firstName;

    @Column(name="last_name", nullable=false)
    private String lastName;

    @Column(name = "password", nullable=false)
    private String password;

    @Column(name="dob", nullable=false)
    private Date DoB;

    @Column(name="account_active", nullable=false)
    private boolean accountActive;

    @Column(name="address")
    private String address;

    @Column(name="mobile_number", unique=true)
    private String mobileNumber;

    @Column(name="role", nullable=false)
    private String role;
}
