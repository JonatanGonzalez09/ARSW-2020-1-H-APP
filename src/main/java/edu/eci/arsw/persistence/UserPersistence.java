package edu.eci.arsw.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.eci.arsw.model.Nurse;
import edu.eci.arsw.model.User;

@Repository
public interface UserPersistence extends JpaRepository<User, Integer> {	
	
	@Query(value = "select user_id, active, email, gov_type, gov_id , n.\"name\" from usuarios u join nurse n on u.user_id  = n.usuarios_user_id", nativeQuery = true)
	List<Object[]> findAllBasicInfo();
	User findByLoginUser(String loginUser);
	List<User> findAllByActive(boolean active);
	User findByEmail(String email);
	User findByGovIdAndGovType(String govId, String govType);
	User findByNurses(Nurse nurse);
}
