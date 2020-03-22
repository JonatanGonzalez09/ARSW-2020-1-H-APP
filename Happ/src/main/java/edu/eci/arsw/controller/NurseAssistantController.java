package edu.eci.arsw.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.eci.arsw.service.AdminService;
import edu.eci.arsw.service.NurseAssistantService;
import edu.eci.arsw.model.Nurse;
import edu.eci.arsw.model.Patient;
import edu.eci.arsw.model.Procedure;
import edu.eci.arsw.model.Stay;
import edu.eci.arsw.model.Undergoes;

@RestController
@RequestMapping("test")
public class NurseAssistantController {
	
	@Autowired
	private NurseAssistantService nurseAssistantService;

	@GetMapping("patients/{idNurse}")
	public List<Patient> getAllPatiens(@PathVariable ("idNurse") int idNurse){
		List<Patient> allPatient = nurseAssistantService.getAllPatient();
		Nurse nurse = nurseAssistantService.getNurse(idNurse);
		List<Patient> patientByidNurse = new ArrayList<Patient>();
		List<Undergoes> procedures = nurse.getUndergoes();
		for (int i=0; i < allPatient.size(); i++){
			List<Stay> stays = allPatient.get(i).getStays();
			for (int j=0 ; j < stays.size(); j++){
				if (stays.get(i).getStayId() == procedures.get(j).getUndergoesId()){
					patientByidNurse.add(allPatient.get(i));
				}
			}

			}
		}
	}
}
