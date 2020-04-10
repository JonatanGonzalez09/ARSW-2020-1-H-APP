package edu.eci.arsw.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="patient")
@NamedQuery(name="Patient.findAll", query="SELECT p FROM Patient p")
public class Patient implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="patient_id", unique=true, nullable=false)
	private Integer patientId;

	@Column(nullable=false, length=50)
	private String address;

	
	@Column(nullable=false)
	private Date birthdate;

	@Column(nullable=false, length=15)
	private String gender;

	@Column(name="gov_id", nullable=false, length=20)
	private String govId;

	@Column(name="gov_type", nullable=false, length=4)
	private String govType;

	@Column(nullable=false, length=50)
	private String name;

	@Column(nullable=false)
	private Integer phone;

	@Column(nullable=false, length=4)
	private String rh;

	//bi-directional many-to-one association to Stay
	@JsonManagedReference(value="patient-stay")
	@OneToMany(cascade=CascadeType.ALL,mappedBy="patient")
	private List<Stay> stays;

	public Patient() {
	}

	public Integer getPatientId() {
		return this.patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getBirthdate() {
		return this.birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPhone() {
		return this.phone;
	}

	public void setPhone(Integer phone) {
		this.phone = phone;
	}

	public String getRh() {
		return this.rh;
	}

	public void setRh(String rh) {
		this.rh = rh;
	}

	public List<Stay> getStays() {
		return this.stays;
	}

	public void setStays(List<Stay> stays) {
		this.stays = stays;
	}

	public Stay addStay(Stay stay) {
		getStays().add(stay);
		stay.setPatient(this);

		return stay;
	}

	public Stay removeStay(Stay stay) {
		getStays().remove(stay);
		stay.setPatient(null);

		return stay;
	}

}