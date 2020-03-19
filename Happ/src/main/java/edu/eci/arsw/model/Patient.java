package edu.eci.arsw.model;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="patients")
public class Patient {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long patient_id;
	
	@Column(nullable = false, name = "gov_id")
    private String govId;
    
	@Column(name = "gov_type")
    private String govType = "CC";    
    
    @Column(nullable = false)
    private Date birthdate;
    
    @Column(nullable = false)
    private String rh;
    
    @Column(nullable = false)
    private String address;
    
    @Column(nullable = false)
    private int phone;
    
    @Column(nullable = false)
    private String gender;
    
    @OneToMany
    @JoinColumn(name = "patient_id")
    private List<Stay> stays;

	public Patient(String gov_id, String gov_type, Date birthdate, String rh, String address, int phone,String gender) {
		this.govId = gov_id;
		this.govType = gov_type;
		this.birthdate = birthdate;
		this.rh = rh;
		this.address = address;
		this.phone = phone;
		this.gender = gender;
	}
	
	public Patient() {}

	public long getPatient_id() {
		return patient_id;
	}

	public void setPatient_id(long patient_id) {
		this.patient_id = patient_id;
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

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getRh() {
		return rh;
	}

	public void setRh(String rh) {
		this.rh = rh;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public List<Stay> getStays() {
		return stays;
	}

	public void setStays(List<Stay> stays) {
		this.stays = stays;
	}	
	
}
