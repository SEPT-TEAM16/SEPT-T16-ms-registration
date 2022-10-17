package com.rmit.sept.msregistration.repository;

import com.rmit.sept.msregistration.model.Patient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends CrudRepository<Patient, Integer> {
    Patient findByEmail (String email);
}
