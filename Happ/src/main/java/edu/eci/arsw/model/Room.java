package edu.eci.arsw.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="rooms")
public class Room {
	
	@Id
	private int roomnumber;
	
	private String roomType;
	
	@Column(name = "block_code")
	private int blockCode;	
	
	private boolean unavailable = false;
	
	@OneToMany
	@JoinColumn(name = "bed_id")
	private List<Bed> bedId;
	
	public Room() {}

	public int getRoomnumber() {
		return roomnumber;
	}

	public void setRoomnumber(int roomnumber) {
		this.roomnumber = roomnumber;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}	

	public int getBlockCode() {
		return blockCode;
	}

	public void setBlockCode(int blockCode) {
		this.blockCode = blockCode;
	}

	public boolean isUnavailable() {
		return unavailable;
	}

	public void setUnavailable(boolean unavailable) {
		this.unavailable = unavailable;
	}

	public List<Bed> getBedId() {
		return bedId;
	}

	public void setBedId(List<Bed> bedId) {
		this.bedId = bedId;
	}
	
}
