package edu.eci.arsw.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import edu.eci.arsw.model.Bed;
import edu.eci.arsw.model.Block;
import edu.eci.arsw.model.Nurse;
import edu.eci.arsw.model.Room;
import edu.eci.arsw.model.User;
import edu.eci.arsw.persistence.BedPersistence;
import edu.eci.arsw.persistence.BlockPersistence;
import edu.eci.arsw.persistence.NursePersistence;
import edu.eci.arsw.persistence.RoomPersistence;
import edu.eci.arsw.persistence.UserPersistence;

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
	@Autowired
	private PasswordEncoder passwordEncoder;
	//---------------Users-------------
	public List<Object[]> getAllUsers(){
		return userPersistence.findAllBasicInfo();
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
	
	public User setUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
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
		return nursePersistence.findAllBasicInfo();
	}
	
	public List<Nurse> getAllNursesByPosition(String position){
		return nursePersistence.findByPosition(position);
	}
	
	public Nurse getNurse(int id) {
		return nursePersistence.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));
	}
	
	public Nurse setNurse(Nurse nurse) {
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
		return blockPersistence.findAllBasicInfo();
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
		return roomPersistence.findAllBasicInfo();
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
