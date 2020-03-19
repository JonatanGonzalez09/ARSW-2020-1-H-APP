package edu.eci.arsw.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import edu.eci.arsw.model.User;

@Repository
public interface UserPersistence extends JpaRepository<User, Integer> {
	User findByUsername(String username);
}
