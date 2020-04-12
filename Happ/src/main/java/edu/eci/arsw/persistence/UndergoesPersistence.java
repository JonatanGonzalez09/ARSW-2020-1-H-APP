package edu.eci.arsw.persistence;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.eci.arsw.model.Nurse;
import edu.eci.arsw.model.Stay;
import edu.eci.arsw.model.Undergoes;

public interface UndergoesPersistence extends JpaRepository<Undergoes, Integer> {
	List<Undergoes> findByNurse(Nurse nurse);
	List<Undergoes> findAllByDateAfter(Date date);
	List<Undergoes> findAllByDateBefore(Date date);
	List<Undergoes> findByStay(Stay stay);
}
