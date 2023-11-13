package com.bellasolutions.petclinic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bellasolutions.petclinic.entity.Pet;

import ch.qos.logback.core.net.server.Client;

@Repository
public interface PetRepository extends JpaRepository<Pet, Integer> {
	
	@Query("select p from Pet p where p.client = :cid")
	List<Pet> getPetsByOwner(@Param("cid") int cid);
	
	

}
