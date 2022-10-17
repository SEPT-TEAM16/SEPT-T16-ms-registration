package com.rmit.sept.msregistration.repository;


import com.rmit.sept.msregistration.model.Patient;
import com.rmit.sept.msregistration.model.User;
import org.hibernate.annotations.Subselect;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM users")
    Collection<User> findAllUsers();

    User findByEmail(String email);
}
