package edu.eci.arsw.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
	
	//----------------find----------
	public List<User> getAllUsers(){
		return userPersistence.findAll();
	}
	
	public User getUser(String loginUser) {
		return userPersistence.findByLoginUser(loginUser);
	}
	
	public User getUserByEmail(String email) {
		return userPersistence.findByEmail(email);
	}
	
	public List<User> getAllUserStatus(boolean bo){
		return userPersistence.findAllByActive(bo);
	}
	
	public User getUserByGovId(String type, String code) {
		return userPersistence.findByGovIdAndGovType(code, type);
	}
	
	public User getUserByNurse(int id) {
		Nurse tmp= nursePersistence.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));
		return tmp.getUser();
	}
	
	public User getUserById(int id) {
		return userPersistence.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));
	}
	
	//--------other-------------
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
		return nursePersistence.findAll();
	}
	
	public List<Nurse> getAllNursesByPosition(String position){
		return nursePersistence.findByPosition(position);
	}
	
	public Nurse getNurse(int id) {
		return nursePersistence.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));
	}
	
	public List<Nurse> getAllByName(String name){
		return nursePersistence.findByName(name);
	}
	
	public Nurse setNurse(Nurse nurse) {
		List<Nurse> tmp = new ArrayList<Nurse>();
		tmp.add(nurse);
		nurse.getUser().setNurses(tmp);
		return nursePersistence.save(nurse);
	}
	
	public Nurse updateNurse(Nurse nurse) {
		Nurse tmp = nursePersistence.getOne(nurse.getNurseId());
		tmp.setName(nurse.getName());
		tmp.setPosition(nurse.getPosition());		
		return nursePersistence.save(tmp);		
	}
	
	//-----------Block-----------------
	
	public List<Block> getAllBlocks(){
		return blockPersistence.findAll();
	}
	
	public List<Block> findByFloor(int floor){
		return blockPersistence.findByBlockfloor(floor);
	}
	
	public Block getBlock(int id) {
		return blockPersistence.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));
	}
	
	public Block setBlock(Block block) {
		return blockPersistence.save(block);
	}
	
	public Block updateBlock(Block block) {				
		return blockPersistence.save(block);		
	} 
	
	//----------Room--------------
	
	public List<Room> getAllRooms(){
		return roomPersistence.findAll();
	}
	
	public Room getRoom(int id) {
		return roomPersistence.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));
	}
	
	public List<Room> getRoomByAvailable(boolean b){
		return roomPersistence.findByUnavailable(b);
	}
	
	public List<Room> getRoomByType(String type){
		return roomPersistence.findByRoomtype(type);
	}
	
	public Room setRoom(Room room) {
		return roomPersistence.save(room);
	}
	
	public Room updateRoom(Room room) {
		Room tmp = roomPersistence.findByRoomnumber(room.getRoomnumber());
		room.setBlock(tmp.getBlock());
		return roomPersistence.save(room);		
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
		Optional<Bed> tmp = bedPersistence.findById(bed.getBedId());
		bed.setRoom(tmp.get().getRoom());
		return bedPersistence.save(bed);		
	}
}
