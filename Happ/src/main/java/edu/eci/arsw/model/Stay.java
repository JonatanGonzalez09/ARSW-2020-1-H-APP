package edu.eci.arsw.model;


import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="stay")
@NamedQuery(name="Stay.findAll", query="SELECT s FROM Stay s")
public class Stay implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="stay_id", unique=true, nullable=false)
	private Integer stayId;

	@Column(name="end_time")
	private Timestamp endTime;

	@Column(name="start_time", nullable=false)
	private Timestamp startTime;

	//bi-directional many-to-one association to Bed
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="bed_id", nullable=false)
	private Bed bed;

	//bi-directional many-to-one association to Patient
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="patient_id", nullable=false)
	private Patient patient;

	//bi-directional many-to-one association to Undergoe
	@OneToMany(mappedBy="stay")
	private List<Undergoes> undergoes;

	public Stay() {
	}

	public Integer getStayId() {
		return this.stayId;
	}

	public void setStayId(Integer stayId) {
		this.stayId = stayId;
	}

	public Timestamp getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	public Timestamp getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public Bed getBed() {
		return this.bed;
	}

	public void setBed(Bed bed) {
		this.bed = bed;
	}

	public Patient getPatient() {
		return this.patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public List<Undergoes> getUndergoes() {
		return this.undergoes;
	}

	public void setUndergoes(List<Undergoes> undergoes) {
		this.undergoes = undergoes;
	}

	public Undergoes addUndergoe(Undergoes undergoes) {
		getUndergoes().add(undergoes);
		undergoes.setStay(this);

		return undergoes;
	}

	public Undergoes removeUndergoe(Undergoes undergoes) {
		getUndergoes().remove(undergoes);
		undergoes.setStay(null);

		return undergoes;
	}

}