package edu.eci.arsw.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="nurses")
public class Nurse implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name= "nurse_Id")
	private int nurseId;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@MapsId
    private User users;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String rh;
	
	private boolean registered = true;
	
	private String position;
	
	@OneToMany
	@JoinColumn(name = "oncall_id")
	@Column(name = "oncall_id")
	private List<Oncall> OncallId;
	
	@OneToMany
	@JoinColumn(name = "undergoes_id")
	private List<Undergoes> undergoes;

	public Nurse(User users, String name, String rh, String position) {
		this.users = users;
		this.name = name;
		this.rh = rh;
		this.position = position;
	}
	
	public Nurse() {}

	public User getUsers() {
		return users;
	}

	public void setUsers(User users) {
		this.users = users;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRh() {
		return rh;
	}

	public int getNurseId() {
		return nurseId;
	}

	public void setNurseId(int nurseId) {
		this.nurseId = nurseId;
	}

	public void setRh(String rh) {
		this.rh = rh;
	}

	public boolean isRegistered() {
		return registered;
	}

	public void setRegistered(boolean registered) {
		this.registered = registered;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public List<Oncall> getOncallId() {
		return OncallId;
	}

	public void setOncallId(List<Oncall> oncallId) {
		OncallId = oncallId;
	}

	public List<Undergoes> getUndergoes() {
		return undergoes;
	}

	public void setUndergoes(List<Undergoes> undergoes) {
		this.undergoes = undergoes;
	}	
}
