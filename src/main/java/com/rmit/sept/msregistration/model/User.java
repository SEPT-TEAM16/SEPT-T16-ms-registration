package com.rmit.sept.msregistration.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rmit.sept.msregistration.constants.AppRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "user_id")
    private Integer userId;

    @Column(name="email")
    @NotBlank (message = "email is required")
    private String email;

    @NotBlank (message = "first name is required")
    @Pattern(regexp = "[a-zA-Z ]", message = "first name should only contain letters")
    @Column(name="first_name")
    private String firstName;

    @NotBlank (message = "first name is required")
    @Pattern(regexp = "[a-zA-Z ]", message = "last name should only contain letters")
    @Column(name="last_name")
    private String lastName;

    @NotBlank (message = "password is required")
    @Column(name = "password")
    private String password;

    @NotBlank (message = "date of birth is required")
    @Column(name="dob")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date DoB;

    @Column(name="account_active")
    private boolean accountActive;

    @Column(name="address")
    private String address;

    @NotBlank (message = "mobile number is required")
    @Column(name="mobile_number", unique=true)
    private String mobileNumber;

    @Column(name="role")
    @Enumerated(EnumType.STRING)
    private AppRole role;

}
