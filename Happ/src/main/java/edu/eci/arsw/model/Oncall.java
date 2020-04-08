package edu.eci.arsw.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="on_call")
@NamedQuery(name="Oncall.findAll", query="SELECT o FROM Oncall o")
public class Oncall implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="oncall_id", unique=true, nullable=false)
	private Integer oncallId;

	private Timestamp oncallend;

	@Column(nullable=false)
	private Timestamp oncallstart;

	//bi-directional many-to-one association to Block
	@ManyToOne
	@JoinColumn(name="blockcode", nullable=false)
	private Block block;

	//bi-directional many-to-one association to Nurse
	@ManyToOne
	@JoinColumn(name="nurse_id", nullable=false)
	private Nurse nurse;

	public Oncall() {
	}

	public Integer getOncallId() {
		return this.oncallId;
	}

	public void setOncallId(Integer oncallId) {
		this.oncallId = oncallId;
	}

	public Timestamp getOncallend() {
		return this.oncallend;
	}

	public void setOncallend(Timestamp oncallend) {
		this.oncallend = oncallend;
	}

	public Timestamp getOncallstart() {
		return this.oncallstart;
	}

	public void setOncallstart(Timestamp oncallstart) {
		this.oncallstart = oncallstart;
	}

	public Block getBlock() {
		return this.block;
	}

	public void setBlock(Block block) {
		this.block = block;
	}

	public Nurse getNurse() {
		return this.nurse;
	}

	public void setNurse(Nurse nurse) {
		this.nurse = nurse;
	}

}
