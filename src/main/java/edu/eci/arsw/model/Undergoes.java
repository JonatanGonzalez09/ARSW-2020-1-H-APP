package edu.eci.arsw.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
@Table(name="undergoes")
@NamedQuery(name="Undergoes.findAll", query="SELECT u FROM Undergoes u")
public class Undergoes implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="undergoes_id", unique=true, nullable=false)
	private Integer undergoesId;

	@Column(nullable=false)
	private Timestamp date;

	//bi-directional many-to-one association to Nurse
	@JsonBackReference(value="nurse-undergoes")
	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="nurse_id", nullable=false)
	private Nurse nurse;

	//bi-directional many-to-one association to Procedure
	@JsonBackReference(value="procedure-undergoes")
	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="procedure_id", nullable=false)
	private Procedure procedure;

	//bi-directional many-to-one association to Stay"
	@JsonBackReference(value="stay-undergoes")
	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="stay_id", nullable=false)
	private Stay stay;
	
	@Column(nullable=true)
	private Timestamp Done = null;
	
	@Column(nullable=true)
	private String additionalNotes = null;

	public Undergoes() {
	}

	public Integer getUndergoesId() {
		return this.undergoesId;
	}

	public void setUndergoesId(Integer undergoesId) {
		this.undergoesId = undergoesId;
	}

	public Timestamp getDate() {
		return this.date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public Nurse getNurse() {
		return this.nurse;
	}

	public void setNurse(Nurse nurse) {
		this.nurse = nurse;
	}

	public Procedure getProcedure() {
		return this.procedure;
	}

	public void setProcedure(Procedure procedure) {
		this.procedure = procedure;
	}	
	
	public Timestamp getDone() {
		return Done;
	}

	public void setDone(Timestamp done) {
		Done = done;
	}

	public Stay getStay() {
		return this.stay;
	}

	public void setStay(Stay stay) {
		this.stay = stay;
	}

	public String getAdditionalNotes() {
		return additionalNotes;
	}

	public void setAdditionalNotes(String additionalNotes) {
		this.additionalNotes = additionalNotes;
	}
}
