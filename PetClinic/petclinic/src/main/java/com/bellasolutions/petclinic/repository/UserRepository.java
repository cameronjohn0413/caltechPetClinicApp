package com.bellasolutions.petclinic.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bellasolutions.petclinic.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	@Query("select u from User u where u.username = :userun")
	Optional<User> findByUsername(@Param("userun") String username);
	
	// Users Can Only Update their Own Password
	@Query("select u from User u where u.username = :username and u.username = :owner")
	Optional<User> findByUsernameAndOwner(String username, String owner);
}
