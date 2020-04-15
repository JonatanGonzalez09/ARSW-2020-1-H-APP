package edu.eci.arsw.persistence;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.eci.arsw.model.Nurse;
import edu.eci.arsw.model.Procedure;
import edu.eci.arsw.model.Stay;
import edu.eci.arsw.model.Undergoes;

public interface UndergoesPersistence extends JpaRepository<Undergoes, Integer> {
	@Query(value = "select undergoes.* from patient p join stay on p.patient_id = stay.patient_id join undergoes on stay.stay_id = undergoes.stay_id join nurse on nurse.nurse_id = undergoes.nurse_id where nurse.nurse_id = :nurseId and DATE(undergoes.date) = (select now()::date)", nativeQuery = true)
	List<Undergoes> getUndergoesToday(int nurseId);

	@Query(value = "select u.* from undergoes u where u.done is not null;", nativeQuery = true)	
	List<Undergoes> getUndergoesDone();

	@Query(value = "select u.* from undergoes u where u.done is null", nativeQuery = true)
	List<Undergoes> getUndergoesNotDone();
	
	List<Undergoes> findByNurse(Nurse nurse);
	List<Undergoes> findAllByDateAfter(Date date);
	List<Undergoes> findAllByDateBefore(Date date);
	List<Undergoes> findByStay(Stay stay);
	List<Undergoes> findByProcedure(Procedure procedure);

	

}
