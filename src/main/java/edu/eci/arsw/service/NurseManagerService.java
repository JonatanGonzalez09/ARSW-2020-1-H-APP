package edu.eci.arsw.service;

import java.sql.Date;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.Null;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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

	public Block getBlockByPatientId(int patientId) {
		return blockPersistence.getBlockByPatientId(patientId);
	}

	//-----------------Nurse----------------------
	public List<Nurse> getAllNurses(){
		return nursePersistence.findAll();
	}
	
	@Cacheable(cacheNames= "nurse", key= "#id")
	public Nurse getNurse(int id) {
		return nursePersistence.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));
	}
	
	@CachePut(cacheNames= "nurse", key= "#nurse.nurseId")
	public Nurse updateNurse(Nurse nurse) {
		Nurse tmpNurse = nursePersistence.findById(nurse.getNurseId())
			.orElseThrow(() -> new EntityNotFoundException(String.valueOf(nurse.getNurseId())));
		tmpNurse.setOnCalls(nurse.getOnCalls());
		tmpNurse.setUndergoes(nurse.getUndergoes());
		return nursePersistence.save(tmpNurse);		
	}

	@CachePut(cacheNames= "nurse_block", key= "#blockId")
	public List<Nurse> getNursesByBlockId(int blockId) {
		return nursePersistence.getNursesByBlockId(blockId);
	}

	public List<Nurse> getNursesByBlockIdAndDate(int blockId, Date date) {
		return nursePersistence.getNursByBlockIdAndDate(blockId, date);
	}

	@CachePut(cacheNames= "nurse", key= "#blockId")
	public List<Nurse> getNursesByBlockIdToday(int blockId) {
		return nursePersistence.getNursByBlockIdToday(blockId);
	}


	//-----------------Oncall----------------------
	public List<Oncall> getAllOncalls(){
		return oncallPersistence.findAll();
	}
	
	@Cacheable(cacheNames= "oncall", key= "#id")
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
	
	@Cacheable(cacheNames= "oncall_Block", key= "#block.blockId")
	public List<Oncall> getOnCallsByBlockId(Block block) {
		return oncallPersistence.findByBlock(block);
	}

	@Cacheable(cacheNames= "oncall_Nurse", key= "#nurse.nurseId")
	public List<Oncall> getOnCallsByNurseId(Nurse nurse) {
		return oncallPersistence.findByNurse(nurse);
	}

	@Cacheable(cacheNames= "oncall", key= "#oncall.oncallId")
	public Oncall setOncall(Oncall oncall) {
		return oncallPersistence.save(oncall);
	}
	
	@CachePut(cacheNames= "oncall", key= "#oncall.oncallId")
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
	
	@Cacheable(cacheNames= "patient", key= "#id")
	public Patient getPatient(int id) {
		return patienPersistence.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));
	}

	public Patient getPatientByGovId(String type, String patientId) {
		return patienPersistence.findByGovTypeAndGovId(type, patientId);
	}

	@Cacheable(cacheNames= "patient_Nurse", key= "#nurse.nurseId")
	public Object getPatientsByNurseId(Nurse nurse) {
		return patienPersistence.getAllPatientsFromNurse(nurse.getNurseId());
	}
		
	//-----------------Procedure----------------------
	public List<Procedure> getProcedures(){
		return procedurePersistence.findAll();
	}
	
	@Cacheable(cacheNames= "procedure", key= "#idProcedure")
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

	public Procedure getProcedureByUndergoesId(int undergoesId) {
		return procedurePersistence.getNameUndergoesId(undergoesId);
	}
	
	public List<Procedure> getProcedureByPatientId(int patientId) {
		return procedurePersistence.getProcedureByPatientId(patientId);
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

	public Room getRoomByPatientId(int patientId) {
		return roomPersistence.getRoomByPatientId(patientId);
	}
		
	//-----------------Stay----------------------
	public List<Stay> getStays(){
		return stayPersistence.findAll();
	}
	
	@Cacheable(cacheNames= "stay", key= "#id")
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

	@Cacheable(cacheNames= "stay_patient", key= "#patient.patientId")
	public List<Stay> getStaysBypatientId(Patient patient) {
		return stayPersistence.findByPatient(patient);
	}

	public List<Stay> getStaysByBedId(Bed bed) {
		return stayPersistence.findByBed(bed);
	}
	
	//@Cacheable(cacheNames= "stay", key= "#stay.stayId")
	public Stay setStay(Stay stay) {
		Stay auxStay = null;
		List <Stay> staysList = getStaysBypatientId(stay.getPatient());
		for(Stay aux : staysList){
			if(aux.getEndTime().equals(null)){
				auxStay = aux;
			}
		}
		Bed bedaux =  bedPersistence.findByBedId(stay.getBed().getBedId());
		Patient patientAux = patienPersistence.findById(stay.getPatient().getPatientId()).get();
		if(auxStay.equals(null)){
			bedaux.addStay(stay);
			patientAux.addStay(stay);
			stay.setBed(bedaux);
			stay.setPatient(patientAux);
		}else{
			bedaux.addStay(auxStay);
			patientAux.addStay(auxStay);
			auxStay.setBed(bedaux);
			auxStay.setPatient(patientAux);
			stay = auxStay;
		}
		patienPersistence.save(patientAux);
		bedPersistence.save(bedaux);
		return stayPersistence.save(stay);
	}
	
	@CachePut(cacheNames= "stay", key= "#stay.stayId")
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
	
	@Cacheable(cacheNames= "undergoes", key= "#id")
	public Undergoes getUndergoes(int id) {
		System.out.println("Enter to undergoes");
		return undergoesPersistence.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));
	}

	@Cacheable(cacheNames= "undergoes_date", key= "{Today: #date}")
	public List<Undergoes> getUndergoesToday(Nurse nurse) {
		System.out.println("Enter to today");
		return undergoesPersistence.getUndergoesToday(nurse.getNurseId());
	}
	
	
	@Cacheable(cacheNames= "undergoes_date", key= "{After: #date}")
	public List<Undergoes> getUndergoesAfterToday(Date date) {
		System.out.println("Enter to after today");
		return undergoesPersistence.findAllByDateAfter(date);
	}

	@Cacheable(cacheNames= "undergoes_date", key= "{Before: #date}")
	public List<Undergoes> getUndergoesBeforeToday(Date date) {
		System.out.println("Enter to before today");
		return undergoesPersistence.findAllByDateBefore(date);
	}

	@Cacheable(cacheNames= "undergoes_nurse", key= "#nurse.nurseId")
	public List<Undergoes> getUndergoesByNurseId(Nurse nurse) {
		System.out.println("Enter to nurse");
		return undergoesPersistence.findByNurse(nurse);
	}

	@Cacheable(cacheNames= "undergoes_procedure", key= "#procedure.procedureId")
	public List<Undergoes> getUndergoesByProcedureId(Procedure procedure) {
		return undergoesPersistence.findByProcedure(procedure);
	}

	@Cacheable(cacheNames= "undergoes_stay", key= "#stay.stayId")
	public List<Undergoes> getUndergoesByStayId(Stay stay) {
		return undergoesPersistence.findByStay(stay);
	}

	@Cacheable(cacheNames= "undergoes_done", key= "#root.methodName")
	public List<Undergoes> getUndergoesDone() {
		return undergoesPersistence.getUndergoesDone();
	}

	@Cacheable(cacheNames= "undergoes_not_done", key= "#root.methodName")
	public List<Undergoes> getUndergoesNotDone() {
		return undergoesPersistence.getUndergoesNotDone();
	}
	
	@Cacheable(cacheNames= "undergoes_block_date", key= "{#blockId + #date}")
	public List<Undergoes> getNurseUndergoesByBlockIdAndDate(int blockId, Date date) {
		return undergoesPersistence.getUndergoesByBlockIdAndDate(blockId, date);
	}

	@Cacheable(cacheNames= "undergoes_block_today", key= "{today: #blockId}")
	public List<Undergoes> getUndergoesTodayByNurseId(int nurseId) {
		return undergoesPersistence.getTodayUndergoesByNurseId(nurseId);
	}
	
	//@Cacheable(cacheNames= "undergoes", key= "#undergoes.undergoesId")
	public Undergoes setUndergoes(Undergoes undergoes) {
		return undergoesPersistence.save(undergoes);
	}
	
	//@CachePut(cacheNames= "undergoes", key= "#undergoes.undergoesId")
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

	public List<Undergoes> getUndergoesNotDoneByNurseId(int nurseId) {
		return undergoesPersistence.getUndergoesNoDoneByNurseId(nurseId);
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