package edu.eci.arsw.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="blocks")
public class Block {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "block_code")
	private int blockcode;
	
	private int blockfloor;	
	
	@OneToMany
	@JoinColumn(name = "oncall_id")
	private List<Oncall> oncallId;
	
	@OneToMany
	@JoinColumn(name = "roomnumber")
	private List<Room> roomNumber;
		
	public Block() {}

	public int getBlockfloor() {
		return blockfloor;
	}

	public void setBlockfloor(int blockfloor) {
		this.blockfloor = blockfloor;
	}

	public int getBlockcode() {
		return blockcode;
	}

	public void setBlockcode(int blockcode) {
		this.blockcode = blockcode;
	}

	public List<Oncall> getOncallId() {
		return oncallId;
	}

	public void setOncallId(List<Oncall> oncallId) {
		this.oncallId = oncallId;
	}

	public List<Room> getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(List<Room> roomNumber) {
		this.roomNumber = roomNumber;
	}
}
