package edu.eci.arsw.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.eci.arsw.model.Block;

public interface BlockPersistence extends JpaRepository<Block, Integer> {
	
	@Query(value = "select b.blockcode, b.blockfloor, room.roomnumber, room.roomtype, room.unavailable, bed.bed_id from block b join room on b.blockcode = room.blockcode join bed on room.roomnumber = bed.roomnumber \r\n", nativeQuery = true)
	List findAllBasicInfo();

}
