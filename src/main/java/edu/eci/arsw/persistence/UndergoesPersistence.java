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

	@Query(value= "select u.* from undergoes u join nurse on u.nurse_id = nurse.nurse_id join on_call on nurse.nurse_id = on_call.nurse_id join block on on_call.blockcode = block.blockcode where block.blockcode = :blockId and DATE (on_call.oncallstart) = :date", nativeQuery = true)
	List<Undergoes> getUndergoesByBlockIdAndDate(int blockId, Date date);

	@Query(value= "select u.* from undergoes u join nurse on u.nurse_id = nurse.nurse_id join on_call on nurse.nurse_id = on_call.nurse_id where nurse.nurse_id = :nurseId and DATE (on_call.oncallstart) = (select now()::date)", nativeQuery = true)
	List<Undergoes> getTodayUndergoesByNurseId(int nurseId);

	@Query(value= "select p.*, u.* from \"procedure\" p join undergoes u on p.procedure_id = u.procedure_id join nurse on u.nurse_id = nurse.nurse_id where nurse.nurse_id = :nurseId and u.done is null and DATE(u.\"date\") = (select DATE(now()))", nativeQuery = true)
	List<Undergoes> getUndergoesNoDoneByNurseId(int nurseId);

	@Query(value= "select undergoes.* from \"procedure\" p join undergoes on p.procedure_id = undergoes.procedure_id join nurse on undergoes.nurse_id = nurse.nurse_id join on_call on nurse.nurse_id = on_call.nurse_id join usuarios on nurse.usuarios_user_id = usuarios.user_id where usuarios.gov_id = :nurseGovId and on_call.oncallend is null", nativeQuery = true)
	List<Undergoes> getUndergoesByNurseGovId(String nurseGovId);
}