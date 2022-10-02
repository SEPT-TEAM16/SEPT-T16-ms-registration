package com.rmit.sept.msregistration.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "medical_record")
public class MedicalRecord {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "medical_id")
    private Integer medicalId;

    @Column(name = "medicare_number")
    private String medicareNo;

    @Column(name = "height")
    private String height;

    @Column(name = "weight")
    private String weight;

    @Column(name = "medical_history")
    private String medicalHistory;

    @Column(name = "gender")
    private String gender;
}