package com.rmit.sept.msregistration.repository;

import com.rmit.sept.msregistration.model.Doctor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends CrudRepository<Doctor, Integer> {

}
