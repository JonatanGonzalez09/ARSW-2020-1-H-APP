package edu.eci.arsw;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import edu.eci.arsw.model.*;
import edu.eci.arsw.persistence.OncallPersistence;
import edu.eci.arsw.persistence.UserPersistence;


@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class HappDatabaseTest {

	@Autowired
    private TestEntityManager entityManager;
	
	@Autowired
	private UserPersistence userPersistence;
	@Autowired
	private OncallPersistence oncallPersistence;
	
	
	@Test
	void whenFindByUsername_thenReturnUser() {
		//default
		User manager = new User("Pedro","pedro123","pedro@mail.com","1234678","CC","MANAGER");
		entityManager.persist(manager);
	    entityManager.flush();
		
		User found = userPersistence.findByUsername(manager.getUsername());
		
		assertThat(found.getUsername())
	      .isEqualTo(manager.getUsername());		
	}	

}
