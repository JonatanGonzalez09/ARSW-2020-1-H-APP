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

	@Query(value="select us.* from usuarios us join nurse n on us.user_id = n.usuarios_user_id join on_call on n.nurse_id = on_call.nurse_id join block on on_call.blockcode = block.blockcode where n.\"position\"= 'mngr' and block.blockcode = (select block.blockcode from usuarios us join nurse on us.user_id = nurse.usuarios_user_id join on_call on nurse.nurse_id = on_call.nurse_id join block on on_call.blockcode = block.blockcode where us.login_user = :userNurseAssistant and on_call.oncallend is null)", nativeQuery = true)
	User getBossNurseByUser(String userNurseAssistant);
}
