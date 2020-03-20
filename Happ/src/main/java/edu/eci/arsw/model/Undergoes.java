package edu.eci.arsw.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="undergoes")
public class Undergoes {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "undergoes_id")
    private int undergoesId;
	
	private int procedure;
	
	private int stay;
	
	private Timestamp date;
	
	@Column(name = "nurse_id")
	private int nurseId;
	
	private boolean done;
	
	private Timestamp doneDate;
	
	public Undergoes() {}

	public int getUndergoesId() {
		return undergoesId;
	}

	public void setUndergoesId(int undergoesId) {
		this.undergoesId = undergoesId;
	}

	public int getProcedure() {
		return procedure;
	}

	public void setProcedure(int procedure) {
		this.procedure = procedure;
	}

	public int getStay() {
		return stay;
	}

	public void setStay(int stay) {
		this.stay = stay;
	}	

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}	

	public Timestamp getDoneDate() {
		return doneDate;
	}

	public void setDoneDate(Timestamp doneDate) {
		this.doneDate = doneDate;
	}

	public int getNurseId() {
		return nurseId;
	}

	public void setNurseId(int nurseId) {
		this.nurseId = nurseId;
	}

}
