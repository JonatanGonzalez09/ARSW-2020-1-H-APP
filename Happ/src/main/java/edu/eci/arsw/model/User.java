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
import javax.validation.constraints.Email;

@Entity
@Table(name="usuarios")
public class User {	
	 	@Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	 	@Column(name = "user_id")
	    private long userId;

	    @Column(nullable = false)
	    private String username;

	    @Column(nullable = false)
	    private String password;

	    private boolean active;

	    private String roles = "";
	    
	    @Column(nullable = false)
	    private String gov_id;
	    
	    private String gov_type = "CC";
	    
	    @Column(nullable = false)
	    @Email
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
	    protected User() {}	
	    
		public List<String> getRoleList(){
	        if(this.roles.length() > 0){
	            return Arrays.asList(this.roles.split(","));
	        }
	        return new ArrayList<>();
	    }
		public long getUserId() {
			return userId;
		}
		public void setUserId(long userId) {
			this.userId = userId;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public boolean isActive() {
			return active;
		}
		public void setActive(boolean active) {
			this.active = active;
		}
		public String getRoles() {
			return roles;
		}
		public void setRoles(String roles) {
			this.roles = roles;
		}
		public String getGov_id() {
			return gov_id;
		}
		public void setGov_id(String gov_id) {
			this.gov_id = gov_id;
		}
		public String getGov_type() {
			return gov_type;
		}
		public void setGov_type(String gov_type) {
			this.gov_type = gov_type;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}

}
