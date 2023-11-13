package com.bellasolutions.petclinic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bellasolutions.petclinic.entity.Client;
import com.bellasolutions.petclinic.service.ClientService;

@RestController
// @CrossOrigin(origins = "http://localhost:83")
@CrossOrigin
@RequestMapping("petclinic/client")
public class ClientController {
	
	@Autowired
	ClientService clientSvc;
	
	// Creating New Client Requires Sending New Pet Info to This Endpoint
	@PostMapping(value = "/newClient", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String newClient(@RequestBody Client client) {
		return clientSvc.newClient(client);
	}
	
	@GetMapping(value = "/getClients", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Client> getAllClients() {
		return clientSvc.getAllClients();
	}
	
	@GetMapping(value = "/getClientById", produces = MediaType.APPLICATION_JSON_VALUE)
	public Client getClientById(@RequestParam("cid") int cid) {
		return clientSvc.getClientById(cid);
	}
	
	@GetMapping(value = "/getClientByEmail", produces = MediaType.APPLICATION_JSON_VALUE)
	public Client getClientByEmail(@RequestParam("email") String email) {
		return clientSvc.getClientByEmail(email);
	}
	
	@DeleteMapping(value = "/deleteClient")
	public String deleteClient(@RequestParam("cid") int cid) {
		return clientSvc.deleteClient(cid);
	}
	
	@PutMapping(value = "/updateClientAndPets", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String updateClientandPets(@RequestBody Client client) {
		return clientSvc.updateClient(client);
	}
	
	@GetMapping(value = "/clientTest")
	public String clientTest() {
		return "ClientTest EndPoint is Working";
	}

}
