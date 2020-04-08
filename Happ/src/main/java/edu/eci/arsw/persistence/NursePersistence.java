package edu.eci.arsw.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.eci.arsw.model.Nurse;

public interface NursePersistence extends JpaRepository<Nurse, Integer> {
}
