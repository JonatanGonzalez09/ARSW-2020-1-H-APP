package edu.eci.arsw.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.eci.arsw.model.Patient;

public interface PatientPersistence extends JpaRepository<Patient, Integer> {

}
