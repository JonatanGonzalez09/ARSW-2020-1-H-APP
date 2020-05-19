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

	@Query(value = "select r.* from room r join bed b on r.roomnumber = b.roomnumber join stay on b.bed_id = stay.bed_id join patient on stay.patient_id = patient.patient_id where patient.patient_id = :patientId and stay.stay_id = (select max(stay.stay_id) from bed b join stay on b.bed_id = stay.bed_id join patient on stay.patient_id = patient.patient_id where patient.patient_id = :patientId)", nativeQuery = true)
	Room getRoomByPatientId(int patientId);

	@Query(value = "select r.* from room r join block on r.blockcode = block.blockcode join on_call on block.blockcode = on_call.blockcode join nurse on on_call.nurse_id = nurse.nurse_id join usuarios on nurse.usuarios_user_id = usuarios.user_id where usuarios.gov_id = :nurseGovId", nativeQuery = true)
	List<Room> getRoomByNurseGovId(String nurseGovId);

}
