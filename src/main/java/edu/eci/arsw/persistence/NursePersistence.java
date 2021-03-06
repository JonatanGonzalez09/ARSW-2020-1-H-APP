package edu.eci.arsw.persistence;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.eci.arsw.model.Nurse;

public interface NursePersistence extends JpaRepository<Nurse, Integer> {
	
	
	List<Nurse> findByPosition(String position);	
	@Query(value= "select nurse_id,\"name\" , \"position\" , rh, u.email , u.gov_id , u.gov_type from nurse n join usuarios u on u.user_id = n.usuarios_user_id \r\n", nativeQuery = true)
	List findAllBasicInfo();
	List<Nurse> findByName(String name);

	@Query(value= "select * from nurse n join on_call on n.nurse_id = on_call.nurse_id join block on on_call.blockcode = block.blockcode where block.blockcode = :blockId and \"position\"=\'asst\'", nativeQuery = true)
	List<Nurse> getNursesByBlockId(int blockId);

	@Query(value= "select * from nurse n join on_call on n.nurse_id = on_call.nurse_id join block on on_call.blockcode = block.blockcode where block.blockcode = :blockId and \"position\"='asst' and DATE (on_call.oncallstart) = :date", nativeQuery = true)
	List<Nurse> getNursByBlockIdAndDate(int blockId, Date date);

	@Query(value= "select * from nurse n join on_call on n.nurse_id = on_call.nurse_id join block on on_call.blockcode = block.blockcode where block.blockcode = :blockId and \"position\"='asst' and DATE (on_call.oncallstart) = select u.* from undergoes u join nurse on u.nurse_id = nurse.nurse_id where nurse.nurse_id = :nurseId and DATE(u.\"date\") = (select DATE(now()))", nativeQuery = true)
	List<Nurse> getNursByBlockIdToday(int blockId);

	@Query(value= "select n.* from nurse n join usuarios on n.usuarios_user_id = usuarios.user_id join on_call on n.nurse_id = on_call.nurse_id join block on on_call.blockcode = block.blockcode where n.\"position\" = 'asst' and block.blockcode = (select on_call.blockcode from nurse n join usuarios on n.usuarios_user_id = usuarios.user_id join on_call on n.nurse_id = on_call.nurse_id where usuarios.gov_id = :nurseGovId)" , nativeQuery = true)
	List<Nurse> getNursesByNurseGovId(String nurseGovId);
}
