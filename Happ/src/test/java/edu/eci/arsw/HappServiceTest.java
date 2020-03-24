package edu.eci.arsw;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import edu.eci.arsw.model.User;
import edu.eci.arsw.persistence.UserPersistence;
import edu.eci.arsw.service.AdminService;

@RunWith(SpringRunner.class)
@SpringBootTest
class HappServiceTest {

	@TestConfiguration
    static class AdminServiceTestContextConfiguration {  
        @Bean
        public AdminService adminService() {
            return new AdminService();
        }
    }
	@MockBean
    private AdminService adminService;
 
	@Before
	public void setUp() {
		User manager = new User("Pedro","pedro123","pedro@mail.com","1234678","CC","MANAGER");
	 
	    Mockito.when(adminService.getUser(manager.getUsername()))
	      .thenReturn(manager);
	}
	
	@Test
	public void whenValidUsername_thenUserShouldBeFound() {
	    String name = "Pedro";
	    User found = adminService.getUser(name);
	  
	     assertThat(found.getUsername())
	      .isEqualTo(name);
	 }
}
