package edu.eci.arsw.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.eci.arsw.service.NurseAssistantService;
import edu.eci.arsw.model.Nurse;
import edu.eci.arsw.model.Patient;
import edu.eci.arsw.model.Procedure;
import edu.eci.arsw.model.Stay;
import edu.eci.arsw.model.Undergoes;

@Controller
@RequestMapping("assistant-nurse")
public class NurseAssistantController {
	
	@Autowired
	private NurseAssistantService nurseAssistantService;

	@GetMapping("patients")
	public List<Patient> getAllPatiens(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String tmp = auth.getName();
		Nurse nurse = nurseAssistantService.getUser(tmp).getNurse();
		List<Patient> allPatient = nurseAssistantService.getAllPatient();
		List<Patient> patientByidNurse = new ArrayList<Patient>();
		List<Undergoes> undergoes = nurse.getUndergoes();
		for (int i=0; i < allPatient.size(); i++){
			List<Stay> stays = allPatient.get(i).getStays();
			for (int j=0 ; j < stays.size(); j++){
				if (stays.get(i).getStayId() == undergoes.get(j).getUndergoesId()){
					patientByidNurse.add(allPatient.get(i));
				}
			}
		}
		return patientByidNurse;
	}

	@GetMapping("states")
	public List<Procedure> getStatesPatients(){
		return nurseAssistantService.getAllProcedures();
	}

	@GetMapping("/state/{idPatient}")
	public String getStatePatient(@PathVariable ("idPatient") long idPatient){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String tmp = auth.getName();
		Nurse nurse = nurseAssistantService.getUser(tmp).getNurse();
		Patient patient = nurseAssistantService.getPatient((int)idPatient);
		List<Undergoes> undergoes = nurse.getUndergoes();
		int idProcedure = 0;
		for (int i=0; i < undergoes.size(); i++ ){
			if (patient.getPatient_id() == idPatient){
				idProcedure = undergoes.get(i).getProcedure();
			}
		}
		Procedure procedure = nurseAssistantService.getProcedure(idProcedure);
		return procedure.getDescription();
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/stateProcedure/{idProcedure}")
	public void updateProcedures(@RequestBody Procedure procedure, @PathVariable("idProcedure") int idProcedure){
        List<Procedure> procedures = nurseAssistantService.getAllProcedures();
        for (int i=0; i < procedures.size();i++){
            if (procedures.get(i).getId() == idProcedure){
                this.nurseAssistantService.updateProcedure(procedure);
            }
        }
    }	
}
