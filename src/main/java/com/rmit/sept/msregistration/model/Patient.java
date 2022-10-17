package com.rmit.sept.msregistration.model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.rmit.sept.msregistration.constants.AppRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "patients")
public class Patient {

    public Patient(User user){
        this.userId = user.getUserId();
        this.email = user.getEmail();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.password = user.getPassword();
        this.DoB = user.getDoB();
        this.accountActive = user.isAccountActive();
        this.address = user.getAddress();
        this.mobileNumber = user.getMobileNumber();
        this.role = AppRole.PATIENT;
    }

    @Id
    @GenericGenerator(name = "new_id", strategy = "increment")
    @GeneratedValue(generator = "new_id")
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
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date DoB;

    @Column(name="account_active", nullable=false)
    private boolean accountActive;

    @Column(name="address")
    private String address;

    @Column(name="mobile_number")
    private String mobileNumber;

    @Column(name="role", nullable=false)
    @Enumerated(EnumType.STRING)
    private AppRole role = AppRole.PATIENT;

}