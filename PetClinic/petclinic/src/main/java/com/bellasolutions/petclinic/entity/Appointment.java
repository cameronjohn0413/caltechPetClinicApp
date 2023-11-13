package com.bellasolutions.petclinic.entity;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Appointment implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "schedule_date")
	private LocalDate scheduleDate;
	
	@Column(name = "pet_id")
	private int petId;
	
	@Column(name = "reason")
	private String reason;
	
	@Column(name = "meds")
	private String meds;
	
	@Column(name = "note")
	private String note;
	
	@Column(name = "complete")
	private boolean complete;
	
	// Empty Constructor
	public Appointment() {
		
	}
	/*
	// Parameterized Constructor
	public Appointment(int id, LocalDate scheduleDate, int petId, String reason, String meds, String note,
			boolean complete) {
		super();
		this.id = id;
		this.scheduleDate = scheduleDate;
		this.petId = petId;
		this.reason = reason;
		this.meds = meds;
		this.note = note;
		this.complete = complete;
	}
	*/
	
	// GETTERS & SETTERS
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LocalDate getScheduleDate() {
		return scheduleDate;
	}
	public void setScheduleDate(LocalDate scheduleDate) {
		this.scheduleDate = scheduleDate;
	}
	public int getPetId() {
		return petId;
	}
	public void setPetId(int petId) {
		this.petId = petId;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getMeds() {
		return meds;
	}
	public void setMeds(String meds) {
		this.meds = meds;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public boolean isComplete() {
		return complete;
	}
	
	public void setComplete(boolean complete) {
		this.complete = complete;
	}
	
	// ToString Method
	@Override
	public String toString() {
		return "Appointment [id=" + id + ", scheduleDate=" + scheduleDate + ", petId=" + petId + ", reason=" + reason
				+ ", meds=" + meds + ", note=" + note + ", complete=" + complete + "]";
	}
	

}
