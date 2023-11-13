package com.bellasolutions.petclinic.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bellasolutions.petclinic.entity.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

	@Query("select c from Client c where c.email = :clientemail")
	Optional<Client> findByEmail(@Param("clientemail") String email);
	
//	@Query("select c from Client c where c.pets.ownerId = c.id")
//	List<Client> getAllClientsWithPets();
	
}
