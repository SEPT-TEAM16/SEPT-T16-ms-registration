package com.rmit.sept.msregistration.repository;

import com.rmit.sept.msregistration.model.MedicalRecord;
import org.springframework.data.repository.CrudRepository;

public interface MedicalRepository extends CrudRepository<MedicalRecord, Integer> {
}
