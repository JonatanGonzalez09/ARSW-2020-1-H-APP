package edu.eci.arsw.model;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Table(name="bed")
@NamedQuery(name="Bed.findAll", query="SELECT b FROM Bed b")
public class Bed implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="bed_id", unique=true, nullable=false)
	private Integer bedId;

	//bi-directional many-to-one association to Room
	@JsonBackReference(value="room-bed")
	@ManyToOne(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="roomnumber", nullable=false)
	private Room room;

	//bi-directional many-to-one association to Stay
	@JsonManagedReference(value="bed-stay")
	@OneToMany(cascade=CascadeType.ALL,mappedBy="bed")
	private List<Stay> stays;

	public Bed() {
	}

	public Integer getBedId() {
		return this.bedId;
	}

	public void setBedId(Integer bedId) {
		this.bedId = bedId;
	}

	public Room getRoom() {
		return this.room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public List<Stay> getStays() {
		return this.stays;
	}

	public void setStays(List<Stay> stays) {
		this.stays = stays;
	}

	public Stay addStay(Stay stay) {
		getStays().add(stay);
		stay.setBed(this);

		return stay;
	}

	public Stay removeStay(Stay stay) {
		getStays().remove(stay);
		stay.setBed(null);

		return stay;
	}

}