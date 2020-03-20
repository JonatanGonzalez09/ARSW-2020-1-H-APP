package edu.eci.arsw.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.eci.arsw.model.Nurse;
import edu.eci.arsw.model.User;

public interface NursePersistence extends JpaRepository<Nurse, Integer> {
}
