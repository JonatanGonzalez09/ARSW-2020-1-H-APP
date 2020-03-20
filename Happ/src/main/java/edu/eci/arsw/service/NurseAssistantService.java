package edu.eci.arsw.service;

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
	
	//----------------User-----------------------
	
	public User getUser(String username) {
		return userPersistence.findByUsername(username);
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
	
	//---------------Oncall-----------------
	
	public Oncall getOncall(int id) {
		return oncallPersistence.findByNurseId(id);
	}	
	
	//---------------undergoes-----------
	
	public Undergoes getUndergoes(int id) {
		return undergoesPersistence.findByNurseId(id);
	}
	
	public Undergoes updateUndergoes(Undergoes undergoes) {
		Undergoes tmp = undergoesPersistence.getOne(undergoes.getUndergoesId());
		tmp.setDone(undergoes.isDone());
		tmp.setDoneDate(undergoes.getDoneDate());
		return undergoesPersistence.save(tmp);		
	}

}
