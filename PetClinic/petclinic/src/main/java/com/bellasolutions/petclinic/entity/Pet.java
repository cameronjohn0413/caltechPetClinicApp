package com.bellasolutions.petclinic.entity;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "pet")
public class Pet implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "age")
	private short age;
	
	@Column(name = "weight")
	private float weight;
	
	@Column(name = "animal_type")
	private String animalType;
	
	@JsonIgnoreProperties("pets")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="owner_id")
	private Client client;
	
	@OneToMany(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "pet_id")
	private List<Appointment> appointments;

	/*
	// Empty Constructor
	public Pet() {
		
	}

	// Parameterized Constructor
	public Pet(int id, String name, short age, float weight, String animalType) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.weight = weight;
		this.animalType = animalType;
	//	this.client = client;
	}
*/
	// GETTERS & SETTERS
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public short getAge() {
		return age;
	}

	public void setAge(short age) {
		this.age = age;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public String getAnimalType() {
		return animalType;
	}

	public void setAnimalType(String animalType) {
		this.animalType = animalType;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	// This Method Causes the Error when Loading Clients with Pets. Must Leave Out 'client' on to string 
//	@Override
//	public String toString() {
//		return "Pet [id=" + id + ", name=" + name + ", age=" + age + ", weight=" + weight + ", animalType=" + animalType
//				+ ", client=" + client + "]";
//	}
	
	// ToString Method
	@Override
	public String toString() {
		return "Pet [id=" + id + ", name=" + name + ", age=" + age + ", weight=" + weight + ", animalType=" + animalType
				+ "]";
	}

	
	
	
	
	

}
