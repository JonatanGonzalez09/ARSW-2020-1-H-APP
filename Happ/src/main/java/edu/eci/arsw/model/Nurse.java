package edu.eci.arsw.model;

import java.io.Serializable;
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

@Entity
@Table(name="nurse")
@NamedQuery(name="Nurse.findAll", query="SELECT n FROM Nurse n")
public class Nurse implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="nurse_id", unique=true, nullable=false)
	private Integer nurseId;

	@Column(nullable=false, length=50)
	private String name;

	@Column(nullable=false, length=5)
	private String position;

	@Column(nullable=false, length=4)
	private String rh;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="usuarios_user_id", nullable=false)
	private User user;

	//bi-directional many-to-one association to OnCall
	@OneToMany(mappedBy="nurse")
	private List<Oncall> onCalls;

	//bi-directional many-to-one association to Undergoe
	@OneToMany(mappedBy="nurse")
	private List<Undergoes> undergoes;

	public Nurse() {
	}

	public Integer getNurseId() {
		return this.nurseId;
	}

	public void setNurseId(Integer nurseId) {
		this.nurseId = nurseId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPosition() {
		return this.position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getRh() {
		return this.rh;
	}

	public void setRh(String rh) {
		this.rh = rh;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Oncall> getOnCalls() {
		return this.onCalls;
	}

	public void setOnCalls(List<Oncall> onCalls) {
		this.onCalls = onCalls;
	}

	public Oncall addOnCall(Oncall onCall) {
		getOnCalls().add(onCall);
		onCall.setNurse(this);

		return onCall;
	}

	public Oncall removeOnCall(Oncall onCall) {
		getOnCalls().remove(onCall);
		onCall.setNurse(null);

		return onCall;
	}

	public List<Undergoes> getUndergoes() {
		return this.undergoes;
	}

	public void setUndergoes(List<Undergoes> undergoes) {
		this.undergoes = undergoes;
	}

	public Undergoes addUndergoe(Undergoes undergoe) {
		getUndergoes().add(undergoe);
		undergoe.setNurse(this);

		return undergoe;
	}

	public Undergoes removeUndergoe(Undergoes undergoe) {
		getUndergoes().remove(undergoe);
		undergoe.setNurse(null);

		return undergoe;
	}

}
