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

	public List<Bed> getBedsAvailables() {
		return bedPersistence.getBedAviables();
	}

	public List<Bed> getBedsUnavailables() {
		return bedPersistence.getBedUnavailable();
	}
	
	public Bed updateBed(Bed bed) {
		Bed tmpBed = bedPersistence.findById(bed.getBedId())
			.orElseThrow(() -> new EntityNotFoundException(String.valueOf(bed.getBedId())));
		tmpBed.setStays(bed.getStays());
		return bedPersistence.save(tmpBed);		
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
		Nurse tmpNurse = nursePersistence.findById(nurse.getNurseId())
			.orElseThrow(() -> new EntityNotFoundException(String.valueOf(nurse.getNurseId())));
		tmpNurse.setOnCalls(nurse.getOnCalls());
		tmpNurse.setUndergoes(nurse.getUndergoes());
		return nursePersistence.save(tmpNurse);		
	}

	//-----------------Oncall----------------------
	public List<Oncall> getAllOncalls(){
		return oncallPersistence.findAll();
	}
	
	public Oncall getOncall(int id) {
		return oncallPersistence.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));
	}

	public List<Oncall> getOnCallsAfterToday(Date date) {
		return oncallPersistence.findAllByOncallstartAfter(date);
	}

	public List<Oncall> getOnCallsBeforeToday(Date date) {
		return oncallPersistence.findAllByOncallstartBefore(date);
	}
	
	public List<Oncall> getOnCallsByBlockId(Block block) {
		return oncallPersistence.findByBlock(block);
	}

	public List<Oncall> getOnCallsByNurseId(Nurse nurse) {
		return oncallPersistence.findByNurse(nurse);
	}

	public Oncall setOncall(Oncall oncall) {
		return oncallPersistence.save(oncall);
	}

	public Oncall updateOncall(Oncall oncall) {
		Oncall tmpOncall = oncallPersistence.findById(oncall.getOncallId())
			.orElseThrow(() -> new EntityNotFoundException(String.valueOf(oncall.getOncallId())));
		tmpOncall.setOncallend(oncall.getOncallend());
		tmpOncall.setOncallstart(oncall.getOncallstart());
		return oncallPersistence.save(tmpOncall);
	}

	//-----------------Patient----------------------
	public List<Patient> getPatients(){
		return patienPersistence.findAll();
	}
	
	public Patient getPatient(int id) {
		return patienPersistence.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));
	}

	public Patient getPatientByGovId(String type, String patientId) {
		return patienPersistence.findByGovTypeAndGovId(type, patientId);
	}

	public Object getPatientsByNurseId(Nurse nurse) {
		return patienPersistence.getAllPatientsFromNurse(nurse.getNurseId());
	}
		
	//-----------------Procedure----------------------
	public List<Procedure> getProcedures(){
		return procedurePersistence.findAll();
	}
	
	public Procedure getProcedure(int idProcedure) {
		return procedurePersistence.findById(idProcedure)
				.orElseThrow(() -> new EntityNotFoundException(String.valueOf(idProcedure)));
	}

	public String getDescriptionProcedure(int idProcedure) {
		return procedurePersistence.getDescriptionByProcedureId(idProcedure);
	}

	public String getNameProcedure(int idProcedure) {
		return procedurePersistence.getNameByProcedureId(idProcedure);
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

	public List<Stay> getStayEnd() {
		return stayPersistence.getStaysEnd();
	}

	public List<Stay> getStayNotEnd() {
		return stayPersistence.getStaysNotEnd();
	}

	public List<Stay> getStaysBypatientId(Patient patient) {
		return stayPersistence.findByPatient(patient);
	}

	public List<Stay> getStaysByBedId(Bed bed) {
		return stayPersistence.findByBed(bed);
	}
	
	public Stay setStay(Stay stay) {
		return stayPersistence.save(stay);
	}
	
	public Stay updateStay(Stay stay) {
		Stay tmpStay = stayPersistence.findById(stay.getStayId())
		.orElseThrow(() -> new EntityNotFoundException(String.valueOf(stay.getStayId())));
		tmpStay.setEndTime(stay.getEndTime());
		tmpStay.setStartTime(stay.getStartTime());
		tmpStay.setBed(stay.getBed());
		tmpStay.setPatient(stay.getPatient());
		return stayPersistence.save(tmpStay);
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
		User tmpUser = userPersistence.findById(user.getUserId())
			.orElseThrow(() -> new EntityNotFoundException(String.valueOf(user.getUserId())));
		tmpUser.setEmail(user.getEmail());
		tmpUser.setPassword(user.getPassword());
		return userPersistence.save(tmpUser);
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