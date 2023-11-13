package com.bellasolutions.petclinic.service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bellasolutions.petclinic.entity.Client;
import com.bellasolutions.petclinic.entity.Pet;
import com.bellasolutions.petclinic.repository.ClientRepository;
import com.bellasolutions.petclinic.repository.PetRepository;

@Service
public class ClientService {
	
	@Autowired
	ClientRepository clientRep;
	
	@Autowired
	PetRepository petRep;
	
	
	@Transactional
	public String newClient(Client client) {
		client.getPets().forEach((p) -> {
			p.setClient(client);
		});
		clientRep.saveAndFlush(client);
		return "Client Saved";
	}
	
	@Transactional
	public List<Client> getAllClients() {
		return clientRep.findAll();
	}
	
	// Get Client By Id
	@Transactional
	public Client getClientById(int cid) {
		Optional<Client> result = clientRep.findById(cid);
		return result.get();
	}
	
	// Get Client By Email
	@Transactional
	public Client getClientByEmail(String email) {
		Optional<Client> result = clientRep.findByEmail(email);
		if (result.isEmpty()) {
			return null;
		} else {
			return result.get();
		}
	}
	
	// Update Client and/or their Pets
	// Create/Delete/Update Pets With this ONE METHOD
	@Transactional
	public String updateClient(Client client) {
		Optional<Client> result = clientRep.findById(client.getId());
		if (result.isEmpty()) {
			return "Client Not Found with Given Email";
		} else {
			Client c = result.get();
			c.setName(client.getName());
			c.setEmail(client.getEmail());
			c.setPhone(client.getPhone());
			if (client.getPets().isEmpty()) {	// Pet(s) Not In Request
				clientRep.save(c);
				System.out.println("Client Saved, No Pet Changes");
				return "Client Updated Success";
			} else {	
				Iterator<Pet> itPets = result.get().getPets().iterator();
				while (itPets.hasNext()) {			// If Pet Not In Request, then DELETE from DB
					Pet itp = itPets.next();
					if (client.getPets().stream().noneMatch(cp -> cp.getId() == (itp.getId()))) {
						petRep.deleteById(itp.getId()); 			// Pet in Database But Not In Request Is DELETED
						System.out.println("Pet Deleted " + itp.getName());
					} else {
						System.out.println("DB Pet Found In Submitted Pet List");
					}
				}
				
				// Update Existing & New Pet Info From Request
				client.getPets().forEach((p) -> {
				p.setClient(client);
			});
				c.setPets(client.getPets());
				System.out.println("DATA IN c Client After Setting");
				System.out.println(c.toString());
				clientRep.saveAndFlush(c);
				return "Client and Pets Update Success ";
			}
		}
	}
	
	// Delete Client
	@Transactional
	public String deleteClient(int cid) {
		Optional<Client> result = clientRep.findById(cid);
		if (result.isPresent()) {
			clientRep.deleteById(cid);
			return "Client Deleted Successfully, All Pets by this Client Are Also Deleted";
		} else {
			return "Delete Client Failed";
		}
		
	}

}
