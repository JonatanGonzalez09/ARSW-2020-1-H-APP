package edu.eci.arsw.model;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

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
@Table(name="block")
@NamedQuery(name="Block.findAll", query="SELECT b FROM Block b")
public class Block implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer blockcode;

	@Column(nullable=false)
	private Integer blockfloor;

	//bi-directional many-to-one association to OnCall
	@JsonManagedReference(value="block-oncall")
	@OneToMany(cascade=CascadeType.ALL,mappedBy="block")
	private List<Oncall> onCalls;

	//bi-directional many-to-one association to Room
	@JsonManagedReference(value="block-room")
	@OneToMany(cascade=CascadeType.ALL,mappedBy="block")
	private List<Room> rooms;

	public Block() {
	}

	public Integer getBlockcode() {
		return this.blockcode;
	}

	public void setBlockcode(Integer blockcode) {
		this.blockcode = blockcode;
	}

	public Integer getBlockfloor() {
		return this.blockfloor;
	}

	public void setBlockfloor(Integer blockfloor) {
		this.blockfloor = blockfloor;
	}

	public List<Oncall> getOnCalls() {
		return this.onCalls;
	}

	public void setOnCalls(List<Oncall> onCalls) {
		this.onCalls = onCalls;
	}

	public Oncall addOnCall(Oncall onCall) {
		getOnCalls().add(onCall);
		onCall.setBlock(this);

		return onCall;
	}

	public Oncall removeOnCall(Oncall onCall) {
		getOnCalls().remove(onCall);
		onCall.setBlock(null);

		return onCall;
	}

	public List<Room> getRooms() {
		return this.rooms;
	}

	public void setRooms(List<Room> rooms) {
		this.rooms =rooms;
	}

	public Room addRoom(Room room) {
		getRooms().add(room);
		room.setBlock(this);

		return room;
	}

	public Room removeRoom(Room room) {
		getRooms().remove(room);
		room.setBlock(null);

		return room;
	}

}
