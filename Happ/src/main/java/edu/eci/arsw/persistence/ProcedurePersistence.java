package edu.eci.arsw.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.eci.arsw.model.Procedure;

public interface ProcedurePersistence extends JpaRepository<Procedure, Integer> {

}
