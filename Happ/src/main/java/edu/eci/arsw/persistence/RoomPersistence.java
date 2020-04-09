package edu.eci.arsw.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.eci.arsw.model.Room;

public interface RoomPersistence extends JpaRepository<Room, Integer> {

	@Query(value = "select r.roomnumber, r.unavailable, r.roomtype, "
			+ "(select count(bed.bed_id) from bed where r.roomnumber =bed.roomnumber) as bed_number from room r ", nativeQuery = true)
	List findAllBasicInfo();
}
