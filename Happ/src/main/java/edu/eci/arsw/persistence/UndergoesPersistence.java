package edu.eci.arsw.persistence;

import org.springframework.data.jpa.repository.JpaRepository;


import edu.eci.arsw.model.Undergoes;

public interface UndergoesPersistence extends JpaRepository<Undergoes, Integer> {
	Undergoes findByNurseId(int nurseId);
}
