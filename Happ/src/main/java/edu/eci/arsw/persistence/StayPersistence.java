package edu.eci.arsw.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.eci.arsw.model.Stay;

public interface StayPersistence extends JpaRepository<Stay, Integer> {

}
