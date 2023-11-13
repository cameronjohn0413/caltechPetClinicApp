package com.bellasolutions.petclinic.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bellasolutions.petclinic.entity.Pet;
import com.bellasolutions.petclinic.repository.ClientRepository;
import com.bellasolutions.petclinic.repository.PetRepository;

@Service
public class PetService {
	
	@Autowired
	PetRepository petRep;
	
	@Autowired
	ClientRepository clientRep;
	
	// Create New Pet: 1.Check if Owner Exists. 2.Check If Owner Already Has Pet with this name. 
	// 3.If Owner Exists & the Owner Does Not Already Have a Pet with this name then Create New.
	@Transactional
	public List<Pet> newPet(List<Pet> pets) {
		/*		Create Pet One at a time in a for loop
		Set<Pet> returnPets = new HashSet<>();
		for (Pet p: pets) {
			returnPets.add(petRep.save(p));
			System.out.println("Pet Created In For Loop");
		}
		*/
		// Create all Pets in Set at once
		pets.addAll(petRep.saveAllAndFlush(pets));
		return pets;
		
		/*
		Client petOwner = pet.getOwnerId();
		Optional<Client> result = clientRep.findById(petOwner.getId());
		if (result.isEmpty()) {
			return "Owner Not Found. All Pets Must Have an Owner";
		} else {
			Client resultClient = result.get();
			Set<Pet> ownerPets = resultClient.getPets();
			Iterator<Pet> petIterator = ownerPets.iterator();
			while (petIterator.hasNext()) {
				Pet op = petIterator.next();
				if (op.getName().equals(pet.getName())) {
					return "Pet Name Already Exists for this Owner. Pet Names Must Be Unique Per Owner";
				} 
			}
			petRep.save(pet);
			return pet.getName() + " Successfully Added";
		}
		*/
	}
	
	// Update Pet
	public String updatePet(List<Pet> pets) {
		List<Pet> result = petRep.findAll();
		if (result.isEmpty()) {
			return "No Pets Found";
		} else {
		for (Pet p : pets) {
			Optional<Pet> dbPet = petRep.findById(p.getId());
			Pet updatePet = dbPet.get();
			updatePet.setName(p.getName());
			updatePet.setAge(p.getAge());
			updatePet.setWeight(p.getWeight());
			updatePet.setAnimalType(p.getAnimalType());
			updatePet.setClient(p.getClient());
			petRep.save(updatePet);
		}
		return "All Pets Updated";
		}
	}
	
	// Delete Pet By ID
	public String deletePet(int pid) {
		Optional<Pet> result = petRep.findById(pid);
		if (result.isEmpty()) {
			return "Delete Failed. Pet Not Found";
		} else {
			petRep.deleteById(pid);
			return "Pet Successfully Deleted";
		}
	}
	
	// Get All Pets
	@Transactional
	public List<Pet> getAllPets() {
		return petRep.findAll();
	}
	
	// Get Pet By Id
	public Pet getPetById(int pid) {
		Optional<Pet> result = petRep.findById(pid);
		if (result.isEmpty()) {
			return null;
		} else {
			return result.get();
		}
	}
	
	// Get Pets By Owner
	public List<Pet> getPetsByOwner(int cid) {
		List<Pet> result = petRep.getPetsByOwner(cid);
		if (result.isEmpty()) {
			return null;
		} else {
			return result;
		}
	}

}
