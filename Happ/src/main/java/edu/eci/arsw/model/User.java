package edu.eci.arsw.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.Table;

@Entity
@Table(name="usuarios")
public class User {	
	 	@Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private long id;

	    @Column(nullable = false)
	    private String username;

	    @Column(nullable = false)
	    private String password;

	    private boolean active;

	    private String roles = "";
	    
	    @Column(nullable = false)
	    private String gov_id;
	    
	    private String gov_type;
	    
	    @Column(nullable = false)
	    private String email;
   	    
	    public User(String username, String password, String email, String gov_id, String gov_type, String roles){
	        this.username = username;
	        this.password = password;
	        this.email = email;
	        this.gov_id = gov_id;
	        this.gov_type = gov_type;
	        this.roles = roles;
	        this.active = true;
	    }
	    protected User(){}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getRoles() {
			return roles;
		}

		public void setRoles(String roles) {
			this.roles = roles;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public long getId() {
			return id;
		}

		public String getUsername() {
			return username;
		}

		public boolean getActive() {
			return active;
		}

		public String getGov_id() {
			return gov_id;
		}

		public String getGov_type() {
			return gov_type;
		}
	    
		public List<String> getRoleList(){
	        if(this.roles.length() > 0){
	            return Arrays.asList(this.roles.split(","));
	        }
	        return new ArrayList<>();
	    }

}
