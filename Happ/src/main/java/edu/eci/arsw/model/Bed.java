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
@Table(name="beds")
public class Bed {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "bed_id")
	private int bedId;
		
	@Column(name = "room_id")
	private int room;
	
	@OneToMany
	@JoinColumn(name = "stay_id")
	private List<Stay> stayId;
		
	public Bed(){}

	public int getBedId() {
		return bedId;
	}

	public void setBedId(int bedId) {
		this.bedId = bedId;
	}

	public int getRoom() {
		return room;
	}

	public void setRoom(int room) {
		this.room = room;
	}

	public List<Stay> getStaysId() {
		return stayId;
	}

	public void setStaysId(List<Stay> stays) {
		this.stayId = stays;
	}	

}
