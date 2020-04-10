package edu.eci.arsw.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.eci.arsw.model.Nurse;

public interface NursePersistence extends JpaRepository<Nurse, Integer> {
	
	
	List<Nurse> findByPosition(String position);	
	@Query(value= "select nurse_id,\"name\" , \"position\" , rh, u.email , u.gov_id , u.gov_type from nurse n join usuarios u on u.user_id = n.usuarios_user_id \r\n", nativeQuery = true)
	List findAllBasicInfo();
	List<Nurse> findByName(String name);
	
}
