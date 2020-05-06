package edu.eci.arsw.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.eci.arsw.model.Room;

public interface RoomPersistence extends JpaRepository<Room, Integer> {

	@Query(value = "select r.roomnumber, r.unavailable, r.roomtype, "
			+ "(select count(bed.bed_id) from bed where r.roomnumber =bed.roomnumber) as bed_number from room r ", nativeQuery = true)
	List findAllBasicInfo();
	
	Room findByRoomnumber(int id);
	List<Room> findByUnavailable(boolean n);
	List<Room> findByRoomtype(String type);

	@Query(value = "select room.* from patient p join stay on p.patient_id = stay.patient_id join bed on stay.bed_id = bed.bed_id join room on bed.roomnumber = room.roomnumber join block on room.blockcode = block.blockcode where p.patient_id = :patientId and stay.stay_id = (select MAX(stay.stay_id) from stay where stay.patient_id = :patientId)", nativeQuery = true)
	Room getRoomByPatientId(int patientId);
}
