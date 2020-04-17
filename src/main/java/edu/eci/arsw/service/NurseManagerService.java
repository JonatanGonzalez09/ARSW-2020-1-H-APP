package edu.eci.arsw.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.eci.arsw.model.*;
import edu.eci.arsw.persistence.*;

@Service
public class NurseManagerService {
	@Autowired
	private BedPersistence bedPersistence;
	@Autowired
	private BlockPersistence blockPersistence;
	@Autowired
	private NursePersistence nursePersistence;	
	@Autowired
	private OncallPersistence oncallPersistence;
	@Autowired
	private PatientPersistence patienPersistence;
	@Autowired
	private ProcedurePersistence procedurePersistence;
	@Autowired
	private RoomPersistence roomPersistence;
	@Autowired
	private StayPersistence stayPersistence;
	@Autowired
	private UndergoesPersistence undergoesPersistence;	
	@Autowired
	private UserPersistence userPersistence;	
	
	//-----------------Bed----------------------
	public List<Bed> getAllBed(){
		return bedPersistence.findAll();
	}
	
	public Bed getBed(int id) {
		return bedPersistence.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));
	}
	
	public Bed updateBed(Bed bed) {
		Bed tmp = bedPersistence.getOne(bed.getBedId());
		tmp.setStays(bed.getStays());
		return bedPersistence.save(tmp);		
	} 
	//-----------------Block----------------------
	
	public List<Block> getAllBlocks(){
		return blockPersistence.findAll();
	}
	
	public Block getBlock(int id) {
		return blockPersistence.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));
	}
	//-----------------Nurse----------------------
	public List<Nurse> getAllNurses(){
		return nursePersistence.findAll();
	}
	
	public Nurse getNurse(int id) {
		return nursePersistence.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));
	}
	
	public Nurse updateNurse(Nurse nurse) {
		Nurse tmp = nursePersistence.getOne(nurse.getNurseId());
		tmp.setOnCalls(nurse.getOnCalls());
		tmp.setUndergoes(nurse.getUndergoes());
		return nursePersistence.save(tmp);		
	}
	//-----------------Oncall----------------------
	public List<Oncall> getAllOncalls(){
		return oncallPersistence.findAll();
	}
	
	public Oncall getOncall(int id) {
		return oncallPersistence.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));
	}
	
	public Oncall setOncall(Oncall oncall) {
		return oncallPersistence.save(oncall);
	}

	public List<Oncall> getOnCallsAfterToday(Date date) {
		return oncallPersistence.findAllByOncallstartAfter(date);
	}

	public List<Oncall> getOnCallsBeforeToday(Date date) {
		return oncallPersistence.findAllByOncallstartBefore(date);
	}
	
	public Oncall updateOncall(Oncall oncall) {
		Oncall tmp = oncallPersistence.getOne((int) oncall.getOncallId());
		tmp.setBlock(oncall.getBlock());
		tmp.setNurse(oncall.getNurse());
		tmp.setOncallend(oncall.getOncallend());
		tmp.setOncallstart(oncall.getOncallstart());
		return oncallPersistence.save(tmp);
	}
	//-----------------Patient----------------------
	public List<Patient> getPatients(){
		return patienPersistence.findAll();
	}
	
	public Patient getPatient(int id) {
		return patienPersistence.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));
	}		
	//-----------------Procedure----------------------
	public List<Procedure> getProcedures(){
		return procedurePersistence.findAll();
	}
	
	public Procedure getProcedure(int idProcedure) {
		return procedurePersistence.findById(idProcedure)
				.orElseThrow(() -> new EntityNotFoundException(String.valueOf(idProcedure)));
	}
	
	public Procedure setProcedure(Procedure procedure) {
		return procedurePersistence.save(procedure);
	}
	
	public Procedure updateProcedure(Procedure procedure) {
		Procedure procedureTmp = procedurePersistence.findById(procedure.getProcedureId())
								.orElseThrow(() -> new EntityNotFoundException(String.valueOf(procedure.getProcedureId())));
		procedureTmp.setDescription(procedure.getDescription());
		procedureTmp.setName(procedure.getName());
		return procedurePersistence.save(procedureTmp);
	}

	//-----------------Room----------------------
	public List<Room> getRooms(){
		return roomPersistence.findAll();
	}
	
	public Room getRoom(int id) {
		return roomPersistence.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));				
	}
	
	public Room setRoom(Room room) {
		return roomPersistence.save(room);
	}
		
	//-----------------Stay----------------------
	public List<Stay> getStays(){
		return stayPersistence.findAll();
	}
	
	public Stay getStay(int id) {
		return stayPersistence.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));	
	}
	
	public Stay setStay(Stay stay) {
		return stayPersistence.save(stay);
	}
	
	public Stay updateStay(Stay stay) {
		Stay tmp = stayPersistence.getOne(stay.getStayId());
		tmp.setBed(stay.getBed());
		tmp.setEndTime(stay.getEndTime());
		tmp.setPatient(stay.getPatient());
		tmp.setStartTime(stay.getStartTime());
		tmp.setUndergoes(stay.getUndergoes());
		return stayPersistence.save(tmp);
	}
	//-----------------Undergoes-----------------
	public List<Undergoes> getUndergoes(){
		return undergoesPersistence.findAll();
	}
	
	public Undergoes getUndergoes(int id) {
		return undergoesPersistence.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));
	}

	public List<Undergoes> getUndergoesToday(Nurse nurse) {
		return undergoesPersistence.getUndergoesToday(nurse.getNurseId());
	}

	public List<Undergoes> getUndergoesAfterToday(Date date) {
		return undergoesPersistence.findAllByDateAfter(date);
	}

	public List<Undergoes> getUndergoesBeforeToday(Date date) {
		return undergoesPersistence.findAllByDateBefore(date);
	}

	public List<Undergoes> getUndergoesByNurseId(Nurse nurse) {
		return undergoesPersistence.findByNurse(nurse);
	}

	public List<Undergoes> getUndergoesByProcedureId(Procedure procedure) {
		return undergoesPersistence.findByProcedure(procedure);
	}

	public List<Undergoes> getUndergoesByStayId(Stay stay) {
		return undergoesPersistence.findByStay(stay);
	}

	public List<Undergoes> getUndergoesDone() {
		return undergoesPersistence.getUndergoesDone();
	}

	public List<Undergoes> getUndergoesNotDone() {
		return undergoesPersistence.getUndergoesNotDone();
	}
	
	public Undergoes setUndergoes(Undergoes undergoes) {
		return undergoesPersistence.save(undergoes);
	}
	
	public Undergoes updateUndergoes(Undergoes undergoes) {
		Undergoes undergoesTmp = undergoesPersistence.findById(undergoes.getUndergoesId())
								.orElseThrow(() -> new EntityNotFoundException(String.valueOf(undergoes.getUndergoesId())));
		undergoesTmp.setDone(undergoes.getDone());
		undergoesTmp.setDate(undergoes.getDate());
		undergoesTmp.setNurse(undergoes.getNurse());
		undergoesTmp.setProcedure(undergoes.getProcedure());
		undergoesTmp.setStay(undergoes.getStay());		
		return undergoesPersistence.save(undergoesTmp);				
	}

	//-----------------User----------------------
	public List<User> getAllUsers(){
		return userPersistence.findAll();
	}

	public List<User> getAllActivesUser(boolean is){
		return userPersistence.findAllByActive(is);
	}

	public User getUser(String username) {
		return userPersistence.findByLoginUser(username);
	}
	
	public User updateUser(User user) {
		User tmp = userPersistence.getOne(user.getUserId());
		tmp.setEmail(user.getEmail());
		tmp.setPassword(user.getPassword());
		return userPersistence.save(tmp);
	}

	public User getUserById(int userId) {
		return userPersistence.findById(userId)
		.orElseThrow(() -> new EntityNotFoundException(String.valueOf(userId)));
	}

	public User getUserByGovId(String type, String code) {
		return userPersistence.findByGovIdAndGovType(code, type);
	}

	public Object getUserByEmail(String email) {
		return userPersistence.findByEmail(email);
	}
}