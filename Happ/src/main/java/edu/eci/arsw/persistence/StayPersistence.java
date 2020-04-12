package edu.eci.arsw.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.eci.arsw.model.Bed;
import edu.eci.arsw.model.Stay;

public interface StayPersistence extends JpaRepository<Stay, Integer> {
	Stay findByStayId(int stayId);
	
	@Query(value = "select s.* from stay s join undergoes on s.stay_id = undergoes.stay_id join nurse on undergoes.nurse_id = nurse.nurse_id where nurse.nurse_id = :nurseId ", nativeQuery = true)
	List<Stay> findAllStayFromNurse(int nurseId);

	List<Stay> findByBed(Bed bed);

}
