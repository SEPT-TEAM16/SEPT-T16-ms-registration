package com.rmit.sept.msregistration.repository;

import com.rmit.sept.msregistration.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, String> {

   User findByEmail(String email);

}
