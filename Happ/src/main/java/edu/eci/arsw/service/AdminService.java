package edu.eci.arsw.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.eci.arsw.model.*;
import edu.eci.arsw.persistence.*;

@Service
public class AdminService {
	
	@Autowired
	private UserPersistence userPersistence;
	@Autowired
	private NursePersistence nursePersistence;	
	@Autowired
	private BlockPersistence blockPersistence;
	@Autowired
	private RoomPersistence roomPersistence;
	@Autowired
	private BedPersistence bedPersistence;
	//---------------Users-------------
	public List<User> getAllUsers(){
		return userPersistence.findAll();
	}
	
	public User getUser(String loginUser) {
		return userPersistence.findByLoginUser(loginUser);
	}
	
	public List<User> getAllActiveUsers(){
		return userPersistence.findAllByActive(true);
	}
	
	public List<User> getAllInActiveUsers(){
		return userPersistence.findAllByActive(false);
	}
	
	public User setUser(User user, Nurse nurse) {
		nursePersistence.save(nurse);
		return userPersistence.save(user);
	}
	
	public User updateUser(User user) {
		User tmp = userPersistence.getOne(user.getUserId());
		tmp.setActive(user.getActive());
		tmp.setEmail(user.getEmail());
		tmp.setPassword(user.getPassword());
		tmp.setRol(user.getRol());
		return userPersistence.save(tmp);
	}	
	
	//---------------Nurses-------------
	public List<Nurse> getAllNurses(){
		return nursePersistence.findAll();
	}
	
	public Nurse getNurse(int id) {
		return nursePersistence.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));
	}
	
	public Nurse setNurse(User user, Nurse nurse) {
		userPersistence.save(user);
		return nursePersistence.save(nurse);	
	}
	
	public Nurse updateNurse(Nurse nurse) {
		Nurse tmp = nursePersistence.getOne(nurse.getNurseId());
		tmp.setName(nurse.getName());
		tmp.setOnCalls(nurse.getOnCalls());
		tmp.setPosition(nurse.getPosition());
		tmp.setUndergoes(nurse.getUndergoes());
		return nursePersistence.save(tmp);		
	}
	
	//-----------Block-----------------
	
	public List<Block> getAllBlocks(){
		return blockPersistence.findAll();
	}
	
	public Block getBlock(int id) {
		return blockPersistence.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));
	}
	
	public Block setBlock(Block block) {
		return blockPersistence.save(block);
	}
	
	public Block updateBlock(Block block) {
		Block tmp = blockPersistence.getOne(block.getBlockcode());
		tmp.setOnCalls(block.getOnCalls());
		tmp.setRooms(block.getRooms());
		return blockPersistence.save(tmp);		
	} 
	
	//----------Room--------------
	
	public List<Room> getAllRooms(){
		return roomPersistence.findAll();
	}
	
	public Room getRoom(int id) {
		return roomPersistence.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));
	}
	
	public Room setRoom(Room room) {
		return roomPersistence.save(room);
	}
	
	public Room updateRoom(Room room) {
		Room tmp = roomPersistence.getOne(room.getRoomnumber());
		tmp.setRoomtype(room.getRoomtype());
		tmp.setBeds(room.getBeds());
		tmp.setUnavailable(room.getUnavailable());
		return roomPersistence.save(tmp);		
	} 
	
	//----------Bed--------------
	
		public List<Bed> getAllBed(){
			return bedPersistence.findAll();
		}
		
		public Bed getBed(int id) {
			return bedPersistence.findById(id)
					.orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));
		}
		
		public Bed setBed(Bed bed) {
			return bedPersistence.save(bed);
		}
		
		public Bed updateBed(Bed bed) {
			Bed tmp = bedPersistence.getOne(bed.getBedId());
			tmp.setRoom(bed.getRoom());
			tmp.setStays(bed.getStays());
			return bedPersistence.save(tmp);		
		}
}
