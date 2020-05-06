package edu.eci.arsw.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.eci.arsw.model.Block;

public interface BlockPersistence extends JpaRepository<Block, Integer> {
	
	@Query(value = "select b.blockcode, b.blockfloor, room.roomnumber, room.roomtype, room.unavailable, bed.bed_id from block b join room on b.blockcode = room.blockcode join bed on room.roomnumber = bed.roomnumber \r\n", nativeQuery = true)
	List findAllBasicInfo();
	Block findByBlockcode(int id);
	List<Block> findByBlockfloor(int floor);

	@Query(value = "select block.* from patient p join stay on p.patient_id = stay.patient_id join bed on stay.bed_id = bed.bed_id join room on bed.roomnumber = room.roomnumber join block on room.blockcode = block.blockcode where p.patient_id = :patientId and stay.stay_id = (select MAX(stay.stay_id) from stay where stay.patient_id = :patientId)", nativeQuery = true)
	Block getBlockByPatientId(int patientId);
}
