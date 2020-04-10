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
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="room")
@NamedQuery(name="Room.findAll", query="SELECT r FROM Room r")
public class Room implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer roomnumber;

	@Column(nullable=false, length=50)
	private String roomtype;

	@Column(nullable=false)
	private Boolean unavailable;

	//bi-directional many-to-one association to Bed
	@JsonManagedReference(value="room-bed")
	@OneToMany(cascade=CascadeType.ALL, mappedBy="room")
	private List<Bed> beds;

	//bi-directional many-to-one association to Block	
	@JsonBackReference(value="block-room")
	@ManyToOne(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="blockcode", nullable=false)
	private Block block;

	public Room() {
	}

	public Integer getRoomnumber() {
		return this.roomnumber;
	}

	public void setRoomnumber(Integer roomnumber) {
		this.roomnumber = roomnumber;
	}

	public String getRoomtype() {
		return this.roomtype;
	}

	public void setRoomtype(String roomtype) {
		this.roomtype = roomtype;
	}

	public Boolean getUnavailable() {
		return this.unavailable;
	}

	public void setUnavailable(Boolean unavailable) {
		this.unavailable = unavailable;
	}

	public List<Bed> getBeds() {
		return this.beds;
	}

	public void setBeds(List<Bed> beds) {
		this.beds = beds;
	}

	public Bed addBed(Bed bed) {
		getBeds().add(bed);
		bed.setRoom(this);

		return bed;
	}

	public Bed removeBed(Bed bed) {
		getBeds().remove(bed);
		bed.setRoom(null);

		return bed;
	}

	public Block getBlock() {
		return this.block;
	}

	public void setBlock(Block block) {
		this.block = block;
	}

}