package edu.eci.arsw.controller;

import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.eci.arsw.model.Bed;
import edu.eci.arsw.model.Block;
import edu.eci.arsw.model.Nurse;
import edu.eci.arsw.model.Stay;
import edu.eci.arsw.model.Undergoes;
import edu.eci.arsw.service.AdminService;
import edu.eci.arsw.service.NurseAssistantService;

@RestController
@RequestMapping("assistant-nurse")
@CrossOrigin(origins = "*")
public class NurseAssistantController {
	
	@Autowired
	private NurseAssistantService nurseAssistantService;

	@Autowired // --- para test
    private AdminService adminService;

	private String loginUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return currentPrincipalName;
	}

	//------------------- GET Nurse-----------------
    @GetMapping("nurses") // --- para test 
    public ResponseEntity<?> nurses() {
    	try {
    	      return new ResponseEntity<>(adminService.getAllNurses(), HttpStatus.OK);
    	    } catch (Exception ex) {
    	      Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
    	      return new ResponseEntity<>("No nurses on the database", HttpStatus.NOT_FOUND);
    	    }
    }
	
	//----------------User-----------------------
	@GetMapping("user")
	public ResponseEntity<?> getUser(){
		try {
			return new ResponseEntity<>(nurseAssistantService.getUser(this.loginUser()), HttpStatus.OK);
		}catch (Exception ex) { 
			Logger.getLogger(NurseAssistantController.class.getName()).log(Level.SEVERE, null, ex);
			return new ResponseEntity<>("No user exist", HttpStatus.NOT_FOUND);
		} 
	}

	@GetMapping("user/{userId}")
	public ResponseEntity<?> getUserById(@PathVariable("userId") int userId){
		try {
			return new ResponseEntity<>(nurseAssistantService.getUserById(userId), HttpStatus.OK);
		}catch (Exception ex) { 
			Logger.getLogger(NurseAssistantController.class.getName()).log(Level.SEVERE, null, ex);
			return new ResponseEntity<>("No user exist", HttpStatus.NOT_FOUND);
		} 
	}

	@GetMapping("user/{type}/{code}")
	public ResponseEntity<?> getUserByGovId(@PathVariable("type") String type, @PathVariable("code") String code ){
		try {
			return new ResponseEntity<>(nurseAssistantService.getUserByGovId(type,code), HttpStatus.OK);
		}catch (Exception ex) { 
			Logger.getLogger(NurseAssistantController.class.getName()).log(Level.SEVERE, null, ex);
			return new ResponseEntity<>("No user exist", HttpStatus.NOT_FOUND);
		} 
	}

	//----------------Nurse-----------------------
	@GetMapping("nurse/{nurseId}")
	public ResponseEntity<?> getNurseById(@PathVariable("nurseId") int nurseId){
		try {
			return new ResponseEntity<>(nurseAssistantService.getNurse(nurseId), HttpStatus.OK);
		}catch (Exception ex) { 
			Logger.getLogger(NurseAssistantController.class.getName()).log(Level.SEVERE, null, ex);
			return new ResponseEntity<>("No nurse exist", HttpStatus.NOT_FOUND);
		} 
	}

	//----------------Patient----------------------
	@GetMapping("patients/{patientId}")
	public ResponseEntity<?> getPatient(@PathVariable("patientId") int patientId){
		try {
			return new ResponseEntity<>(nurseAssistantService.getPatient(patientId), HttpStatus.OK);
		}catch (Exception ex) { 
			Logger.getLogger(NurseAssistantController.class.getName()).log(Level.SEVERE, null, ex);
			return new ResponseEntity<>("No patient exist", HttpStatus.NOT_FOUND);
		} 
	}

	@GetMapping("patients/{type}/{patientId}")
	public ResponseEntity<?> getPatientByGovId(@PathVariable("type") String type, @PathVariable("patientId") String patientId){
		try {
			return new ResponseEntity<>(nurseAssistantService.getPatientByGovId(type, patientId), HttpStatus.OK);
		}catch (Exception ex) { 
			Logger.getLogger(NurseAssistantController.class.getName()).log(Level.SEVERE, null, ex);
			return new ResponseEntity<>("No patient exist", HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("patients")
	public ResponseEntity<?> getAllPatients(){
		try {
			Nurse tmpNurse = nurseAssistantService.getUser(this.loginUser()).getNurses().get(0);
			return new ResponseEntity<>(nurseAssistantService.getAllPatient(tmpNurse), HttpStatus.OK);
		}catch (Exception ex) { 
			Logger.getLogger(NurseAssistantController.class.getName()).log(Level.SEVERE, null, ex);
			return new ResponseEntity<>("No patient exist", HttpStatus.NOT_FOUND);
		} 
	}

	//---------------Oncall-----------------
	@GetMapping("oncalls/{onCallId}")
	public ResponseEntity<?> getOncall(@PathVariable("onCallId") int onCallId){
		try {
			return new ResponseEntity<>(nurseAssistantService.getOncall(onCallId), HttpStatus.OK);
		}catch (Exception ex) { 
			Logger.getLogger(NurseAssistantController.class.getName()).log(Level.SEVERE, null, ex);
			return new ResponseEntity<>("No this oncall exist", HttpStatus.NOT_FOUND);
		} 
	}

	@GetMapping("oncalls")
	public ResponseEntity<?> getAllOnCalls(){
		try {
			Nurse tmpNurse = nurseAssistantService.getUser(this.loginUser()).getNurses().get(0);
			return new ResponseEntity<>(nurseAssistantService.getAllOnCalls(tmpNurse), HttpStatus.OK);
		}catch (Exception ex) { 
			Logger.getLogger(NurseAssistantController.class.getName()).log(Level.SEVERE, null, ex);
			return new ResponseEntity<>("No this oncalls exist", HttpStatus.NOT_FOUND);
		} 
	}

	@GetMapping("oncalls/schedeule/after/{date}")
	public ResponseEntity<?> getOnCallsAfterToday(@PathVariable("date") Date date){
		try {
			return new ResponseEntity<>(nurseAssistantService.getOnCallsAfterToday(date), HttpStatus.OK);
		}catch (Exception ex) { 
			Logger.getLogger(NurseAssistantController.class.getName()).log(Level.SEVERE, null, ex);
			return new ResponseEntity<>("No oncalls with this date exist", HttpStatus.NOT_FOUND);
		} 
	}

	@GetMapping("oncalls/schedeule/before/{date}")
	public ResponseEntity<?> getOnCallsBeforeToday(@PathVariable("date") Date date){
		try {
			return new ResponseEntity<>(nurseAssistantService.getOnCallsBeforeToday(date), HttpStatus.OK);
		}catch (Exception ex) { 
			Logger.getLogger(NurseAssistantController.class.getName()).log(Level.SEVERE, null, ex);
			return new ResponseEntity<>("No oncalls with this date exist", HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("oncalls/block/{blockId}")
	public ResponseEntity<?> getOnCallsByBlockId(@PathVariable("blockId") int block){
		try {
			Block blockTmp=nurseAssistantService.getBlockById(block);
			return new ResponseEntity<>(nurseAssistantService.getOnCallsByBlockCode(blockTmp), HttpStatus.OK);
		}catch (Exception ex) { 
			Logger.getLogger(NurseAssistantController.class.getName()).log(Level.SEVERE, null, ex);
			return new ResponseEntity<>("No oncalls with this block exist", HttpStatus.NOT_FOUND);
		}
	}

	//----------------Procedures----------------------
	@GetMapping("procedures")
	public ResponseEntity<?> getAllProcedures(){
		try {
			Nurse tmpNurse = nurseAssistantService.getUser(this.loginUser()).getNurses().get(0);
			return new ResponseEntity<>(nurseAssistantService.getAllProcedures(tmpNurse), HttpStatus.OK);
		}catch (Exception ex) { 
			Logger.getLogger(NurseAssistantController.class.getName()).log(Level.SEVERE, null, ex);
			return new ResponseEntity<>("No this procedures exist", HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("procedures/{procedureId}")
	public ResponseEntity<?> getAllProceduresById(@PathVariable("procedureId") int procedureId){
		try {
			return new ResponseEntity<>(nurseAssistantService.getProcedure(procedureId), HttpStatus.OK);
		}catch (Exception ex) { 
			Logger.getLogger(NurseAssistantController.class.getName()).log(Level.SEVERE, null, ex);
			return new ResponseEntity<>("No this procedure exist", HttpStatus.NOT_FOUND);
		}
	}

	//----------------Undergoes----------------------
	@GetMapping("undergoes")
	public ResponseEntity<?> getAllUndergoes(){
		try {
			Nurse tmpNurse = nurseAssistantService.getUser(this.loginUser()).getNurses().get(0);
			return new ResponseEntity<>(nurseAssistantService.getAllUndergoes(tmpNurse), HttpStatus.OK);
		}catch (Exception ex) { 
			Logger.getLogger(NurseAssistantController.class.getName()).log(Level.SEVERE, null, ex);
			return new ResponseEntity<>("No this undergoes exist", HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("undergoes/{underId}")
	public ResponseEntity<?> getAllUndergoesById(@PathVariable("underId") int underId){
		try {
			return new ResponseEntity<>(nurseAssistantService.getUndergoes(underId), HttpStatus.OK);
		}catch (Exception ex) { 
			Logger.getLogger(NurseAssistantController.class.getName()).log(Level.SEVERE, null, ex);
			return new ResponseEntity<>("No this undergoes exist", HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("undergoes/after/{date}")
	public ResponseEntity<?> getUndergoesAfterToday(@PathVariable("date") Date date){
		try {
			return new ResponseEntity<>(nurseAssistantService.getUndergoesAfterToday(date), HttpStatus.OK);
		}catch (Exception ex) { 
			Logger.getLogger(NurseAssistantController.class.getName()).log(Level.SEVERE, null, ex);
			return new ResponseEntity<>("This not undergoes exist with this after date", HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("undergoes/before/{date}")
	public ResponseEntity<?> getUndergoesBeforeToday(@PathVariable("date") Date date){
		try {
			return new ResponseEntity<>(nurseAssistantService.getUndergoesBeforeToday(date), HttpStatus.OK);
		}catch (Exception ex) { 
			Logger.getLogger(NurseAssistantController.class.getName()).log(Level.SEVERE, null, ex);
			return new ResponseEntity<>("This not undergoes exist with this before date", HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("undergoes/stays/{stayId}")
	public ResponseEntity<?> getUndergoesByStayId(@PathVariable("stayId") int stayId){
		try {
			Stay stayTmp= nurseAssistantService.getStayById(stayId);
			return new ResponseEntity<>(nurseAssistantService.getUndergoesByStaysId(stayTmp), HttpStatus.OK);
		}catch (Exception ex) { 
			Logger.getLogger(NurseAssistantController.class.getName()).log(Level.SEVERE, null, ex);
			return new ResponseEntity<>("No this undergoes exist", HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("undergoes/today")
	public ResponseEntity<?> getUndergoesToday(){
		try {
			Nurse tmpNurse = nurseAssistantService.getUser(this.loginUser()).getNurses().get(0);
			return new ResponseEntity<>(nurseAssistantService.getUndergoesToday(tmpNurse), HttpStatus.OK);
		}catch (Exception ex) { 
			Logger.getLogger(NurseAssistantController.class.getName()).log(Level.SEVERE, null, ex);
			return new ResponseEntity<>("No undergoes exist today", HttpStatus.NOT_FOUND);
		}
	}
	

	//----------------Stay----------------------
	@GetMapping("stays")
	public ResponseEntity<?> getAllStays(){
		try {
			return new ResponseEntity<>(nurseAssistantService.getAllStays(), HttpStatus.OK);
		}catch (Exception ex) { 
			Logger.getLogger(NurseAssistantController.class.getName()).log(Level.SEVERE, null, ex);
			return new ResponseEntity<>("No stays exist", HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("stays/{stayId}")
	public ResponseEntity<?> getStaysById(@PathVariable("stayId") int stayId){
		try {
			return new ResponseEntity<>(nurseAssistantService.getStayById(stayId), HttpStatus.OK);
		}catch (Exception ex) { 
			Logger.getLogger(NurseAssistantController.class.getName()).log(Level.SEVERE, null, ex);
			return new ResponseEntity<>("No stay exist", HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("stays/now")
	public ResponseEntity<?> getStaysFromNurseNow(){
		try {
			Nurse tmpNurse = nurseAssistantService.getUser(this.loginUser()).getNurses().get(0);
			return new ResponseEntity<>(nurseAssistantService.getAllStaysNow(tmpNurse), HttpStatus.OK);
		}catch (Exception ex) { 
			Logger.getLogger(NurseAssistantController.class.getName()).log(Level.SEVERE, null, ex);
			return new ResponseEntity<>("No stay exist now", HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("stays/beds/{bedId}")
	public ResponseEntity<?> getStaysByBedsId(@PathVariable("bedId") int bedId){
		try {
			Bed bedTmp = nurseAssistantService.getBedById(bedId);
			return new ResponseEntity<>(nurseAssistantService.getStaysByBedsId(bedTmp), HttpStatus.OK);
		}catch (Exception ex) { 
			Logger.getLogger(NurseAssistantController.class.getName()).log(Level.SEVERE, null, ex);
			return new ResponseEntity<>("No stay exist with this bed Id.", HttpStatus.NOT_FOUND);
		}
	}

	//----------------PUT Undergoes----------------------
	@RequestMapping(method = RequestMethod.PUT, value = "undergoes")
	public ResponseEntity<?> updateUsers(@RequestBody Undergoes undergoes){
		try {
			return new ResponseEntity<>(nurseAssistantService.updateUndergoes(undergoes), HttpStatus.OK);
		}catch (Exception ex) { 
			Logger.getLogger(NurseAssistantController.class.getName()).log(Level.SEVERE, null, ex);
			return new ResponseEntity<>("Error updating this undergoes. ", HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
	
}
