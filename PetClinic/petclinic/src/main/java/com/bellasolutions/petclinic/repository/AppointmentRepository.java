package com.bellasolutions.petclinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bellasolutions.petclinic.entity.Appointment;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

}
