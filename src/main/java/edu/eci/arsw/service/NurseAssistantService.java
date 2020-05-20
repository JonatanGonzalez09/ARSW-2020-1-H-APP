package edu.eci.arsw.service;

import java.sql.Date;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.eci.arsw.model.*;
import edu.eci.arsw.persistence.*;

@Service
public class NurseAssistantService {
	@Autowired
	private UserPersistence userPersistence;
	@Autowired
	private NursePersistence nursePersistence;
	@Autowired
	private PatientPersistence patientPersistence;
	@Autowired
	private OncallPersistence oncallPersistence;
	@Autowired
	private UndergoesPersistence undergoesPersistence;
	@Autowired
	private ProcedurePersistence procedurePersistence;
	@Autowired
	private BlockPersistence blockPersistence;
	@Autowired
	private StayPersistence stayPersistence;
	@Autowired
	private BedPersistence bedPersistence;
	@Autowired
	private RoomPersistence roomPersistence;

	// ----------------User-----------------------
	public User getUser(String username) {
		return userPersistence.findByLoginUser(username);
	}

	public User getUserById(int code) {
		return userPersistence.findById(code)
		.orElseThrow(() -> new EntityNotFoundException(String.valueOf(code)));
	}

	public User getUserByGovId(String type, String code) {
		return userPersistence.findByGovIdAndGovType(code, type);
	}

	public User updateUser(User user) {
		User tmp = userPersistence.getOne(user.getUserId());
		tmp.setEmail(user.getEmail());
		tmp.setPassword(user.getPassword());
		return userPersistence.save(tmp);
	}

	public User getBossNurse(String nurseAssistantGovId) {
		return userPersistence.getBossNurseByUser(nurseAssistantGovId);
	}

	// ----------------Nurse-----------------------
	public Nurse getNurse(int id) {
		return nursePersistence.findById(id)
		.orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));
	}

	public Nurse updateNurse(Nurse nurse) {
		Nurse tmp = nursePersistence.getOne(nurse.getNurseId());
		tmp.setName(nurse.getName());
		return nursePersistence.save(tmp);
	}

	// ----------------Patient----------------------

	public Patient getPatient(int id) {
		return patientPersistence.findById(id)
		.orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));
	}

	public Patient getPatientByGovId(String type, String code) {
		return patientPersistence.findByGovTypeAndGovId(type, code);
	}

	public List<Patient> getAllPatient(Nurse nurse) {
		return patientPersistence.getAllPatientsFromNurse(nurse.getNurseId());
	}

	public List<Patient> getPatientsByGovId(String nurseGovId) {
		return patientPersistence.getPatietntsByGovId(nurseGovId);
	}

	// ---------------Rooms----------------
	public List<Room> getRoomsByGovId(String nurseGovId) {
		return roomPersistence.getRoomByNurseGovId(nurseGovId);
	}

	public Object getRoomByPatientId(int patientId) {
		return roomPersistence.getRoomByPatientId(patientId);
	}

	// ---------------Oncall----------------
	public Oncall getOncall(int id) {
		return oncallPersistence.findById(id)
		.orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));
	}

	public List<Oncall> getAllOnCalls(Nurse nurse) {
		return oncallPersistence.findByNurse(nurse);
	}

	public List<Oncall> getOnCallsAfterToday(Date date) {
		return oncallPersistence.findAllByOncallstartAfter(date);
	}

	public List<Oncall> getOnCallsBeforeToday(Date date) {
		return oncallPersistence.findAllByOncallstartBefore(date);
	}

	public List<Oncall> getOnCallsByBlockCode(Block block) {
		return oncallPersistence.findByBlock(block);
	}

	// ---------------undergoes-----------

	public Undergoes getUndergoes(int id) {
		return undergoesPersistence.findById(id)
		.orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));
	}

	public List<Undergoes> getAllUndergoes(Nurse nurse) {
		return undergoesPersistence.findByNurse(nurse);
	}

	public List<Undergoes> getUndergoesAfterToday(Date date) {
		return undergoesPersistence.findAllByDateAfter(date);
	}

	public List<Undergoes> getUndergoesBeforeToday(Date date) {
		return undergoesPersistence.findAllByDateBefore(date);
	}

	public List<Undergoes> getUndergoesByStaysId(Stay stay) {
		return undergoesPersistence.findByStay(stay);
	}

	public List<Undergoes> getUndergoesToday(Nurse nurse) {
		return undergoesPersistence.getUndergoesToday(nurse.getNurseId());
	}

	public Undergoes updateUndergoes(Undergoes undergoes) {
		Undergoes undergoesTmp = undergoesPersistence.findById(undergoes.getUndergoesId())
								.orElseThrow(() -> new EntityNotFoundException(String.valueOf(undergoes.getUndergoesId())));
		undergoesTmp.setDone(undergoes.getDone());
		undergoesTmp.setDate(undergoes.getDate());
		return undergoesPersistence.save(undergoesTmp);
	}

	public List<Undergoes> getUndergoesByGovId(String nurseGovId) {
		return undergoesPersistence.getUndergoesByNurseGovId(nurseGovId);
	}

	// -----------------Procedures-------------
	public Procedure getProcedure(int idProcedure) {
		return procedurePersistence.findById(idProcedure)
				.orElseThrow(() -> new EntityNotFoundException(String.valueOf(idProcedure)));
	}

	public List<Procedure> getAllProcedures(Nurse nurse) {
		return procedurePersistence.getAllProceduresFromNurse(nurse.getNurseId());
	}

	public Procedure updateProcedure(Procedure procedure) {
		Procedure tmp = procedurePersistence.getOne((int) procedure.getProcedureId());
		tmp.setDescription(procedure.getDescription());
		tmp.setName(procedure.getName());
		tmp.setUndergoes(procedure.getUndergoes());
		return procedurePersistence.save(tmp);
	}

	public List<Procedure> getProceduresByGovId(String nurseGovId) {
		return procedurePersistence.getProceduresByGovId(nurseGovId);
	}

	// -----------------Block-------------
	public Block getBlockById(int blockCode) {
		return blockPersistence.findByBlockcode(blockCode);
	}

	// -----------------Stay-------------
	public Stay getStayById(int stayId) {
		return stayPersistence.findByStayId(stayId);
	}

	public List<Stay> getAllStays() {
		return stayPersistence.findAll();
	}


	public List<Stay> getAllStaysNow(Nurse nurse) {
		return stayPersistence.findAllStayFromNurse(nurse.getNurseId());
	}

	public List<Stay> getStaysByBedsId(Bed bed) {
		return stayPersistence.findByBed(bed);
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

	// -----------------Bed-------------
	public Bed getBedById(int bedId){
		return bedPersistence.findByBedId(bedId);
	}

	public Bed getBedByPatientId(int patientId) {
		return bedPersistence.getBedByPatientId(patientId);
	}

}
