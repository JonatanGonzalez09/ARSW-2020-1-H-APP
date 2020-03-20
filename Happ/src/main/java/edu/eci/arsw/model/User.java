package edu.eci.arsw.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;

@Entity
@Table(name="usuarios")
public class User {	
	 	@Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	 	@Column(name = "user_id")
	    private int userId;

	    @Column(nullable = false)
	    private String username;

	    @Column(nullable = false)
	    private String password;	    
	    
	    @OneToOne(mappedBy = "users", cascade = CascadeType.ALL,
	              fetch = FetchType.LAZY, optional = false)
	    private Nurse nurse;

	    private boolean active;

	    private String roles = "";
	    
	    @Column(nullable = false, name = "gov_id")
	    private String govId;
	    
	    @Column(name = "gov_type")
	    private String govType = "CC";
	    
	    @Column(nullable = false)
	    @Email
	    private String email;
   	    
	    public User(String username, String password, String email, String gov_id, String gov_type, String roles){
	        this.username = username;
	        this.password = password;
	        this.email = email;
	        this.govId = gov_id;
	        this.govType = gov_type;
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
		public int getUserId() {
			return userId;
		}
		public void setUserId(int userId) {
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
		public Nurse getNurse() {
			return nurse;
		}
		public void setNurse(Nurse nurse) {
			this.nurse = nurse;
		}
		public String getGovId() {
			return govId;
		}
		public void setGovId(String govId) {
			this.govId = govId;
		}
		public String getGovType() {
			return govType;
		}
		public void setGovType(String govType) {
			this.govType = govType;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}

}
