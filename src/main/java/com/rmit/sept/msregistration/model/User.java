package com.rmit.sept.msregistration.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "user_id")
    @Getter
    @Setter
    private Integer id;

    @Column(name = "first_name")
    @Getter
    @Setter
    private String firstName;

    @Column(name = "last_name")
    @Getter
    @Setter
    private String lastName;

    @Column(name = "email")
    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    private String password;

    @Getter
    @Setter
    private String role;

    @Getter
    @Setter
    private Date dob;

    @Column(name = "account_active")
    @Getter
    @Setter
    private boolean accountActive;

    @Getter
    @Setter
    private String address;

    @Column(name = "mobile_number")
    @Getter
    @Setter
    private String mobileNumber;

}
