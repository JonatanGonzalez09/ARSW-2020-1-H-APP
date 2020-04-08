package edu.eci.arsw.service;

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
	
	//----------------User-----------------------
	
	public User getUser(String username) {
		return userPersistence.findByLoginUser(username);
	}
	
	public User updateUser(User user) {
		User tmp = userPersistence.getOne(user.getUserId());
		tmp.setEmail(user.getEmail());
		tmp.setPassword(user.getPassword());
		return userPersistence.save(tmp);
	}
	
	//----------------Nurse-----------------------
	
	public Nurse getNurse(int id) {
		return nursePersistence.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));
	}
	
	public Nurse updateNurse(Nurse nurse) {
		Nurse tmp = nursePersistence.getOne(nurse.getNurseId());
		tmp.setName(nurse.getName());
		return nursePersistence.save(tmp);		
	}
	
	//----------------Patient----------------------
	
	public Patient getPatient(int id) {
		return patientPersistence.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));
	}

	public List<Patient> getAllPatient() {
		return patientPersistence.findAll();
	}
	
	//---------------Oncall-----------------
	
	public Oncall getOncall(int id) {
		return oncallPersistence.findByNurse(id);
	}	
	
	//---------------undergoes-----------
	
	public Undergoes getUndergoes(int id) {
		return undergoesPersistence.findByNurse(id);
	}
	
	public Undergoes updateUndergoes(Undergoes undergoes) {
		Undergoes tmp = undergoesPersistence.getOne(undergoes.getUndergoesId());
		tmp.setDone(undergoes.getDone());
		return undergoesPersistence.save(tmp);		
	}

	//-----------------Procedures-------------
	public Procedure getProcedure(int idProcedure){
		return procedurePersistence.findById(idProcedure)
				.orElseThrow(() -> new EntityNotFoundException(String.valueOf(idProcedure)));
	}

	public List<Procedure> getAllProcedures(){
		return procedurePersistence.findAll();
	}

	public Procedure updateProcedure(Procedure procedure) {
		Procedure tmp = procedurePersistence.getOne((int) procedure.getProcedureId());
		tmp.setDescription(procedure.getDescription());
		tmp.setName(procedure.getName());
		tmp.setUndergoes(procedure.getUndergoes());
		return procedurePersistence.save(tmp);
	}

}
