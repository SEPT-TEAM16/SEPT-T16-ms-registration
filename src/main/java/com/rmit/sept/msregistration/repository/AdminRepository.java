package com.rmit.sept.msregistration.repository;

import com.rmit.sept.msregistration.model.Admin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends CrudRepository<Admin, Integer> {
}
