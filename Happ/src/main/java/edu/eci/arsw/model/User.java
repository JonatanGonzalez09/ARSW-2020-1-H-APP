package edu.eci.arsw.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="usuarios")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_id", unique=true, nullable=false)
	private Integer userId;

	@Column(nullable=false)
	private Boolean active;

	@Column(nullable=false, length=50)
	private String email;

	@Column(name="gov_id", nullable=false, length=15)
	private String govId;

	@Column(name="gov_type", nullable=false, length=4)
	private String govType;

	@Column(name="login_user", nullable=false, length=50)
	private String loginUser;

	@Column(nullable=false, length=255)
	private String password;

	@Column(nullable=false, length=10)
	private String rol;

	//bi-directional many-to-one association to Nurse
	@OneToMany(mappedBy="user")
	private List<Nurse> nurses;

	
	public User() {
	}

	public User(Boolean active, String loginUser, String password,String email, String govId, String govType, String rol) {		
		this.active = active;
		this.email = email;
		this.govId = govId;
		this.govType = govType;
		this.loginUser = loginUser;
		this.password = password;
		this.rol = rol;
	}

	public List<String> securityRoleList(){
        if(this.rol.length() > 0){
            return Arrays.asList(this.rol.split(","));
        }
        return new ArrayList<>();
    }
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Boolean getActive() {
		return this.active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGovId() {
		return this.govId;
	}

	public void setGovId(String govId) {
		this.govId = govId;
	}

	public String getGovType() {
		return this.govType;
	}

	public void setGovType(String govType) {
		this.govType = govType;
	}

	public String getLoginUser() {
		return this.loginUser;
	}

	public void setLoginUser(String loginUser) {
		this.loginUser = loginUser;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRol() {
		return this.rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public List<Nurse> getNurses() {
		return this.nurses;
	}

	public void setNurses(List<Nurse> nurses) {
		this.nurses = nurses;
	}

	public Nurse addNurs(Nurse nurs) {
		getNurses().add(nurs);
		nurs.setUser(this);

		return nurs;
	}

	public Nurse removeNurs(Nurse nurs) {
		getNurses().remove(nurs);
		nurs.setUser(null);

		return nurs;
	}

}